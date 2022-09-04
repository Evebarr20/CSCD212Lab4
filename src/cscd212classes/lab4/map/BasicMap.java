package cscd212classes.lab4.map;

import cscd212classes.lab3.GameMaster;
import cscd212classes.lab3.entities.Agent;
import cscd212classes.lab3.entities.characters.Farmer;
import cscd212classes.lab3.entities.characters.Goblin;
import cscd212classes.lab3.entities.characters.SimpleHero;

import java.util.ArrayList;

public class BasicMap extends Map {

	protected BasicMap() {
		super(10, 10, GameMaster.getGameMaster());
		this.addAgent(9,9, new Goblin());
		this.addAgent(8, 9, new Goblin());
		this.addAgent(9, 8, new Goblin());
		this.addAgent(8, 8, new Goblin());
		this.addAgent(0, 8, new Goblin());
		this.addAgent(8, 0, new Farmer("Bob"));
		this.addAgent(0, 0, new SimpleHero("Adams"));
		this.allAgents.trimToSize();

	}
}
