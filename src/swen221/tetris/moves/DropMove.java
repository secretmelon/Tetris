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


	public int getStepsToVacantPos
	public boolean isValid(Board board){
		return true;

	}

	@Override
	public Board apply(Board board) {

		// Create copy of the board to prevent modifying its previous state.
		board = new Board(board);
		// Apply translation for this move


		Tetromino tetromino = board.getActiveTetromino().translate(0, -1);




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
