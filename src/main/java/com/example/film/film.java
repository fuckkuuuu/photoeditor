package com.example.film;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class film extends Application {
    private ImageView[] slides = new ImageView[3]; // 存储要播放的图片
    private int currentSlide = 0; // 当前图片的索引
    private Timeline timeline; // 定义时间轴对象
    private Stage stage;

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;

        // 创建要播放的图片
        slides[0] = new ImageView(new Image("C:\\Users\\Forge\\Documents\\javahw\\film\\src\\image1.jpeg"));
        slides[1] = new ImageView(new Image("C:\\Users\\Forge\\Documents\\javahw\\film\\src\\image2.jpeg"));
        slides[2] = new ImageView(new Image("C:\\Users\\Forge\\Documents\\javahw\\film\\src\\image3.png"));

        // 创建堆叠面板
        StackPane root = new StackPane();
        root.setPadding(new Insets(10));
        root.getChildren().add(slides[0]);

        // 创建时间轴对象
        timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
            currentSlide = (currentSlide + 1) % slides.length;
            root.getChildren().set(0, slides[currentSlide]);
            resizeStage(slides[currentSlide].getFitWidth(), slides[currentSlide].getFitHeight());
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        // 创建场景
        double minWidth = 600;
        double minHeight = 800;
        double maxWidth = 1000;
        double maxHeight = 1200;
        Scene scene = new Scene(root, minWidth, minHeight);
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(minWidth);
        primaryStage.setMinHeight(minHeight);
        primaryStage.setMaxWidth(maxWidth);
        primaryStage.setMaxHeight(maxHeight);
        primaryStage.setTitle("Slideshow");
        primaryStage.show();


        // 创建按钮布局
        HBox buttonLayout = new HBox();
        buttonLayout.setAlignment(Pos.BOTTOM_CENTER);
        buttonLayout.setSpacing(10);
        buttonLayout.setPadding(new Insets(10));

        // 创建按钮
        Button prevButton = new Button("Prev");
        Button playButton = new Button("Play");
        Button pauseButton = new Button("Pause");
        Button nextButton = new Button("Next");

        // 添加按钮事件
        prevButton.setOnAction(event -> {
            currentSlide = (currentSlide - 1 + slides.length) % slides.length;
            root.getChildren().set(0, slides[currentSlide]);
            resizeStage(slides[currentSlide].getFitWidth(), slides[currentSlide].getFitHeight());
        });

        playButton.setOnAction(event -> timeline.play());

        pauseButton.setOnAction(event -> timeline.pause());

        nextButton.setOnAction(event -> {
            currentSlide = (currentSlide + 1) % slides.length;
            root.getChildren().set(0, slides[currentSlide]);
            resizeStage(slides[currentSlide].getFitWidth(), slides[currentSlide].getFitHeight());
        });

        // 将按钮添加到按钮布局中
        buttonLayout.getChildren().addAll(prevButton, playButton, pauseButton, nextButton);

        // 创建垂直布局
        VBox layout = new VBox();
        layout.getChildren().addAll(root, buttonLayout);

        // 将布局设置为场景的根节点
        scene.setRoot(layout);

        // 设置按钮布局在场景中的位置
        VBox.setMargin(buttonLayout, new Insets(scene.getHeight() - buttonLayout.getHeight(), 0, 0, 0));
    }

    private void resizeStage(double imageWidth, double imageHeight) {
        // 调整窗口大小以适应当前图片的尺寸
        double stageWidth = Math.max(stage.getMinWidth(), imageWidth + stage.getWidth() - slides[currentSlide].getFitWidth());
        double stageHeight = Math.max(stage.getMinHeight(), imageHeight + stage.getHeight() - slides[currentSlide].getFitHeight());
        stage.setWidth(stageWidth);
        stage.setHeight(stageHeight);
    }

    public static void main(String[] args) {
        launch(args);
    }
}