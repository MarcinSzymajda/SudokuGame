package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SudokuRowTest {
    private SudokuSolver bc = new BacktrackingSudokuSolver();
    private SudokuBoard board = new SudokuBoard(bc);
    @Test
    public void toStringTest(){
        board.solveGame();

        assertNotEquals(board.getRow(2).toString(),board.getRow(2).toString());
    }
    @Test
    public void equalsTest(){
        board.solveGame();
        SudokuRow a = board.getRow(3);

            assertTrue(board.getRow(2).equals(board.getRow(2)));
            assertTrue(a.equals(a));
            assertFalse(board.getRow(2).equals(6));
            assertFalse(board.getRow(2).equals(board.getRow(5)));
            assertFalse(board.getRow(2).equals(null));

    }
    @Test
    public void hashCodeTest(){
        SudokuRow s = board.getRow(1);
        board.solveGame();
        assertNotEquals(s.hashCode(),board.getRow(1).hashCode());
    }
    @Test
    public void cloneRowTest() {
        SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(sudokuSolver);
        sudokuBoard.solveGame();
        SudokuRow row =  sudokuBoard.getRow(2);
        SudokuShape rowClone = (SudokuShape) row.clone();
        assertTrue(row.equals(rowClone));
        row.set(0,0);
        assertFalse(row.equals(rowClone));

    }
}
