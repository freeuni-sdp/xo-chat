import static org.junit.Assert.*;

import java.awt.List;
import java.util.ArrayList;

import ge.edu.freeuni.sdp.xo.chat.ChatServise;
import ge.edu.freeuni.sdp.xo.chat.FakeAuthorizationChecker;
import ge.edu.freeuni.sdp.xo.chat.FakeData;
import ge.edu.freeuni.sdp.xo.chat.Message;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.microsoft.azure.storage.StorageException;


public class ChatServiceTest {

	ChatServise chatService ;
	
	@Before
	public void initChatService(){
		chatService = new ChatServise();
	}
	
	@Test (expected=WebApplicationException.class)
	public void testPublicChatMessagesException() {
		chatService.checker = Mockito.mock(FakeAuthorizationChecker.class);
		
		Mockito.when(chatService.checker.isAuthorized("")).thenReturn(false);
		chatService.publicChatMessages("");
	}
	
	@Test 
	public void testPublicChatMessages1() {
		chatService.fakeData = Mockito.mock(FakeData.class);
		
		Mockito.when(chatService.fakeData.getPublicChatMessages()).thenReturn(new ArrayList<Message>());
		
		chatService.checker = Mockito.mock(FakeAuthorizationChecker.class);	
		Mockito.when(chatService.checker.isAuthorized("")).thenReturn(true);
		
		ArrayList<Message> result = (ArrayList<Message>) chatService.publicChatMessages("");
		assertEquals(0,result.size());
	}
	
	@Test 
	public void testPublicChatMessages2() {
		
		Message message = Mockito.mock(Message.class);
		ArrayList<Message> helper = new ArrayList<>();
		helper.add(message);
		chatService.fakeData = Mockito.mock(FakeData.class);		
		Mockito.when(chatService.fakeData.getPublicChatMessages()).thenReturn(helper);
		
		chatService.checker = Mockito.mock(FakeAuthorizationChecker.class);	
		Mockito.when(chatService.checker.isAuthorized("")).thenReturn(true);
		
		ArrayList<Message> result = (ArrayList<Message>) chatService.publicChatMessages("");
		assertEquals(1,result.size());
	}

	
	@Test (expected=WebApplicationException.class)
	public void testPrivateChatMessagesException() {
		chatService.checker = Mockito.mock(FakeAuthorizationChecker.class);
		
		Mockito.when(chatService.checker.isAuthorized("")).thenReturn(false);
		chatService.privateChatMessages(1,"");
	}
	
	@Test 
	public void testPrivateChatMessages1() {
		chatService.fakeData = Mockito.mock(FakeData.class);
		
		Mockito.when(chatService.fakeData.getPrivateChatMessages(1)).thenReturn(new ArrayList<Message>());
		
		chatService.checker = Mockito.mock(FakeAuthorizationChecker.class);	
		Mockito.when(chatService.checker.isAuthorized("")).thenReturn(true);
		
		ArrayList<Message> result = (ArrayList<Message>) chatService.privateChatMessages(1,"");
		assertEquals(0,result.size());
	}
	
	@Test 
	public void testPrivateChatMessages2() {
		
		Message message = Mockito.mock(Message.class);
		ArrayList<Message> helper = new ArrayList<>();
		helper.add(message);
		chatService.fakeData = Mockito.mock(FakeData.class);		
		Mockito.when(chatService.fakeData.getPrivateChatMessages(1)).thenReturn(helper);
		
		chatService.checker = Mockito.mock(FakeAuthorizationChecker.class);	
		Mockito.when(chatService.checker.isAuthorized("")).thenReturn(true);
		
		ArrayList<Message> result = (ArrayList<Message>) chatService.privateChatMessages(1,"");
		assertEquals(1,result.size());
	}
	
	@Test 
	public void testaddChatMessageMessageIsNull() throws StorageException {
		Message message = null;
		Response actual = chatService.addChatMessage(message);
		assertEquals(Response.Status.BAD_REQUEST.getStatusCode(),actual.getStatus());
	}
	
	@Test 
	public void testaddChatMessageIsNotAuthorize() throws StorageException {
		Message message = Mockito.mock(Message.class);
		message.senderUserToken = "";
		chatService.checker = Mockito.mock(FakeAuthorizationChecker.class);	
		Mockito.when(chatService.checker.isAuthorized("")).thenReturn(false);
		
		
		Response actual = chatService.addChatMessage(message);
		
		assertEquals(Response.status(401).build().getStatus(),actual.getStatus());
	}
	
	@Test 
	public void testaddChatMessageIsNotCorrectRoomID() throws StorageException {
		Message message = Mockito.mock(Message.class);
		message.roomID = 1;
		message.senderUserToken = "";
		
		chatService.checker = Mockito.mock(FakeAuthorizationChecker.class);	
		Mockito.when(chatService.checker.isCorectRoomId(1, "")).thenReturn(false);
		Response actual = chatService.addChatMessage(message);
		
		assertEquals(Response.status(401).build().getStatus(),actual.getStatus());
	}
	
	@Test 
	public void testaddChatMessageIsAuthorizedAndIsNotCorrectRoomID() throws StorageException {
		Message message = Mockito.mock(Message.class);
		message.roomID = 1;
		message.senderUserToken = "";
		
		
		chatService.checker = Mockito.mock(FakeAuthorizationChecker.class);	
		Mockito.when(chatService.checker.isAuthorized("")).thenReturn(true);
		
		chatService.checker = Mockito.mock(FakeAuthorizationChecker.class);	
		Mockito.when(chatService.checker.isCorectRoomId(1, "")).thenReturn(false);
		Response actual = chatService.addChatMessage(message);
		
		assertEquals(Response.status(401).build().getStatus(),actual.getStatus());
	}
	
	@Test 
	public void testaddChatMessageIsNotAuthorizedAndIsCorrectRoomID() throws StorageException {
		Message message = Mockito.mock(Message.class);
		message.roomID = 1;
		message.senderUserToken = "";
		
		
		chatService.checker = Mockito.mock(FakeAuthorizationChecker.class);	
		Mockito.when(chatService.checker.isAuthorized("")).thenReturn(false);
		
		chatService.checker = Mockito.mock(FakeAuthorizationChecker.class);	
		Mockito.when(chatService.checker.isCorectRoomId(1, "")).thenReturn(true);
		Response actual = chatService.addChatMessage(message);
		
		assertEquals(Response.status(401).build().getStatus(),actual.getStatus());
	}
	
	@Test 
	public void testaddChatMessageIsNotAuthorizeORIsNotCorrectRoomID() throws StorageException {
		Message message = Mockito.mock(Message.class);
		
		chatService.checker = Mockito.mock(FakeAuthorizationChecker.class);	
		Mockito.when(chatService.checker.isAuthorized("")).thenReturn(false);
		
		chatService.checker = Mockito.mock(FakeAuthorizationChecker.class);	
		Mockito.when(chatService.checker.isCorectRoomId(1, "")).thenReturn(false);
		Response actual = chatService.addChatMessage(message);
		
		assertEquals(Response.status(401).build().getStatus(),actual.getStatus());
	}
	
	@Test 
	public void testaddChatMessageAddMessageToPublicChat() throws StorageException {
		Message message = Mockito.mock(Message.class);
		message.roomID = -1;
		

		Response actual = chatService.addChatMessage(message);
		
		assertEquals(Response.Status.CREATED.getStatusCode(),actual.getStatus());
	}
	
	@Test 
	public void testaddChatMessageAddMessageToPrivateChat() throws StorageException {
		Message message = Mockito.mock(Message.class);
		message.roomID = 4;

		Response actual = chatService.addChatMessage(message);
		
		assertEquals(Response.Status.CREATED.getStatusCode(),actual.getStatus());
	}
	
	


}
