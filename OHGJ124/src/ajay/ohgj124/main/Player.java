package ajay.ohgj124.main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Player {
	BufferedImage image;
	
	float x=500, y=400;
	
	float speed = 4;
	
	long shootlast;
	
	public Player(){
		try {
			image = ImageIO.read(Player.class.getResourceAsStream("/player.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void render(Graphics2D g){
		g.drawImage(image, (int) x,(int) y, null);
	}
	
	public void update(Screen screen){
		if(screen.lose) return;
		if(screen.left){
			x-= speed * screen.delta;
		}
		if(screen.right){
			x+= speed * screen.delta;
		}
		if(screen.up){
			y-= speed * screen.delta;
		}
		if(screen.down){
			y+= speed * screen.delta;
		}
		
		if(screen.shooting){
			if(System.currentTimeMillis() - shootlast >= 300){
				screen.projectiles.add(new Projectile(x+image.getWidth()/2-24/2, y, 0, -12));
				shootlast = System.currentTimeMillis();
			}
		}
		
		if(x<0) x=0;
		if(x>screen.getWidth()-image.getWidth()){
			x = screen.getWidth()-image.getWidth();
		}
		if(y<0) y=0;
		if(y>screen.getHeight()-image.getHeight()){
			y = screen.getHeight()-image.getHeight();
		}
		
		for(Enemy enemy: new ArrayList<>(screen.enemyHandler.enemies)){
			if(x<enemy.x+screen.enemyHandler.x+enemy.image.getWidth() && x+image.getWidth()>enemy.x+screen.enemyHandler.x && y<enemy.y+enemy.image.getHeight()+screen.enemyHandler.y && y+image.getHeight()>enemy.y+screen.enemyHandler.y){
				screen.lose = true;
			}
		}
	}
}
