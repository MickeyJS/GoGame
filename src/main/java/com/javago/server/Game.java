package com.javago.server;

import java.awt.*;
import java.util.Arrays;


public class Game extends Thread {
    private int ID;
    private Thread thread;
    private Player player1 = null;
    private Player player2 = null;
    private boolean isFinished = false;
    private Player currentPlayer = null;
    private int boardSize;

    private Stone[][] previousBoard = null;
    private Stone[][] board = null;

    public Game(int ID, Player player, int boardSize) {
        this.ID = ID;
        this.player1 = player;
        this.boardSize = boardSize;
        this.initializeBoard();
        currentPlayer = player1;
    }

    public void addPlayer(Player player) {
        this.player2 = player;
    }

    public void run() {
        while (!isFinished) {

        }

        player1.resetPlayer();
        player2.resetPlayer();
    }

    public boolean hasTwoPlayers() {
        boolean twoPlayers = false;
        if (this.player1 != null && this.player2 != null) {
            twoPlayers = true;
        }

        return twoPlayers;
    }

    public Player getHost() {
        return this.player1;
    }

    public int getGameID() {
        return this.ID;
    }

    private void makeMove(Point stonePosition) {
        previousBoard = this.board.clone();
        int x = (int) stonePosition.getX();
        int y = (int) stonePosition.getY();
        if (checkMove(stonePosition)) {
            this.putStone(stonePosition);
            updateBoard(x,y);                   /// <-------TODO Moja metoda na zbijanie. Czy działa?
            this.changePlayer();
        } else {
           // currentPlayer.inform();
        	System.out.println("Zły ruch");
        }
    }

    private boolean checkMove(Point stonePosition) {
        int x = (int) stonePosition.getX();
        int y = (int) stonePosition.getY();

        //czy pole jest już zajęte
        if (board[x][y].getColor() != null)
            return false;

        //ko
        if (this.isKo(stonePosition)) {
            return false;
        }

        if (this.isSuicidal(stonePosition)) {
            return false;
        }

        return true;
    }

    private boolean isKo(Point stonePosition) {
        Stone[][] boardCopy = board.clone();
        this.putStone(stonePosition);
        if (Arrays.equals(previousBoard, board)) {
            board = boardCopy.clone();
            return true;
        }

        return false;
    }

    private boolean isSuicidal(Point stonePosition) {

        return false;
    }

    private void putStone(Point stonePosition) {
        int x = (int) stonePosition.getX();
        int y = (int) stonePosition.getY();
        board[x][y].setColor(currentPlayer.getStoneColor());
    }

    public void changePlayer() {
        if (currentPlayer.equals(player1))
            currentPlayer = player2;
        else
            currentPlayer = player1;
    }

    private void initializeBoard() {
        if (this.board == null) {
            this.board = new Stone[this.boardSize][this.boardSize];
        }

        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                board[i][j] = new Stone(new Point(i, j));
            }
        }
    }
    
    //--------------------------------------------------//
    /**
     * Metoda czyszcząca planszę z biało czarnych kamieni
     */
    private void clearBoard() {
    	
    	for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                board[i][j].setColor(null);
            } 
    	}
    }
    
    
    /**
     * Metoda sprawdzająca czy ruch jest w planszy
     */
    public boolean isInBoard(int x, int y){
        
        
    	 if (x >= 0 && y >= 0 && x < boardSize && y < boardSize)
    	 	{return true;
    	 	}
    	 
    	 else {
    		 return false;
    	 }
    	 
     }
    
    
    /**
     * 
     * Sprawdza czy jeden kamien ma wolne miejsce w sąsiedztwie
     */
    public boolean getLiberties(int x, int y) {
       
        previousBoard = this.board.clone();
      
        
      boolean  liberties = false;
        int[] dx = {-1,0,1,0}, dy = {0,-1,0,1};
        
        //Pętla sprawdza sąsiednie pola
        for (int i = 0; i < dx.length; i++){
        	int newX= x+dx[i];
        	int newY= y + dy[i];
        
        	
        	if (isInBoard(newX, newY) ) {
        		if (board[newX][newY].getColor() == null){
        		 liberties = true;
        		 break;
        		}
        	}
        	
        }
        return liberties;
    }


    /**
     * Metoda sprawdzająca czy wokoł kamienia jest inny kamien o tym samym kolorze
     */
    public boolean getEmptyNeighbor(int x, int y) {
        
        
        previousBoard = this.board.clone();
        
        Color beginningcol = currentPlayer.getStoneColor();
        
       boolean isThisColor = true;
        int[] dx = {-1,0,1,0}, dy = {0,-1,0,1};
        
        //Pętla sprawdza sąsiednie pola
        for (int i = 0; i < dx.length; i++){
        	int newX= x+dx[i];
        	int newY= y + dy[i];
        
        	if (isInBoard(newX, newY))  {
        		if (previousBoard[newX][newY].getColor() == beginningcol ){
        		isThisColor = false;
        		 break;
        		}
        	}
        	
        }
        return isThisColor;
    }
    
    /**
 	 * Zabija kamień i sprawdza jednocześnie czy istnieją inne 
 	 * 
 	 */
    private void kill(int x, int y) {
    
        Color color = board [x][y].getColor();
    	board[x][y].setColor(null);;
    	
    
    	if(isInBoard(x - 1, y) && board[x - 1][y].getColor() == color)
			kill(x - 1, y);	
		if(isInBoard(x, y - 1) && board[x][y - 1].getColor() == color)
			kill(x, y - 1);
		if(isInBoard(x+1, y - 1) && board[x + 1][y].getColor() == color)
			kill(x + 1, y);
		if(isInBoard(x, y + 1) && board[x][y + 1].getColor() == color)
			kill(x, y + 1);
}
    /**
   	 * Sprawdza czy kamień ma oddech, jeśli nie ma on, to sprawdza oddech całego łańcucha
   	 * @return	Prawdziwe jeśli ma oddech
   	 */
    private boolean hasLiberty(int x, int y, int [][]tab){
    	
    	if(board[x][y].getColor() == null)
    		return true;
    	if(tab[x][y] == 1)
    		return false;
    	tab[x][y] = 1;
    	boolean hasLibertie= false;
		if(isInBoard(x - 1, y))
			hasLibertie = hasLibertie || hasLiberty(x - 1, y, tab);
		if(isInBoard(x, y - 1))
			hasLibertie = hasLibertie || hasLiberty(x, y - 1, tab);
		if(isInBoard(x + 1, y))
			hasLibertie = hasLibertie || hasLiberty(x + 1, y, tab);
		if(isInBoard(x, y + 1))
			hasLibertie = hasLibertie || hasLiberty(x, y + 1, tab);
		
return hasLibertie;
    }
   
    /**
 	 * Sprawdza kamienie wokół i jeśli któryś wokół nie ma oddechu, to go zbija
 	 * 
 	 */
    private void tryToKill(int x, int y) {
		Color opponentColor = currentPlayer.getOpponentStoneColor();
		
		if(isInBoard(x - 1, y))
			if(board[x - 1][y].getColor() == opponentColor && !hasLiberty(x - 1, y, new int[boardSize][boardSize])) {
				kill(x - 1, y);
			}
		if(isInBoard(x, y - 1))
			if(board[x][y - 1].getColor() == opponentColor && !hasLiberty(x, y - 1, new int[boardSize][boardSize])) {
				kill(x, y - 1);
			}
		if(isInBoard(x + 1, y))
			if(board[x + 1][y].getColor() == opponentColor && !hasLiberty(x + 1, y, new int[boardSize][boardSize])) {
				kill(x + 1, y);
			}
		if(isInBoard(x, y + 1))
			if(board[x][y + 1].getColor() == opponentColor && !hasLiberty(x, y + 1, new int[boardSize][boardSize])) {
				kill(x, y + 1);
			}
		
}
    
    /**
	 * Sprawdza czy na planszy są jeszcze wolne miejsca
	 * @return	Prawdziwe jeśli nie ma już wolnego miejsca
	 */
	public boolean allPositionsOccupied() {
		boolean noFreePositions = true;
		
		for(int i = 0; i < boardSize; i++)
		{
			for(int j = 0; j < boardSize; j++)
			{
				if(board[i][j].getColor() != null) {
					noFreePositions = false;
				}
			}
		}
		return noFreePositions;
}
	 /**
		 * Sprawdza miejsce, gdzie postawiliśmy właśnie kamień i próbuje zabić wszystko wokół. Jeśli mu się uda, to usuwa kamień
		 * 
		 */
	private void updateBoard(int x, int y) {
		tryToKill(x, y);
		if(!hasLiberty(x, y, new int[boardSize][boardSize])) {
			board[x][y].setColor(null);
		}
}
		
}

