package com.example.javafxview;

import java.util.Random;
import org.example.SudokuBoard;


public enum Level {
    EASY(10),
    MEDIUM(20),
    HARD(30);

    final int removedFields;

    Level(int removedFields) {
        this.removedFields = removedFields;
    }

    public void removeFields(SudokuBoard sudokuBoard) {
        Random randomx = new Random();
        Random randomy = new Random();
        int buffer = this.removedFields;
        while (buffer >= 0) {
            int x = randomx.nextInt(9);
            int y = randomy.nextInt(9);
            if (sudokuBoard.getBoard(x, y).getFieldValue() != 0) {
                sudokuBoard.setBoard(x, y,0);
                buffer--;
            }
        }
    }
}
