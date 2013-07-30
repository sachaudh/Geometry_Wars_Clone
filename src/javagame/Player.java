package javagame;

import java.awt.Rectangle;
import java.util.ArrayList;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class Player extends Entity{

    static float currentX;
    static float currentY;
    float width = 25;
    float height = 25;
    float scale = (float) 0.50;
    double angle;
    long timer;
    ArrayList<Image> imgArray = new ArrayList<Image>();
    Image shield;
    int i = 0;
    Sound s;
    
	//private long start;
	private long burstTimer = 10000;
    
    public Player(float x, float y) {
        super(x, y);
        Player.currentX = x;
        Player.currentY = y;
        try {
			s = new Sound("res/sounds/shotborn.wav");
		} catch (SlickException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        //this.width = 15;
        //this.height = 15;
        this.timer = System.currentTimeMillis();
        try {
        	imgArray.add(new Image("res/player_up.png"));
        	imgArray.add(new Image("res/player_down.png"));
        	imgArray.add(new Image("res/player_left.png"));
        	imgArray.add(new Image("res/player_right.png"));
        	imgArray.add(new Image("res/player_nw.png"));
        	imgArray.add(new Image("res/player_ne.png"));
        	imgArray.add(new Image("res/player_sw.png"));
        	imgArray.add(new Image("res/player_se.png"));
			//this.width = imgArray.get(0).getWidth() * scale;
			//this.height = imgArray.get(0).getHeight() * scale;
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void update(GameContainer gc, StateBasedGame sbg,  int delta)
			throws SlickException {
    	
      Input input = gc.getInput();
    	
      if(input.isKeyDown(Input.KEY_A)){
        x -= 0.1f * delta;
        i = 2;
      }
 
      if(input.isKeyDown(Input.KEY_D)) {
        x += 0.1f * delta;
        i = 3;
      }
 
      if(input.isKeyDown(Input.KEY_W)){
        y -= 0.1f * delta;
        i = 0;
      }
        
      if(input.isKeyDown(Input.KEY_S)){
        y += 0.1f * delta;
        i = 1;
      }
      
      if(input.isKeyDown(Input.KEY_W) && input.isKeyDown(Input.KEY_A)){
        i = 4;
      }
      
      if(input.isKeyDown(Input.KEY_W) && input.isKeyDown(Input.KEY_D)){
        i = 5;
      }
      
      if(input.isKeyDown(Input.KEY_S) && input.isKeyDown(Input.KEY_A)){
        i = 6;
      }
      
      if(input.isKeyDown(Input.KEY_S) && input.isKeyDown(Input.KEY_D)){
        i = 7;
      }
      
      if(input.isKeyPressed(Input.KEY_SPACE)) {
        if(Game.paused == true) {
          Game.paused = false;
        }
        else {
          Game.paused = true;
        }  
      }  
      
      if(input.isMousePressed(Input.MOUSE_RIGHT_BUTTON)) {
    	if(10000 < System.currentTimeMillis() - burstTimer) {
    	  Game.addPowerUp(new Burst(x + 12, y + 12, width, height));
    	  burstTimer = System.currentTimeMillis();
    	}
      }
      
      if(100 < System.currentTimeMillis() - this.timer) {
        if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
          s.play();
          Game.addMissile(new NormalMissile(x + (width / 2), y + (height / 2),input));
        }
        timer = System.currentTimeMillis();
      }
         
      Player.currentX = x;
      Player.currentY = y;
      
      checkEdges();
      checkCollisions(sbg);
      
      if(10000 < System.currentTimeMillis() - burstTimer) {
        Game.burstBar = System.currentTimeMillis() - burstTimer;
      }
    }
    
    public void render(GameContainer gc, Graphics g)
			throws SlickException {
    	g.setColor(new Color(255,255,255)); 
    	//g.fillOval(x, y, width, height);
    	imgArray.get(i).draw(x,y,scale);
    	//g.drawRect(x,y,width,height);
    }
    
    
    public void checkEdges() {
      if(x >= 1070) {
        x = 1070;
      }
      else if(x <= 0) {
        x = 0;
      }
      if(y >= 665) {
        y = 665;
      }
      else if(y < 0) {
        y = 0;
      }
    }
    
    public Rectangle getBounds() {
      return new Rectangle((int)x, (int)y, (int)width, (int)height);
    }
    
    public void checkCollisions(StateBasedGame sbg) {
      ArrayList<Enemy> enemies = Game.getEnemyList();
      
      for(int i = 0; i < enemies.size(); i++) {
        
        if(getBounds().intersects(enemies.get(i).getBounds())) {
          if(Game.lives > 0) {
            Game.startRound();
            revive();
            Game.restarting();
          }
          else {
        	Highscores.getInstance().addScore(Game.score);
        	Game.gameOver();
        	revive();
            sbg.enterState(JavaGame.MAINMENUSTATE);
          }
        }
      }
      
      ArrayList<Event> events = Game.getEventList();
      
      for(int i = 0; i < events.size(); i++) {
        if(getBounds().intersects(events.get(i).getBounds())) {
          events.get(i).getItem();
          Game.removeEvent(events.get(i));
        }
      }
    }
    
    public void revive() {
      if(Game.lives > 0) {
        Game.lives--;
      }
      x = 400;
      y = 300;
      currentX = x;
      currentY = y;
      i = 0;
    }
    
    public static Vector2f getPlayerLocation() {
	  Vector2f vec = new Vector2f(Player.currentX,Player.currentY);
      return vec;
    }
}

