package pong;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {
 double xVel,yVel,x,y;
 
 public Ball(){
	 x =350;
	 y= 250;
	 xVel = getRandomSpeed()*getRandomDirection();
	 yVel =getRandomSpeed()*getRandomDirection();
 }
 
 public double getRandomSpeed(){
	 return (Math.random() *3+2);
 }
 public double getRandomDirection(){
	 int rend = (int)(Math.random()*2);
	 if(rend == 1)
		 return 1;
	 else
		 return -1;
 }
 
 
 public void draw(Graphics g){
	 g.setColor(Color.white);
	 g.fillOval((int)x-1, (int)y-10, 20, 20);
 }
 
 public void checkPaddleCollistion(Paddle p1,Paddle p2){
	 
	 if(x<= 50){
		 if(y>p1.getY()&&y<=p1.getY()+80)
			 xVel =-xVel;
	 }
	 else if(x>=650){
		 if(y>=p2.getY()&&y<=p2.getY()+80)
			 xVel =-xVel;
	 }
 }
 
 public void move() {

	 
	 if(y< 10)
		 yVel =-yVel;
	 if(y>490)
		 yVel =-yVel;
	 x+= xVel;
	 y+= yVel;
	
}
 public int getX(){
	 return (int) x;
	 
 }
 public int getY(){
	 return (int) y;
 }
 
}
