package org.example;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class BacktrackingSudokuSolverTest {

    private SudokuSolver bc = new BacktrackingSudokuSolver();
    private SudokuBoard board = new SudokuBoard(bc);

    @Test
    public void fillBoardTest() { //sprawdzamy czy tablica w ogóle się wypełnia
        board.solveGame();
        boolean filled = true;
        for (int i = 0; i < 9;i++) {
            for (int j = 0;j < 9;j++) {
                if (board.getBoard(i,j).getFieldValue() == 0) {
                    filled = false;
                    break;
                }
            }
        }

        assertTrue(filled);
    }

    @Test
    public void differentBoards() {
        board.solveGame();
        int[][] temp = new int[9][9];
        int[][] temp2 = new int[9][9];
        for (int i = 0; i < 9;i++) {
            for (int j = 0;j < 9;j++) {
                temp[i][j] = board.getBoard(i,j).getFieldValue();
            }
        }
        board.solveGame();
        for (int i = 0; i < 9;i++) {
            for (int j = 0;j < 9;j++)
            {
                temp2[i][j] = board.getBoard(i,j).getFieldValue();
            }
        }
        assertFalse(Arrays.equals(temp,temp2));
    }
    @Test
    public void checkRows() {
        board.solveGame();
        boolean[] numbersColumn = new boolean[9];
        boolean result = true;
        for (int i = 0;i < 9;i++) {
            for (boolean nC : numbersColumn) {
                nC = false;
            }
            for (int j = 0; j < 9; j++) {
                numbersColumn[board.getBoard(i,j).getFieldValue() - 1] = true;
            }
            for (boolean condition : numbersColumn) {
                if (!condition) {
                    result = false;
                    break;
                }
            }
            if (!result) {
                break;
            }
        }
        assertTrue(result);

    }

    @Test
    public void checkColumns() {
        board.solveGame();
        boolean[] numbersColumn = new boolean[9];
        boolean result = true;
        for (int i = 0;i < 9;i++) {
            for (boolean nC : numbersColumn) {
                nC = false;
            }
            for (int j = 0; j < 9; j++) {
                numbersColumn[board.getBoard(j,i).getFieldValue() - 1] = true;
            }
            for (boolean condition : numbersColumn) {
                if (!condition) {
                    result = false;
                    break;
                }
            }
            if (!result) {
                break;
            }
        }
        assertTrue(result);
    }

    @Test
    public void checkBoxes() {
        board.solveGame();
        boolean error = true;
        boolean[] tab = new boolean[9];
        for (int i = 0; i < 9;i+=3) {
            for (int j = 0; j < 9;j++) {
                for (boolean box : tab) {
                    box = false;
                }
                for (int m = 0; m < 3; m++) {
                    for (int n = 0; n < 3; n++) {
                        tab[board.getBoard((i / 3) * 3 + m,(j / 3) * 3 + n).getFieldValue() - 1] = true;
                    }
                }
                for (boolean box : tab) {
                    if (!box) {
                        error = false;
                        break;
                    }
                }
            }
        }
        assertTrue(error);

    }
}


