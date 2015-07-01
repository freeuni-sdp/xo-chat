package ge.edu.freeuni.sdp.xo.chat;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class SendMessageEntity {
	
	@XmlElement
	public int roomID;
	
	@XmlElement
	public String text;
}
