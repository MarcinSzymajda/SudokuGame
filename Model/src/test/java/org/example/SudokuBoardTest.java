package org.example;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


class SudokuBoardTest {

    private SudokuSolver bc = new BacktrackingSudokuSolver();
    private SudokuBoard board = new SudokuBoard(bc);
    @Test
    public void fillBoardTest() { //sprawdzamy czy tablica w ogóle się wypełnia
        board.solveGame();
        boolean filled = true;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board.getBoard(i, j).getFieldValue() == 0) {
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
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                temp[i][j] = board.getBoard(i, j).getFieldValue();
            }
        }
        board.solveGame();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                temp2[i][j] = board.getBoard(i, j).getFieldValue();
            }
        }
        assertFalse(Arrays.equals(temp, temp2));
    }

    @Test
    public void checkRows() {
        board.solveGame();
        boolean[] numbersColumn = new boolean[9];
        boolean result = true;
        for (int i = 0; i < 9; i++) {
            for (boolean nC : numbersColumn) {
                nC = false;
            }
            for (int j = 0; j < 9; j++) {
                numbersColumn[board.getBoard(i, j).getFieldValue() - 1] = true;
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
        for (int i = 0; i < 9; i++) {
            for (boolean nC : numbersColumn) {
                nC = false;
            }
            for (int j = 0; j < 9; j++) {
                numbersColumn[board.getBoard(j, i).getFieldValue() - 1] = true;
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
        SudokuBoard board2 = new SudokuBoard(bc);
        board2.setBoard(0,0,9);
        board2.setBoard(0,1,9);
        board2.print();

        SudokuBoard board3 = new SudokuBoard(bc);
        board3.setBoard(0,0,9);
        board3.setBoard(1,0,9);
        board3.print();

        SudokuBoard board4 = new SudokuBoard(bc);
        board4.setBoard(0,0,9);
        board4.setBoard(1,1,9);
        board4.print();

    }

    @Test
    public void getBoxTest() {
        boolean flag = true;

        board.solveGame();
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                int t = 0;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (board.getBox(x, y).get(t) != board.getBoard((x / 3) * 3 + i, (y / 3) * 3 + j).getFieldValue()) {
                            flag = false;
                        }
                        t++;
                    }
                }
            }
            assertTrue(flag);
        }

    }

    @Test
    public void hashCodeTest() {
        SudokuBoard board2 = new SudokuBoard(bc);
        SudokuBoard xy = new SudokuBoard(bc);
        BacktrackingSudokuSolver z = new BacktrackingSudokuSolver();
        SudokuBoard board3 = new SudokuBoard(z);

        assertEquals(board.equals(board3), true);
        assertEquals(board.equals(board2), true);
        board.solveGame();
        board3.solveGame();
        assertEquals(board.equals(board3), false);
        xy.solveGame();
        assertEquals(board.equals(xy), false);

        assertFalse(xy.equals(board) && board.equals(xy));
        assertFalse(xy.hashCode() == board.hashCode());
        assertTrue(board.equals(board));
        int t1 = board.hashCode();
        board.setBoard(5,6,0);
        assertFalse(t1 == board.hashCode());
        t1 = board.hashCode();
        board.setBoard(5,6,1);
        assertFalse(t1== board.hashCode());


        assertFalse(board.equals(null));
        assertTrue(board.equals(board));
        assertFalse(board.equals(xy));
        assertEquals(board.equals(bc),false);

    }

    @Test
    public void toStringTest() {
        board.solveGame();
       assertEquals(board.toString(), board.toString());
    }


        @Test
        public void getColumnTest () {

            board.solveGame();
            boolean flag = true;
            for (int x = 0; x < 9; x++) {
                for (int y = 0; y < 9; y++) {
                    if (board.getColumn(x).get(y) != board.getBoard(y, x).getFieldValue()) {
                        flag = false;
                    }
                }
            }
            assertTrue(flag);
        }


        @Test
        public void getRowTest () {

            board.solveGame();
            boolean flag = true;
            for (int x = 0; x < 9; x++) {
                for (int y = 0; y < 9; y++) {
                    if (board.getRow(x).get(y) != board.getBoard(x, y).getFieldValue()) {
                        flag = false;
                    }
                }
            }
            assertTrue(flag);
        }
        @Test
        public void cloneTest(){
        SudokuSolver sudokuSolver= new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(sudokuSolver);
        sudokuBoard.solveGame();
        SudokuBoard sudokuBoardCopy = (SudokuBoard) sudokuBoard.clone();
        assertTrue(sudokuBoard.equals(sudokuBoardCopy));
        sudokuBoard.solveGame();
        sudokuBoardCopy.setBoard(0,0,0);
        assertFalse(sudokuBoard.equals(sudokuBoardCopy));
        }
    }
