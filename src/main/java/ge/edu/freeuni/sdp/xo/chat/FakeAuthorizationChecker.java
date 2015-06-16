package ge.edu.freeuni.sdp.xo.chat;

public class FakeAuthorizationChecker {

	
	public static boolean isAuthorized(String token){
		return true;
	}
	
	public static boolean isCorectRoomId(int roomID, String token){
		return true;
	}
} 
