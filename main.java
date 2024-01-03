package Pong;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main {

    //declare and initialize the frame
    static JFrame f = new JFrame("Pong");

    public static void main(String[] args) {

        //make it so program exits on close button click
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //the size of the game will be 640x480, the size of the JFrame needs to be slightly larger
        f.setSize(655,510);

        //create a new PongGame and add it to the JFrame
        PongGame game = new PongGame();
        f.add(game);

        //show the frame/program window
        f.setVisible(true);

        //make a new swing Timer
        Timer timer = new Timer(33, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //game logic
                game.gameLogic();

                //repaint the screen
                game.repaint();


            }
        });

        //start the timer after it's been created
        timer.start();

    }
}