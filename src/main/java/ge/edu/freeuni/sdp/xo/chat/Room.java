package ge.edu.freeuni.sdp.xo.chat;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * 
 * Copied from xo-rooms/src/main/java/ge/edu/freeuni/sdp/xo/rooms/data/Room.java
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Room {
	
	@XmlElement
	private String id;
	
	@XmlElement(nillable=true)
	private String x_user;
	
	@XmlElement(nillable=true)
	private String o_user;
	
	
	public Room(){
		//Dummy
	}
	
	/**
	 * @param id
	 * @param x_user
	 * @param o_user
	 */
	public Room(String id, String x_user, String o_user) {
		this.id = id;
		this.x_user = x_user;
		this.o_user = o_user;
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * @return the x_user
	 */
	public String getx_user() {
		return x_user;
	}
	
	
	/**
	 * @return the o_user
	 */
	public String geto_user() {
		return o_user;
	}
	
}
