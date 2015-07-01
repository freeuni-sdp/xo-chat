package ge.edu.freeuni.sdp.xo.chat;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * Copied From xo-login/src/main/java/ge/edu/freeuni/sdp/xo/login/UserName.java
 *
 */

@XmlRootElement
public class UserName {

	@XmlElement
	public String username;
}