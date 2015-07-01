package ge.edu.freeuni.sdp.xo.chat.data;

import ge.edu.freeuni.sdp.xo.chat.MessageEntity;

import java.util.ArrayList;

import com.microsoft.azure.storage.StorageException;

public interface Repository {
	
	public  abstract ArrayList<MessageEntity> getPublicChatMessages();
	
	public abstract ArrayList<MessageEntity> getPrivateChatMessages(int roomId);
	
	public abstract void addMessageToPublicChat(MessageEntity message) throws StorageException;
	
	public abstract void addMessageToPrivateChat(MessageEntity message) throws StorageException;

}
