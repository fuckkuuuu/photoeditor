package com.example.demo2;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ImageBoxLabel {
    private final String imageName;
    private final String imagePath;
    private final Labeled imageLabel = new Label();
    private final VBox vBox = new VBox();

    ImageBoxLabel(String imagePath, String imageName) {
        this.imagePath = imagePath;
        this.imageName = imageName;

        Image image = new Image(imagePath);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(100);
        imageView.setFitHeight(120);
        imageView.setPreserveRatio(true);//保持缩放比例
        imageLabel.setGraphic(imageView);
        Label nameOfImage = new Label();
        nameOfImage.setText(imageName);
        imageLabel.setAlignment(Pos.BASELINE_CENTER);
        imageLabel.setPrefSize(100, 100);

        vBox.getChildren().addAll(imageLabel, nameOfImage);

        //点击图片事件
        setOnMouseClickedOnImage();


        //右击图片事件
        ImageMenuItem item = new ImageMenuItem(imagePath);
        imageLabel.setContextMenu(item.getContextMenu());

    }

    private void setOnMouseClickedOnImage() {
        imageLabel.setOnMouseClicked(mouseEvent -> {

            if(Refresh.temptVBox!=null){
                Refresh.temptVBox.setStyle("-fx-background-color: rgb(255,255,255)");
                Refresh.temptVBox = vBox;
                vBox.setStyle("-fx-background-color: lightgray");
            }
            else {
                Refresh.temptVBox = vBox;
                vBox.setStyle("-fx-background-color: rgb(255,255,255)");
            }
                /*
                //创建一个新的播放幻灯片舞台
                if (mouseEvent.getButton() == MouseButton.PRIMARY && mouseEvent.getClickCount() == 2) {
                    //System.out.println("点击了两下");




                }
                else

                 */
            if(mouseEvent.getButton() == MouseButton.PRIMARY && mouseEvent.getClickCount() == 1){

                PictureFlowPane.a = 1;

                ImagePreviewLabel imagePreviewLabel = new ImagePreviewLabel(imagePath,300,300);
                imagePreviewLabel.addLabelOnPane(imagePreviewLabel.getImageLabeled());

                ImagePreviewInformation imagePreviewInformation = null;
                try {
                    imagePreviewInformation = new ImagePreviewInformation(imagePath,imageName);
                    PictureFlowPane.a =0;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                assert imagePreviewInformation != null;
                imagePreviewInformation.addInformationOfImageOnPane(imagePreviewInformation.getImageInformationLabel());
            }
        });
    }


    public Node getImageLabel() {
       return vBox;
   }
};