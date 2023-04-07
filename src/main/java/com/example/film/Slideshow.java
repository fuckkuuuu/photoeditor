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


    public class Slideshow extends Application {
        private ImageView[] slides = new ImageView[3]; // 存储要播放的图片
        private int currentSlide = 0; // 当前图片的索引
        private Timeline timeline; // 定义时间轴对象
        private Stage stage; // 舞台对象

        @Override
        public void start(Stage primaryStage) {
            // 创建要播放的图片
            slides[0] = new ImageView(new Image("C:\\Users\\Forge\\Documents\\javahw\\film\\src\\image1.jpeg"));
            slides[1] = new ImageView(new Image("C:\\Users\\Forge\\Documents\\javahw\\film\\src\\image2.jpeg"));
            slides[2] = new ImageView(new Image("C:\\Users\\Forge\\Documents\\javahw\\film\\src\\image3.png"));

            // 创建堆叠面板
            StackPane root = new StackPane();
            root.setPadding(new Insets(10));
            root.setStyle("-fx-background-color: black;");
            root.getChildren().add(slides[0]);

            // 创建时间轴对象
            timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
                currentSlide = (currentSlide + 1) % slides.length;
                root.getChildren().set(0, slides[currentSlide]);
                resizeStage(slides[currentSlide].getImage().getWidth(), slides[currentSlide].getImage().getHeight());
            }));
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();

            // 创建场景
            stage = primaryStage;
            double minWidth = 1000;
            double minHeight = 800;
            double maxWidth = 1400;
            double maxHeight = 1000;
            Scene scene;
            scene = new Scene(root, minWidth, minHeight);
            primaryStage.setScene(scene);
            primaryStage.setMinWidth(minWidth);
            primaryStage.setMinHeight(minHeight);
            primaryStage.setMaxWidth(maxWidth);
            primaryStage.setMaxHeight(maxHeight);
            primaryStage.setTitle("Slideshow");
            primaryStage.show();
            // 创建按钮容器
            HBox buttonBox = new HBox();
            buttonBox.setAlignment(Pos.BOTTOM_CENTER);
            buttonBox.setSpacing(10);
            buttonBox.setPadding(new Insets(0, 0, 20, 0));

            // 创建上一张按钮
            Button prevButton = new Button("Prev");
            prevButton.setOnAction(event -> {
                currentSlide = (currentSlide - 1 + slides.length) % slides.length;
                root.getChildren().set(0, slides[currentSlide]);
                resizeStage(slides[currentSlide].getImage().getWidth(), slides[currentSlide].getImage().getHeight());
            });

            // 创建下一张按钮
            Button nextButton = new Button("Next");
            nextButton.setOnAction(event -> {
                currentSlide = (currentSlide + 1) % slides.length;
                root.getChildren().set(0, slides[currentSlide]);
                resizeStage(slides[currentSlide].getImage().getWidth(), slides[currentSlide].getImage().getHeight());
            });
            Button pauseButton = new Button("Pause");
            pauseButton.setOnAction(event ->{
                timeline.pause();
            });
            Button playButton = new Button("Play");

            playButton.setOnAction(event -> {
                timeline.play();
            });
            // 将按钮添加到容器中
            buttonBox.getChildren().addAll(prevButton, nextButton,pauseButton,playButton);
            root.getChildren().add(buttonBox);
        }



        private void resizeStage(double imageWidth, double imageHeight) {
            // 调整窗口大小以适应当前图片的尺寸
            double stageWidth = Math.min(stage.getMinWidth(), imageWidth + stage.getWidth() - slides[currentSlide].getFitWidth());
            double stageHeight = Math.min(stage.getMinHeight(), imageHeight + stage.getHeight() - slides[currentSlide].getFitHeight());
            stage.setWidth(stageWidth);
            stage.setHeight(stageHeight);
        }

        public static void main(String[] args) {
            launch(args);
        }
    }