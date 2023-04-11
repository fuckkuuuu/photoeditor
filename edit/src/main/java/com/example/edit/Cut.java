package com.example.edit;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.shape.Rectangle;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Cut {
    double startX;
    double startY;
    double width;
    double height;

    public Cut(EditController editController) {
        Rectangle rectangle = new ResizableRectangle(editController).rectangle;



        editController.save.setOnMouseClicked(event -> {
            startX = rectangle.getX();
            startY = rectangle.getY();
            width = rectangle.getWidth();
            height = rectangle.getHeight();
            // editController.showPane.getChildren().remove(rectangle);
            Node rec = new Rectangle(startX, startY, width, height);
            EditController.showPane.getChildren().remove(rectangle);

            EditController.showPane.setClip(rec);


            SnapshotParameters parameters = new SnapshotParameters();
            WritableImage writableImage = EditController.showPane.snapshot(parameters, null);

            EditController.imageView.setFitHeight(writableImage.getHeight());
            EditController.imageView.setFitWidth(writableImage.getWidth());
            EditController.imageView.setImage(writableImage);


            //居中
            EditController.showPane.setTranslateX((editController.scrollPaneImage.getMaxWidth() - EditController.imageView.getFitWidth()) / 2);
            EditController.showPane.setTranslateY((editController.scrollPaneImage.getMaxHeight() - EditController.imageView.getFitHeight()) / 2);



            File outputFile = new File("D:\\360MoveData\\Users\\86157\\Desktop\\edit\\outputFile\\2.png");
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null), "png", outputFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            EditController.showPane.setClip(null);

        });

    }

}



