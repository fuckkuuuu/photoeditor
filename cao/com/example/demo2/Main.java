package com.example.demo2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("图片管理系统");
        primaryStage.getIcons().add(new Image("file:图标1.jpg"));
        primaryStage.setMaximized(true);//能够最大化
        primaryStage.setResizable(true);//能够改变窗口大小

        BorderPane borderPane = new BorderPane();


        //附加功能栏
        Fuction fuction=new Fuction();
        borderPane.setTop(fuction.menuBar);

        //具体信息栏
        PicInfPane myPane = new PicInfPane();
        borderPane.setRight(myPane);

        //目录树
        FileTreeView myTreeView = new FileTreeView();
        borderPane.setLeft(myTreeView.rootpane);

        //图片缩放pane
        PictureFlowPane myFlowPane = new PictureFlowPane();
        borderPane.setCenter(myFlowPane.scrollPane);

        //文件夹信息菜单
        FolderPane myFolderPane = new FolderPane();
        borderPane.setBottom(myFolderPane.pane);


        Scene scene=new Scene(borderPane,1300,780);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}