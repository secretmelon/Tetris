// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a SWEN221 assignment.
// You may not distribute it in any other way without permission.
package swen221.tetris.moves;

import swen221.tetris.logic.Board;
import swen221.tetris.logic.Rectangle;
import swen221.tetris.tetromino.ActiveTetromino;
import swen221.tetris.tetromino.Tetromino;

/**
 * Provides some mechanisms which are common across all moves.
 *
 * @author David J. Pearce
 * @author Marco Servetto
 *
 */
public abstract class AbstractMove implements Move {

    private int stepsToVacantPos;
    private int tempRow;

	@Override
	public boolean isValid(Board board) {
        Tetromino t = board.getTetromino().translate(dx, dy);

        if(t.getBoundingBox().getMinX() < 0 || t.getBoundingBox().getMaxX() >= board.getWidth() || t.getBoundingBox().getMinY() < 0) {
            return false;
        }
    return true;
    }

	/**
	 * Apply a single step of this move to a given board, producing an updated
	 * board. This allows us to easily check every intermediate step for a
	 * multi-part move is valid.
	 *
	 * @param board
	 */
	protected abstract Board step(Board board);
}
