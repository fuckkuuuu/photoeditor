package com.example.edit;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class EditPage {
    public EditPage(){


        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("edit.fxml"));
        Stage editStage=new Stage();

        editStage.setOnCloseRequest(event->{
            new DataClear();
        });

        editStage.setResizable(false);
        Scene scene;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        editStage.setScene(scene);
        editStage.show();

    }
}
