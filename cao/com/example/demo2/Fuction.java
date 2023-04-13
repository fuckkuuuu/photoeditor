package com.example.demo2;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;

public class Fuction{
    MenuBar menuBar=new MenuBar();

    Fuction(){
        Menu contact=new Menu("联系我们");
        MenuItem phone=new MenuItem("电话");
        MenuItem Wechat=new MenuItem("微信");
        Image image1=new Image("file:客服.png");
        ImageView imageView1=new ImageView(image1);
        imageView1.setFitHeight(16);
        imageView1.setFitWidth(16);
        contact.setGraphic(imageView1);
        phone.setOnAction(actionEvent->{
            Stage stage=new Stage();
            stage.setAlwaysOnTop(true);
            stage.initStyle(StageStyle.UTILITY);
            stage.setTitle("客服电话:");
            Text text1=new Text("客服1:12345657");
            Text text2=new Text("客服2:99999999");
            VBox vBox=new VBox(text1,text2);
            Scene scene=new Scene(vBox,200,50);
            stage.setScene(scene);
            stage.show();
        });
        Wechat.setOnAction(actionEvent->{
            Stage stage=new Stage();
            stage.setAlwaysOnTop(true);
            stage.initStyle(StageStyle.UTILITY);
            stage.setTitle("客服微信:");
            Text text1=new Text("客服1:kefu12345657");
            Text text2=new Text("客服2:kefu99999999");
            VBox vBox=new VBox(text1,text2);
            Scene scene=new Scene(vBox,200,50);
            stage.setScene(scene);
            stage.show();
        });
        contact.getItems().addAll(phone,Wechat);

        Menu setup=new Menu("设置");
        Image image2=new Image("file:设置.jpg");
        ImageView imageView2=new ImageView(image2);
        imageView2.setFitHeight(16);
        imageView2.setFitWidth(16);
        Menu size=new Menu("缩略图大小");
        MenuItem superbig=new MenuItem("超大图");
        MenuItem big=new MenuItem("大图");
        MenuItem mid=new MenuItem("中等大小");
        MenuItem small=new MenuItem("小图");
        size.getItems().addAll(superbig,big,mid,small);
        Menu sort=new Menu("排序方式");
        MenuItem date=new MenuItem("修改日期");
        MenuItem picturesize=new MenuItem("图片大小");
        MenuItem name=new MenuItem("名称");
        sort.getItems().addAll(date,picturesize,name);
        Menu style=new Menu("颜色风格");
        MenuItem white=new MenuItem("简约白(默认)");
        MenuItem black=new MenuItem("深沉黑");
        MenuItem blue=new MenuItem("时尚蓝");
        style.getItems().addAll(white,black,blue);
        MenuItem our=new MenuItem("关于我们");
        setup.getItems().addAll(size,sort,style,our);
        setup.setGraphic(imageView2);
        menuBar.getMenus().addAll(contact,setup);
    }
}
