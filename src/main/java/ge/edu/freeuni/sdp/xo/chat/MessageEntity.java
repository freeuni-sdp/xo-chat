package ge.edu.freeuni.sdp.xo.chat;

import javax.xml.bind.annotation.XmlElement;

import com.microsoft.azure.storage.table.TableServiceEntity;

public class MessageEntity extends TableServiceEntity {
	@XmlElement
	public String roomID;
	
	@XmlElement
	public String text;
	
	@XmlElement
	public String senderUserName;
	
	public MessageEntity(String roomID, String text, String senderUserName) {
		this.roomID = roomID;
		this.text = text;
		this.senderUserName = senderUserName;
		this.rowKey = roomID ;
	}
	
	public String getRoomID(){
		return this.roomID;
	}

}
