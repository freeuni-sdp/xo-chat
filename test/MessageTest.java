import ge.edu.freeuni.sdp.xo.chat.MessageDo;
import ge.edu.freeuni.sdp.xo.chat.data.MessageEntity;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class MessageTest {

	private MessageEntity message;

    @Before
    public void initMessage() {
		MessageDo mDo = new MessageDo();
		mDo.setId(UUID.randomUUID().toString());
		mDo.setRoomID("1");
		mDo.setSenderUserName("senderUserToken");
		mDo.setText("text");
    	message = new MessageEntity(mDo);
    }
	
	@Test
	public void testRoomID() {
		assertEquals("1",message.getRoomID());

	}
	
	@Test
	public void testText() {
		assertTrue(message.getText().equals("text"));

	}
	
	@Test
	public void testSenderUserToken() {
		assertTrue(message.getSenderUserName().equals("senderUserToken"));
	}

}
