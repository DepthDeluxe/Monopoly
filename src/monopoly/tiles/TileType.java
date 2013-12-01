package monopoly.tiles;

public enum TileType {
	PROPERTY, CARD_TILE, FREE_PARKING, GO_TO_JAIL,
	JAIL, GO, CHANCE, COMMUNITY_CHEST, RAILROAD,
	TAX, UTILITY;
	
	public static TileType parseType(String s) {
		// return the proper TileType for the string
		switch (s) {
		case "Property":
			return TileType.PROPERTY;
			
		case "CardTile":
			return TileType.CARD_TILE;
			
		case "FreeParking":
			return TileType.FREE_PARKING;
			
		case "GoToJail":
			return TileType.GO_TO_JAIL;
			
		case "Jail":
			return TileType.JAIL;
			
		case "Go":
			return TileType.GO;
			
		case "Chance":
			return TileType.CHANCE;
			
		case "CommunityChest":
			return TileType.COMMUNITY_CHEST;
			
		case "Railroad":
			return TileType.RAILROAD;
			
		case "Tax":
		case "IncomeTax":
			return TileType.TAX;
			
		case "Utility":
			return TileType.UTILITY;
			
		default:
			return null;
		}
	}
}
