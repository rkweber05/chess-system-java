package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece { // Peça de xadrez
 
	private Color color;
	private int moveCount;

	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
	public int getMoveCount() {
		return moveCount;
	}
	
			  //aumentar a contagem de movimentos
	public void increaseMoveCount() {
		moveCount++;
	}
			 // diminuir a contagem de movimentos
	public void decreaseMoveCount() {
		moveCount--;
	}
	
	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosition(position);
	}
	
					//existe peça adversária
	protected boolean isThereOpponentPiece(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position); // peguei a peça dessa posição
	
		return p != null && p.getColor() != color; // se a cor da peça dessa posição é diferente da cor da minah peça
	}
	
	
}
