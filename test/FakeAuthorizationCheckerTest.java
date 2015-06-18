import static org.junit.Assert.*;
import ge.edu.freeuni.sdp.xo.chat.FakeAuthorizationChecker;

import org.junit.Before;
import org.junit.Test;


public class FakeAuthorizationCheckerTest {

	private FakeAuthorizationChecker checker;
	
	@Before
	public void initFakeAuthorizationChecker(){
		checker = new FakeAuthorizationChecker();
	}
	
	@Test
	public void testIsAuthorized() {
		assertTrue(checker.isAuthorized("something"));
	}
	
	@Test
	public void testIsCorectRoomId() {
		assertTrue(checker.isCorectRoomId(1,"something"));
	}


}
