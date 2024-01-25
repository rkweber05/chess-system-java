package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece { // Rook = torre

	public Rook(Board board, Color color) {
		super(board, color);
	}
	
	@Override
	public String toString() {
		return "R";
	}

	@Override
	public boolean[][] possibleMoves(){
		
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()]; // esta matriz esta com todas as posições valendo falso
		
		Position p = new Position(0, 0);
		
		//above
		p.setValues(position.getRow() - 1, position.getColumn());
		
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { // enquanto estiver vaga a posição eu marco como verdadeira
			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() - 1);
		}
		
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)){ // se tem alguma peça adversário no final
			mat[p.getRow()][p.getColumn()] = true;
		}
 		
		//left
		p.setValues(position.getRow(), position.getColumn() - 1);
		
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { // enquanto estiver vaga a posição eu marco como verdadeira
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() - 1);
		}
		
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)){
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//right
		p.setValues(position.getRow(), position.getColumn() + 1);
		
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { // enquanto estiver vaga a posição eu marco como verdadeira
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() + 1);
		}
		
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)){
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// below
		p.setValues(position.getRow() + 1, position.getColumn());
		
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { // enquanto estiver vaga a posição eu marco como verdadeira
			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() + 1);
		}
		
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)){ // se tem alguma peça adversário no final
			mat[p.getRow()][p.getColumn()] = true;
		}

		return mat;
			
	}
	
	
}
