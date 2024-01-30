package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece { // Peão

	private ChessMatch chessMatch;
	
	public Pawn(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0, 0);
		
		if (getColor() == Color.WHITE) { 
			p.setValues(position.getRow() - 1, position.getColumn()); // uma posição acima
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { // se existir e tiver vazinha ele pode mover pra cima 
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			p.setValues(position.getRow() - 2, position.getColumn()); // uma posição acima
			Position p2 = new Position(position.getRow() - 1, position.getColumn());
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) {
				mat[p.getRow()][p.getColumn()] = true;
				// se todas as condições forem verdadeiras, o peão podera se mover duas casas
			}
			
			p.setValues(position.getRow() - 1, position.getColumn() - 1);
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) { // diagonal esquerda  
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			p.setValues(position.getRow() - 1, position.getColumn() + 1);
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) { // diagonal direita  
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			// #special move en passant white
			if (position.getRow() == 3) {
				Position left = new Position(position.getRow(), position.getColumn() - 1);
				// testando se a posição da esquerda existe, se tem uma peça que é oponente e se a peça esta vulneravel a tomar a jogada enPassant
				if (getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
					mat[left.getRow() - 1][left.getColumn()] = true; // posição possivel para o peão mover
				}
				
				Position right = new Position(position.getRow(), position.getColumn() + 1);
				// testando se a posição da esquerda existe, se tem uma peça que é oponente e se a peça esta vulneravel a tomar a jogada enPassant
				if (getBoard().positionExists(right) && isThereOpponentPiece(right) && getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
					mat[right.getRow() - 1][right.getColumn()] = true; // posição possivel para o peão mover
				}
			}
			
		}
		else {
			p.setValues(position.getRow() + 1, position.getColumn()); // uma posição acima
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { // se existir e tiver vazinha ele pode mover pra cima 
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			p.setValues(position.getRow() + 2, position.getColumn()); // uma posição acima
			Position p2 = new Position(position.getRow() + 1, position.getColumn());
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) {
				mat[p.getRow()][p.getColumn()] = true;
				// se todas as condições forem verdadeiras, o peão podera se mover duas casas
			}
			
			p.setValues(position.getRow() + 1, position.getColumn() - 1);
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) { // diagonal esquerda  
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			p.setValues(position.getRow() + 1, position.getColumn() + 1);
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) { // diagonal direita  
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			// #special move en passant black
			if (position.getRow() == 4) {
				Position left = new Position(position.getRow(), position.getColumn() - 1);
				// testando se a posição da esquerda existe, se tem uma peça que é oponente e se a peça esta vulneravel a tomar a jogada enPassant
				if (getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
					mat[left.getRow() + 1][left.getColumn()] = true; // posição possivel para o peão mover
				}
				
				Position right = new Position(position.getRow(), position.getColumn() + 1);
				// testando se a posição da esquerda existe, se tem uma peça que é oponente e se a peça esta vulneravel a tomar a jogada enPassant
				if (getBoard().positionExists(right) && isThereOpponentPiece(right) && getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
					mat[right.getRow() + 1][right.getColumn()] = true; // posição possivel para o peão mover
				}
			}
		}
		
		return mat;
	} 
	
	@Override
	public String toString() {
		return "P";
	}

	
	
}
