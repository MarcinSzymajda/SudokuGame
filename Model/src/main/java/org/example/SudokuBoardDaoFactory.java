package org.example;

public class SudokuBoardDaoFactory {

    public Dao<SudokuBoard> getFileDao(String filename) {
            return new FileSudokuBoardDao(filename);
    }

    public Dao<SudokuBoard> getDatabaseDao(String fileName) {
        return new JdbcSudokuBoardDao(fileName);
    }
}
