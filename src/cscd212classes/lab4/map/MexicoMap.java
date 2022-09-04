package cscd212classes.lab4.map;

import cscd212classes.lab3.GameMaster;
import cscd212classes.lab3.entities.Agent;
import cscd212classes.lab3.entities.characters.Farmer;
import cscd212classes.lab3.entities.characters.Goblin;
import cscd212classes.lab3.entities.characters.SimpleHero;

public class MexicoMap extends Map {
    protected MexicoMap() {
        super(10, 10, GameMaster.getGameMaster());
        this.addAgent(9,9, new Goblin());
        this.addAgent(8, 9, new Goblin());
        this.addAgent(9, 8, new Goblin());
        this.addAgent(8, 8, new Goblin());
        this.addAgent(0, 8, new Goblin());
        this.addAgent(9, 0, new Farmer("Carlos"));
        this.addAgent(6, 9, new Farmer("Tivo"));
        this.addAgent(5, 8, new Farmer("Zenida"));
        this.addAgent(4, 8, new Farmer("Silva"));
        this.addAgent(7, 7, new SimpleHero("Isabel"));
        this.addAgent(7, 0, new SimpleHero("Eduardo"));
        this.addAgent(0, 0, new SimpleHero("Estephanie"));
        this.allAgents.trimToSize();
    }
}
