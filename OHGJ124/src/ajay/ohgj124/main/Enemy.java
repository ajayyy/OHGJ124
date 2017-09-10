package ajay.ohgj124.main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Enemy {
	BufferedImage image;
	
	float x,y;
	
	public Enemy(float x, float y){
		this.x = x;
		this.y = y;
		
		try {
			image = ImageIO.read(Enemy.class.getResourceAsStream("/enemy.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void render(Graphics2D g, EnemyHandler enemyHandler){
		g.drawImage(image, (int) (x+enemyHandler.x), (int) (y+enemyHandler.y), null);
	}
}
