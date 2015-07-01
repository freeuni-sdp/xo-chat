import static org.junit.Assert.*;
import ge.edu.freeuni.sdp.xo.chat.MessageEntity;
import ge.edu.freeuni.sdp.xo.chat.SendMessageEntity;

import org.junit.Before;
import org.junit.Test;


public class MessageTest {

	private MessageEntity message;

    @Before
    public void initMessage() {
    	message = new MessageEntity(1,"text","senderUserToken");
    }
	
	@Test
	public void testRoomID() {
		assertEquals(1,message.roomID);
		assertNotEquals(2,message.roomID);
		
	}
	
	@Test
	public void testText() {
		assertTrue(message.text.equals("text"));
		assertFalse(message.text.equals("something"));
		
	}
	
	@Test
	public void testSenderUserToken() {
		assertTrue(message.senderUserName.equals("senderUserToken"));
		assertFalse(message.senderUserName.equals("something"));
	}

}
