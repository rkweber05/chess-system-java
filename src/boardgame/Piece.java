package boardgame;

public abstract class Piece { // peça

	protected Position position;
	private Board board; // tabuleiro
	
	public Piece(Board board) {
		this.board = board;
		position = null; // não precisa por, pois o Java coloca
	}

	protected Board getBoard() {
		return board;
	}
	
	public abstract boolean[][] possibleMoves(); // Possiveis movimentos
	
	public boolean possibleMove(Position position) {
		return possibleMoves()[position.getRow()][position.getColumn()];
	}
	
	public boolean isThereAnyPossibleMove() { // Existe algum movimento possível
		boolean[][] mat = possibleMoves();
		
		for ( int i = 0; i < mat.length; i++) {
			for ( int j = 0; j < mat.length; j++) {
				if (mat[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
}
