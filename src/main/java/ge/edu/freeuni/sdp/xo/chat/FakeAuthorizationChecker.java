package ge.edu.freeuni.sdp.xo.chat;

public class FakeAuthorizationChecker {
	
	private boolean isAuthorized;
	private boolean isCorectRoomId;
	
	public FakeAuthorizationChecker(){
		isAuthorized = true;
		isCorectRoomId = true;
	}
	
	
	public  boolean isAuthorized(String token){
		if (token.equals("")){
			return false;
		}
		return isAuthorized;
	}
	
	public  boolean isCorectRoomId(int roomID, String token){
		if(roomID < -1){
			return false;
		}else{
			return isCorectRoomId;
		}
	}
} 
