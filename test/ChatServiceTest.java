import static org.junit.Assert.*;
import javax.ws.rs.core.MediaType;
import ge.edu.freeuni.sdp.xo.chat.ChatService;
import ge.edu.freeuni.sdp.xo.chat.FakeAuthorizationChecker;
import ge.edu.freeuni.sdp.xo.chat.SendMessageEntity;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.mockito.Mockito;
import com.microsoft.azure.storage.StorageException;
import javax.ws.rs.core.Application;
public class ChatServiceTest extends JerseyTest{

	
	
	 @Override
	 protected Application configure() {
		 return new ResourceConfig(ChatService.class);
	 }
	
	 
//	 @Test
//	 public void testGetPublicMessagesOK() {
//		 Response actual = target("/").queryParam("token", "1234").request().get();
//		 assertEquals(Response.Status.OK.getStatusCode(), actual.getStatus());
//	 } 	
//	 
//	 @Test
//	 public void testGetPrivateMessagesOK() {
//		 Response actual = target("/5/").queryParam("token", "1234").request().get();
//		 assertEquals(Response.Status.OK.getStatusCode(), actual.getStatus());
//	 } 
	 
	
	
	@Test (expected=WebApplicationException.class)
	public void testPublicChatMessagesException() {
		
		ChatService chatService = new ChatService();
		chatService.checker = Mockito.mock(FakeAuthorizationChecker.class);
        Mockito.when(chatService.checker.isAuthorized("smth")).thenReturn(false);
        
		chatService.publicChatMessages("smth");
	}
	
	@Test (expected=WebApplicationException.class)
	public void testPrivateChatMessagesException() {
		
		ChatService chatService = new ChatService();
		chatService.checker = Mockito.mock(FakeAuthorizationChecker.class);
        Mockito.when(chatService.checker.isAuthorized("smth")).thenReturn(false);
        
		chatService.privateChatMessages(5,"smth");
	}

	
//	@Test
//    public void testAddChatMessageOK() {
//		SendMessageEntity message = new SendMessageEntity();
//		message.roomID = "-1";
//		message.text = "";
//        Response actual = target("/").queryParam("token", "1234").request().post(Entity.entity(message, MediaType.APPLICATION_JSON_TYPE));
//        assertEquals(Response.Status.CREATED.getStatusCode(), actual.getStatus());
//    }
	
	
	
	@Test
    public void testAddChatMessageBad() {
		SendMessageEntity message = null;
		
        Response actual = target("/").queryParam("token", "1234").request().post(Entity.entity(message, MediaType.APPLICATION_JSON_TYPE));
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), actual.getStatus());
    }
	
	@Test 
    public void testAddChatMessageExceptionBadRoomID() throws StorageException{
		
		SendMessageEntity message = new SendMessageEntity();
		message.roomID = "-5";

		Response actual = target("/").request().post(Entity.entity(message, MediaType.APPLICATION_JSON_TYPE));
        assertEquals(Response.status(422).build().getStatus(), actual.getStatus());
    }
	
	@Test 
    public void testAddChatMessageExceptionBadToken() throws StorageException{
		
		SendMessageEntity message = new SendMessageEntity();
		message.roomID = "1";

		Response actual = target("/").request().post(Entity.entity(message, MediaType.APPLICATION_JSON_TYPE));
        assertEquals(Response.status(422).build().getStatus(), actual.getStatus());
    }
	


}
