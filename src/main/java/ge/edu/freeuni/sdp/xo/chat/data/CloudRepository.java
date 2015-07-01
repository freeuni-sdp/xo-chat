package ge.edu.freeuni.sdp.xo.chat.data;

import ge.edu.freeuni.sdp.xo.chat.MessageEntity;

import java.util.ArrayList;

import com.microsoft.azure.storage.*;
import com.microsoft.azure.storage.table.*;

public class CloudRepository implements Repository {

	private CloudTable table;

	public CloudRepository(CloudTable table) {
		this.table = table;
	}

	@Override
	public ArrayList<MessageEntity> getPublicChatMessages() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<MessageEntity> getPrivateChatMessages(int roomId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addMessageToPublicChat(MessageEntity message)
			throws StorageException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addMessageToPrivateChat(MessageEntity message)
			throws StorageException {
		// TODO Auto-generated method stub
		
	}
}