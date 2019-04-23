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
	private int boxHeight;
	//private ActiveTetromino activeTetronimo;
	@Override
	public boolean isValid(Board board) {
		Rectangle r = board.getActiveTetromino().getBoundingBox(); //get box of active tetronimo
		boxHeight = r.getMaxY() - r.getMinY();  //Height of the bounding box
		stepsToVacantPos = board.getHeight() - r.getMinY() + 1; //How far down the floor is

		Board ghostBoard = new Board(board);  //creating copy of current board (no overwriting)
		ActiveTetromino ghostPiece = board.getActiveTetromino();
		ghostBoard.setActiveTetromino(ghostPiece); //getting the OG active tetromino and setting as the active one in new board

		Rectangle ghostBox = r.translate(0,-stepsToVacantPos); // pushing a new ghostbox down to the bottom of new board
		if(vacantPos(ghostBox, ghostBoard)){ //check if that position is vacant
			return true;
		}

		return false;
	}

	public boolean vacantPos(Rectangle ghostBox, Board ghostBoard){

		for(int rowInBox = ghostBox.getMinY(); rowInBox <= ghostBox.getMaxY(); rowInBox++){  //iterate through every row in box
			for(int cellInRow = ghostBox.getMinX(); cellInRow <= ghostBox.getMaxX(); cellInRow++){ //and every cell in that row
				if(ghostBoard.getTetrominoAt(rowInBox,cellInRow) != null){ //if a tetromino exists in that cell
					ghostBox = ghostBox.translate(0,1); //move the ghost box up by one
					stepsToVacantPos--;
					vacantPos(ghostBox, ghostBoard); //rinse and repeat
				}
			}
		}
		return true; //If there is no interfering existing part of a Tetromino already placed where the ghostBox is;
					 //then it is vacant

	}

	@Override
	public Board apply(Board board) {

		if(this.isValid(board)){
			ActiveTetromino activeTetromino = board.getActiveTetromino();
			if(activeTetromino != null) {
				activeTetromino = activeTetromino.translate(0, stepsToVacantPos); //moves active tet to the vacant pos
				board.setActiveTetromino(activeTetromino);
			}
		}

		return board;


	}

	@Override
	public String toString() {
		return "drop";
	}
}
