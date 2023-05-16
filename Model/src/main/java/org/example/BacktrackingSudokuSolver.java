package org.example;

import java.io.Serializable;
import java.util.Random;

public class BacktrackingSudokuSolver implements SudokuSolver, Serializable {

    private boolean[] previousNumbers = new boolean[9];

    private boolean checkBoxes(int i, int j,int number,SudokuBoard board) {
        boolean flag2 = true;
        for (int m = 0; m < 3; m++) {
            for (int n = 0; n < 3; n++) {
                if (board.getBoard((i / 3) * 3 + m, (j / 3) * 3 + n).getFieldValue() == number) {
                    flag2 = false;
                    break;
                }
            }
        }
        return flag2;
    }

        private boolean checkRows(int i,int number, SudokuBoard board) {
        boolean flag2 = true;
            for (int w = 0; w < 9; w++) {
                if (board.getBoard(i,w).getFieldValue() == number) {
                    flag2 = false;
                    break;
                }
            }
            return flag2;
        }

    private boolean checkColumns(int j,int number, SudokuBoard board) {
        boolean flag2 = true;
        for (int k = 0; k < 9; k++) {
            if (board.getBoard(k,j).getFieldValue() == number) {
                flag2 = false;
                break;
            }
        }
        return flag2;
    }

    public void previousNumbersArrayReset() {
        for (int p = 0; p < 9; p++) {
            previousNumbers[p] = false;
        }
    }

    public void solve(SudokuBoard board) {
        {
            for (int i = 0;i < 9;i++) {
                for (int j = 0;j < 9;j++) {
                    board.setBoard(i,j,0);
                }
            }
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    boolean flag = true;
                    while (flag) {
                        Random random = new Random();
                        int number = random.nextInt(9) + 1;
                        previousNumbers[number - 1] = true;
                        boolean result = true;
                        for (boolean condition : previousNumbers) {
                            if (!condition) {
                                result = false;
                                break;
                            }
                        }
                        if (result) {
                            if (j > 0) {
                                for (int z = 0;z < 9;z++) {
                                    board.setBoard(i,z,0);
                                }
                                j = 0;
                                previousNumbersArrayReset();
                            }

                            if (i > 0) {
                                    i--;
                                    j = 6;
                                    previousNumbersArrayReset();
                            }
                            if (i == 0) {
                                board.setBoard(i, j,number);

                            }
                        }
                        if (checkRows(i,number,board)
                                && checkColumns(j,number,board)
                                && checkBoxes(i,j,number,board)) {
                            board.setBoard(i,j,number);
                            flag = false;
                            previousNumbersArrayReset();
                        }
                    }
                }
            }
        }
    }
}
