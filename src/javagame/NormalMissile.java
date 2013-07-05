package javagame;

import java.awt.Rectangle;

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
    	System.out.println(start);
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
      }
 }
	  
  public Rectangle getBounds() {
	//return new Rectangle((int)x - 13, (int)y - 20, image.getWidth(), image.getHeight());
	  return new Rectangle((int)x - (5/2),(int) y - (5/2), 5, 5);
  }
	    
  public void offScreen() {
    if(y <= 0 || y >= 595 || x <= 0 || x >= 795) {
	  //Game.removeMissile(this);
      hitsWall = true;
      this.start = System.currentTimeMillis();
  	  this.x1 = x + 10;
      this.y1 = y + 10;
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
	}
  }
  
  public void explode(int delta) {
	  y1 -= 0.3f * delta;
      x2 += 0.22f * delta;
      y2 -= 0.22f * delta;
      x3 += 0.3f * delta;
      x4 += 0.22f * delta;
      y4 += 0.22f * delta;
      y5 += 0.3f * delta;
      x6 -= 0.22f * delta;
      y6 += 0.22f * delta;
      x7 -= 0.3f * delta;
      x8 -= 0.22f * delta;
      y8 -= 0.22f * delta;
  }

}

