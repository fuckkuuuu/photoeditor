package com.example.edit;

import javafx.beans.binding.Bindings;
import javafx.scene.image.ImageView;

public class BackAndNext {
    public BackAndNext(EditController editController){
        editController.back.disableProperty().bind(EditController.saveTimes.lessThan(1));

        editController.back.setOnMouseClicked(event -> {
            EditController.saveTimes.set(EditController.saveTimes.get() - 1);
            EditController.stickerData data = Sticker.stickersDataArrayList.get(EditController.saveTimes.get());

            double timesWhenClicked = EditController.imageView.getFitHeight() / (editController.image.getHeight() * editController.n);
            double correctTimes = timesWhenClicked / data.times;
            ImageView draggedImage = (ImageView) editController.borderPane.lookup("#" + data.index);
            draggedImage.setX(data.X * correctTimes);
            draggedImage.setY(data.Y * correctTimes);
            draggedImage.setFitWidth(data.width * correctTimes);
            draggedImage.setFitHeight(data.height * correctTimes);

        });

        editController.next.disableProperty().bind(EditController.saveTimes.isEqualTo(Bindings.size(Sticker.stickersDataArrayList).subtract(1)));
        editController.next.setOnMouseClicked(event -> {
            EditController.saveTimes.set(EditController.saveTimes.get() + 1);
            EditController.stickerData data = Sticker.stickersDataArrayList.get(EditController.saveTimes.get());
            double timesWhenClicked = EditController.imageView.getFitHeight() / (editController.image.getHeight() * editController.n);
            double correctTimes = timesWhenClicked / data.times;
            ImageView draggedImage = (ImageView) editController.borderPane.lookup("#" + data.index);
            draggedImage.setX(data.X * correctTimes);
            draggedImage.setY(data.Y * correctTimes);
            draggedImage.setFitWidth(data.width * correctTimes);
            draggedImage.setFitHeight(data.height * correctTimes);
        });
    }
}
