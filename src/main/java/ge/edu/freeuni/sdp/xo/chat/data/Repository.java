package ge.edu.freeuni.sdp.xo.chat.data;

import com.microsoft.azure.storage.StorageException;

import java.util.ArrayList;

public interface Repository {
	
	Iterable<MessageEntity> getPublicChatMessages();
	
	ArrayList<MessageEntity> getPrivateChatMessages(String roomId);
	
	void addMessageToPublicChat(MessageEntity message) throws StorageException;
	
	void addMessageToPrivateChat(MessageEntity message) throws StorageException;

}
