package ge.edu.freeuni.sdp.xo.chat;

import javax.xml.bind.annotation.XmlElement;

public class MessageDo {
    @XmlElement
    public String id;

    @XmlElement
    public String roomID;

    @XmlElement
    public String text;

    @XmlElement
    public String senderUserName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
