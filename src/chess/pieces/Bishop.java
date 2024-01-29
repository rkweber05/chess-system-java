package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Bishop extends ChessPiece { // Rook = torre

	public Bishop(Board board, Color color) {
		super(board, color);
	}
	
	@Override
	public String toString() {
		return "B";
	}

	@Override
	public boolean[][] possibleMoves(){
		
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()]; // esta matriz esta com todas as posições valendo falso
		
		Position p = new Position(0, 0);
		
		//northwest
		p.setValues(position.getRow() - 1, position.getColumn() - 1);
		
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { // enquanto estiver vaga a posição eu marco como verdadeira
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() - 1, p.getColumn() - 1);
		}
		
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)){ // se tem alguma peça adversário no final
			mat[p.getRow()][p.getColumn()] = true;
		}
 		
		//North East
		p.setValues(position.getRow() - 1, position.getColumn() + 1);
		
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { // enquanto estiver vaga a posição eu marco como verdadeira
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() - 1, p.getColumn() + 1);
		}
		
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)){
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//southeast
		p.setValues(position.getRow() + 1, position.getColumn() + 1);
		
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { // enquanto estiver vaga a posição eu marco como verdadeira
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() + 1, p.getColumn() + 1);
		}
		
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)){
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// south-west
		p.setValues(position.getRow() + 1, position.getColumn() - 1);
		
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { // enquanto estiver vaga a posição eu marco como verdadeira
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() + 1, p.getColumn() - 1);
		}
		
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)){ // se tem alguma peça adversário no final
			mat[p.getRow()][p.getColumn()] = true;
		}

		return mat;
			
	}
	
	
}
