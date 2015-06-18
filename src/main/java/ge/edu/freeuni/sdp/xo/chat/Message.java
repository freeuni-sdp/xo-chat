package ge.edu.freeuni.sdp.xo.chat;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Message {
	
	@XmlElement
	public int roomID;
	
	@XmlElement
	public String text;
	
	@XmlElement
	public String senderUserToken;
}
