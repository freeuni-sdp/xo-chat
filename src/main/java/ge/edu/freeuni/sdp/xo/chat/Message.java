package ge.edu.freeuni.sdp.xo.chat;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Message {
	
	@XmlElement
	int roomID;
	
	@XmlElement
	String text;
	
	@XmlElement
	String senderUserToken;
}
