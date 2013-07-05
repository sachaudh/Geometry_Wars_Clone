package javagame;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
 
/**
 *
 * @author Spiegel
 *
 */
public class JavaGame extends StateBasedGame {
 
    public static final int MAINMENUSTATE = 0;
    public static final int GAME = 1;
 
    public JavaGame()
    {
        super("JavaGame");
    }
 
    public static void main(String[] args) throws SlickException
    {
         AppGameContainer app = new AppGameContainer(new JavaGame());
 
         app.setDisplayMode(800, 600, false);
         app.setShowFPS(false);
         app.start();
    }
 
    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        this.addState(new MainMenuState(MAINMENUSTATE));
        this.addState(new Game(GAME));
    }
}