class Static {

	// CONSTANTES
	static final Color CORRECT_BG = new Color(59, 163, 148);
	static final Color EXISTS_BG = new Color(212, 173, 106);
	static final Color NOT_IN_WORD_BG = new Color(48, 42, 44);
	static final Color WHITE = new Color(255, 255, 255);
	static final Color EMPTY_BG = new Color(96, 84, 88);
	static final Color DEFAULT_BG = new Color(111, 92, 98);

	static final int DEFAULT_WIDTH = 700;
	static final int DEFAULT_HEIGHT = 600;
	static final int ICON_SIZE = 40;
	static final int ICON_SPACING = 4;

	static final int MAX_LINES = 6;

	static final int CORRECT_POSITION = 3;
	static final int EXISTS = 2;
	static final int NOT_EXISTS = 1;
	static final int UNTESTED = 0;

	static int attempt = 0;
	static boolean state = false;

	static final char[][] QWERTY = { { 'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P' },
			{ 'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L' }, { 'Z', 'X', 'C', 'V', 'B', 'N', 'M' } };

	static char[][] m = new char[MAX_LINES][6];

	// Stats
	static int[] lin = new int[7];
	static int numJog, numVit, numVitCons, numBestSeq;

	static void initializeBoard(char[][] matrix) {
		for (int i = 0; i != MAX_LINES; i++) {
			for (int j = 0; j != 6; j++) {
				matrix[i][j] = ' ';
			}

		}

	}

	// 1.
	// Criar o background
	static ColorImage createBackground() {
		ColorImage img1 = new ColorImage(DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_BG);

		return img1;
	}

	// Criar o quadrado com um char
	static void drawSquare(ColorImage img, int xi, int yi, Color color, char c) {
		String s = String.valueOf(c);
		for (int x = xi; x != xi + ICON_SIZE; x++) {
			for (int y = yi; y != yi + ICON_SIZE; y++) {
				img.setColor(x, y, color);
			}
		}
		img.drawCenteredText(xi + (ICON_SIZE / 2), yi + (ICON_SIZE / 2), s, 20, WHITE);
	}

	// 2.
	static void drawBoard(ColorImage img, char[][] m, String guess, String correct) {
		m[attempt] = guess.toCharArray();
		char[] c = correct.toCharArray();
		int xi = 205;
		int yi = 50;

		for (int i = 0; i != MAX_LINES; i++) {
			for (int j = 0; j != 6; j++) {
				if (m[i][j] != ' ') {
					drawSquare(img, xi, yi, NOT_IN_WORD_BG, m[i][j]);
				} else {
					drawSquare(img, xi, yi, EMPTY_BG, m[i][j]);
				}
				for (int k = 0; k != c.length; k++) {
					if (m[i][j] == c[k] && j != k)
						drawSquare(img, xi, yi, EXISTS_BG, m[i][j]);
				}
				if (m[i][j] == c[j]) {
					drawSquare(img, xi, yi, CORRECT_BG, m[i][j]);
				}
				xi += 44;
			}
			xi = 205;
			yi += 44;
		}
		attempt++;

	}

	// 3.
	static void drawKeyboard(ColorImage img, char[][] c, int[] state) {
		int xi = 120;
		int yi = 400;
		int a = 0;
		for (int i = 0; i != c.length; i++) {
			for (int j = 0; j != c[i].length; j++) {
				if (state[a] == 0) {
					drawSquare(img, xi, yi, EMPTY_BG, c[i][j]);
					xi += 44;
				}
				if (state[a] == 1) {
					drawSquare(img, xi, yi, NOT_IN_WORD_BG, c[i][j]);
					xi += 44;
				}
				if (state[a] == 2) {
					drawSquare(img, xi, yi, EXISTS_BG, c[i][j]);
					xi += 44;
				}
				if (state[a] == 3) {
					drawSquare(img, xi, yi, CORRECT_BG, c[i][j]);
					xi += 44;
				}
				a++;
			}
			xi = 120 + ((i + 1) * 22);
			yi += 44;
		}
	}

	// 4.
	static String correctionWord(String str) {
		str = str.replaceAll("[ÂÀÁÃ]", "A");
		str = str.replaceAll("[ÊÈÉ]", "E");
		str = str.replaceAll("[ÎÍÌ]", "I");
		str = str.replaceAll("[ÔÕÒÓ]", "O");
		str = str.replaceAll("[ÛÙÚ]", "U");
		str = str.replaceAll("Ç", "C");
		return str;

	}

	// main
	static void main() {
		ColorImage img = createBackground();
		Dictionary dictionary = new Dictionary("pt_br.txt");
		initializeBoard(m);

		Game game = new Game(img, m, Constantes.QWERTY, dictionary);

		String correct = game.puzzle();

		while (true) {

			String guess1 = correctionWord(game.get());

			game.changeState(guess1, correct);
			drawBoard(img, m, guess1, correct);
			drawKeyboard(img, Constantes.QWERTY, game.stateOfKey());
			if (guess1 == correct) {
				state = true;
				new Stats(lin, numJog, numVit, numVitCons, numBestSeq, attempt, state);
				break;

			}
			if (guess1 != correct && attempt == 6) {
				state = false;
				new Stats(lin, numJog, numVit, numVitCons, numBestSeq, attempt, state);
				break;
			}

		}
		main();

	}

}
