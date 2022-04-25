package com.semesterproject.tourplanner.bl;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FileHelper {
    public static void openDefault(String f){
        //reference to a file
        File file = new File(f);
        try {
            Desktop desktop = Desktop.getDesktop();
            //open a file using the default program for the file type
            //launches a default program to open the file
            desktop.open(file);
        } catch (IOException e) {
            LogHelper.error(e);
        }
    }

    public static void saveImage(Image image, String type, File file) throws IOException {
        ImageIO.write(ImageHelper.toBufferedImage(image), type, file);
    }

    public static void delFile(File file){
        //no inspection ResultOfMethodCallIgnored
        file.delete();
    }
}
