package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuBoxTest {
    @Test
    public void verifyBoxTest() {
        SudokuSolver bc = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(bc);
        board.solveGame();
        SudokuBox box;
        boolean flag = true;
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++)
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        box = board.getBox(x,y);
                        if(!box.verify()) {
                         flag = false;
                    }
                }
            }
        }
        assertTrue(flag);
    }
    private SudokuSolver bc = new BacktrackingSudokuSolver();
    private SudokuBoard board = new SudokuBoard(bc);
    @Test
    public void toStringTest(){
        board.solveGame();
        assertNotEquals(board.getBox(2,3).toString(),board.getBox(2,3).toString());
    }
    @Test
    public void equalsTest(){
        board.solveGame();
        SudokuBox a = board.getBox(3,2);

        assertTrue(board.getBox(2,2).equals(board.getBox(2,2)));
        assertTrue(a.equals(a));
        assertFalse(board.getBox(2,2).equals(6));
        assertFalse(board.getBox(2,2).equals(board.getBox(4,1)));
        assertFalse(board.getBox(2,2).equals(null));

    }
    @Test
    public void hashCodeTest(){
        SudokuBox s = board.getBox(1,1);
        board.solveGame();
        assertNotEquals(s.hashCode(),board.getBox(1,1).hashCode());
    }
    @Test
    public void cloneBoxTest() {
        SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(sudokuSolver);
        sudokuBoard.solveGame();
        SudokuBox box =  sudokuBoard.getBox(2,3);
        SudokuShape boxClone = (SudokuShape) box.clone();
        assertTrue(box.equals(boxClone));
        box.set(0,0);
        assertFalse(box.equals(boxClone));
    }
}
