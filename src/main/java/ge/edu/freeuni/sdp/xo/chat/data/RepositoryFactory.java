package ge.edu.freeuni.sdp.xo.chat.data;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.CloudTableClient;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;

public class RepositoryFactory {
	
	public static Repository create() throws StorageException {
		return new CloudRepository(getTable("publicMessages"),getTable("privateMessages"));
	}

	private static CloudTable getTable(String table) throws StorageException {
		
		final String storageConnectionString = "DefaultEndpointsProtocol=http;"
				+ "AccountName=freeunisdptodo;"
				+ "AccountKey=+UKHsHFQUWDjoHT1S7q4Ivc1phivLmXwWESvpcRCCJwhs1BnShkaFOOQs+BmI4XWtNnyg78S6ovbD2J5QCKxsQ==";

		CloudStorageAccount storageAccount;
		try {
			storageAccount = CloudStorageAccount.parse(storageConnectionString);
		} catch (InvalidKeyException | URISyntaxException e) {
			e.printStackTrace();
			return null;
		}

		CloudTableClient tableClient = storageAccount.createCloudTableClient();
		CloudTable cloudTable;
		try {
			cloudTable = new CloudTable(table, tableClient);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return null;
		}
		cloudTable.createIfNotExists();
		return cloudTable;
	}
}
