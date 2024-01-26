package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch { // Partida de xadrez

	private int turn;  // vez
	private Color currentPlayer; // Jogador atual
	private Board board;
	private boolean check;
	
						   // peças no quadro
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();
	
	public ChessMatch() {
		board = new Board(8, 8); // crio um tabuleiro
		turn = 1;
		currentPlayer = Color.WHITE;
		initialSetup(); // e chamo para iniciar a partida
	}
	
	public int getTurn() {
		return turn;
	}
	
	public Color getCurrentPlayer() {
		return currentPlayer;
	}
	
	public boolean getCheck() {
		return check;
	}
	
	public ChessPiece[][] getPieces(){ // retorna uma matriz de peças de chadrex correspondetes a essa partida
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		
		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getColumns(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j);
			}
		}
		return mat;
	}
	
	public boolean[][] possibleMoves(ChessPosition sourcePosition) {
		Position position = sourcePosition.toPosition(); // converter essa posição de xadrez para uma posição normal
		validateSourcePosition(position);
		
		return board.piece(position).possibleMoves();
	}
	
				   // Executar movimento de xadrez  Posição de origem			  Posição do alvo	 
	public ChessPiece perfomChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateSourcePosition(source); // Validar posição de origem
		validateTargetPosition(source, target); // Validar posição de destino
		Piece capturedPiece = makeMove(source, target);
		
		if (testCheck(currentPlayer)) { // conferir se o jogador se colocou em check
			undoMove(source, target, capturedPiece);
			throw new ChessException("You can't put yourself in check");
		}
		
		check = (testCheck(opponent(currentPlayer))) ? true : false;
		
		nextTurn();
		return (ChessPiece)capturedPiece; //Peça capturada
	}
	
				//Fazer mover
	private Piece makeMove(Position source, Position target) {
		Piece p = board.removePiece(source); // tiro a peça de origem do tabuleiro
		Piece capturedPiece = board.removePiece(target); // tiro uma peça possivelmente capturada da posição de destino
		board.placePiece(p, target); // coloca na posição de destino a peça que estava na origem
		
		if (capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
		
		return capturedPiece;
	}
	
			  // desfazer movimento 	
	private void undoMove(Position source, Position target, Piece capturedPiece) {
		Piece p = board.removePiece(target); // tira aquela peça que você moveu para o destino
		board.placePiece(p, source); // vopu devolver para posição de origem
		
		if (capturedPiece != null) { // voltar peça do tabuleiro para a posição de destino
			board.placePiece(capturedPiece, target);
			capturedPieces.remove(capturedPiece); 
			piecesOnTheBoard.add(capturedPiece);
		}
	}
	
	private void validateSourcePosition(Position position) {
		if (!board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece on source position");
		}
		if(currentPlayer != ((ChessPiece)board.piece(position)).getColor()) { // se a cor for diferente do jogador atual é a peça do adversario
			throw new ChessException("The chosen piece is not yours.");
		}
		if (!board.piece(position).isThereAnyPossibleMove()) { // se não tiver nenhum movimento possivel
			throw new ChessException("There is no possible moves for the chosen piece");
		}
	}
	
	private void validateTargetPosition(Position source, Position target) {
		if (!board.piece(source).possibleMove(target)) { // se pra peça de prigem a posição de destino não é um movimento possivel
			throw new ChessException("The chosen piece can't move to target position");
		}
	}
	
	private void nextTurn() {
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
		// se curretPlay foir igual a White, entao ele vai ser o Black,  caso contrario ele vai ser White
	}
	
	private Color opponent(Color color) {
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	private ChessPiece king(Color color) {
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
	
		for (Piece p : list) {
			if (p instanceof King) {
				return (ChessPiece)p;
			}
		}
		throw new IllegalStateException("Tehere is no " + color + " king on the board"); // não deve acontecer, se ocorrer tem algum problema no sistema
	}
	
	private boolean testCheck(Color color) {
		Position kingPosition = king(color).getChessPosition().toPosition(); // pegar a posição do meu rei em formato de matriz
		List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList()); // peguei a lista de peças do oponente
	
		for (Piece p : opponentPieces) {
			boolean[][] mat = p.possibleMoves(); // movimentos possiveis da minha peça adversaria p
			
			if (mat[kingPosition.getRow()][kingPosition.getColumn()]) {
				return true;
			}
		}
		return false;
	}
	
			  // Coloque nova peça	
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition()); // convertendo para matriz, colcando a peça no tabuleiro
		piecesOnTheBoard.add(piece); // colca as pelças dentro da lista de peças no tabuleiro
	}
	
			  // configuração inicial	
	private void initialSetup() { // metodo responsavel por iniciar o tabuleiro e por as peças
		placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));

        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
	}
	
}
