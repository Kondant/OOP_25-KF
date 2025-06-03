package pl.umcs.oop;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ImageHandler {
    private BufferedImage image;

    public void loadImage(String filepath) {
        try {
            image= ImageIO.read(new File(filepath));
        } catch (IOException e) {
            System.err.println("blad odczytu pliku: "+ e.getMessage());
        }
    }
    public void saveImage(String filepath){
        String format= filepath.substring(filepath.lastIndexOf('.')+1);
        try {
            ImageIO.write(image, "jpg", new File(filepath));
        } catch (IOException e) {
           System.err.println("blad zapisywania obrazu: "+e.getMessage());
        }
    }
    public void increaseBrightness(int value){//rozjesnianie pikseli
        for(int y=0;y<image.getHeight();y++){
            for(int x=0;x<image.getWidth();x++){
                int rgb =image.getRGB(x,y);
                Color color=new Color(rgb);
                int r=Math.clamp(color.getRed()+value,0,255);//zamiast if mozna dac math clamp i zwraca wartosc min lub max
                int g=Math.clamp(color.getGreen()+value,0,255);
                int b=Math.clamp(color.getBlue()+value,0,255);
                Color brighter=new Color(r,g,b);
                image.setRGB(x,y,brighter.getRGB());
            }
        }
    }
    public void increaseBrightnessThreaded(int value){
        int cores = Runtime.getRuntime().availableProcessors();
        Thread[] threads=new Thread[cores]; //klasa thread - watek
        System.out.println("rdzenie: "+cores);
        int totalSize=image.getHeight()*image.getWidth();
        int chunkSize=totalSize/cores;
        for(int i=0;i<cores;i++) {
            int start = i * chunkSize;
            int end = (i == cores - 1) ? totalSize : start + chunkSize;
            threads[i] = new Thread(() -> {
                for (int index = start; index < end; index++) {
                    int x = index % image.getWidth();
                    int y = index / image.getHeight();
                    int rgb = image.getRGB(x, y);
                    Color color = new Color(rgb);
                    int r = Math.clamp(color.getRed() + value, 0, 255);
                    int g = Math.clamp(color.getGreen() + value, 0, 255);
                    int b = Math.clamp(color.getBlue() + value, 0, 255);
                    Color brighter = new Color(r, g, b);
                    image.setRGB(x, y, brighter.getRGB());
                }
            });
        }
            for(Thread t: threads){
                t.start();
            }
            for(Thread t:threads){
                try {
                    t.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            }
            public void increaseBrightnessThreadPool(int values) {
                ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
                for (int y = 0; y < image.getHeight(); y++) {
                    final int row = y;
                    executor.execute(() -> {
                        for (int x = 0; x < image.getWidth(); x++) {
                            int rgb = image.getRGB(x, row);
                            Color color = new Color(rgb);
                            int r = Math.clamp(color.getRed() + values, 0, 255);
                            int g = Math.clamp(color.getGreen() + values, 0, 255);
                            int b = Math.clamp(color.getBlue() + values, 0, 255);
                            Color brighter = new Color(r, g, b);
                            image.setRGB(x, row, brighter.getRGB());
                        }
                    });
                }
                    try {
                        executor.awaitTermination(1, TimeUnit.MINUTES);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }


            }
        }


