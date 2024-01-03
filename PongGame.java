package Pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

//This class must extend JPanel so we can use paintComponent and implement MouseMotionListener to track mouse
public class PongGame extends JPanel implements MouseMotionListener {

    //Constants for window width and height, in case we want to change the width/height later
    static final int WINDOW_WIDTH = 640, WINDOW_HEIGHT = 480;
    private Ball gameBall;
    private Paddle userPaddle, pcPaddle;
    private int userScore, pcScore;

    private int userMouseY; //to track the user's mouse position
    private int bounceCount; //to count number of ball bounces between paddles

    /**
     * Standard constructor for a PongGame
     */
    public PongGame() {

        //Make the ball and paddles
        gameBall = new Ball(300, 200, 3, 3, 3, Color.YELLOW, 10);
        userPaddle = new Paddle(10, 200, 75, 3, Color.BLUE);
        pcPaddle = new Paddle(610, 200, 75, 3, Color.RED);

        //Set instance variables to zero to start
        userMouseY = 0;
        userScore = 0; pcScore = 0;
        bounceCount = 0;

        //listen for motion events on this object
        addMouseMotionListener(this);

    }

    /**
     * resets the game to start a new round
     */
    public void reset(){

        //pause for a second
        try{
            Thread.sleep(1000);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        gameBall.setX(300);
        gameBall.setY(200);
        gameBall.setCx(3);
        gameBall.setCy(3);
        gameBall.setSpeed(3);
        bounceCount = 0;

    }

    /**
     * Updates and draws all the graphics on the screen
     */
    public void paintComponent(Graphics g) {

        //draw the background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        //draw the ball
        gameBall.paint(g);

        //draw the paddles
        userPaddle.paint(g);
        pcPaddle.paint(g);

        //update score
        g.setColor(Color.WHITE);
        //the drawString method needs a String to print, and a location to print it at.
        g.drawString("Score - User [ " + userScore + " ]   PC [ " + pcScore + " ]", 250, 20   );

    }

    /**
     * Called once each frame to handle essential game operations
     */
    public void gameLogic() {

        //move the ball one frame
        gameBall.moveBall();

        //edge check/bounce
        gameBall.bounceOffEdges(0, WINDOW_HEIGHT);

        //move the paddle towards where the mouse is
        userPaddle.moveTowards(userMouseY);

        //move the PC paddle towards the ball y position
        pcPaddle.moveTowards(gameBall.getY());

        //check if ball collides with either paddle
        if(pcPaddle.checkCollision(gameBall) || userPaddle.checkCollision(gameBall)){
            //reverse ball if they collide
            gameBall.reverseX();
            //increase the bounce count
            bounceCount++;
        }

        //after 5 bounces
        if (bounceCount == 5){
            //reset counter
            bounceCount = 0;
            //increase ball speed
            gameBall.increaseSpeed();
        }

        //check if someone lost
        if(gameBall.getX() < 0){
            //player has lost
            pcScore++;
            reset();
        }
        else if(gameBall.getX() > WINDOW_WIDTH){
            //pc has lost
            userScore++;
            reset();
        }

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //Update saved mouse position on mouse move
        userMouseY = e.getY();

    }
}