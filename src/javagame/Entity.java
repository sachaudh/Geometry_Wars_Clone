package javagame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

  public class Entity {

	float x;
	float y;
	
	public Entity(float x, float y) {
	    this.x = x;
	    this.y = y;
    }
	  
	public void update(GameContainer gc, int delta)
			throws SlickException {
    }
  
    public void render(GameContainer gc, Graphics g)
			throws SlickException {
    }
}
