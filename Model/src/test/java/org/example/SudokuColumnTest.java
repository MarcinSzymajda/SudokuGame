package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuColumnTest {

    @Test
    public void verifyColumnTest() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.solveGame();
        board.print();
        for (int i = 0; i < 9 ; i++){
            for (int j = 0; j < 9; j++){

            }
        }
        board.print();
    }
    private SudokuSolver bc = new BacktrackingSudokuSolver();
    private SudokuBoard board = new SudokuBoard(bc);
    @Test
    public void toStringTest(){
        board.solveGame();

        assertNotEquals(board.getColumn(2).toString(),board.getColumn(2).toString());
    }
    @Test
    public void equalsTest(){
        board.solveGame();
        SudokuColumn a = board.getColumn(3);

        assertTrue(board.getColumn(2).equals(board.getColumn(2)));
        assertTrue(a.equals(a));
        assertFalse(board.getColumn(2).equals(6));
        assertFalse(board.getColumn(2).equals(board.getColumn(5)));
        assertFalse(board.getColumn(2).equals(null));

    }
    @Test
    public void hashCodeTest(){
        SudokuColumn s = board.getColumn(1);
        board.solveGame();
        assertNotEquals(s.hashCode(),board.getColumn(1).hashCode());
    }
    @Test
    public void cloneColumnTest() {
        SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(sudokuSolver);
        sudokuBoard.solveGame();
        SudokuColumn column =  sudokuBoard.getColumn(2);
        SudokuShape columnClone = (SudokuShape) column.clone();
        assertTrue(column.equals(columnClone));
        column.set(0,0);
        assertFalse(column.equals(columnClone));

    }
}
