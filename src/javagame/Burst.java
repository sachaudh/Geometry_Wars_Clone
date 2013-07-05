package javagame;

import java.awt.Rectangle;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

public class Burst extends PowerUp {

  private float width;
  private float height;
  private int xx = 25;
  private int yy = 25;
  private long start;
		  
  public Burst(float x, float y, float width2, float height2) {
    super(x,y);
	this.width = width2;
	this.height = height2;
	this.start = System.currentTimeMillis();
  }
		  
  public void update(GameContainer gc, int delta)
			throws SlickException {
	if(500 <= System.currentTimeMillis() - this.start) {
	  Game.powerUps.remove(this);
	}
	x = Player.currentX + 25 - xx;
	y = Player.currentY + 25 - yy;
	float a = 2.5f * delta;
	width += a;
	height += a;
	xx += (a / 2);
	yy += (a / 2);
  }
		  
  public void render(GameContainer gc, Graphics g)
			throws SlickException {
	//g.setColor(new Color(255,0,0));
	g.setColor(new Color(255,255,255));
	g.drawOval(x, y, width, height);
  }
		  
  public Rectangle getBounds() {
	return new Rectangle((int)x,(int)y,(int)width,(int)height);
  }
}
