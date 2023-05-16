package org.example;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;



public class SudokuBoard implements Serializable,Cloneable {

    private SudokuSolver sudokuSolver;

    private List<List<SudokuField>> board = Arrays.asList(new List[9]);

    private boolean fieldVerify(int x, int y) {
        AtomicBoolean flag = new AtomicBoolean(true);
        List.of(getRow(y), getColumn(x), getBox(x, y)).forEach(verfication -> {
            if (!verfication.verify()) {
                flag.set(false);
            }
        });
        return flag.get();

    }

    public SudokuBoard(SudokuSolver solver) {
        for (int i = 0; i < 9; i++) {
            board.set(i, Arrays.asList(new SudokuField[9]));
        }
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                board.get(x).set(y, new SudokuField());
            }
        }
        sudokuSolver = solver;
    }

    public SudokuBoard(SudokuBoard sudokuBoard) {
        for (int i = 0; i < 9; i++) {
            this.board.set(i, Arrays.asList(new SudokuField[9]));
        }
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                this.board.get(x).set(y, sudokuBoard.getBoard(x,y));
            }
        }
        this.sudokuSolver = sudokuBoard.getSudokuSolver();
    }

    public SudokuSolver getSudokuSolver() {
        return sudokuSolver;
    }

    public String print() {
        String text = "";
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                text += getBoard(i,j).getFieldValue() + "  ";
                if (j % 3 == 2) {
                    text += "  ";
                }
            }
            text += "\n";
            if (i % 3 == 2) {
                text += "\n";
            }
        }
        checkBoard();
        return text;
    }

    public SudokuField getBoard(int x, int y) {
        return board.get(x).get(y);

    }

    public void setBoard(int x, int y, int n) {
        board.get(x).set(y,new SudokuField());
        board.get(x).get(y).setFieldValue(n);
    }

    private boolean checkBoard() {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                if (!fieldVerify(x, y)) {
                    return false;
                }
            }
        }
        return true;
    }


    public void solveGame() {
        sudokuSolver.solve(this);

    }

    public SudokuRow getRow(int y) {
        SudokuRow rw = new SudokuRow();
        for (int z = 0; z < 9; z++) {
            rw.set(z, getBoard(y, z).getFieldValue());
        }
        return rw;
    }

    public SudokuColumn getColumn(int x) {
        SudokuColumn cl = new SudokuColumn();
        for (int y = 0; y < 9; y++) {
            cl.set(y, getBoard(y, x).getFieldValue());
        }
        return cl;
    }

    public SudokuBox getBox(int x, int y) {
        SudokuBox box = new SudokuBox();
        int i = 0;
        for (int m = 0; m < 3; m++) {
            for (int n = 0; n < 3; n++) {
                box.set(i, getBoard((x / 3) * 3 + m, (y / 3) * 3 + n).getFieldValue());
                i++;
            }
        }
        return box;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("board", board)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SudokuBoard that = (SudokuBoard) o;

        return new EqualsBuilder().append(board, that.board).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(sudokuSolver).append(board).toHashCode();
    }

    @Override
    public Object clone() {
        return new SudokuBoard(this);
    }
}

