
public class TicTacGame {
	private final static String BLANK = "";
	private String playerMark = "X";
	private static String gameMark = "O";
	int rowCanBeWon;
	
	String[] positions = new String[9];
	
	public TicTacGame() {
		for (int i=0; i<9; i++) {
			positions[i] = BLANK;
		}
	}
	
	public void setPlayerPosition(int position) throws Exception {
		if (position < 0 || position > 8) {
			throw new Exception("invalid position");
		}
		if (positions[position].equals(BLANK)) {
			positions[position] = playerMark;
			makeGameNextMove();
		} else {
			throw new Exception("position is occupied");
		}
	}

	private void makeGameNextMove() {
		if (checkPlayerCanWinNextMove()) {
			blockPlayer();
		} else {
			for (int i=0; i<9; i++) {
				if (positions[i].equals(BLANK)) {
					positions[6] = gameMark;
				}
			}
		}
	}
	private boolean checkPlayerCanWinNextMove() {
		rowCanBeWon = findRowCanBeWon(playerMark);
		return rowCanBeWon != -1;
	}
	private int findRowCanBeWon(String side) {
		for (int i=0; i<3; i++) {
			int marksForSide = 0;
			int rowBase = 3 * i;
			for (int j=0; j<3; j++) {
				if (positions[rowBase + j].equals(side)) {
					marksForSide++;
				}
				if (marksForSide == 2) {
					return i;
				}
			}
		}
		return -1;
	}
	private void blockPlayer() {
		int rowBase = 3 * rowCanBeWon;
		for (int j=0; j<3; j++) {
			positions[rowBase + j] = gameMark;
		}
	}

	public String getPosition(int position) {
		return positions[position];
	}
	public void nextMove() {
		positions[2] = gameMark;
	}
	public String getStatus() {
		String winsRow = checkRowWinner();
		if (winsRow.equals(playerMark)) {
			return "player wins!";
		} else {
			return "game wins!";
		}
	}
	private String checkRowWinner() {
		for (int i=0; i<3; i++) {
			int rowStart = 3 * i;
			int game = 0;
			int player = 0;
			for (int j=0; j<3; j++) {
				if (positions[rowStart + j].equals(gameMark)) {
					game++;
				}
				if (positions[rowStart + j].equals(playerMark)) {
					player++;
				}
			}
			if (player == 3 || game == 3) {
				return (player == 3) ? playerMark : gameMark;
			}
		}
		return "";
	}
	public void takeFirstMove() {
		gameMark = "X";
		playerMark = "O";
		positions[0] = gameMark;
	}
}
