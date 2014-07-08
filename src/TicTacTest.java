import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class TicTacTest {

	TicTacGame game = null;
	
	@Before
	public void setUp() {
		game = new TicTacGame();
	}

	@Test
	public void shouldMarkPositionWhenPlayerGoesFirst() throws Exception {
		game.setPlayerPosition(1);
		assertEquals("X", game.getPosition(1));
	}
	@Test
	public void shouldMakeMoveAndMark() throws Exception {
		game.setPlayerPosition(1);
		game.nextMove();
		assertEquals("O", game.getPosition(2));
	}
	@Test
	public void shouldAnnouncePlayerWinner() throws Exception {
		game.setPlayerPosition(5);
		game.setPlayerPosition(4);
		game.setPlayerPosition(0);
		assertEquals("player wins!", game.getStatus());
	}
	@Test
	public void shouldAnnounceGameWinner() throws Exception {
		game.setPlayerPosition(4);
		game.setPlayerPosition(3);
		game.setPlayerPosition(0);
		assertEquals("game wins!", game.getStatus());
	}
	@Test
	public void shouldAnnouncePlayerAttemptToMarkOccupiedPosition() throws Exception {
		game.setPlayerPosition(0);
		boolean caught = false;
		try {
			game.setPlayerPosition(6);
		}
		catch (Exception e) {
			caught = true;
		}
		assertTrue(caught);
	}
	// FIXME: could eliminate this by using enum for position
	@Test
	public void shouldAnnounceInvalidPosition() {
		boolean caught = false;
		try {
			game.setPlayerPosition(9);
		} catch (Exception e) {
			caught = true;
		}
		assertTrue(caught);
	}
	@Test
	public void shouldMarkPositionWhenGameGoesFirst() {
		game.takeFirstMove();
		assertEquals("X", game.getPosition(0));
	}
	@Test
	public void shouldTryThreeInRowWhenPlayerCanNotWinNextTurn() {
		// TBD
	}
	@Test
	public void shouldBlockPlayerWhenPlayerCanWinNextTurn() throws Exception {
		game.setPlayerPosition(0);
		game.setPlayerPosition(1);
		Exception caught = null;
		try {
			game.setPlayerPosition(2);
		} catch (Exception e) {
			caught = e;
		}
		assertEquals("position is occupied", caught.getMessage());
	}
}
