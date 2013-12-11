MonopolyMain.java - starts the program

controller/StartController.java - the controller for the start menu (the first menu that comes up)
controller/MenuListener.java - the listener for startController.java
controller/MController.java - the controller for the main game
controller/MortgageListener.java - listener for the Mortgage button in the game
controller/RollDiceAction.java - deals with actions performed when the dice are rolled
controller/XMLFilter.java - file filter that makes sure only XML files are shown

gui/AboutDialog.java - deals with the popup when user clicks about
gui/MBoardPanel.java - the panel containing the game board
gui/MCardDialog.java - deals with popups for community chest and chance
gui/MControlPanel.java - the control panel 
gui/MMainFrame.java - the main frame for when the game is being played
gui/MMainMenu.java - the gui for the main menu
gui/MMenuBar.java - the menu bar at the top of the main frame
gui/MMortageDialog.java - the dialog box that shows up when you click mortgage
gui/MPlayerProperties.java - the dialog box that shows all the player properties
gui/MPropertiesPanel.java - the property panel on the right side of the main frame
gui/MSettingsMenu.java - the settings menu gui that can be selected from the start menu
gui/MUnmortgageDialog.java - responsible for when clicking mortgage and then selecting unmortgage
gui.ColorBoxes - JPGs that are responsible for the colors of the properties
gui.Dice - JPG images of the different sides of the dice
gui.Images - JPG images of different spaces around the board as well as the players

monopoly/AIPlayer.java - the AI player
monopoly/Board.java - the board of the monopoly game
monopoly/Card.java - a card representing community chest or chance card
monopoly/CardDeck.java - a deck of the cards (community chest or chance)
monopoly/Dice.java - the dice, rolling and getting values
monopoly/Monopoly.java - the actual game, brings everything together
monopoly/MonopolyModelState.java - an enumeration representing the state of the board
monopoly/Player.java - a player palying the monopoly game
monopoly/PlayerBankruptException.java - an exception used to deal with when a player goes bankrupt

monopoly.tiles/CardTile.java - information about the card type landed on (chance/community chest)
monopoly.tiles/FreeParking.java - deals with player landing on free parking
monopoly.tiles/GoTile.java - deals with player landing on or passing go
monopoly.tiles/GoToJailTile.java - deals with the player landing on go to jail
monopoly.tiles/ITile.java - an interface that represents a space on the board
monopoly.tiles/Property.java - implements ITile, has info about spaces that can be bought
monopoly.tiles/Railroad.java - extends property, information on the railroads
monopoly.tiles/TaxTile.java - deals with a player landing on a tax tile
monopoly.tiles/TileType.java - enumeration that represents the type of ITile
monopoly.tiles/Utility.java - deals with a player landing on a utility space

monopoly.xml/CardLoader.java - can load and parse the XML files for the chance and community chest cards
monopoly.xml/TileLoader.java - can load and parse the XML files for the ITiles
monopoly.xml/ISerializable.java - deals with saving and loading the game
monopoly.xml/MonopolyIO.java - deals with saving the game and loading the game
monopoly.xml/XMLIO.java - works on elements and documents

test - a package containing test files for the files in the monopoly package
test.tiles - a package containing test files for the files in the monopoly.tiles package
test.xml - a package containing test files for the filse in the monopoly.xml package

Chance.xml - the xml file for the chance cards
CommunityChest.xml - the xml file for the community chest cards
Original-Tiles.xml - the xml file for the original properties in the monopoly game
Tiles.xml - the xml file for the properties in our reddit edition game

UserManual.pdf - the user manual
TechnicalManual.pdf - the technical manual
projectlog.txt - the project log
readme.txt - the readme file
