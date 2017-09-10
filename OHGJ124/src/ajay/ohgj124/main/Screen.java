package ajay.ohgj124.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Screen extends Canvas implements Runnable, KeyListener{
	//ImageIO.read(Game.class.getResourceAsStream("/res/loading.png"));
	private static final long serialVersionUID = -572016035490436338L;
	BufferStrategy s;
	
	//delta
	long last;
	double delta;
	long timeperupdate = 1000/60;
	
	Player player;
	ArrayList<Projectile> projectiles = new ArrayList<>();
	EnemyHandler enemyHandler;
	
	boolean left,right,up,down,shooting,lose,win;
	
	public Screen(){
		addKeyListener(this);
	}
	
	public void init(){
		reset();
		
		Thread game = new Thread(this);
		game.start();
	}
	
	public void reset(){
		player = new Player();
		projectiles = new ArrayList<>();
		enemyHandler = new EnemyHandler(this);
	}
	
	public void run() {
		while(true){
			long now = System.currentTimeMillis();
			long updateLength = now - last;
		    last = now;
			delta = updateLength / ((double)timeperupdate);
			if(delta > 10) delta = 1;
			
			render();
			
			update();
		}
	}
	
	public void update(){
		player.update(this);
		
		enemyHandler.update(this);
		
		for(Projectile projectile: new ArrayList<>(projectiles)){
			projectile.update(this);
		}
		
	}
	
	public void render(){
		s = getBufferStrategy();
		if(s!=null){
			Graphics2D g = (Graphics2D) s.getDrawGraphics();
			
			g.setColor(Color.black);
			g.fillRect(0, 0, getWidth(), getHeight());
			
			player.render(g);
			
			for(Projectile projectile: projectiles){
				projectile.render(g);
			}
			
			enemyHandler.render(g);
			
			if(lose){
				g.setColor(Color.white);
				g.setFont(g.getFont().deriveFont(Font.PLAIN, 50));
				String message = "You lose :(";
				g.drawString(message, getWidth()/2-g.getFontMetrics().stringWidth(message)/2, 300);
			}else if(win){
				g.setColor(Color.white);
				g.setFont(g.getFont().deriveFont(Font.PLAIN, 50));
				String message = "You won, congrats! :)";
				g.drawString(message, getWidth()/2-g.getFontMetrics().stringWidth(message)/2, 300);
				g.setFont(g.getFont().deriveFont(Font.PLAIN, 25));
				message = "Sorry for making such a simple game, but at least I did it!";
				g.drawString(message, getWidth()/2-g.getFontMetrics().stringWidth(message)/2, 500);
			}
			
			s.show();
		}else{
			createBufferStrategy(3);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_D){
			right = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_A){
			left = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_W){
			up = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_S){
			down = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE){
			shooting = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_D){
			right = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_A){
			left = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_W){
			up = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_S){
			down = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE){
			shooting = false;
		}
	}
	
}
