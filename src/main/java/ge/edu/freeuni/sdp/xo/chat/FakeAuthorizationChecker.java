package ge.edu.freeuni.sdp.xo.chat;

public class FakeAuthorizationChecker {
	
	private boolean isAuthorized;
	private boolean isCorectRoomId;
	
	public FakeAuthorizationChecker(){
		isAuthorized = true;
		isCorectRoomId = true;
	}
	
	
	public  boolean isAuthorized(String token){
		return isAuthorized;
	}
	
	public  boolean isCorectRoomId(int roomID, String token){
		return isCorectRoomId;
	}
} 
