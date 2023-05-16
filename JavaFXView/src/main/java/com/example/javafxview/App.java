package com.example.javafxview;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.GameBuildFailException;


public class App extends Application {

    @Override public void start(Stage stage) throws GameBuildFailException {

            Menu menu = new Menu();
            menu.showStage();

    }

    public static void main(String[] args) {
        launch();
    }
}