package ajay.ohgj124.main;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class EnemyHandler {
	ArrayList<Enemy> enemies = new ArrayList<>();
	
	float x,y;
	
	boolean direction;
	
	public EnemyHandler(Screen screen){
		for(int y=0;y<5;y++){
			for(int i=100;i<screen.getWidth()-100;i+=70){
				enemies.add(new Enemy(i, y*60));
			}
		}
	}
	
	public void render(Graphics2D g){
		for(Enemy enemy: enemies){
			enemy.render(g, this);
		}
	}
	
	public void update(Screen screen){
//		for(Enemy enemy: enemies){
//			enemy
//		}
		
		if(direction){
			x-= 1.5f * screen.delta;
			if(x+100<0){
				direction = !direction;
				y += 30;
			}
		}
		else{
			x+= 1.5f * screen.delta;
			if(x+100+70*11+40 > screen.getWidth()){
				direction = !direction;
				y += 30;
			}
		}
		
		if(enemies.isEmpty()){
			screen.win = true;
		}
	}
}
