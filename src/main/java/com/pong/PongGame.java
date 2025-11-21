package com.pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class PongGame extends JPanel implements MouseMotionListener {
    static int width = 640; // this is the amount of pixels to the right side of the screen
    static int height = 480; // this is the amount of pixels to the top of the screen.

    private int userMouseY;

    private Paddle aiPaddle;
    private Paddle playerPaddle;

    private int playerScore;
    private int aiScore;

    private Ball ball;

    private SlowDown slowZone;

    private Speedup speedZone1;
    private Speedup speedZone2;
    

    public PongGame() {
        // Paddle def
        aiPaddle = new Paddle(610, 240, 50, 9, Color.WHITE);
        playerPaddle = new Paddle (30, 240, 50, 9, Color.WHITE);

        // Label def
        JLabel pScore = new JLabel("0");
        JLabel aiScore = new JLabel("0");

        // Score Setup
        pScore.setBounds(280, 440, 20, 20);
        aiScore.setBounds(360, 440, 20, 20); 

        pScore.setVisible(true);
        aiScore.setVisible(true);

        // User Mouse Setup
        userMouseY = 0;
        addMouseMotionListener(this);

        // Ball Def
        ball = new Ball(width / 2, height / 2, 8, 5, Color.BLUE, 10);

        // Zone Def
        slowZone = new SlowDown(width/2 - 50, height/2 - 75, 150, 100);

        speedZone1 = new Speedup(width/2 - 50, height - 75 - 50, 75, 100);
        speedZone2 = new Speedup(width/2 - 50, 50, 75, 100);
    }

    // precondition: None
    // postcondition: returns playerScore
    public int getPlayerScore() {
        return playerScore;
    }

    // precondition: None
    // postcondition: returns aiScore
    public int getAiScore() {
        return aiScore;
    }

    //precondition: All visual components are initialized, non-null, objects 
    //postcondition: A frame of the game is drawn onto the screen.
    public void paintComponent(Graphics g) {
        // Clears screen
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);

        // Screen Setup
        g.setColor(Color.WHITE);
        g.drawString("The Score is User:" + playerScore + " vs Ai:" + aiScore, 240, 20);

        // Draw Objects
        aiPaddle.draw(g);
        playerPaddle.draw(g);

        slowZone.draw(g);

        speedZone1.draw(g);
        speedZone2.draw(g);

        ball.draw(g);
    }

    // precondition: all required visual components are intialized to non-null
    // values
    // postcondition: one frame of the game is "played"
    public void gameLogic() {
        // Ball Movement
        ball.moveBall();

        if (ball.getY() <= 0 || ball.getY() >= height - 20) {
            ball.reverseY();
        }

        // Player Paddle
        playerPaddle.moveY(userMouseY);
        
        if (playerPaddle.isTouching(ball)) {
           ball.reverseX();
        }

        // Ai Stuff
        aiPaddle.moveY(ball.getY());

        if (aiPaddle.isTouching(ball)) {
           ball.reverseX();
        }
        
        // Zone effects
        int signX = 1;
        int signY = 1;
        if (ball.getChangeX() < 0) signX = -1;
        if (ball.getChangeY() < 0) signY = -1;

        if (slowZone.isTouching(ball)) {
            ball.setChangeX(4.0 * signX);
            ball.setChangeY(2.5 * signY);
        } else if (speedZone1.isTouching(ball) || speedZone2.isTouching(ball)) {
            ball.setChangeX(25.0 * signX);
            ball.setChangeY(10 * signY);
        } else {
            ball.setChangeX(8.0 * signX);
            ball.setChangeY(5.0 * signY);
        }    

        pointScored();

    }

    // precondition: ball is a non-null object that exists in the world
    // postcondition: determines if either ai or the player score needs to be
    // updated and re-sets the ball
    // the player scores if the ball moves off the right edge of the screen (640
    // pixels) and the ai scores
    // if the ball goes off the left edge (0)
    public void pointScored() {
        boolean offscreen = false;

        if (ball.getX() <= 0) {
            aiScore++;
            offscreen = true;
        } else if (ball.getX() >= width) {
            playerScore++;
            offscreen = true;
        }

        if (offscreen) {
            ball.setX(width / 2);
            ball.setY(height / 2);

            // Randomize direction
            if (Math.random() > 0.5) ball.reverseX();
            if (Math.random() > 0.5) ball.reverseY();
        }
    }

    // you do not need to edit the below methods, but please do not remove them as
    // they are required for the program to run.
    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        userMouseY = e.getY();
    }

}
