package com.example.demo2;

import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.VBox;

import java.io.File;
import java.text.DecimalFormat;

public class FolderPane {
    public static VBox pane = new VBox();
    private static int numOfImage=0;
    private static double sizeOfImage=0;
    private static String sizeofimage;
    public static TreeItem<File> folder = new TreeItem<>();
    public static Label numLabel = new Label();
    public static Label sizeLabel = new Label();
    private static int v=0;    //判断图片总大小是否大于1MB


    public FolderPane(){
        pane.setPrefSize(20,20);
    }


    public void getInformationOfFolder(){
        pane.getChildren().clear();

        File[] fileList = folder.getValue().listFiles();
        if(fileList.length>0) {
            for (File value : fileList) {
                if (!value.isDirectory()) {
                    String fileName = value.getName();
                    String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
                    //支持图片的格式
                    // 程序能够显示的图片格式包括:.JPG、.JPEG、.GIF、.PNG、和.BMP。
                    if (suffix.equals("jpg")||suffix.equals("JPG")||suffix.equals("png")||suffix.equals("BMP")
                            ||suffix.equals("GIF")||suffix.equals("JPEG")||suffix.equals("gif")) {
                        numOfImage++;

                        File file = new File(value.getAbsolutePath());
                        System.out.println(file);
                        sizeOfImage +=file.length()/1024.0;
                    }
                }
            }
        }
        if(sizeOfImage>=1024){
            sizeOfImage/=1024;
            v=1;
            DecimalFormat format=new DecimalFormat("0.00");
            sizeofimage=format.format(sizeOfImage);
        }else {
            v=0;
            DecimalFormat format=new DecimalFormat("0.00");
            sizeofimage=format.format(sizeOfImage);
        }
        System.out.println(numOfImage);
        setInformationOfFolder();


    }

    public void setInformationOfFolder(){
        numLabel.setText("图片数量："+numOfImage);
        if(v==1){
            sizeLabel.setText("图片总大小："+sizeofimage+"MB");
        }else{
            sizeLabel.setText("图片总大小："+sizeofimage+"KB");
        }

        pane.getChildren().addAll(numLabel,sizeLabel);

        numOfImage=0;
        sizeOfImage=0;

    }


}