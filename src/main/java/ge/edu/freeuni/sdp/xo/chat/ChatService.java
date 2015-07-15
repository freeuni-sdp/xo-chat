package ge.edu.freeuni.sdp.xo.chat;

import com.microsoft.azure.storage.StorageException;
import ge.edu.freeuni.sdp.xo.chat.data.MessageEntity;
import ge.edu.freeuni.sdp.xo.chat.data.Repository;
import ge.edu.freeuni.sdp.xo.chat.data.RepositoryFactory;
import org.glassfish.jersey.client.ClientConfig;
import org.joda.time.DateTime;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;

import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Path("/")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class ChatService {

	@Context
	private UriInfo uriInfo;
	private static final String XO_LOGIN_SERVICE = "http://xo-login.herokuapp.com/webapi/login/";
	private static final String XO_ROOMS_SERVICE = "http://xo-rooms.herokuapp.com/room_id?token=";
	public static final int UNPROCESSABLE_ENTITY = 422;
//	public FakeData fakeData = new FakeData();
	public FakeAuthorizationChecker checker = new FakeAuthorizationChecker();

	public Repository getRepository() throws StorageException {
		return RepositoryFactory.create();
	}

	/**
	 *
	 * @return 200 (OK), list of messages.
	 * @throws StorageException
	 */

	@GET
	public List<MessageDo> publicChatMessages(
			@QueryParam("token") String token) throws StorageException {
		final List result = new ArrayList<>();
		if (!isTokenValid(token))
			throw new WebApplicationException(UNPROCESSABLE_ENTITY);
		for (MessageEntity message : getRepository().getPublicChatMessages()) {
			result.add(message.toDo());
		}

		return result;
	}

	/**
	 *
	 * @return 200 (OK), list of messages.
	 * @throws StorageException
	 */

	@GET
	@Path("{roomId}")
	public List<MessageDo> privateChatMessages(
			@PathParam("roomId") String roomId, @QueryParam("token") String token) {
		if (!isTokenValid(token))
			throw new WebApplicationException(UNPROCESSABLE_ENTITY);

		final List result = new ArrayList<>();
		try {
			for (MessageEntity message : getRepository()
					.getPrivateChatMessages(roomId)) {
				result.add(message.toDo());
			}
		} catch (StorageException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 *
	 * @param message
	 * @return 201 (Created)
	 * @throws StorageException
	 */
	@POST
	public Response addPrivateChatMessage(@QueryParam("token") String token,
										  MessageDo message) throws StorageException {

		if (message == null)
			return Response.status(Status.BAD_REQUEST).build();

		if (!isTokenValid(token))
			return Response.status(UNPROCESSABLE_ENTITY).build();

		String userName = getUserNameFromToken(token);
		message.setId(uniqueSequentialId());
		message.setSenderUserName(userName);

		MessageEntity messageEntity = new MessageEntity(message);
		String negativeRoomID = "-1";

		if (negativeRoomID.equals(message.roomID)) {
			getRepository().addMessageToPublicChat(messageEntity);
		} else {
			// TODO real check Is correct room id
			if (!isCorectRoomId(message.roomID, token))
				return Response.status(Status.FORBIDDEN).build();

			getRepository().addMessageToPrivateChat(messageEntity);
		}

		return Response.status(Status.CREATED).build();
	}

	private boolean isCorectRoomId(String roomID, String token) {
		return roomID.equals(getRoomIDFromToken(token));
	}

	private String getRoomIDFromToken(String token) {
		Client client = ClientBuilder.newClient(new ClientConfig());
		try {
			Response response = client.target(XO_ROOMS_SERVICE + token)
					.request(MediaType.APPLICATION_JSON_TYPE)
					.get(Response.class);
			if (response.getStatus() != Status.OK.getStatusCode())
				return null;

			RoomDo room = response.readEntity(RoomDo.class);

			return room.getId();
		} catch (WebApplicationException e) {
			return null;
		}
	}

	private String getUserNameFromToken(String token) {
		if (token == null)
			return null;

		Client client = ClientBuilder.newClient(new ClientConfig());
		try {
			Response response = client.target(XO_LOGIN_SERVICE + token)
					.request(MediaType.APPLICATION_JSON_TYPE)
					.get(Response.class);
			if (response.getStatus() != Status.OK.getStatusCode())
				return null;

			UserName username = response.readEntity(UserName.class);

			return username.username;
		} catch (WebApplicationException e) {
			return null;
		}
	}

	private boolean isTokenValid(String token) {
		return getUserNameFromToken(token) != null;
	}

	private static final AtomicInteger seq = new AtomicInteger();
	private String uniqueSequentialId() {
		int nextVal = seq.incrementAndGet();

		DateTime dt = new DateTime(DateTimeZone.UTC);
		LocalDate ld = dt.toLocalDate();

		return ld + "-" + System.currentTimeMillis() + "-" + nextVal;
	}

}
