package javagame;

import java.awt.Rectangle;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Missile extends Entity {
	
  public Missile(float x, float y, Input i) {
    super(x,y);
  }
  
  public void update(GameContainer gc, int delta)
			throws SlickException {
  }
  
  public void render(GameContainer gc, Graphics g)
			throws SlickException {
  }
    
  public void offScreen() {
  }

  public Rectangle getBounds() {
	// TODO Auto-generated method stub
	return null;
  }

}
