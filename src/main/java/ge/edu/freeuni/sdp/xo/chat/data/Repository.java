package ge.edu.freeuni.sdp.xo.chat.data;

import ge.edu.freeuni.sdp.xo.chat.MessageEntity;

import java.util.ArrayList;

import com.microsoft.azure.storage.StorageException;

public interface Repository {
	
	Iterable<MessageEntity> getPublicChatMessages();
	
	ArrayList<MessageEntity> getPrivateChatMessages(String roomId);
	
	void addMessageToPublicChat(MessageEntity message) throws StorageException;
	
	void addMessageToPrivateChat(MessageEntity message) throws StorageException;

}
