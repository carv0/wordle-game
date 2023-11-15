class Game {

	// Constantes
	static final int MAX_LINES = 6;
	static final int CORRECT_POSITION = 3;
	static final int EXISTS = 2;
	static final int NOT_EXISTS = 1;
	static final int UNTESTED = 0;
	static char[][] m = new char[MAX_LINES][6];

	// Atributos
	String puzzle, input;
	int stat;
	char[][] board, keyBoard;
	Dictionary dictionary;
	ColorImage img;
	int[] stateKey = new int[26];

	// Construtores
	Game(ColorImage img, char[][] board, char[][] keyBoard, Dictionary dictionary) {
		this.img = img;
		this.keyBoard = keyBoard;
		this.board = board;
		this.dictionary = dictionary;
		for (int i = 0; i < 26; i++) {
			stateKey[i] = 0;
		}
	}

	Game(int stat) {
		this.stat = stat;
	}

	Game(Dictionary dictionary) {
		this.dictionary = dictionary;
	}

	Game(String puzzle) {
		this.puzzle = puzzle;

	}

	// 1.
	String puzzle() {
		Dictionary dictionary = new Dictionary("pt_br.txt");
		puzzle = dictionary.generateSecretWord(6);
		return puzzle;
	}

	// 2.
	int[] stateOfKey() {
		return stateKey;
	}

	// 3.
	void changeState(String guess, String correct) {
		char[] correct1 = correct.toCharArray();
		char[] guess1 = guess.toCharArray();
		boolean ch = false;

		for (int i = 0; i != correct1.length; i++) {
			if (correct1[i] == guess1[i]) {
				int a = 0;
				for (int l = 0; l != keyBoard.length; l++) {
					for (int p = 0; p != keyBoard[l].length; p++) {
						if (keyBoard[l][p] == guess1[i]) {
							stateKey[a] = 3;
						}
						a++;
					}
				}
			} else {
				for (int j = 0; j != guess1.length; j++) {
					if (correct1[i] == guess1[j]) {
						int a = 0;
						for (int l = 0; l != keyBoard.length; l++) {
							for (int p = 0; p != keyBoard[l].length; p++) {
								if (keyBoard[l][p] == correct1[i]) {
									stateKey[a] = 2;
								}
								a++;
							}
						}
					}
				}
			}
		}

		for (int i = 0; i != guess1.length; i++) {
			for (int j = 0; j != correct1.length; j++) {
				if (guess1[i] == correct1[j] && ch == false) {
					ch = true;
				}
			}
			if (ch == false) {
				int a = 0;
				for (int l = 0; l != keyBoard.length; l++) {
					for (int p = 0; p != keyBoard[l].length; p++) {
						if (keyBoard[l][p] == guess1[i]) {
							stateKey[a] = 1;
						}
						a++;
					}
				}
			} else {
				ch = false;
			}
		}
	}

	// 4.
	void set(String input) {
		this.input = input;
	}

	String get() {
		return input;
	}

}