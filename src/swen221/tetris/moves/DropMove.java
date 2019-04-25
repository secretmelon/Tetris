// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a SWEN221 assignment.
// You may not distribute it in any other way without permission.
package swen221.tetris.moves;

import swen221.tetris.logic.Board;
import swen221.tetris.logic.Rectangle;
import swen221.tetris.tetromino.ActiveTetromino;
import swen221.tetris.tetromino.Tetromino;

/**
 * Implements a "hard drop". That is, when the tetromino is immediately dropped
 * all the way down as far as it can go.
 *
 * @author David J. Pearce
 * @author Marco Servetto
 *
 */
public class DropMove implements Move {

	private int stepsToVacantPos;
	private int tempRow;
	private int startingMinY;
	//private ActiveTetromino activeTetronimo;
	@Override
	public boolean isValid(Board board) {

		///////////////////////////////////////////////////////////////////////////////////////////
		//HAYDEN's CODE

//		Rectangle r = board.getActiveTetromino().getBoundingBox(); //get box of active tetronimo
//
//		Board ghostBoard = new Board(board); //creating copy of current board (no overwriting)
//		ActiveTetromino ghostPiece = board.getActiveTetromino();
//		ghostBoard.setActiveTetromino(ghostPiece); //getting the OG active tetromino and setting as the active one in new board
//		Rectangle ghostBox = r.translate(0,-1);
//
//		int rowInBox = ghostBox.getMinY();
//		if((rowInBox < 0) || (ghostBox.getMaxX() > board.getWidth())) {
//			for (int cellInRow = ghostBox.getMinX(); cellInRow <= ghostBox.getMaxX(); cellInRow++) { //and every cell in that row
//				if (board.getPlacedTetrominoAt(rowInBox, cellInRow) != null) {
//					return false;
//				}
//			}
//			return true;
//		}
//		return false;

		///////////////////////////////////////////////////////////////////////////////////////////

		//DECLARING & INITIALISING COPIES
		Rectangle r = board.getActiveTetromino().getBoundingBox(); 			//Actual bounding box
		Board ghostBoard = new Board(board);  								//Board copy
		ActiveTetromino ghostPiece = board.getActiveTetromino(); 			//ghost Tetromino
		ghostBoard.setActiveTetromino(ghostPiece); 							//Set ghostPiece as Active Tetromino in ghostBoard
		Rectangle ghostBox = r;												//Copy actual bounding box


		//DELCARING VARIABLES
//		int bottomRow = ghostBox.getMinY();
//		int leftMostCol = ghostBox.getMinX();
//		int rightMostCol = ghostBox.getMaxX();
//		int numCellsInRow = rightMostCol - leftMostCol;

		int bottomRow = r.getMinY();
		int leftMostCol = r.getMinX();
		int rightMostCol = r.getMaxX();
		int numCellsInRow = rightMostCol - leftMostCol;

		int vacancyCount = 0;
//		Tetromino[] cells = ghostBoard.getCells();
		Tetromino[] cells = board.getCells();

		//Checking every cell in bottom row of bounding box
		for(int currentRow = bottomRow; currentRow > -1; currentRow--) {
			for (int cellInBox = leftMostCol; cellInBox <= rightMostCol; cellInBox++) {

				r.translate(0,bottomRow - currentRow);

				//ID OF CURRENT CELL & ID OF CELL DIRECTLY UNDER CURRENT CELL
				int id = (currentRow * board.getWidth()) + cellInBox;
				int idUnder = ((currentRow - 1) * board.getWidth()) + cellInBox;



				if (r.getMinY() == 0) { //if null then we have reached end of board
					stepsToVacantPos = board.getHeight() - (board.getHeight() - currentRow);
					return true;
				}

				//Checking if null cell in aT and tetromino existing under aT cell, or
				// if tetromino in aT cell and null cell under aT cell. If so, add to count. We will check this later
				else if (/*ghostPiece.isWithin(cellInBox, currentRow) && */ (cells[id] != null && cells[idUnder] == null) ||
						(cells[id] == null && cells[idUnder] != null)) {
						vacancyCount++;
						tempRow = currentRow;
				}

				//If there is a piece in the aT cell, and also a piece under the cell, then land on top
				else if (/*ghostPiece.isWithin(cellInBox, currentRow) && */ cells[id] != null && cells[idUnder] != null) {
					stepsToVacantPos = board.getHeight() - (board.getHeight() - currentRow);
					return true;
				}
			}
		}

		if (vacancyCount == numCellsInRow){
			stepsToVacantPos = ghostBoard.getHeight() - (ghostBoard.getHeight() - tempRow);
			return true;
		}

		return false;
	}


	@Override
	public Board apply(Board board) {

		// Create copy of the board to prevent modifying its previous state.
		board = new Board(board);
		// Apply translation for this move
		Tetromino tetromino = board.getActiveTetromino().translate(0, stepsToVacantPos);
		// Apply the move to the new board.
		board.setActiveTetromino(null);
		// Return updated version of board
		return board;
	}

	@Override
	public String toString() {
		return "drop";
	}
}
