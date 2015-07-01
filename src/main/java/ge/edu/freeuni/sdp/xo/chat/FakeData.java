package ge.edu.freeuni.sdp.xo.chat;

import java.util.ArrayList;
import java.util.HashMap;


public class FakeData {
	
	private ArrayList<MessageEntity> publicMessages = new ArrayList<>();
	private HashMap<Integer, ArrayList<MessageEntity>> privateMessages = new HashMap<>();
	
	private final int defaultRoomId = 7;
	
	public FakeData() {
		privateMessages.put(defaultRoomId, new ArrayList<MessageEntity>());
		for(int i = 0; i < 10; i++){
			String text = "PUBLIC message text " + i;
			MessageEntity m1 = new MessageEntity(-1,text,"Sanji");
			publicMessages.add(m1);
			
			text = "PRIVATE message text " + i;
			MessageEntity m2 = new MessageEntity(7,text,"Nico Robin");
			privateMessages.get(defaultRoomId).add(m2);
		}
	}
	
	public ArrayList<MessageEntity> getPublicChatMessages(){
		return publicMessages;
	}
	public ArrayList<MessageEntity> getPrivateChatMessages(int roomId){
		return privateMessages.get(defaultRoomId);
	}
	
	public void addMessageToPublicChat(MessageEntity message){
		publicMessages.add(message);
	}
	
	public void addMessageToPrivateChat(MessageEntity message){
		privateMessages.get(defaultRoomId).add(message);
	}
}
