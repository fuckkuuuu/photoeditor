package com.example.edit;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class EditController implements Initializable {
    private final Pane pane = new Pane();

    boolean isCutClicked = false;
    //   private int saveTimes=-1;


    static final Pane showPane = new Pane();
    final ScrollPane scrollPaneImage = new ScrollPane();

    static final IntegerProperty saveTimes = new SimpleIntegerProperty(-1);
    // final ObservableList<stickerData> stickersDataArrayList = FXCollections.observableArrayList();
    static double times = 1; //放大缩小倍率
    double n;
    static ImageView imageView;
    Image image;

    static class stickerData {
        double X, Y, height, width, times;
        String index;

        protected stickerData(String index, double times, double X, double Y, double height, double width) {
            this.index = index;
            this.X = X;
            this.Y = Y;
            this.height = height;
            this.width = width;
            this.times = times;
        }
    }

    @FXML
    Button returnToPre;
    @FXML
    BorderPane borderPane;
    @FXML
    ScrollPane scrollPane;
    @FXML
    Button tietu;
    @FXML
    Button back;
    @FXML
    Button next;
    @FXML
    Button cut;
    @FXML
    Button save;
    @FXML
    Pane rootPane;


    //ImageView draggedImage;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        int MAX_WIDTH = 1000;
        int MAX_HEIGHT = 650;
        n = 1;
        scrollPaneImage.setMaxSize(MAX_WIDTH, MAX_HEIGHT);

        File picture = new File("D:\\360MoveData\\Users\\86157\\Desktop\\a.png");
        image = new Image(picture.toURI().toString());


        while (image.getHeight() * n > MAX_HEIGHT || image.getWidth() * n > MAX_WIDTH) {
            n = n - 0.1;
        }
        imageView = new ImageView(image);
        imageView.setFitWidth(image.getWidth() * n);
        imageView.setFitHeight(image.getHeight() * n);

        imageView.setOnScroll(eventScroll -> {


            double deltaY = eventScroll.getDeltaY();
            double v = deltaY / Math.abs(deltaY) / 1000 * 99 + 1;
            times = times * v;


            imageView.setFitWidth(imageView.getFitWidth() * v);
            imageView.setFitHeight(imageView.getFitHeight() * v);
            for (Node node : showPane.getChildren()) {
                if (node.getClass().equals(ImageView.class) && !node.equals(imageView)) {
                    ImageView imageViewTemp = (ImageView) node;
                    imageViewTemp.setFitWidth(imageViewTemp.getFitWidth() * v);
                    imageViewTemp.setFitHeight(imageViewTemp.getFitHeight() * v);

                    imageViewTemp.setX(imageViewTemp.getX() * v);
                    imageViewTemp.setY(imageViewTemp.getY() * v);
                }
            }
        });

        showPane.getChildren().add(imageView);


        pane.setPrefSize(MAX_WIDTH, MAX_HEIGHT);
        pane.setOnScroll(eventScroll -> {

            double deltaY = eventScroll.getDeltaY();
            double v = deltaY / Math.abs(deltaY) / 1000 * 99 + 1;


            pane.setPrefWidth(pane.getWidth() * v);
            pane.setPrefHeight(pane.getHeight() * v);
        });

        pane.getChildren().add(showPane);


        scrollPaneImage.setContent(pane);


//        ColorAdjust colorAdjust = new ColorAdjust();
//        colorAdjust.setBrightness(-1);
//        showPane.setEffect(colorAdjust);

        borderPane.setCenter(scrollPaneImage);

        showPane.setTranslateX((scrollPaneImage.getMaxWidth() - imageView.getFitWidth()) / 2);
        showPane.setTranslateY((scrollPaneImage.getMaxHeight() - imageView.getFitHeight()) / 2);


        returnToPre.setOnMouseClicked(event -> {
            Stage stage = (Stage) returnToPre.getScene().getWindow();
            stage.close();
            new DataClear();
        });

        new BackAndNext(this);


        tietu.setOnMouseClicked(event -> new Sticker(this));




        cut.setOnMouseClicked(event -> {
            if (isCutClicked) {
                showPane.getChildren().remove(showPane.lookup("#" + "rec"));
                isCutClicked = false;
            } else {
                new Cut(this);
                isCutClicked = true;
            }

        });


    }
}