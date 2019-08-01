package pong;
import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class Tennis extends Applet implements Runnable,KeyListener {
	final int WIDTH = 700 , HEIGHT = 500;
	Thread thread;
	HumanPaddle pl;
	AIPaddle p2;
	Ball bl;
	boolean gameStarted;
	
	Graphics gfx;
	Image img;
	public void init(){
		this.resize(WIDTH,HEIGHT);
		gameStarted = false;
		this.addKeyListener(this);
		pl= new HumanPaddle(1);
		bl= new Ball();
		p2 =new AIPaddle(2,bl);
		img = createImage(WIDTH,HEIGHT);
		gfx = img.getGraphics();
		thread = new Thread(this);
		thread.start();
	}
	
	public void paint(Graphics g){
		gfx.setColor(Color.BLACK);
		gfx.fillRect(0, 0, WIDTH, HEIGHT);
		if(bl.getX()<-10||bl.getX()>710){
			gfx.setColor(Color.red);
			gfx.drawString("Game Over", 350, 250);
		}else{
			pl.draw(gfx);
			bl.draw(gfx);
			p2.draw(gfx);
		}
		if(!gameStarted){
			gfx.setColor(Color.white);
			gfx.drawString("Tennis", 340, 100);
			gfx.drawString("Press Enter to Begin", 310, 130);
		}
		g.drawImage(img, 0, 0, this);
	}
	public void update (Graphics g){
		paint(g);
	}

	
	public void run() {
		for(;;){
			
			if(gameStarted){
				bl.move();
			pl.move();
			p2.move();
			
			bl.checkPaddleCollistion(pl, p2);
			}
			repaint();
			try{
				Thread.sleep(10);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}


	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()== KeyEvent.VK_UP){
			pl.setUpAccel(true);
		}else if(e.getKeyCode()==KeyEvent.VK_DOWN)
		{
			pl.setDownAccel(true);
		}else if(e.getKeyCode()== KeyEvent.VK_ENTER){
			gameStarted =true;
		}
		
	}


	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()== KeyEvent.VK_UP){
			pl.setUpAccel(false);
		}else if(e.getKeyCode()==KeyEvent.VK_DOWN)
		{
			pl.setDownAccel(false);
		}
		
	}


	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
