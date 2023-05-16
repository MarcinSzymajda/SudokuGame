package org.example;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class FileSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {

    private String filename;

    public FileSudokuBoardDao(String filename) {
        this.filename = filename + ".txt";
    }

    @Override
    public SudokuBoard read() throws  ProblemWithFileException {
    SudokuBoard s;
        try (FileInputStream fileInputStream = new FileInputStream(filename);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            s = (SudokuBoard) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new ProblemWithFileException("loadGameError",e);
        }
        return s;

    }

    @Override
    public void write(SudokuBoard obj) throws ProblemWithFileException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filename);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
             objectOutputStream.writeObject(obj);
        } catch (IOException e) {
            throw new ProblemWithFileException("buildGameError",e);
        }
    }



    @Override
    public void close() {

    }

    @Override
    public void saveBoards(List<SudokuBoard> list) throws ProblemWithFileException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(this.filename);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            for (int i = 0; i < list.size();i++) {
                objectOutputStream.writeObject(list.get(i));
            }
        } catch (IOException e) {
            throw new ProblemWithFileException("buildGameError", e);
        }
    }

    @Override
    public List<SudokuBoard> loadBoards() throws ProblemWithFileException {
        List<SudokuBoard> list = new ArrayList<>();
        try (FileInputStream fileInputStream = new FileInputStream(this.filename);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                while (true) {
                    list.add((SudokuBoard) objectInputStream.readObject());
                }
        } catch (EOFException g) {
            g.hashCode();
        } catch (IOException | ClassNotFoundException e) {
            throw new ProblemWithFileException("buildGameError", e);
        }
        return list;
    }
}
