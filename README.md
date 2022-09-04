# cscd212-w22-lab4

[Student List Todo](#Student)

[Links](#Links)

## Student
  
For lab 4, you will be making 6 classes. The User class is a singleton that handles user input via mouse clicks. The MapFactory is a factory that handles the creation of Maps.  Map is an abstract class that all Maps extend from. There will be three concrete Maps to make: BasicMap, CityMap and your own cusom map. 

- [ ] Make the User class to the specifications listed in the API
    - [ ] The constructor for User should not be accessible by classes outside of User
    - [ ] There should only be one instance of User allowed to exist which is retrieved by using one of the getUser methods.
        - [ ] getUser() returns the User if it exists and throws an exception if it does not
        - [ ] getUser(Display display) and getUser(Display display, Map map) return the User if it exists and creates the User if it does not.
        - [ ] getUserInput() is a method that will be run in main once per frame. This means at 800 frames per second, this method will be run 800 times per second while the users mouse is in the window. This method checks to see if the mouse button is pressed and updates the state of the game according to what was clicked.
            - [ ] Note that x and y coordinates on a user's display (their laptop screen for instance) is different from the x and y coordinates on the map that is created. See the API for details on how to convert between these two systems. 
        - [ ] updateLastClickedAgent() takes an int array where mapXandY[0] represents an X coordinate and mapXandY[1] represents a Y coordinate. The method checks if those coordinates are in the map (see Map#isInMap(int, int)) and if so, gets the Agent at those coordinates (see Map#getAgent(int, int). If there is an Agent at those coordinates (ie, not null), this.lastClickedAgent is updated to be agent.
        - [ ] checkAndRunAbilityButton() checks the Display to see if the button that was clicked was the option button on the right (the Ability button). If so, and if the lastClickedAgent is a Player, the Player's useSpecialAbility() method is called. If the ability fails, the info box is updated with a special message.
        - [ ] checkAndRunMoveButton() checks the Display to see if the button that was clicked was the option button on the left (the Move button). If so, and if the lastClickedAgent is a Player, the Player is moved to the Map location passed to the method.
        - [ ] updateInfoBoxWithAgentInfo() updates the Display's info box message to be the name of the lastClickedAgent and the toString of that agent in the form "AgentSimpleName : AgentToString" if lastClickedAgent is not null, otherwise updates it to "Info : empty"
		
- [ ] Make the abstract class Map to the specifications listed in the API
- [ ] Make the concrete class BasicMap to the specifications listed in the API
- [ ] Make the concrete class CityMap to the specifications listed in the API
- [ ] Make your own map
  - Note you are allowed to make other characters if you want (Ex a Slime)
- [ ] Make the MapFactory class to the specifications listed in the API

## Links
- [Lab4 Java Doc](docs/index.html)
- [Ray lib Documentation (Cheatsheet)](https://www.raylib.com/cheatsheet/cheatsheet.html)
- [Singleton pattern](https://refactoring.guru/design-patterns/singleton)
- [Factory Method Pattern](https://refactoring.guru/design-patterns/factory-method)
