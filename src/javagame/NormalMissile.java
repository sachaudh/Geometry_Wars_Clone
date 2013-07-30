package javagame;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class NormalMissile extends Missile {
	  
  Image image;
  private Vector2f direction;
  Input i;
  //private double angleToTurn;
  private boolean hitsWall = false;

  private long start;
  
  private float x1;
  private float y1;
  private float x2;
  private float y2;
  private float x3;
  private float y3;
  private float x4;
  private float y4;
  private float x5;
  private float y5;
  private float x6;
  private float y6;
  private float x7;
  private float y7;
  private float x8;
  private float y8;
  private float x9;
  private float y9;
  private float x10;
  private float y10;
  private float x11;
  private float y11;
  private float x12;
  private float y12;
  private float x13;
  private float y13;
  private float x14;
  private float y14;
  private float x15;
  private float y15;
  private float x16;
  private float y16;
  
  Random random = new Random();
  
  private ArrayList<Vector2f> vectors = new ArrayList<Vector2f>();
  
  public NormalMissile(float x, float y,Input i) {
	super(x,y,i);
	this.i = i;
	direction = new Vector2f(i.getMouseX() - x, i.getMouseY() - y);
	System.out.println(direction.getTheta());
	/*try {
		this.image = new Image("res/gfx/high/yellowshot2.png");
	} catch (SlickException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	angleToTurn = Math.toDegrees(-1 * Math.atan2(direction.x, direction.y));
	image.setRotation((float) angleToTurn);*/
  }
	  
  public void update(GameContainer gc, int delta)
			throws SlickException {
	if(!hitsWall ) {  
	  float h = 0.6f * delta;
      x += h * java.lang.Math.cos(java.lang.Math.toRadians(direction.getTheta()));
      y += h *java.lang.Math.sin(java.lang.Math.toRadians(direction.getTheta()));
      offScreen();
	}
	else {
	  explode(delta);
	}
  }
	  
  public void render(GameContainer gc, Graphics g)
			throws SlickException {
	if(!hitsWall) {
	  g.setColor(new Color(255,255,255));
      g.fillOval(x - (5/2), y - (5/2), 5, 5);
	  //image.draw(x - 13,y - 20,(float) 0.5);
    }
    else {
        if(400 <= System.currentTimeMillis() - this.start) {
           Game.removeMissile(this);
        }
        g.setColor(new Color(255,255,255));
        g.fillOval(x1, y1, 3, 3);
        g.fillOval(x2, y2, 3, 3);
        g.fillOval(x3, y3, 3, 3);
        g.fillOval(x4, y4, 3, 3);
        g.fillOval(x5, y5, 3, 3);
        g.fillOval(x6, y6, 3, 3);
        g.fillOval(x7, y7, 3, 3);
        g.fillOval(x8, y8, 3, 3);
        g.fillOval(x9, y9, 3, 3);
        g.fillOval(x10, y10, 3, 3);
        g.fillOval(x11, y11, 3, 3);
        g.fillOval(x12, y12, 3, 3);
        g.fillOval(x13, y13, 3, 3);
        g.fillOval(x14, y14, 3, 3);
        g.fillOval(x15, y15, 3, 3);
        g.fillOval(x16, y16, 3, 3);
      }
 }
	  
  public Rectangle getBounds() {
	//return new Rectangle((int)x - 13, (int)y - 20, image.getWidth(), image.getHeight());
	  return new Rectangle((int)x - (5/2),(int) y - (5/2), 5, 5);
  }
	    
  public void offScreen() {
    if(y <= -10 || y >= 595 || x <= -10 || x >= 795) {
	  //Game.removeMissile(this);
      hitsWall = true;
      this.start = System.currentTimeMillis();
  	  this.x1 = x + 9;
      this.y1 = y + 9;
      this.x2 = x + 10;
      this.y2 = y + 10;
      this.x3 = x + 10;
      this.y3 = y + 10;
      this.x4 = x + 10;
      this.y4 = y + 10;
      this.x5 = x + 10;
      this.y5 = y + 10;
      this.x6 = x + 10;
      this.y6 = y + 10;
      this.x7 = x + 10;
      this.y7 = y + 10;
      this.x8 = x + 10;
      this.y8 = y + 10;
      this.x9 = x + 10;
      this.y9 = y + 10;
      this.x10 = x + 10;
      this.y10 = y + 10;
      this.x11 = x + 10;
      this.y11 = y + 10;
      this.x12 = x + 10;
      this.y12 = y + 10;
      this.x13 = x + 10;
      this.y13 = y + 10;
      this.x14 = x + 10;
      this.y14 = y + 10;
      this.x15 = x + 10;
      this.y15 = y + 10;
      this.x16 = x + 10;
      this.y16 = y + 10;
	}
  }
  
  public void explode(int delta) {
	  float h = 1.5f * delta;
	  
	  //angled explosion
      /*x1 += h * java.lang.Math.cos(java.lang.Math.toRadians(0.0D));
      y1 += h *java.lang.Math.sin(java.lang.Math.toRadians(0.0D));
      x2 += h * java.lang.Math.cos(java.lang.Math.toRadians(45.0D));
      y2 += h *java.lang.Math.sin(java.lang.Math.toRadians(45.0D));
      x3 += h * java.lang.Math.cos(java.lang.Math.toRadians(90.0D));
      y3 += h *java.lang.Math.sin(java.lang.Math.toRadians(90.0D));
      x4 += h * java.lang.Math.cos(java.lang.Math.toRadians(135.0D));
      y4 += h *java.lang.Math.sin(java.lang.Math.toRadians(135.0D));
      x5 += h * java.lang.Math.cos(java.lang.Math.toRadians(180.0D));
      y5 += h *java.lang.Math.sin(java.lang.Math.toRadians(180.0D));
      x6 += h * java.lang.Math.cos(java.lang.Math.toRadians(225.0D));
      y6 += h *java.lang.Math.sin(java.lang.Math.toRadians(225.0D));
      x7 += h * java.lang.Math.cos(java.lang.Math.toRadians(270.0D));
      y7 += h *java.lang.Math.sin(java.lang.Math.toRadians(270.0D));
      x8 += h * java.lang.Math.cos(java.lang.Math.toRadians(315.0D));
      y8 += h *java.lang.Math.sin(java.lang.Math.toRadians(315.0D));
      x9 += h * java.lang.Math.cos(java.lang.Math.toRadians(22.5D));
      y9 += h *java.lang.Math.sin(java.lang.Math.toRadians(22.5D));
      x10 += h * java.lang.Math.cos(java.lang.Math.toRadians(67.5D));
      x10 += h *java.lang.Math.sin(java.lang.Math.toRadians(67.5D));
      x11 += h * java.lang.Math.cos(java.lang.Math.toRadians(112.5D));
      y11 += h *java.lang.Math.sin(java.lang.Math.toRadians(112.5D));
      x12 += h * java.lang.Math.cos(java.lang.Math.toRadians(157.5D));
      y12 += h *java.lang.Math.sin(java.lang.Math.toRadians(157.5D));
      x13 += h * java.lang.Math.cos(java.lang.Math.toRadians(202.5D));
      y13 += h *java.lang.Math.sin(java.lang.Math.toRadians(202.5D));
      x14 += h * java.lang.Math.cos(java.lang.Math.toRadians(247.5D));
      y14 += h *java.lang.Math.sin(java.lang.Math.toRadians(247.5D));
      x15 += h * java.lang.Math.cos(java.lang.Math.toRadians(292.5D));
      y15 += h *java.lang.Math.sin(java.lang.Math.toRadians(292.5D));
      x16 += h * java.lang.Math.cos(java.lang.Math.toRadians(337.5D));
      y16 += h *java.lang.Math.sin(java.lang.Math.toRadians(337.5D));*/
      
	  
	  //scrambling explosion
      x1 += h * java.lang.Math.cos(java.lang.Math.toRadians(random.nextDouble() * 360));
      y1 += h *java.lang.Math.sin(java.lang.Math.toRadians(random.nextDouble() * 360));
      x2 += h * java.lang.Math.cos(java.lang.Math.toRadians(random.nextDouble() * 360));
      y2 += h *java.lang.Math.sin(java.lang.Math.toRadians(random.nextDouble() * 360));
      x3 += h * java.lang.Math.cos(java.lang.Math.toRadians(random.nextDouble() * 360));
      y3 += h *java.lang.Math.sin(java.lang.Math.toRadians(random.nextDouble() * 360));
      x4 += h * java.lang.Math.cos(java.lang.Math.toRadians(random.nextDouble() * 360));
      y4 += h *java.lang.Math.sin(java.lang.Math.toRadians(random.nextDouble() * 360));
      x5 += h * java.lang.Math.cos(java.lang.Math.toRadians(random.nextDouble() * 360));
      y5 += h *java.lang.Math.sin(java.lang.Math.toRadians(random.nextDouble() * 360));
      x6 += h * java.lang.Math.cos(java.lang.Math.toRadians(random.nextDouble() * 360));
      y6 += h *java.lang.Math.sin(java.lang.Math.toRadians(random.nextDouble() * 360));
      x7 += h * java.lang.Math.cos(java.lang.Math.toRadians(random.nextDouble() * 360));
      y7 += h *java.lang.Math.sin(java.lang.Math.toRadians(random.nextDouble() * 360));
      x8 += h * java.lang.Math.cos(java.lang.Math.toRadians(random.nextDouble() * 360));
      y8 += h *java.lang.Math.sin(java.lang.Math.toRadians(random.nextDouble() * 360));
      x9 += h * java.lang.Math.cos(java.lang.Math.toRadians(random.nextDouble() * 360));
      y9 += h *java.lang.Math.sin(java.lang.Math.toRadians(random.nextDouble() * 360));
      x10 += h * java.lang.Math.cos(java.lang.Math.toRadians(random.nextDouble() * 360));
      x10 += h *java.lang.Math.sin(java.lang.Math.toRadians(random.nextDouble() * 360));
      x11 += h * java.lang.Math.cos(java.lang.Math.toRadians(random.nextDouble() * 360));
      y11 += h *java.lang.Math.sin(java.lang.Math.toRadians(random.nextDouble() * 360));
      x12 += h * java.lang.Math.cos(java.lang.Math.toRadians(random.nextDouble() * 360));
      y12 += h *java.lang.Math.sin(java.lang.Math.toRadians(random.nextDouble() * 360));
      x13 += h * java.lang.Math.cos(java.lang.Math.toRadians(random.nextDouble() * 360));
      y13 += h *java.lang.Math.sin(java.lang.Math.toRadians(random.nextDouble() * 360));
      x14 += h * java.lang.Math.cos(java.lang.Math.toRadians(random.nextDouble() * 360));
      y14 += h *java.lang.Math.sin(java.lang.Math.toRadians(random.nextDouble() * 360));
      x15 += h * java.lang.Math.cos(java.lang.Math.toRadians(random.nextDouble() * 360));
      y15 += h *java.lang.Math.sin(java.lang.Math.toRadians(random.nextDouble() * 360));
      x16 += h * java.lang.Math.cos(java.lang.Math.toRadians(random.nextDouble() * 360));
      y16 += h *java.lang.Math.sin(java.lang.Math.toRadians(random.nextDouble() * 360));
  }

}

