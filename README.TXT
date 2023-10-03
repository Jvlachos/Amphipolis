******************AMPHIPOLIS************************
@AUTHOR:Dimitris Vlachos/CSD_4492

Run instructions:
The class Main_menu in the View package is runnable.Run the class (via the main method) to enter the start menu of the application.
There are three options:

New game:goes into the new game menu where inputs of player ids and colors are accepted.If there is only one id then the game start at single_player mode.

Load game:Choose what file you want to load a saved game from.

Exit:Closes the application.

Alternatively the can be started through the Controller class in the Controller package.The purpose of a runnable controller class is for testing and debugging.The main method starts the game with two players with ids "p1" and "p2" and colors Red and Blue.

Known bugs:
Some times a error message pops up regarding the character skill.The bug can be ignored as it does not hinder any function of the game nor it messes with the rules.

P.S:the classes save and load were last minute changes so they are not included in the report .There is a save file in the directory saves for test purposes,you can load it either from the main_menu or in_game using the Load game button.