package boardgame;

public class Board {
	private int rows;
	private int columns;
	private Piece[][] pieces;
	
	public Board(int rows, int columns) {
		if (rows < 1 || columns < 1)
			throw new BoardEsception("Erro ao criar o tabuleiro: Necess�rio informar ao menos uma coluna e uma linha.");
		this.rows = rows;
		this.columns = columns;
		this.pieces = new Piece[rows][columns];
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}
	
	public Piece piece(int row, int column) {
		if (!this.positionExists(row, column)) 
			throw new BoardEsception("A posi��o informada � inv�lida.");
		return this.pieces[row][column];
	}
	
	public Piece piece(Position position) {
		if (!this.positionExists(position)) 
			throw new BoardEsception("A posi��o informada � inv�lida.");
		return this.pieces[position.getRow()][position.getColumn()];
	}
	
	public void placePiece(Piece piece, Position position) {
		if (this.thereIsAPiece(position))
			throw new BoardEsception("J� existe uma pe�a na posi��o " + position);
		this.pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}
	
	public Piece removePiece(Position position) {
		if (!this.positionExists(position))
			throw new BoardEsception("A posi��o informada � inv�lida.");
		
		if (this.piece(position) == null)
			return null;
		
		Piece aux = this.piece(position);
		aux.position = null;
		this.pieces[position.getRow()][position.getColumn()] = null;
		return aux;
	}
	
	private boolean positionExists(int row, int column) {
		return row >= 0 && row < this.rows && column >= 0 && column < this.columns;
	}
	
	public boolean positionExists(Position position) {
		return this.positionExists(position.getRow(), position.getColumn());
	}
	
	public boolean thereIsAPiece(Position position) {
		if (!this.positionExists(position)) 
			throw new BoardEsception("A posi��o informada � inv�lida.");
		return this.piece(position) != null;
	}
}
