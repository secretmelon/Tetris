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


	@Override
	public boolean isValid(Board board){
		return true;
	}

	@Override
	public Board apply(Board board) {

		// Create copy of the board to prevent modifying its previous state.
		board = new Board(board);
		// Apply translation for this move

		boolean dropping = true;

		while(dropping) {
			Tetromino t = board.getActiveTetromino(); //current placement of aT
			if (board.canPlaceTetromino(t)) {  // if current placement is valid
				ActiveTetromino newPlacement = board.getActiveTetromino().translate(0, -1); // newPlacement = move down
				board.setActiveTetromino(newPlacement); //set newplacement as active tet
			} else {
				ActiveTetromino newPlacement = board.getActiveTetromino().translate(0, 1);
				board.placeTetromino(newPlacement);
				board.setActiveTetromino(null);
				dropping = false;
			}
		}

//		Tetromino tetromino = board.getActiveTetromino().translate(0, -1);
//
//		// Apply the move to the new board.
//		board.setActiveTetromino(null);
//		// Return updated version of board
		return board;
	}

	@Override
	public String toString() {
		return "drop";
	}
}
