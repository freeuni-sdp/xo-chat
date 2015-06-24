import static org.junit.Assert.*;
import javax.ws.rs.core.MediaType;
import ge.edu.freeuni.sdp.xo.chat.ChatServise;
import ge.edu.freeuni.sdp.xo.chat.FakeAuthorizationChecker;
import ge.edu.freeuni.sdp.xo.chat.Message;
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
		 return new ResourceConfig(ChatServise.class);
	 }
	
	 
	 @Test
	 public void testGetPublicMessagesOK() {
		 System.out.println(1);
		 Response actual = target("/").queryParam("token", "1234").request().get();
		 assertEquals(Response.Status.OK.getStatusCode(), actual.getStatus());
	 } 	
	 
	 @Test
	 public void testGetPrivateMessagesOK() {
		 Response actual = target("/5/").queryParam("token", "1234").request().get();
		 assertEquals(Response.Status.OK.getStatusCode(), actual.getStatus());
	 } 
	 
	
	
	@Test (expected=WebApplicationException.class)
	public void testPublicChatMessagesException() {
		
		ChatServise chatService = new ChatServise();
		chatService.checker = Mockito.mock(FakeAuthorizationChecker.class);
        Mockito.when(chatService.checker.isAuthorized("smth")).thenReturn(false);
        
		chatService.publicChatMessages("smth");
	}
	
	@Test (expected=WebApplicationException.class)
	public void testPrivateChatMessagesException() {
		
		ChatServise chatService = new ChatServise();
		chatService.checker = Mockito.mock(FakeAuthorizationChecker.class);
        Mockito.when(chatService.checker.isAuthorized("smth")).thenReturn(false);
        
		chatService.privateChatMessages(5,"smth");
	}
//	
	
	
	@Test
    public void testAddChatMessageOK() {
		Message message = new Message();
		message.roomID = -1;
		message.senderUserToken = "smth";
		message.text = "";
        Response actual = target("/").request().post(Entity.entity(message, MediaType.APPLICATION_JSON_TYPE));
        assertEquals(Response.Status.CREATED.getStatusCode(), actual.getStatus());
    }
	
	
	
	@Test
    public void testAddChatMessageBad() {
		Message message = null;
		
        Response actual = target("/").request().post(Entity.entity(message, MediaType.APPLICATION_JSON_TYPE));
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), actual.getStatus());
    }
	
	@Test 
    public void testAddChatMessageExceptionBadRoomID() throws StorageException{
		
		Message message = new Message();
		message.roomID = -5;
		message.senderUserToken = "smth";

		Response actual = target("/").request().post(Entity.entity(message, MediaType.APPLICATION_JSON_TYPE));
        assertEquals(Response.status(401).build().getStatus(), actual.getStatus());
    }
	
	@Test 
    public void testAddChatMessageExceptionBadToken() throws StorageException{
		
		Message message = new Message();
		message.roomID = 1;
		message.senderUserToken = "";

		Response actual = target("/").request().post(Entity.entity(message, MediaType.APPLICATION_JSON_TYPE));
        assertEquals(Response.status(401).build().getStatus(), actual.getStatus());
    }
	


}
