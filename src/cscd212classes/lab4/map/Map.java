package cscd212classes.lab4.map;

import cscd212classes.lab3.GameMaster;
import cscd212classes.lab3.entities.Agent;

import javax.swing.event.EventListenerList;
import java.beans.PropertyChangeListener;
import java.io.Console;
import java.util.ArrayList;

public abstract class Map {
	private Agent[][] agentsMap;
	private GameMaster gameMaster;
	protected ArrayList<Agent> allAgents;
	private static Map currentMap;

	public static Map getCurrentMap() {

		return currentMap;
	}

	public static void setCurrentMap(final Map currentMap) {
		Map.currentMap = currentMap;

	}

	public Map(final int numOfCols, final int numOfRows, final GameMaster gameMaster) { //?
		this.agentsMap = new Agent[numOfCols][numOfRows];
		this.gameMaster = gameMaster;
		this.allAgents = new ArrayList<>();
	}

	public Agent resetAgent(final int x, final int y) { //?
		if (y < 0 || y > this.agentsMap.length || x < 0 || x > this.agentsMap[0].length) {
			throw new IllegalArgumentException("given coordinates are not in the map.");
		}
		Agent agentReturn = this.agentsMap[y][x];
		this.agentsMap[y][x] = null;
		return agentReturn;
	}

	public void setAgent(final int x, final int y, final Agent agent) { //?
		if (y < 0 || y > this.agentsMap.length || x < 0 || x > this.agentsMap[0].length || !currentMap.allAgents.contains(agent)) {
			throw new IllegalArgumentException("Bad params");
		}
		//this.allAgents.add(agent);
		this.agentsMap[y][x] = agent;
	}

	public void addAgent(final int x, final int y, final Agent agent) { //?
		if(agent == null) {
			throw new IllegalArgumentException("bad param");
		}
		this.allAgents.add(agent);
		agent.setXY(x, y);
		this.agentsMap[y][x] = agent;
		agent.addPropertyChangeListener(this.gameMaster);
		if(agent instanceof PropertyChangeListener){
			this.gameMaster.addPropertyChangeListener((PropertyChangeListener) agent);
		}
	}

	public Agent getAgent(final int x, final int y) {
		if (x >= this.agentsMap[0].length || y >= this.agentsMap.length) {
			throw new IllegalArgumentException("Not on map");
		}
		return this.agentsMap[y][x];
	}

	public boolean isInMap(final int x, final int y) {
		return y >= 0 && y<= this.agentsMap.length && x>= 0 && x<=this.agentsMap[0].length;
		}
		//Agent agentMap = this.agentsMap[x][y];
		//for(Agent allAgents : this.allAgents){
			//if(allAgents.equals(agentMap)) {
			//	return true;
		//	}
		//}
		//return false;

	public int getNumOfCols() { //?
		return this.agentsMap[0].length;
	}

	public int getNumOfRows() { //?
		return this.agentsMap.length;
	}

	public void updateAgents() {
		for(Agent agent: this.allAgents) {
			agent.update();
		}
	}

	public String agentsStatus() {
		String agentStatus = "";
		for (Agent allAgents: this.allAgents) {
			agentStatus += allAgents.getClass().getSimpleName() + " : " + allAgents + "\n";
		}
		return agentStatus;
	}

	@Override
	public String toString() {

		return "------Agents Status------ \n" + this.agentsStatus() + " ------Canvas------\n" + this.gameMaster.getMap().toString();
	}

}
