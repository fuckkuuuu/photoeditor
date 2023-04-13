package com.example.demo2;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.*;

import java.io.File;
import java.io.IOException;

public class PictureFlowPane extends StackPane {
    public static final FlowPane flowPane = new FlowPane();
    public static TreeItem<File> file = new TreeItem<>();
    public static StackPane stackPane = new StackPane();
    public ScrollPane scrollPane = new ScrollPane();     //滚动面板
    public static int a=0;
    public void setFile(TreeItem<File> file) {
        PictureFlowPane.file = file;
    }

    PictureFlowPane() throws IOException {
        super();
        //setPrefSize(569.8,718);
        flowPane.setPadding(new Insets(10,20,20,20));
        flowPane.setOrientation(Orientation.HORIZONTAL);
        flowPane.setHgap(40);
        flowPane.setVgap(45);
        //this.getChildren().addAll(flowPane,MouseDraggedController.pane);

        flowPane.setStyle("-fx-background-color: rgb(248 248 255)");
        flowPane.setPrefSize(680,760);


        this.getChildren().add(flowPane);
        scrollPane.setContent(flowPane);
    }

    public void getPicture(TreeItem<File> file){
        setFile(file);

        flowPane.getChildren().clear();

        File[] fileList = file.getValue().listFiles();
        if(fileList.length>0) {
            for (File value : fileList) {
                if (!value.isDirectory()) {
                    String fileName = value.getName();
                    String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
                    //支持图片的格式
                    // 程序能够显示的图片格式包括:.JPG、.JPEG、.GIF、.PNG、和.BMP。
                    if (suffix.equals("jpg")||suffix.equals("JPG")||suffix.equals("png")||suffix.equals("BMP")
                            ||suffix.equals("GIF")||suffix.equals("JPEG")||suffix.equals("gif")) {
                        setPictureOnFlowPane("File:"+value.getAbsolutePath(),fileName);
                    }
                }
            }
        }

    }
    public static void reGetPicture(TreeItem<File> file){
        File[] fileList = file.getValue().listFiles();
        if(fileList.length>0) {
            for (File value : fileList) {
                if (!value.isDirectory()) {
                    String fileName = value.getName();
                    String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
                    //支持图片的格式
                    if (suffix.equals("jpg")||suffix.equals("JPG")||suffix.equals("png")||suffix.equals("BMP")
                            ||suffix.equals("GIF")||suffix.equals("JPEG")||suffix.equals("gif")) {
                        setPictureOnFlowPane("File:"+value.getAbsolutePath(),fileName);
                    }
                }
            }
        }

    }

    public static void setPictureOnFlowPane(String picturePath, String fileName){

        //图片缩略图加载进去
        ImageBoxLabel imageBoxLabel = new ImageBoxLabel(picturePath,fileName);
        flowPane.getChildren().add(imageBoxLabel.getImageLabel());


    }



}