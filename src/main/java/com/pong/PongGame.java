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
    

    public PongGame() {
        aiPaddle = new Paddle(610, 240, 50, 9, Color.WHITE);

        JLabel pScore = new JLabel("0");
        JLabel aiScore = new JLabel("0");

        pScore.setBounds(280, 440, 20, 20);
        aiScore.setBounds(360, 440, 20, 20); 

        pScore.setVisible(true);
        aiScore.setVisible(true);

        userMouseY = 0;
        addMouseMotionListener(this);

        ball = new Ball(200, 200, 10, 3, Color.RED, 10);

        //create any other objects necessary to play the game.
        playerPaddle = new Paddle (30, 240, 50, 9, Color.WHITE);
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
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);

        g.setColor(Color.WHITE);
        g.drawString("The Score is User:" + playerScore + " vs Ai:" + aiScore, 240, 20);
        ball.draw(g);
        aiPaddle.draw(g);
        
        //call the "draw" function of any visual component you'd like to show up on the screen.
        playerPaddle.draw(g);
    }

    // precondition: all required visual components are intialized to non-null
    // values
    // postcondition: one frame of the game is "played"
    public void gameLogic() {
        // Ball Movement
        ball.moveBall();

        if (ball.getY() <= 0 || ball.getY() >= height - 40) {
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
