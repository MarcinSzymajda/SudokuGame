package com.example.javafxview;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.example.GameBuildFailException;
import org.example.MissingSaveException;
import org.example.ProblemWithFileException;

public class Menu implements Initializable {
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("bundle_pl_PL");

    public ResourceBundle getResourceBundle() {
        return resourceBundle;
    }

    public void setResourceBundle(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    @FXML
    public Button bpoziom3;
    @FXML
    public Button bpoziom1;
    @FXML
    public Button bpoziom2;
    @FXML
    public Button bwczytaj;
    @FXML
    public Button bwczytajdb;

    @FXML
    public Button eng;

    @FXML
    public Button pl;

    @FXML
    public Text langchoose;
    @FXML
    public Text aut1;
    @FXML
    public Text aut2;

    private Logger logger = Logger.getLogger(Menu.class);
    private String language = "pl_PL";


    private Authors auth = new Authors();

    static
    GameForm gameForm = null;

    public void setPoziom1() throws GameBuildFailException {
        openForm(Level.EASY);
        logger.info(getResourceBundle().getString("lvlChoose") + " 1");
    }

    public void setPoziom2() throws GameBuildFailException {
        openForm(Level.MEDIUM);
        logger.info(getResourceBundle().getString("lvlChoose") + " 2");
    }

    public void setPoziom3() throws GameBuildFailException {
        openForm(Level.HARD);
        logger.info(getResourceBundle().getString("lvlChoose") + " 3");
    }

    @FXML
    public void languageChange(String language) {
        setResourceBundle(ResourceBundle.getBundle("bundle_" + language));
        settingResources();

    }

    private void settingResources() {
        bpoziom1.setText(getResourceBundle().getString("Level") + " 1");
        bpoziom2.setText(getResourceBundle().getString("Level") + " 2");
        bpoziom3.setText(getResourceBundle().getString("Level") + " 3");
        bwczytaj.setText(getResourceBundle().getString("loadGame"));
        langchoose.setText(getResourceBundle().getString("langchoose"));
        aut1.setText(getResourceBundle().getString("author") + " 1: " + auth.getString("author1"));
        aut2.setText(getResourceBundle().getString("author") + " 2: " + auth.getString("author2"));
    }

    public void setEnglish() {
        language = "en_EN";
        languageChange(language);
    }

    public void setPolish() {
        language = "pl_PL";
        languageChange(language);
    }

    public void showStage() throws GameBuildFailException {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
            Scene scene = new Scene(root, 600, 500);
            stage.setTitle("SudokuGame");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            logger.error(resourceBundle.getString("loadGameError"));
            throw new GameBuildFailException(resourceBundle.getString("loadGameError"),e);
        }
    }

    public void openForm(Level level) throws GameBuildFailException {
        Stage stage = new Stage();
        prepareStage(stage);
        gameForm.changeLanguage(language);
        try {
            gameForm.printBoard(level);
            stage.show();
        } catch (GameBuildFailException e) {
            logger.error(resourceBundle.getString("loadGameError"));
        }

    }

    public GameForm prepareStage(Stage stage) throws GameBuildFailException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameForm.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root, 600, 500);
            stage.setTitle(getResourceBundle().getString("Game"));
            stage.setScene(scene);
            gameForm = loader.getController();
        } catch (IOException e) {
            logger.error(resourceBundle.getString("loadGameError"));
            throw new GameBuildFailException(resourceBundle.getString("loadGameError"),e);
        }
        return gameForm;
    }

    public void wczytajGre() throws GameBuildFailException, MissingSaveException {
        Stage stage = new Stage();
        prepareStage(stage);
        gameForm.changeLanguage(language);
        try {
            gameForm.wczytajGre();
        } catch (MissingSaveException e) {
            logger.error(resourceBundle.getString("lackOfSaveFile"));
            throw new MissingSaveException(resourceBundle.getString("lackOfSaveFile"),e);
        } catch (GameBuildFailException e) {
            logger.error(resourceBundle.getString("loadGameError"),e);
        }
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rscBundle) {
        settingResources();
    }

    public void wczytajDB() throws GameBuildFailException, ProblemWithFileException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameForm.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root, 600, 500);
            stage.setTitle(getResourceBundle().getString("Game"));
            stage.setScene(scene);
            gameForm = loader.getController();
        } catch (IOException e) {
            logger.error(resourceBundle.getString("loadGameError"));
            throw new GameBuildFailException(resourceBundle.getString("loadGameError"),e);
        }
        gameForm.changeLanguage(language);
        gameForm.wczytajDB();
        stage.show();
    }
}