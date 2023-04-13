package com.example.demo2;
import javafx.scene.layout.VBox;

public class Refresh {
    public static VBox temptVBox = null;

    public static void refreshPane(){
        PictureFlowPane.flowPane.getChildren().clear();
        PictureFlowPane.reGetPicture(PictureFlowPane.file);

        PicInfPane.imagePane.getChildren().clear();
    }

}