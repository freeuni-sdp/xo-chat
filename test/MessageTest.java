import static org.junit.Assert.*;
import ge.edu.freeuni.sdp.xo.chat.Message;

import org.junit.Before;
import org.junit.Test;


public class MessageTest {

	private Message message;

    @Before
    public void initMessage() {
    	message = new Message();
    	message.roomID = 1;
    	message.text = "text";
    	message.senderUserToken = "senderUserToken";
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
		assertTrue(message.senderUserToken.equals("senderUserToken"));
		assertFalse(message.senderUserToken.equals("something"));
	}

}
