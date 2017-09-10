package ajay.ohgj124.main;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Projectile {
	BufferedImage image;
	
	float x,y,xspeed,yspeed;
	
	public Projectile(float x, float y,float xspeed, float yspeed){
		this.x = x;
		this.y = y;
		this.xspeed = xspeed;
		this.yspeed = yspeed;
		
		try {
			image = ImageIO.read(Projectile.class.getResourceAsStream("/projectile.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void render(Graphics2D g){
		g.drawImage(image, (int) x, (int) y, null);
	}
	
	public void update(Screen screen){
		x += xspeed * screen.delta;
		y += yspeed * screen.delta;
		
		for(Enemy enemy: new ArrayList<>(screen.enemyHandler.enemies)){
			if(x<enemy.x+screen.enemyHandler.x+enemy.image.getWidth() && x+image.getWidth()>enemy.x+screen.enemyHandler.x && y<enemy.y+enemy.image.getHeight()+screen.enemyHandler.y && y+image.getHeight()>enemy.y+screen.enemyHandler.y){
				screen.projectiles.remove(this);
				screen.enemyHandler.enemies.remove(enemy);
			}
		}
	}
}
