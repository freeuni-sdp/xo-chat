package ge.edu.freeuni.sdp.xo.chat.data;

import com.microsoft.azure.storage.StorageException;

import java.util.ArrayList;

public interface Repository {
	
	Iterable<MessageEntity> getMessages(String roomId);

	void postMessage(MessageEntity message) throws StorageException;
	
	void deleteMessage(MessageEntity message) throws StorageException;

}
