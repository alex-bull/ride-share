package controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Database;


public class App extends Application {

    private static Database database;
    private static Stage primaryStage;


    public void start(Stage stage) throws Exception {
        database = new Database();
        primaryStage = stage;
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("mainMenu.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Welcome");
        primaryStage.setScene(scene);

        database.addUser(0, "Bob McRideshare");

        primaryStage.show();
    }

    public static Database getDatabase(){
        return database;
    }

    public static Stage getPrimaryStage(){
        return primaryStage;
    }


}
