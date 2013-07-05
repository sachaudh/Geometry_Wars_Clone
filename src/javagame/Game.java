package javagame;

import java.util.ArrayList;
import java.util.Random;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
 
public class Game extends BasicGameState{

    float x = 400;
    float y = 300;
    
    Player player;
    static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    static ArrayList<Missile> missiles = new ArrayList<Missile>(); 
    static ArrayList<PowerUp> powerUps = new ArrayList<PowerUp>(); 
    static ArrayList<javagame.Event> events = new ArrayList<javagame.Event>();
    
    private static long pawnStart;
	private static int pawnTime = 1000;
    private static long pinWheelStart;
    private static int pinWheelTime = 1500;
    
    static int enemyCount = 5;
    static int lives = 3;
    static int score = 0;
    static String ammoState = "normal";
    
    static int count = 0;
    static int nextLevel = 3;
    static int multiplier = 1;
    boolean multiplied = false;
    static String multiplierValue;
	//private long multiplierStart;
	//private static long eventStart;
	
    static boolean gameStarted = false;
    static boolean setUp = true;
	static boolean paused = false;
	
	static Random rand = new Random();
    
    static long burstBar = 10000;
 
    Image diamond_enemy;
    Image pinwheel_enemy;
    
    float enemyX = 350;
    float enemyY = -100;
	
	static boolean gameOver = false;
	
	int stateID = 1;
	
	private Image player_icon;
    
    public Game(int stateID )
    {
        this.stateID = stateID;
    }
 
    @Override
    public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {     
    	player = new Player(x,y);
    	
    	player_icon = new Image("res/player_up.png");
    	diamond_enemy = new Image("res/gfx/high/bluecircle2.png");
    	pinwheel_enemy = new Image("res/gfx/high/pinkpinwheel3.png");
    	
    	Game.pawnStart = System.currentTimeMillis();
    	Game.pinWheelStart = System.currentTimeMillis();
    }
 
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException
    {
    	if(!gameOver) {

    	  spawnPawn();
    	  spawnPinWheel();
    	
    	  player.update(gc, sbg, delta);
    	
    	  for(int i = 0; i < getEnemyList().size(); i++) {
    	    getEnemyList().get(i).update(gc, delta);
    	  }
    	  for(int i = 0; i < getPowerUpList().size(); i++) {
            getPowerUpList().get(i).update(gc, delta);
      	  }
    	  for(int i = 0; i < getMissileList().size(); i++) {
            getMissileList().get(i).update(gc, delta);
      	  }
    	  for(int i = 0; i < getEventList().size(); i++) {
      	    getEventList().get(i).update(gc, delta);
    	  }
    	}
    }
 
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException
    {
        player.render(gc,g);
        
        for(int i = 0; i < getEnemyList().size(); i++) {
      	  getEnemyList().get(i).render(gc, g);
      	}
      	for(int i = 0; i < getPowerUpList().size(); i++) {
          getPowerUpList().get(i).render(gc, g);
        }
      	for(int i = 0; i < getMissileList().size(); i++) {
      	  getMissileList().get(i).render(gc, g);
        }
      	for(int i = 0; i < getEventList().size(); i++) {
          getEventList().get(i).render(gc, g);
      	}
      	
    	g.drawString("Score: " + score, 10, 10);
    	player_icon.draw(380,10,(float)0.5);
    	g.drawString("" + lives, 405, 10);
    	if(MainMenuState.getHighScore() > score) {
    	  g.drawString("HighScore: " + MainMenuState.getHighScore(), 650, 10);
    	} else {
    	  g.drawString("HighScore: " + score, 650, 10);
    	}
    }
      
    public static void addMissile(Missile m) {
      missiles.add(m);   
    }
        
    public static void removeMissile(Missile m) {
      missiles.remove(m);   
    }
        
    public static ArrayList<Missile> getMissileList() {
      return missiles;
    }
      
    public static void addPowerUp(PowerUp m) {
      powerUps.add(m);   
    }
          
    public static void removePowerUp(PowerUp m) {
      powerUps.remove(m);   
    }
          
    public static ArrayList<PowerUp> getPowerUpList() {
      return powerUps;
    }
    
    public static void addEvent(javagame.Event e) {
      events.add(e);   
    }
            
    public static void removeEvent(javagame.Event event) {
      events.remove(event);   
    }
            
    public static ArrayList<javagame.Event> getEventList() {
      return events;
    }
      
    public static void addEnemy(Enemy e) {
      enemies.add(e);   
    }
      
    public static void removeEnemy(Enemy e) {
      enemies.remove(e);   
    }
      
    public static ArrayList<Enemy> getEnemyList() {
      return enemies;
    }

	public static void startRound() {
	  enemies.clear();
	  missiles.clear();
	  powerUps.clear();
	  events.clear();
	  Game.multiplier = 1;
	  Game.multiplierValue = "";
	  Game.count = 0;
	  Game.nextLevel = 3;
	}  
	
	public static void gameOver() {
	  Game.gameOver = true;
	  Game.score = 0;
	  Game.lives = 4;
	  Game.multiplier = 1;
	  Game.multiplierValue = "";
	  Game.count = 0;
	  Game.nextLevel = 3;
	  enemies.clear();
	  missiles.clear();
	  powerUps.clear();
	  events.clear();
    }  
	
	public void spawnPawn() {
	  if(Game.pawnTime <= System.currentTimeMillis() - Game.pawnStart) {
        
		int randLoc = 0;
		int randLoc2 = rand.nextInt(2);
		
		//top-left
        if(Player.currentX < 400 && Player.currentY < 400) {
          randLoc = 0;
        }
        //top-right
        if(Player.currentX > 400 && Player.currentY < 400) {
          randLoc = 1;
        }
        //bottom-left
        if(Player.currentX < 400 && Player.currentY > 400) {
          randLoc = 2;
        }
        //bottom-right
        if(Player.currentX > 400 && Player.currentY > 400) {
          randLoc = 3;
        }
        
        
        if(randLoc == 0) {
          if(randLoc2 == 0) {
      	    addEnemy(new Pawn(diamond_enemy, 450 + rand.nextInt(300), rand.nextInt(550)));
      	    Game.pawnStart = System.currentTimeMillis();
    	    Game.pawnTime  = 150 + rand.nextInt(500);
          }
          if(randLoc2 == 1) {
        	addEnemy(new Pawn(diamond_enemy, rand.nextInt(750), 350 + rand.nextInt(200)));
        	Game.pawnStart = System.currentTimeMillis();
      	    Game.pawnTime  = 150 + rand.nextInt(500);
          }
        }
        
        if(randLoc == 1) {
          if(randLoc2 == 0) {
            addEnemy(new Pawn(diamond_enemy, rand.nextInt(350), rand.nextInt(550)));
        	Game.pawnStart = System.currentTimeMillis();
      	    Game.pawnTime  = 150 + rand.nextInt(500);
          }
          if(randLoc2 == 1) {
          	addEnemy(new Pawn(diamond_enemy, rand.nextInt(750), 350 + rand.nextInt(200)));
          	Game.pawnStart = System.currentTimeMillis();
            Game.pawnTime  = 150 + rand.nextInt(500);
          }
        }
        
        if(randLoc == 2) {
          if(randLoc2 == 0) {
            addEnemy(new Pawn(diamond_enemy, 450 + rand.nextInt(300), rand.nextInt(550)));
          	Game.pawnStart = System.currentTimeMillis();
            Game.pawnTime  = 150 + rand.nextInt(500);
          }
          if(randLoc2 == 1) {
            addEnemy(new Pawn(diamond_enemy, rand.nextInt(750), rand.nextInt(250)));
            Game.pawnStart = System.currentTimeMillis();
            Game.pawnTime  = 150 + rand.nextInt(500);
          }
        }
        
        if(randLoc == 3) {
          if(randLoc2 == 0) {
            addEnemy(new Pawn(diamond_enemy, rand.nextInt(750), rand.nextInt(250)));
            Game.pawnStart = System.currentTimeMillis();
            Game.pawnTime  = 150 + rand.nextInt(500);
          }
          if(randLoc2 == 1) {
            addEnemy(new Pawn(diamond_enemy, rand.nextInt(350), rand.nextInt(550)));
            Game.pawnStart = System.currentTimeMillis();
            Game.pawnTime  = 150 + rand.nextInt(500);
          }
        }
        
        /*if(randLoc == 0) {
    	  addEnemy(new Pawn(diamond_enemy, 450 + rand.nextInt(400), rand.nextInt(670)));
    	  Game.pawnStart = System.currentTimeMillis();
  	      Game.pawnTime  = 150 + rand.nextInt(500);
        }
        else if(randLoc == 1) {
          addEnemy(new Pawn(diamond_enemy, rand.nextInt(350), rand.nextInt(670)));
    	  Game.pawnStart = System.currentTimeMillis();
    	  Game.pawnTime  = 150 + rand.nextInt(500);
        }*/
	  }
	}
	
	public void spawnPinWheel() {
      if(Game.pinWheelTime <= System.currentTimeMillis() - Game.pinWheelStart) {
	        
        int randLoc = 0;
  		int randLoc2 = rand.nextInt(2);
  		
  		//top-left
        if(Player.currentX < 400 && Player.currentY < 400) {
          randLoc = 0;
        }
        //top-right
        if(Player.currentX > 400 && Player.currentY < 400) {
          randLoc = 1;
        }
        //bottom-left
        if(Player.currentX < 400 && Player.currentY > 400) {
          randLoc = 2;
        }
        //bottom-right
        if(Player.currentX > 400 && Player.currentY > 400) {
          randLoc = 3;
        }
        
        if(randLoc == 0) {
          if(randLoc2 == 0) {
            addEnemy(new PinWheel(pinwheel_enemy, 450 + rand.nextInt(300), rand.nextInt(550)));
        	Game.pinWheelStart = System.currentTimeMillis();
        	Game.pinWheelTime  = 800 + rand.nextInt(500);
          }
          if(randLoc2 == 1) {
            addEnemy(new PinWheel(pinwheel_enemy, rand.nextInt(750), 350 + rand.nextInt(200)));
          	Game.pinWheelStart = System.currentTimeMillis();
          	Game.pinWheelTime  = 800 + rand.nextInt(500);
          }
        }
          
        if(randLoc == 1) {
          if(randLoc2 == 0) {
            addEnemy(new PinWheel(pinwheel_enemy, rand.nextInt(350), rand.nextInt(550)));
          	Game.pinWheelStart = System.currentTimeMillis();
          	Game.pinWheelTime  = 800 + rand.nextInt(500);
          }
          if(randLoc2 == 1) {
            addEnemy(new PinWheel(pinwheel_enemy, rand.nextInt(750), 350 + rand.nextInt(200)));
            Game.pinWheelStart = System.currentTimeMillis();
            Game.pinWheelTime  = 800 + rand.nextInt(500);
          }
        }
          
        if(randLoc == 2) {
          if(randLoc2 == 0) {
            addEnemy(new PinWheel(pinwheel_enemy, 450 + rand.nextInt(300), rand.nextInt(550)));
            Game.pinWheelStart = System.currentTimeMillis();
            Game.pinWheelTime  = 800 + rand.nextInt(500);
          }
          if(randLoc2 == 1) {
            addEnemy(new PinWheel(pinwheel_enemy, rand.nextInt(750), rand.nextInt(250)));
            Game.pinWheelStart = System.currentTimeMillis();
            Game.pinWheelTime  = 800 + rand.nextInt(500);
          }
        }
          
        if(randLoc == 3) {
          if(randLoc2 == 0) {
            addEnemy(new PinWheel(pinwheel_enemy, rand.nextInt(750), rand.nextInt(250)));
            Game.pinWheelStart = System.currentTimeMillis();
            Game.pinWheelTime  = 800 + rand.nextInt(500);
          }
          if(randLoc2 == 1) {
            addEnemy(new PinWheel(pinwheel_enemy, rand.nextInt(350), rand.nextInt(550)));
            Game.pinWheelStart = System.currentTimeMillis();
            Game.pinWheelTime  = 800 + rand.nextInt(500);
          }
        }
	      
	    /*if(randLoc == 0) {
	      addEnemy(new PinWheel(pinwheel_enemy, 400 + rand.nextInt(370), rand.nextInt(670)));
	      Game.pinWheelStart = System.currentTimeMillis();
	  	  Game.pinWheelTime  = 800 + rand.nextInt(500);
	    }
	    else if(randLoc == 1) {
	      addEnemy(new PinWheel(pinwheel_enemy, rand.nextInt(375), rand.nextInt(670)));
	      Game.pinWheelStart = System.currentTimeMillis();
	      Game.pinWheelTime  = 800 + rand.nextInt(500);
	    }*/
      }
	}


	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 1;
	}
}