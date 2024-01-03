package Pong;

import java.awt.*; //needed for Color

public class Paddle {

    //declare instance variables
    private int height, x, y, speed;
    private Color color;

    //Width of paddle will always be 15 for this app, you may change if you want
    static final int PADDLE_WIDTH = 15;

    /**
     * A paddle is a rectangle/block that can move up and down
     * @param x the x position to start drawing the paddle
     * @param y the y position to start drawing the paddle
     * @param height the paddle height
     * @param speed the amount the paddle may move per frame
     * @param color the paddle color
     */
    public Paddle(int x, int y, int height, int speed, Color color) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.speed = speed;
        this.color = color;
    }

    /**
     * Paints a rectangle on the screen
     * @param g graphics object passed from calling method
     */
    public void paint(Graphics g){

        //set brush color to whatever this paddle's color is
        g.setColor(color);
        //paint the rectangle, starting in the upper left corner at x, y
        g.fillRect(x, y, PADDLE_WIDTH, height);

    }

    /**
     * Move the paddle towards this y position until it's centered on it
     * @param moveToY - position the paddle is centered on
     */
    public void moveTowards(int moveToY) {

        //find the location of the center of the paddle
        int centerY = y + height / 2;

        //make sure the paddle is far enough away from the target moveToY position. If it's closer than the speed to where it should be, don't bother moving the paddle.
        if(Math.abs(centerY - moveToY) > speed){
            //if the center of the paddle is too far down
            if(centerY > moveToY){
                //move the paddle up by the speed
                y -= speed;
            }
            //if the center of the paddle is too far up
            if(centerY < moveToY){
                //move the paddle down by speed
                y += speed;
            }
        }

    }

    /**
     * Checks if this paddle is colliding with a ball
     * @param b the ball we're checking for a collision with
     * @return true if collision is detected
     */
    public boolean checkCollision(Ball b){

        int rightX = x + PADDLE_WIDTH;
        int bottomY = y + height;

        //check if the Ball is between the x values starting from one ball width to the left of the paddle to the right side of the paddle
        if(b.getX() > (x - b.getSize()) && b.getX() < rightX){

            //check if Ball is between the top and bottom y values of the paddle
            if(b.getY() > y && b.getY() < bottomY){
                //if we get here, we know the ball and the paddle have collided
                return true;
            }
        }

        //if we get here, one of the checks failed, and the ball has not collided
        return false;

    }



}