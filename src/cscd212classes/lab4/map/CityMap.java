package cscd212classes.lab4.map;

import cscd212classes.lab3.GameMaster;
import cscd212classes.lab3.entities.characters.Farmer;
import cscd212classes.lab3.entities.characters.Goblin;
import cscd212classes.lab3.entities.characters.SimpleHero;

import java.util.ArrayList;

public class CityMap extends Map {

	protected CityMap() {
		super(20, 30, GameMaster.getGameMaster());
		this.addAgent(8,0, new Farmer("Bob", "Thank you for saving the City", "Can't chat there are enemies in the city"));
		this.addAgent(9,0, new Farmer("Baker", "Thank you for saving the City", "Can't chat there are enemies in the city"));
		this.addAgent(0,8, new Farmer("Clark", "Thank you for saving the City", "Can't chat there are enemies in the city"));
		this.addAgent(0,9, new Farmer("Davis", "Thank you for saving the City", "Can't chat there are enemies in the city"));
		this.addAgent(0,0, new SimpleHero("Irwin"));
		this.addAgent(0,1, new SimpleHero("Mason"));
		this.addAgent(1,1, new SimpleHero("Smith"));
		this.addAgent(1,0, new SimpleHero("Valdo"));


		for(int x = 10; x < 20; x++) {
			for(int y = 10; y < 30; y++) {
				this.addAgent(y, x, new Goblin());
			}
		}
		this.allAgents.trimToSize();


	}
}
