import ge.edu.freeuni.sdp.xo.chat.FakeData;
import ge.edu.freeuni.sdp.xo.chat.data.MessageEntity;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;


public class FakeDataTest {

	private FakeData data;
	
	@Before
	public void initFakeData(){
		data = new FakeData();
	}
	
	@Test
	public void testGetPublicMessages() {
		assertEquals(data.getPublicChatMessages().size(), 10);
		MessageEntity message = Mockito.mock(MessageEntity.class);
		data.addMessageToPublicChat(message);
		assertEquals(data.getPublicChatMessages().size(), 11);
	}
	
	@Test
	public void testGetPrivateMessages() {
		assertEquals(data.getPrivateChatMessages(1).size(), 10);
		MessageEntity message = Mockito.mock(MessageEntity.class);
		data.addMessageToPrivateChat(message);
		assertEquals(data.getPrivateChatMessages(1).size(), 11);
	}
	
	

}
