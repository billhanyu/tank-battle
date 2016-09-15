Genre:
Top Down Scrolling Shooter

Name:
Tank Battle

Back story:
In the darkest world, only your country stands against killing. Can you help protect your own home?

Goal:
Protect home from enemy tanks and destroy as many enemies as possible in a limited period of time.

Basic Mechanics:
Users can use arrow keys or WASD to move the tank and use space to fire a missile.

Levels:
The levels differ mainly by the maps. In harder levels, the home would be more exposed to enemies and protections such as bricks and stones would be fewer.

Cheats:
B - becomes immortal for 5 seconds.
C - eliminate all enemy tanks on the map.
L - add one more life to player's tank.
N - skip to next level.

How to add new features:

Adding a new level is really simple: go to `MapData.java` and add a new `Map` element, giving it positions for stable elements such as `Stone` and `Brick`, player spawn position, enemy tanks spawn positions and home position in the form of arrays. When the game is run, `GameMap` class will then read from `MapData` to know how many levels there are and to build maps for levels.
Other features depend. If a new scene is added, it can reuse buttons defined in `GameUI` class.

Major design choices:

Game Map Building

classes required: `GameMap`, `MapData`, `Map`.
`MapData` stores a collection of `Map` objects. When the program needs to build a map, `buildMap` method in `GameMap` is called, in which the program reads from `MapData` to build a map.
This implementation is easy in that if a new map is to be added,  I can just add the positions as arrays to `MapData` class. However, there is a drawback in this design: `MapData` assumes that each `Map` object contains positions for stable elements, player tank and enemy tank positions, and home position. This in some way limits what a `Map` can be, making them less flexible.

Game Scenes

classes required: `GameScene`, `GameUI`, `StartScene`, `OverScene`, `WinScene`, `LeadersScene`.
`GameScene` is the super class of all the other scenes. It contains a protected `GameUI` object for common game buttons and text labels as well as a public abstract method `initScene` which is overridden by its children scene classes.
`Main` does not need to know how each scene is generated. It only needs to initialize an object for that scene and call `initScene` method. However, each scene needs a `GameUI` object as a manager to initialize. This is an assumption from `GameScene` to `Main`.
This design is quite flexible, if a new scene is to be added, I only need to add a new class, extend it from `GameScene`, and implement the `initScene` method. If there is duplicated code between the new class and an existing one, I can extract it to `GameUI` class so the code can be reused.

Assumptions:

- Enemy tank colliding on Home will not make the player lose the game.
- Enemy tanks move and fire missiles randomly.
- There is only one player.
