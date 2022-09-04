package cscd212classes.lab4;

import com.raylib.Raylib;
import cscd212classes.lab3.entities.Agent;
import cscd212classes.lab3.entities.Player;
import cscd212classes.lab4.map.Map;
import cscd212lib.lab2.ConsoleColors;
import cscd212lib.lab4.Display;

import static com.raylib.Raylib.*;

public final class User {
	private static User user;
	private Display display;
	private Map map;
	private Agent lastClickedAgent;
	private boolean isMoving;

	private User(final Display display) {
		this.display = display;
	}

	public static User getUser() { //?
		if(user == null){
			throw new IllegalStateException("No User Exist");
		}
		return User.user;
	}

	public static User getUser(final Display display) { //?
		if (User.user == null) {
			User.user = new User(display);
			System.out.println(ConsoleColors.RED + "Warning: map need to be set for User" + ConsoleColors.RESET);
			//return user;
		}
		return User.user;
	}

	public static User getUser(final Display display, final Map map) { //*?
		if (User.user == null) {
			User.user = new User(display);
			User.user.setMap(map);
			//return user;
		}
		return User.user;
	}

	public void setMap(final Map map) {
		if (map == null) {
			throw new IllegalArgumentException("map is null");
		}
		this.map = map;
	}

	public void getUserInput() {
		if(Raylib.IsMouseButtonPressed(0)) {
			int [] mapXandY = this.display.getMapXY(Raylib.GetMouseX(), Raylib.GetMouseY());
			this.updateLastClickedAgent(mapXandY);
			this.updateInfoBoxWithAgentInfo();
			this.checkAndRunMoveButton(mapXandY);
			this.checkAndRunAbilityButton();
			this.map.updateAgents();
		}
	} // END of method

	private void updateLastClickedAgent(final int[] mapXandY) {
		int x = mapXandY[0];
		int y = mapXandY[1];
		if (this.map.isInMap(x, y)) {
			if(this.map.getAgent(x, y) != null) {
				this.lastClickedAgent = this.map.getAgent(x, y);
			}
		}

	}

	private void checkAndRunAbilityButton() {
		if (this.display.isInOptionsButtonRight(Raylib.GetMouseX(), Raylib.GetMouseY()) && this.lastClickedAgent instanceof Player) {
			Player plays = (Player) this.lastClickedAgent;
			boolean specialAbility = plays.useSpecialAbility();
			if(!specialAbility) {
				this.display.setInfoBoxMessage(this.lastClickedAgent.getName() + " was not able to use special Ability");
			}
		}
	}

	private void checkAndRunMoveButton(final int[] mapXandY) {
		int x = mapXandY[0];
		int y = mapXandY[1];
		if (this.isMoving && this.map.isInMap(x,y) && this.map.getAgent(x,y) == null) { this.lastClickedAgent.move(x,y);
		}
		this.isMoving = false;
		if (this.display.isInOptionsButtonLeft(Raylib.GetMouseX(), Raylib.GetMouseY()) && this.lastClickedAgent instanceof Player) {
			this.isMoving = true;
		}
		if(this.isMoving) {
			this.display.setInfoBoxMessage("Moving mode for " + this.lastClickedAgent.getName());
		}
	}

	private void updateInfoBoxWithAgentInfo() {
		if(this.lastClickedAgent == null){
			this.display.setInfoBoxMessage("Info : empty");
		} else {
			this.display.setInfoBoxMessage(this.lastClickedAgent.getClass().getSimpleName() + ":" + this.lastClickedAgent.toString());
		}
	}
}
