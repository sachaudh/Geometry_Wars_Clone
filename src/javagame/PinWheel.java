package javagame;

import java.awt.Rectangle;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;


public class PinWheel extends Enemy {

	private int width;
	private int height;
	float scale = (float) 1;
	private Image image;
	Image img;
	Image img2;
	Image img3;
	Image img4;
	Image img5;
	Image img6;
	Image img7;
	Image img8;
	Vector2f vec;
	Random rand = new Random();
    private int randXCoord;
	private int randYCoord;
	private double theta;
	
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
	  private boolean alive = true;
	  private Vector2f direction;
	  
	public PinWheel(Image img, float enemyX, float enemyY) {
	  super(enemyX,enemyY);
	  this.image = img;
	  this.vec = new Vector2f(x,y);
	  this.width = (int) (image.getWidth() * scale);
	  this.height = (int) (image.getHeight() * scale);
	  try {
		this.img = new Image("res/beam2.png");
		this.img2 = new Image("res/beam2.png");
		this.img3 = new Image("res/beam2.png");
		this.img4 = new Image("res/beam2.png");
		this.img5 = new Image("res/beam2.png");
		this.img6 = new Image("res/beam2.png");
		this.img7 = new Image("res/beam2.png");
		this.img8 = new Image("res/beam2.png");
	  } catch (SlickException e) {
	  	e.printStackTrace();
	  }	  
	  
	  randXCoord = rand.nextInt(800);
	  randYCoord = rand.nextInt(700);
	  direction = new Vector2f(randXCoord - vec.x, randYCoord - vec.y);
	  theta = direction.getTheta();
	}
	  
	public void update(GameContainer gc, int delta)
			throws SlickException {
	    if(alive) {
		  checkCollisions();
		  checkBorderCollision();
		}
	    move(delta);
    }
  
    public void render(GameContainer gc, Graphics g)
			throws SlickException {
      if(alive ) {
    	image.draw(x,y,scale);
    	//g.drawRect(x, y, width, height);
    	
      }
      else {
        if(400 <= System.currentTimeMillis() - this.start) {
           Game.removeEnemy(this);
        }
        g.setColor(new Color(255,131,250));
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

	public void checkCollisions() {
		for(int i = 0; i < Game.getMissileList().size(); i++) {
		  Missile m = Game.getMissileList().get(i);
		  if(getBounds().intersects(m.getBounds())) {
			  this.start = System.currentTimeMillis();
		    	alive = false;
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
		        Game.removeMissile(m);
		        Game.score += (10 * Game.multiplier);
		        Game.count++;
		  }
		}
		    
		for(int i = 0; i < Game.getPowerUpList().size(); i++) {
		  PowerUp p = Game.getPowerUpList().get(i);
		  if(getBounds().intersects(p.getBounds())) {
		    if(p instanceof Burst) {
		    	this.start = System.currentTimeMillis();
		    	alive = false;
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
		        Game.score += (10 * Game.multiplier);
		        Game.count++;
		    }
		  }
		}
	}	
	
	public void move(int delta) {
      if(alive) {
    	 
    	image.rotate(0.15f * delta);
    	  
        vec.x = x;
        vec.y = y;

    	float h = 0.1f * delta;

        x += h * java.lang.Math.cos(java.lang.Math.toRadians(theta));
        y += h * java.lang.Math.sin(java.lang.Math.toRadians(theta));

      }
      else {
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

	public Rectangle getBounds() {
	  return new Rectangle((int)x,(int)y,width,height);
	  //return new Rectangle((int)x,(int)y,20,20);
	}
	
	public void checkBorderCollision() {
	  if(x <= 0 || x >= (800 - width)) {
		theta = 360 + (180 - theta);
	  }
	  if(y <= 0) {
		theta = (360 - theta) + 0;
	  }
	  if(y >= (600 - height)) {
		theta = (360 - theta) + 0;
	  }
	}

}
