package ge.edu.freeuni.sdp.xo.chat.data;

import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.TableOperation;
import com.microsoft.azure.storage.table.TableQuery;

import java.util.ArrayList;

public class RepositoryImpl implements Repository {

	private CloudTable privateTable;

	public RepositoryImpl(CloudTable table) {
		this.privateTable = table;
	}

	@Override
	public Iterable<MessageEntity> getMessages(String roomId) {
		ArrayList<MessageEntity> result = new ArrayList<>();
		TableQuery<MessageEntity> query = TableQuery.from(MessageEntity.class);
		for (MessageEntity entity : privateTable.execute(query)) {
			if(entity.getRoomID().equals(roomId)){
				result.add(entity);
			}
		}
		return result;
	}

	@Override
	public void postMessage(MessageEntity message) throws StorageException {
		TableOperation insertMessage = TableOperation.insert(message);
		privateTable.execute(insertMessage);
	}

	@Override
	public void deleteMessage(MessageEntity message) throws StorageException {
		TableOperation deleteMessage = TableOperation.delete(message);
		privateTable.execute(deleteMessage);
	}
}