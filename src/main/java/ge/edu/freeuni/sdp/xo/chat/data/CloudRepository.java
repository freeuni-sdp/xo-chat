package ge.edu.freeuni.sdp.xo.chat.data;

import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.TableOperation;
import com.microsoft.azure.storage.table.TableQuery;

import java.util.ArrayList;

public class CloudRepository implements Repository {

	private CloudTable tableForPublicChat;
	private CloudTable tableForPrivateChat;

	public CloudRepository(CloudTable tableForPublicChat,CloudTable tableForPrivateChat) {
		this.tableForPublicChat = tableForPublicChat;
		this.tableForPrivateChat = tableForPrivateChat;
	}

	@Override
	public Iterable<MessageEntity> getPublicChatMessages() {
		TableQuery<MessageEntity> query = TableQuery.from(MessageEntity.class);
		return tableForPublicChat.execute(query);
	}

	@Override
	public ArrayList<MessageEntity> getPrivateChatMessages(String roomId) {
		ArrayList<MessageEntity> result = new ArrayList<>();
		TableQuery<MessageEntity> query = TableQuery.from(MessageEntity.class);
		for (MessageEntity entity : tableForPrivateChat.execute(query)) {
			if(entity.getRoomID().equals(roomId)){
				result.add(entity);
			}
		}
		return result;
	}

	@Override
	public void addMessageToPublicChat(MessageEntity message)
			throws StorageException {
		TableOperation insertMessage = TableOperation.insert(message);
		try {
			tableForPublicChat.execute(insertMessage);
		} catch (StorageException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void addMessageToPrivateChat(MessageEntity message)
			throws StorageException {
		TableOperation insertMessage = TableOperation.insert(message);
		try {
			tableForPrivateChat.execute(insertMessage);
		} catch (StorageException e) {
			e.printStackTrace();
		}	
	}
}