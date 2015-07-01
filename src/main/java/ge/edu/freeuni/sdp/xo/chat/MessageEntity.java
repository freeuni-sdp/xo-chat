package ge.edu.freeuni.sdp.xo.chat;

import javax.xml.bind.annotation.XmlElement;

public class MessageEntity {
	@XmlElement
	public int roomID;
	
	@XmlElement
	public String text;
	
	@XmlElement
	public String senderUserName;
	
	public MessageEntity(int roomID, String text, String senderUserName) {
		this.roomID =roomID;
		this.text = text;
		this.senderUserName = senderUserName;
	}

}
