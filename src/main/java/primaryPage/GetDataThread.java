package primaryPage;

import java.io.File;
import java.text.Collator;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Locale;

import static primaryPage.PictureFlowPane.*;


//获取总文件大小和数目的线程


public class GetDataThread extends Thread {
    @Override
    public void run() {
        Iterator<File> iterator = fileArrayList.iterator();
        while (iterator.hasNext()) {
            File value = iterator.next();
            if (!value.isDirectory()) {
                String fileName = value.getName();
                String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
                //支持图片的格式
                if (suffix.equals("jpg") || suffix.equals("JPG") || suffix.equals("png") || suffix.equals("gif")
                        || suffix.equals("bmp") || suffix.equals("jpeg")) {
                    fileCount++;
                    sizeOfImage += value.length() / 1024.0;
                    //下方显示该目录的图片项目数
                } else {
                    iterator.remove();
                }
            } else {
                iterator.remove();
            }
        }
        if (sizeOfImage >= 1024) {
            sizeOfImage /= 1024;
            v = 1;
            DecimalFormat format = new DecimalFormat("0.00");
            sizeofimage = format.format(sizeOfImage);
        } else {
            v = 0;
            DecimalFormat format = new DecimalFormat("0.00");
            sizeofimage = format.format(sizeOfImage);
        }
        //至此fileArrayList中仅有图片
        switch (Function.sortType) {
            //按名称排列
            case 1 -> fileArrayList.sort((o1, o2) -> {
                if (o1.getName().length() > o2.getName().length()) {
                    return 1;
                } else if (o1.getName().length() < o2.getName().length()) {
                    return -1;
                }
                return o1.getName().compareTo(o2.getName());
            });
            //按修改时间
            case 2 -> fileArrayList.sort((o1, o2) -> {
                long lastModified1 = o1.lastModified();
                long lastModified2 = o2.lastModified();
                if (lastModified1 > lastModified2) {
                    return -1; //  o1 最近修改，排在 o2 前面
                } else if (lastModified1 < lastModified2) {
                    return 1; // o1 修改时间更早，排在 o2 后面
                }
                return 0;
            });
            //按文件大小
            case 3 -> fileArrayList.sort((o1, o2) -> {
                if (o1.length() > o2.length()) {
                    return 1; // o1 大于 o2，按降序排列
                } else if (o1.length() < o2.length()) {
                    return -1; // o1 小于 o2，按升序排列
                }
                return 0; // 文件大小相等，则按文件名进行字典序排序
            });
            //按类型
            case 4 -> fileArrayList.sort((o1, o2) -> {
                String o1Name = o1.getName();
                String o2Name = o2.getName();
                String o1suffix = o1Name.substring(o1Name.lastIndexOf(".") + 1).toLowerCase();
                String o2suffix = o2Name.substring(o2Name.lastIndexOf(".") + 1).toLowerCase();
                return o1suffix.compareTo(o2suffix);
            });
        }

    }
}
