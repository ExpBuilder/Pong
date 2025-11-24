package com.pong;
import java.awt.Color;
import java.awt.Graphics;

public class Wall {
	private int x;
	private int y;
	private int height;
	private Color color;
	private int width;

	public Wall (int x, int y, int height, int width, Color color) {
		this.x = x;
        this.y = y;
        this.height = height;
        this.width=width;
        this.color = color;
	}
	
	//precondition: g is a non-null value
	//postcondition: draws the wall on the screen
	public void draw(Graphics g) {
		g.setColor(color);
		
		g.fillRect(x, y, width, height);
		
	}
	
	//precondition: none
	//postcondition: returns the walls y value
	public int getY() {
			  return y;
		  }
		  
	//precondition: none
	//postcondition: returns the walls x value
	public int getX() {
			  return x;
		  }
		  
	//precondition: ball is a non-null value
	//postcondition: returns true if the ball is touching one the paddle this is called on
	public boolean isTouching(Ball ball) {
		int rightX= x+width;
		int bottomY = y+height;
				 
				 if(x <= ball.getX() && ball.getX()<=rightX) {
					 System.out.println("x in bounds");
					 if(bottomY >= ball.getY() && ball.getY()>=y) {
						 
						 return true;
					 }
			 }
				return false;
			 }
}

