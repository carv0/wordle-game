class Stats {

	// Constantes
	static final Color BLACK = new Color(0, 0, 0);
	static final Color WHITE = new Color(255, 255, 255);
	static final Color BLUE = new Color(0, 0, 255);
	static final Color GREY = new Color(122, 122, 122);

	// Atributos
	int[] lin;
	int numJog, numVit, numVitCons, numBestSeq;
	ColorImage img;
	int attempt;
	boolean state;

	// Construtores
	Stats(int[] lin, int numJog, int numVit, int numVitCons, int numBestSeq, int attempt, boolean state) {
		this.lin = lin;
		this.numJog = numJog;
		this.numVit = numVit;
		this.numVitCons = numVitCons;
		this.numBestSeq = numBestSeq;
		this.attempt = attempt;
		this.state = state;
		img = createBackground();
		writeBoard(img);

	}

	// Metodos
	// Criar o background
	ColorImage createBackground() {
		ColorImage img1 = new ColorImage(420, 650, BLACK);
		return img1;
	}

	// Escrever no board
	void writeBoard(ColorImage img1) {
		img1.drawText(100, 40, "ESTATÍSTICAS", 30, WHITE);
		img1.drawText(50, 160, "Jogados", 15, WHITE);
		img1.drawText(70, 130, String.valueOf(numJog), 25, WHITE);

		img1.drawText(140, 160, "% Vitórias", 15, WHITE);
		img1.drawText(100, 350, String.valueOf(pVit()), 25, WHITE);

		img1.drawText(220, 160, "Sequência", 15, WHITE);
		img1.drawText(220, 180, "de Vitórias", 15, WHITE);
		img1.drawText(100, 350, String.valueOf(0), 25, WHITE);

		img1.drawText(330, 160, "Melhor", 15, WHITE);
		img1.drawText(318, 180, "Sequência", 15, WHITE);
		img1.drawText(100, 350, String.valueOf(0), 25, WHITE);

		img1.drawText(70, 300, "Distribuição de tentativas", 25, WHITE);
		img1.drawText(80, 350, "1", 25, WHITE);
		img1.drawText(115, 350, String.valueOf(lin[0]), 25, WHITE);

		img1.drawText(80, 385, "2", 25, WHITE);
		img1.drawText(115, 385, String.valueOf(lin[1]), 25, WHITE);

		img1.drawText(80, 420, "3", 25, WHITE);
		img1.drawText(115, 420, String.valueOf(lin[2]), 25, WHITE);

		img1.drawText(80, 455, "4", 25, WHITE);
		img1.drawText(115, 455, String.valueOf(lin[3]), 25, WHITE);

		img1.drawText(80, 490, "5", 25, WHITE);
		img1.drawText(115, 490, String.valueOf(lin[4]), 25, WHITE);

		img1.drawText(80, 525, "6", 25, WHITE);
		img1.drawText(115, 525, String.valueOf(lin[5]), 25, WHITE);

		img1.drawText(80, 560, "X", 25, WHITE);
		img1.drawText(115, 560, String.valueOf(lin[6]), 25, WHITE);

		return;

	}

	// 1.
	int[] li() {
		lin = new int[7];
		for (int i = 0; i != 7; i++) {
			lin[i] = 0;
		}
		return lin;
	}

	void changeVic() {
		if (state == true) {
			lin[attempt] += 1;
		} else {
			lin[6] += 1;
		}

	}

	int jogados(int[] v) {
		numJog = 0;
		for (int i = 0; i != v.length; i++) {
			numJog += v[i];
		}
		return numJog;
	}

	int pVit() {
		double por = (numVit * 100) / numJog;
		return (int) por;
	}

}
