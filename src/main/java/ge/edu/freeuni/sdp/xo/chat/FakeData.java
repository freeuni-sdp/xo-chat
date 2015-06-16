package ge.edu.freeuni.sdp.xo.chat;

import java.util.ArrayList;
import java.util.HashMap;


public class FakeData {
	
	private ArrayList<Message> publicMessages = new ArrayList<>();
	private HashMap<Integer, ArrayList<Message>> privateMessages = new HashMap<>();
//	private ArrayList<Message>  = new ArrayList<>();
	
	private final int defaultRoomId = 7;
	
	public FakeData() {
		privateMessages.put(defaultRoomId, new ArrayList<Message>());
		for(int i = 0; i < 10; i++){
			Message m1 = new Message();
			m1.roomID = -1;
			m1.text = "PUBLIC message text " + i;
			m1.senderUserToken = "1k2we";
			publicMessages.add(m1);
			Message m2 = new Message();
			m2.roomID = 7;
			m2.text = "PRIVATE message text " + i;
			m2.senderUserToken = "1k2we";
			privateMessages.get(defaultRoomId).add(m2);
		}
	}
	
	public ArrayList<Message> getPublicChatMessages(){
		return publicMessages;
	}
	public ArrayList<Message> getPrivateChatMessages(int roomId){
		return privateMessages.get(defaultRoomId);
	}
	
	public void addMessageToPublicChat(Message message){
		publicMessages.add(message);
	}
	
	public void addMessageToPrivateChat(Message message){
		privateMessages.get(defaultRoomId).add(message);
	}
}
