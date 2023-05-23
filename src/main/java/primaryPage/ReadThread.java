package primaryPage;

import javafx.application.Platform;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;

import static primaryPage.PictureFlowPane.*;

//读取图片的线程
public class ReadThread extends Thread {

    public volatile boolean shouldStop = false;
    int num;
    int i;


    public ReadThread(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        File value;
        i = getI(num);
        for (int t = i - num; t < i; t++) {
            if (shouldStop) {
                return;
            }
            value = fileArrayList.get(t);
            String fileName = value.getName();
            ImageBoxButton imageBoxLabel = new ImageBoxButton("File:" + value.getAbsolutePath(), fileName);
            Platform.runLater(() -> {
                //添加时检测是否已经中断，即切换目录
                if (!shouldStop) {
                    Main.pictureFlowPane.flowPane.getChildren().add(imageBoxLabel.getImageLabel());
                }
            });
        }
    }
}