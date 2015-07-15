package ge.edu.freeuni.sdp.xo.chat.data;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.CloudTableClient;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;

public class RepositoryFactory {

	private static RepositoryFactory repositoryFactory;

	public static RepositoryFactory create() throws StorageException {
		if (repositoryFactory == null) {
			repositoryFactory = new RepositoryFactory();
		}
		return repositoryFactory;
	}

	public Repository getPrivateRepository() throws StorageException {
		return new RepositoryImpl(getTable("privateMessages"));
	}

	public Repository getPublicRepository() throws StorageException {
		return new RepositoryImpl(getTable("publicMessages"));
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
