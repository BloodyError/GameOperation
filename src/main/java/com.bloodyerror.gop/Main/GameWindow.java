package com.bloodyerror.gop.Main;


import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame{

    private boolean fullScreen = false;
    private int fsm = 0;
    GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[1];

    public GameWindow(String title, int width, int height){
        setTitle(title);
        setSize(width,height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }
    private void setFullScreen(){
        switch (fsm){
            case 0:
                System.out.println("No Full Screen.");
                setUndecorated(false);
                break;
            case 1:
                setExtendedState(JFrame.MAXIMIZED_BOTH);
                setUndecorated(true);
                break;
            case 2:
                setUndecorated(true);
                device.setFullScreenWindow(this);
                break;
        }
    }

    public void setFullScreen(int fsnm){
        fullScreen = true;
        if (fsm <=2){
            this.fsm = fsnm;
            setFullScreen();
        }
        else{
            System.err.println("ERROR " + fsnm + "Is not supported!");
        }
    }
}
