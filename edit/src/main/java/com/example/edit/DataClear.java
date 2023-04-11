package com.example.edit;

public class DataClear {
    public DataClear(){
        EditController.showPane.getChildren().clear();
        EditController.showPane.getChildren().add(EditController.imageView);
        Sticker.stickersDataArrayList.clear();
        EditController.times = 1;
        Sticker.draggedImageID = 0;
        EditController.saveTimes.set(-1);
    }
}
