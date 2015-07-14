package ge.edu.freeuni.sdp.xo.chat.data;

public class MessageEntityId {
    private String partitionKey;
    private String rowKey;

    public MessageEntityId(String partitionKey, String rowKey) {
        this.partitionKey = partitionKey;
        this.rowKey  = rowKey;
    }

    public MessageEntityId(String id) {
        final int cutIndex = 10;
        this.partitionKey = id.substring(0, cutIndex);
        this.rowKey = id.substring(cutIndex, id.length());
    }

    public String getId() {
        return this.partitionKey.concat(this.rowKey);
    }

    public String getPartitionKey() {
        return partitionKey;
    }

    public String getRowKey() {
        return rowKey;
    }
}
