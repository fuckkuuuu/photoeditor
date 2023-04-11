package com.example.edit;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Cursor;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Sticker {

    static int draggedImageID = 0;
    private double mouseClickedXLen;
    private double mouseClickedYLen;

    static final ObservableList<EditController.stickerData> stickersDataArrayList = FXCollections.observableArrayList();
    static boolean isStickerClicked = false;

    public Sticker(EditController editController){
        if (!isStickerClicked) {
            isStickerClicked = true;
            File file = new File("D:\\360MoveData\\Users\\86157\\Desktop\\edit\\patterns");
            File[] files = file.listFiles();

            GridPane content = new GridPane();
            content.setHgap(10);
            content.setHgap(10);
            int row = 0, col = 0;
            for (File filePatten : files) {
                //加载样式
                Image imagePatten = new Image(filePatten.toURI().toString());
                ImageView imageViewPattern = new ImageView(imagePatten);

                //缩放
                if(Math.max(imagePatten.getWidth(),imagePatten.getHeight())<=100){
                    imageViewPattern.setFitHeight(imagePatten.getHeight());
                    imageViewPattern.setFitWidth(imagePatten.getWidth());
                }
                else{
                    double time=Math.max(imagePatten.getWidth(),imagePatten.getHeight())/100;
                    imageViewPattern.setFitHeight(imagePatten.getHeight()/time);
                    imageViewPattern.setFitWidth(imagePatten.getWidth()/time);
                }


                Pane imageViewPane=new Pane(imageViewPattern);
                imageViewPane.setPrefSize(110,110);
                imageViewPane.setMaxSize(110,110);


                //居中
                imageViewPattern.setTranslateX((imageViewPane.getMaxWidth() - imageViewPattern.getFitWidth()) / 2);
                imageViewPattern.setTranslateY((imageViewPane.getMaxHeight() - imageViewPattern.getFitHeight()) / 2);


                //样式名称
                Label labelPatten = new Label(filePatten.getName());
                labelPatten.setMaxWidth(130);

                //放在vBox里组合起来
                VBox vBox = new VBox(imageViewPane, labelPatten);
                vBox.setMaxWidth(130);
                vBox.setMaxHeight(200);

                //放入样式区
                content.add(vBox, col, row);
                col++;
                if (col == 2) {
                    col = 0;
                    row++;
                }

                //实现点击可拖拉效果


                imageViewPattern.setOnMouseClicked(eventClicked -> {


                    ImageView draggedImage = new ImageView(imageViewPattern.getImage());


                    draggedImage.setId(Integer.toString(draggedImageID));
                    draggedImageID++;

                    draggedImage.setFitWidth(imageViewPattern.getFitWidth());
                    draggedImage.setFitHeight(imageViewPattern.getFitHeight());


                    EditController.showPane.getChildren().add(draggedImage);


                    draggedImage.setOnKeyPressed(keyEvent -> {
                        if (keyEvent.getCode() == KeyCode.DELETE || keyEvent.getCode() == KeyCode.BACK_SPACE) {
                            EditController.showPane.getChildren().remove(draggedImage);
                        }
                    });

                    //                 borderPane.getChildren().add(draggedImage);

                    //                  draggedImage.setX(eventPress.getSceneX() - mouseClickedXLen);
                    //                 draggedImage.setY(eventPress.getSceneY() - mouseClickedYLen);
                    //鼠标移入移出改变指针样式

                    draggedImage.setOnMouseEntered(eventMouseEntered -> {
                        draggedImage.requestFocus();
                        editController.rootPane.setCursor(Cursor.HAND);
                    });
                    draggedImage.setOnMouseExited(eventMouseExited -> editController.rootPane.setCursor(Cursor.DEFAULT));


                    draggedImage.setOnScroll(eventScroll -> {

                        eventScroll.consume();

                        double deltaY = eventScroll.getDeltaY();
                        double v = deltaY / Math.abs(deltaY) / 1000 * 99 + 1;

                        draggedImage.setFitWidth(draggedImage.getFitWidth() * v);
                        draggedImage.setFitHeight(draggedImage.getFitHeight() * v);

                    });

                    draggedImage.setOnMousePressed(eventMousePressed -> {

                        //
                        mouseClickedXLen = eventMousePressed.getSceneX() - draggedImage.getX();
                        mouseClickedYLen = eventMousePressed.getSceneY() - draggedImage.getY();
                        //获取鼠标点击的偏移量，我也不知道为什么直接用eventMousePressed.getX/Y获得的是相对整个scene的坐标
                        // 为什么不能像imageViewPattern
                    });


                    draggedImage.setOnMouseReleased(event1 -> {
                        double draggedImageX, draggedImageY, draggedImageHeight, draggedImageWidth;
                        draggedImageX = draggedImage.getX();
                        draggedImageY = draggedImage.getY();
                        draggedImageHeight = draggedImage.getFitHeight();
                        draggedImageWidth = draggedImage.getFitWidth();

                        //保存XY和width height

                        EditController.saveTimes.set(EditController.saveTimes.get() + 1);

                        for (int i = EditController.saveTimes.get(); i < stickersDataArrayList.size(); i++) {
                           stickersDataArrayList.remove(i);
                        }
                        stickersDataArrayList.add(EditController.saveTimes.get(), new EditController.stickerData(draggedImage.getId(), EditController.times, draggedImageX, draggedImageY, draggedImageHeight, draggedImageWidth));
                        if (stickersDataArrayList.size() > 10) {

                            stickersDataArrayList.remove(0);
                            EditController.saveTimes.set(EditController.saveTimes.get() - 1);
                        }

                    });


                    draggedImage.setOnMouseDragged(eventMouseDragged -> {
                        draggedImage.setX(eventMouseDragged.getSceneX() - mouseClickedXLen);
                        draggedImage.setY(eventMouseDragged.getSceneY() - mouseClickedYLen);
                    });

                });


            }
            //一次性添加进带滑动条的容器里
            editController.scrollPane.setContent(content);
        } else {
            editController.scrollPane.setContent(null);

            isStickerClicked = false;

            SnapshotParameters parameters = new SnapshotParameters();
            WritableImage writableImage = EditController.showPane.snapshot(parameters, null);
            EditController.imageView.setImage(writableImage);


            File outputFile = new File("D:\\360MoveData\\Users\\86157\\Desktop\\edit\\outputFile\\2.png");
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null), "png", outputFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            new DataClear();


        }

        editController.save.setOnMouseClicked(eventClicked -> {
            SnapshotParameters parameters = new SnapshotParameters();
            WritableImage writableImage = EditController.showPane.snapshot(parameters, null);
            EditController.imageView.setImage(writableImage);


            File outputFile = new File("D:\\360MoveData\\Users\\86157\\Desktop\\edit\\outputFile\\2.png");
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null), "png", outputFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        })
;}}
