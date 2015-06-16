package ge.edu.freeuni.sdp.xo.chat;






import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;

import com.microsoft.azure.storage.StorageException;


@Path("/")
@Consumes( { MediaType.APPLICATION_JSON})
@Produces( { MediaType.APPLICATION_JSON})
public class ChatServise {
	
	@Context
	private UriInfo uriInfo;
	
	FakeData fakeData = new FakeData();
	
	
	/**
	 * 
	 * @return 200 (OK), list of messages.
	 * @throws StorageException
	 */
	
	@GET
	public List<Message> publicChatMessages(@QueryParam("token") String token)  {
		final ArrayList<Message> result = new ArrayList<Message>();
		if(!FakeAuthorizationChecker.isAuthorized(token)) throw new WebApplicationException(401);
		for (Message message: fakeData.getPublicChatMessages()) {
			result.add(message);
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
	public List<Message> publicChatMessages(@PathParam("roomId") int roomId,
			@QueryParam("token") String token)  {
		if(!FakeAuthorizationChecker.isAuthorized(token)) throw new WebApplicationException(401);
		final ArrayList<Message> result = new ArrayList<Message>();
		for (Message message: fakeData.getPrivateChatMessages(roomId)) {
			result.add(message);
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
	public Response addChatMessage(Message message) throws StorageException {
		if(message == null)return Response.status(Status.BAD_REQUEST).build();

		if (!FakeAuthorizationChecker.isAuthorized(message.senderUserToken)
				|| !FakeAuthorizationChecker.isCorectRoomId(message.roomID, message.senderUserToken))
			return Response.status(401).build();
		
		if(message.roomID == -1){
			fakeData.addMessageToPublicChat(message);
		}else{
			fakeData.addMessageToPrivateChat(message);
		}
		
		return Response.status(Status.CREATED).build();
	}
}
