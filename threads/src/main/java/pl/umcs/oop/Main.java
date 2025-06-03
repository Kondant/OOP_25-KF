package pl.umcs.oop;


public class Main {
    public static void main(String[] args) {
        ImageHandler ih=new ImageHandler();
        ih.loadImage("C:\\Users\\student\\Desktop\\test.png");
        long start = System.currentTimeMillis();
        ih.increaseBrightness(0x77);
        long end=System.currentTimeMillis();
        ih.saveImage("C:\\Users\\student\\Desktop\\test.png");
        System.out.println("czas 1 watku: "+(end-start)+"ms");

        ih.loadImage("C:\\Users\\student\\Desktop\\test.png");
        long start2 = System.currentTimeMillis();
        ih.increaseBrightnessThreaded(0x77);
        long end2=System.currentTimeMillis();
        ih.saveImage("C:\\Users\\student\\Desktop\\test.png");
        System.out.println("czas 1 watku: "+(end2-start2)+"ms");

        ih.loadImage("C:\\Users\\student\\Desktop\\test.png");
        long start3 = System.currentTimeMillis();
        ih.increaseBrightnessThreadPool(0x77);
        long end3=System.currentTimeMillis();
        ih.saveImage("C:\\Users\\student\\Desktop\\test.png");
        System.out.println("czas wielu watkow: "+(end3-start3)+"ms");
    }
}