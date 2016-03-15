package com.bloodyerror.gop.Main;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class loadingImageFrom {

    public static BufferedImage loadingImageFrom(Class<?> classfile, String path){
        URL url = classfile.getResource(path);
        BufferedImage image = null;

        try{
            image = ImageIO.read(url);
        }catch (IOException e){
            e.printStackTrace();
        }
        return image;
    }
}
