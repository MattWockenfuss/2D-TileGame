package org.toast.entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;

import org.toast.Handler;
import org.toast.entities.itemEntities.ItemEntity;

public class EntityManager {
	
	private Handler handler;
	
	private ArrayList<Entity> entities;
	private int EntityRenderCount = 0;
	private int EntityTickCount = 0;
	
	
	@SuppressWarnings("unused")
	private Comparator<Entity> renderSorter = new Comparator<Entity>() {

		@Override
		public int compare(Entity a, Entity b) {
			if(a instanceof ItemEntity && b instanceof ItemEntity) {		//Make sure to check for both on top
				return 0;
			}
			if(a instanceof ItemEntity) {
				return -1;
			}
			if(b instanceof ItemEntity) {
				return 1;
			}
			if(a.getY() + a.getHeight() < b.getY() + b.getHeight())
				return -1;
			else
				return 1;
		}
		
	};
	public EntityManager(Handler handler) {
		this.handler = handler;
		entities = new ArrayList<Entity>();
	}
	
	
	public void tick() {
		EntityTickCount = 0;
		for(int i = 0;i < entities.size();i++) {
			Entity e = entities.get(i);
			
			if((Math.abs(handler.getGameState().player.x - e.x) < (handler.getWidth() * 1.5) && (Math.abs(handler.getGameState().player.y - e.y) < (handler.getHeight() * 1.5)))) {
				EntityTickCount++;
				e.move();
				e.tick();
			}
			

			
			
			
			if(!e.active) {
				if(e.dropItemsOnDeath)
					e.spawnChildren();
				entities.remove(e);
			}
		}
		
		
		
		
		
		
		
		/*If entities overlap there is a chance that the game crashes,so if we want to sort them,
		 * we need to check for overlaping entities and get rid of one of them, ideally with a ranking system
		 * like crates are removed last, trees first
		 */
		//entities.sort(renderSorter); 
	}
	public void render(Graphics g) {
		EntityRenderCount = 0;
		for(Entity e : entities) {
			if((Math.abs(handler.getGameState().player.x - e.x) < (handler.getWidth() / 2 + 100) && (Math.abs(handler.getGameState().player.y - e.y) < (handler.getWidth() / 2 + 100)))) {
				//System.out.println(handler.getGameState().player.x - e.x + " , " + (Math.abs(handler.getGameState().player.y - e.y) < (handler.getHeight() / 2 + 100)));
				EntityRenderCount++;
				e.render(g);
			}
		}	
	}

	
	
	
	

	
	
	
	
	
	
	
	public void addEntity(Entity e) {
		entities.add(e);
	}
	public void removeEntity(Entity e) {
		entities.remove(e);
	}
	
	
	//GETTERS AND SETTERS//////////////////////////////////////////////
	
	
	public Handler getHandler() {
		return handler;
	}


	public void setHandler(Handler handler) {
		this.handler = handler;
	}
	public ArrayList<Entity> getEntities() {
		return entities;
	}
	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
	public int getEntitiesRendered() {
		return EntityRenderCount;
	}
	public int getEntitiesUpdated() {
		return EntityTickCount;
	}
	
	
	
	
}
