package application;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		
		ChessMatch chessMatch = new ChessMatch();
		
		while(true) {
			try {
				UI.clearScreen();
				
				UI.printMatch(chessMatch);
				System.out.println();
				System.out.print("Source: ");
				ChessPosition source = UI.readChessPosition(sc);
				
				boolean[][] possibleMoves = chessMatch.possibleMoves(source);
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces(), possibleMoves); // imprimir o tabuleiro e colorido as posições possíveis
				
				
				System.out.println();
				System.out.print("Target: ");
				ChessPosition target = UI.readChessPosition(sc);
				
				ChessPiece captuPiece = chessMatch.perfomChessMove(source, target);
			}
			catch (ChessException exception) {
				System.out.println(exception.getMessage());
				sc.nextLine();
			}
			catch (InputMismatchException exception) {
				System.out.println(exception.getMessage());
				sc.nextLine();
			}
			
		}
	}

}
