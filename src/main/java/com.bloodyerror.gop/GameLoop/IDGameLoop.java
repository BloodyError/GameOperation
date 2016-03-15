package com.bloodyerror.gop.GameLoop;


import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/*This class is responsible for the game running*/
public class IDGameLoop extends JPanel implements Runnable{

    private Thread thread;
    private Boolean running;

    private int fps; //Frame Per Second
    private int tps; // Tick Per Second

    private int width;
    private int height;

    public  Graphics2D graphics2D;
    private BufferedImage image;


    public static double currFPS = 60D;


    public IDGameLoop(int width,int height){
        this.width  =   width;
        this.height =   height;

        setPreferredSize(new Dimension(width, height));
        setFocusable(false);
        requestFocus();
    }

    @Override
    public void addNotify() {
        super.addNotify();

        if(thread==null){
            thread = new Thread(this);
            thread.start();
        }
    }

    @Override
    public void run() {
        /*INIT*/
        init();
        long    lastTime    =   System.nanoTime();
        /*System.nanoTime() = return the current Value*/
        double  nsPerTick   =   1000000000D / currFPS;
        int     frames      =   0;
        int     ticks       =   0;
        long    lastTimer   =   System.currentTimeMillis();
        /*System.currentTimeMillis() = Return the current time in milliSeconds*/
        double  deltaTime   =   0; //Speed our game If game will run slow

        while (running){
            long now = System.nanoTime();
            deltaTime += (now-lastTime)/nsPerTick;
            lastTime = now;
            boolean shouldRender = false;

            while (deltaTime >=1){
                ticks++;
                /*TICK + DeltaTime*/
                tick(deltaTime);//Update our game
                deltaTime -= 1;
                shouldRender = true;
            }
            if(shouldRender == true){
                frames++;
                /*RENDER*/
                render();
            }
            //Sleepy
            try {Thread.sleep(2);} catch (InterruptedException e) {e.printStackTrace();}

            if(System.currentTimeMillis() - lastTimer >= 1000){
                lastTimer += 1000;
                tps = frames;
                fps = ticks;

                frames = 0;
                ticks = 0;
            }
        }
    }

    /*Staring the Game*/
    public void init() {
        image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        graphics2D = (Graphics2D) image.getGraphics();
        running = true;
    }

    public void render() {
    }

    public void tick(double deltaTime) {
        graphics2D.clearRect(0, 0, width,height);
    }

    public void clear(){
        Graphics graphics = getGraphics();
        if (image!=null){
            graphics.drawImage(image,0,0,null);
        }
        graphics.dispose();
    }

}
