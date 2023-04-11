package com.example.edit;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage editStage) {
        Button button=new Button("edit");
        button.setOnMouseClicked(event -> new EditPage());
        Pane pane=new Pane(button);
        Scene scene=new Scene(pane);
        editStage.setScene(scene);
        editStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}