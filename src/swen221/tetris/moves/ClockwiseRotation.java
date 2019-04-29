// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a SWEN221 assignment.
// You may not distribute it in any other way without permission.
package swen221.tetris.moves;

import swen221.tetris.logic.Board;
import swen221.tetris.logic.Rectangle;
import swen221.tetris.tetromino.ActiveTetromino;
import swen221.tetris.tetromino.Tetromino;


/**
 * Implements a rotation move which is either clockwise or anti-clockwise.
 *
 * @author David J. Pearce
 * @author Marco Servetto
 *
 */
public class ClockwiseRotation extends AbstractMove implements Move {

	@Override
	public boolean isValid(Board board){

		Board b = new Board(board);
		ActiveTetromino aT = b.getActiveTetromino();
		Rectangle rotatedR = aT.getBoundingBox().rotateClockwise();


		int left = rotatedR.getMinX();
		int right = rotatedR.getMaxX();
		int top = rotatedR.getMaxY();
		int bottom = rotatedR.getMinY();

		Tetromino[] cells = board.getCells();
		Tetromino[] rotatedCells = b.getCells();

		int vacancyCount = 0;
		int cellCount = 0;

		for(int row = top; row <= bottom; row--){
			for(int cell = left; cell <=right; cell++){
				cellCount++;
				int actualRotated = (row*board.getWidth()) + cell;
				if( (rotatedCells[actualRotated] == null && cells[actualRotated] != null) ||
						(rotatedCells[actualRotated] != null && cells[actualRotated] == null)){
					vacancyCount++;
				}
			}
		}

		if(vacancyCount == cellCount){
			return true;
		}

		return false;
	}


	@Override
	public Board apply(Board board) {
		// Create copy of the board to prevent modifying its previous state.
		board = new Board(board);
		// Create a copy of this board which will be updated.
		ActiveTetromino tetromino = board.getActiveTetromino().rotate(1);
		// Apply the move to the new board, rather than to this board.
		board.setActiveTetromino(tetromino);
		// Return updated version of this board.
		return board;
	}

	@Override
	public Board step(Board board) {
		// Create copy of the board to prevent modifying its previous state.
		board = new Board(board);
		// Apply translation for this move
		ActiveTetromino tetromino = board.getActiveTetromino().rotate(1);
		// Apply the move to the new board.
		board.setActiveTetromino(tetromino);
		// Return updated version of board
		return board;

	}
}
