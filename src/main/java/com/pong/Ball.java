package com.pong;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {
private int x;
private int y;
private double changeX;
private double changeY;
private int size;
private Color color; 

public Ball(int x, int y, int changeX, int changeY, Color color, int size) {
        this.x = x;
        this.y = y;
        this.changeX = changeX;
        this.changeY = changeY;
        this.color = color;
        this.size = size;
    }

//precondition: g is a non-null value
//postcondition: makes the ball on the screen
   public void draw(Graphics g){
    g.setColor(color);
    
    g.fillOval(x, y, size, size);

   }


 //precondition: None
 //postcondition: moves the ball by changeX and changeY
   public void moveBall() {
    x+= changeX;
    y+= changeY;
   }

   
 //precondition: None
 //postcondition: changes the y direction of the ball
   public void reverseY(){
    changeY*=-1;
   }
   
   //precondition: none
   //postcondition: changes the x direction of the ball
   public void reverseX(){
    changeX*=-1;
   }
   
   //precondition: top and bottom are non-null ints
   //postcondition: checks if the ball has gone outside of the top or the bottom of the screen
   public void bounceOffwalls(int top, int bottom){
    if(y>top) {
        reverseY();  
      }
      if(y<bottom) {
        reverseY();  
      }
   }


  //precondition: None
  //postcondition: returns the ball's y value
  public int getY() {
	  return y;
  }
  

  //precondition: None
  //postcondition: returns the ball's x value
  public int getX() {
	  return x;
  }
  

  //precondition: y is a non-null number
  //postcondition: sets the y value of the ball equal to the inputed y
  public void setY(int y) {
	  this.y=y;
  }

  
//precondition: x is a non-null number
//postcondition: sets the x value of the ball equal to the inputed x
  public void setX(int x) {
	  this.x=x;
  }


//precondition: none
//postcondition: gets the changeX of the ball equal to the inputed speed
public double getChangeX() {
	return changeX;
}


//precondition: none
//postcondition: gets the changeY of the ball equal to the inputed speed
public double getChangeY() {
	return changeY;
}


//precondition: x is a non-null number, and when increasing or decreasing the changeX don't change by a whole number. Use smaller decimals 
//postcondition: sets the changeX of the ball equal to the inputed x
public void setChangeX(double x) {
	this.changeX= x;
}


//precondition: y is a non-null number, and when increasing or decreasing the changey don't change by a whole number. Use smaller decimals 
//postcondition: sets the changeY of the ball equal to the inputed y
public void setChangey(double y) {
	this.changeY=x;
}
  

}
