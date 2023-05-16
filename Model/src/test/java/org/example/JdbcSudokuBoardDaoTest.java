package org.example;

import org.junit.jupiter.api.Test;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

public class JdbcSudokuBoardDaoTest {

   public void PrepareDB() throws ClassNotFoundException {

       String url = "commitTest.dtf";
       try (Connection con = DriverManager.getConnection("jdbc:sqlite:C:" + url)) {
           con.setAutoCommit(false);
           assertFalse(con.getAutoCommit());
           con.commit();

       } catch (SQLException e) {
           e.printStackTrace();
       }
    }


    @Test
    public void JdbcTest() throws Exception {
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard saveBoard = new SudokuBoard(solver);
        saveBoard.solveGame();
        SudokuBoard loadBoard;


        try(Dao<SudokuBoard> jdbc = factory.getDatabaseDao("saveTest.dtf")) {
        jdbc.write(saveBoard);
        loadBoard = jdbc.read();
        assertTrue(loadBoard.equals(saveBoard));
        assertFalse(loadBoard.hashCode() == saveBoard.hashCode());
        assertNotEquals(loadBoard.toString(),saveBoard.toString());


        } catch (SQLException e) {
            e.printStackTrace();
        }

        final SudokuBoard loadBoard2 = null;
        try(Dao<SudokuBoard> jdbc = factory.getDatabaseDao("saveExTest.dtf")) {
            assertDoesNotThrow(()->jdbc.write(saveBoard));
            assertThrows(NullPointerException.class,()->jdbc.write(loadBoard2));
        }

    }

}
