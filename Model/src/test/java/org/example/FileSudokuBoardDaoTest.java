package org.example;

import org.junit.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class FileSudokuBoardDaoTest {
    private final SudokuSolver solver = new BacktrackingSudokuSolver();
    private SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
    private SudokuBoard sudokuBoard = new SudokuBoard(solver);

    private Dao<SudokuBoard> fileSudokuBoardDao;
    private SudokuBoard sudokuBoardSecond;


    @Test
    public void writeReadTest() throws ProblemWithFileException {
        sudokuBoard.solveGame();
        fileSudokuBoardDao = factory.getFileDao("writeReadTest");
        fileSudokuBoardDao.write(sudokuBoard);
        sudokuBoardSecond = fileSudokuBoardDao.read();
        assertEquals(fileSudokuBoardDao.read(), fileSudokuBoardDao.read());

    }

    @Test(expected = ProblemWithFileException.class)
    public void readGBFExceptionTest() throws ProblemWithFileException {
        fileSudokuBoardDao = factory.getFileDao("readGBFExceptionTest");
        fileSudokuBoardDao.read();
    }

    @Test(expected = ProblemWithFileException.class)
    public void writeGBFExceptionTest() throws ProblemWithFileException{
        try {
                fileSudokuBoardDao = factory.getFileDao("?");
                fileSudokuBoardDao.write(sudokuBoard);
        } catch (ProblemWithFileException e) {
                throw new ProblemWithFileException("writeTest",e);
        }
    }
    @Test
    public void getFileDaoTest() {
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        assertNotNull(factory.getFileDao("getFileDaoTest"));
    }

    @Test
    public void saveLoadBoardTest() throws ProblemWithFileException {
            sudokuBoard.solveGame();
            sudokuBoardSecond = (SudokuBoard) sudokuBoard.clone();
            List<SudokuBoard> sudokuBoardList = new ArrayList<>();
            sudokuBoardList.add(sudokuBoard);
            sudokuBoardList.add(sudokuBoardSecond);
            try {
                fileSudokuBoardDao = factory.getFileDao("writeReadTest");
                fileSudokuBoardDao.saveBoards(sudokuBoardList);
                List<SudokuBoard> list = fileSudokuBoardDao.loadBoards();
                assertTrue(list.get(0).equals(list.get(1)));
                sudokuBoardSecond.setBoard(1, 1, 0);
                List<SudokuBoard> sudokuBoardList2 = new ArrayList<>();
                sudokuBoardList2.add(sudokuBoard);
                sudokuBoardList2.add(sudokuBoardSecond);
                fileSudokuBoardDao = factory.getFileDao("writeReadTest");
                fileSudokuBoardDao.saveBoards(sudokuBoardList2);
                List<SudokuBoard> list2 = fileSudokuBoardDao.loadBoards();
                assertFalse(list2.get(0).equals(list2.get(1)));
            } catch (ProblemWithFileException e) {
                throw new ProblemWithFileException("exception thrown correctly",e);
            }
        }
        @Test(expected = ProblemWithFileException.class)
        public void saveBoardsGBFETest() throws ProblemWithFileException{
            try {
                fileSudokuBoardDao = factory.getFileDao("?");
                sudokuBoard.solveGame();
                sudokuBoardSecond = (SudokuBoard) sudokuBoard.clone();
                List<SudokuBoard> sudokuBoardList = new ArrayList<>();
                sudokuBoardList.add(sudokuBoard);
                sudokuBoardList.add(sudokuBoardSecond);
                fileSudokuBoardDao.saveBoards(sudokuBoardList);
            } catch (ProblemWithFileException e) {
                throw new ProblemWithFileException("exception thrown correctly",e);

            }
        }

    @Test(expected = ProblemWithFileException.class)
    public void loadBoardsGBFETest() throws ProblemWithFileException{
        try {
            fileSudokuBoardDao = factory.getFileDao("?");
            fileSudokuBoardDao.loadBoards();
        } catch (ProblemWithFileException e) {
            throw new ProblemWithFileException("exception thrown correctly",e);
        }
    }
    }

