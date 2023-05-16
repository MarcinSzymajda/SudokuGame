package org.example;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


public class JdbcSudokuBoardDao implements Dao<SudokuBoard> {
    private static String filename;
    private static Connection con;

    public JdbcSudokuBoardDao(String filename) {
        this.filename = filename;
    }


    public static Connection connect() {
        try {

            String url = "jdbc:sqlite:C:" + filename;
            con = DriverManager.getConnection(url);
            con.setAutoCommit(false);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return con;
    }


    @Override
    public SudokuBoard read() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(solver);


        String sql2 = "SELECT xpos,ypos,content "
                + "FROM sudokuContent";
        try (Connection readCon = connect();
             Statement stmt = readCon.createStatement();
             ResultSet rs = stmt.executeQuery(sql2)) {

            for (int i = 0; i < 82; i++) {
                sudokuBoard.setBoard(rs.getInt("xpos"),
                        rs.getInt("ypos"), rs.getInt("content"));
                rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sudokuBoard;
    }

    @Override
    public void write(SudokuBoard obj)  {

        String prepareTable = "DELETE FROM sudokuIndex";
        String prepareTable1 = "DELETE FROM sudokuContent";

        String table1 = "CREATE TABLE IF NOT EXISTS sudokuIndex (\n"
                + "\tid INTEGER NOT NULL\n"
                +
                ");";
        String table2 = "CREATE TABLE IF NOT EXISTS sudokuContent (\n"
                + "\tboardId INTEGER NOT NULL,\n"
                + "\txpos INTEGER NOT NULL,\n"
                + "\typos INTEGER NOT NULL,\n"
                + "\tcontent INTEGER NOT NULL,\n"
                + "    FOREIGN KEY (boardId)\n"
                + " "
                + "       REFERENCES sudokuIndex (id)\n"
                + ");";

        try (Connection con = connect();Statement stmt = con.createStatement()) {
            stmt.execute(table1);
            con.commit();
            stmt.execute(table2);
            con.commit();
            stmt.executeUpdate(prepareTable);
            con.commit();
            stmt.executeUpdate(prepareTable1);
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql1 = "INSERT OR REPLACE INTO sudokuIndex VALUES(1)";
        try (Connection con = connect();Statement stmt = con.createStatement()) {
            stmt.execute(sql1);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        String sql3 = "INSERT OR REPLACE INTO sudokuContent(boardId,xpos,ypos,content) VALUES(?,?,?,?)";
        try (Connection con = connect();
             PreparedStatement prstmt = con.prepareStatement(sql3)) {

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    try {
                        prstmt.setInt(1,1);
                        prstmt.setInt(2, i);
                        prstmt.setInt(3, j);
                        prstmt.setInt(4, obj.getBoard(i,j).getFieldValue());
                        prstmt.executeUpdate();
                    } catch (SQLException e) {
                        try {
                            con.rollback();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }

                    }
                }
            }
            try {
                con.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    @Override
    public void saveBoards(List<SudokuBoard> list) throws ProblemWithFileException {

    }

    @Override
    public List<SudokuBoard> loadBoards() throws ProblemWithFileException {
        return null;
    }

    @Override
    public void close() throws Exception {

    }
}
