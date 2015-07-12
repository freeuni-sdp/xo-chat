package ge.edu.freeuni.sdp.xo.chat;

import com.microsoft.azure.storage.table.TableServiceEntity;

public class MessageEntity extends TableServiceEntity {
	private String roomID;
	private String text;
	private String senderUserName;
	
	public MessageEntity(){
	}
	
	public MessageEntity(MessageDo mDo) {
		MessageEntityId meId = new MessageEntityId(mDo.getId());
		this.partitionKey = meId.getPartitionKey();
		this.rowKey = meId.getRowKey();
		this.roomID = mDo.getRoomID();
		this.text = mDo.getText();
		this.senderUserName = mDo.getSenderUserName();
	}

	public MessageDo toDo() {
		MessageEntityId meId = new MessageEntityId(this.partitionKey, this.rowKey);
		MessageDo mDo = new MessageDo();
		mDo.setId(meId.getId());
		mDo.setRoomID(this.roomID);
		mDo.setSenderUserName(this.senderUserName);
		mDo.setText(this.text);
		return mDo;
	}

	public String getRoomID() {
		return roomID;
	}

	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getSenderUserName() {
		return senderUserName;
	}

	public void setSenderUserName(String senderUserName) {
		this.senderUserName = senderUserName;
	}
}
