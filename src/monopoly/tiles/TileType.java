package monopoly.tiles;

public enum TileType {
	PROPERTY, CARD_TILE, FREE_PARKING, GO_TO_JAIL,
	JAIL, GO, CHANCE, COMMUNITY_CHEST, RAILROAD,
	TAX, UTILITY;
	
	public static TileType parseType(String s) {
		// return the proper TileType for the string
        if (s.equals("Property")) {
            return TileType.PROPERTY;
        } else if (s.equals("CardTile")) {
            return TileType.CARD_TILE;
        } else if (s.equals("FreeParking")) {
            return TileType.FREE_PARKING;
        } else if (s.equals("GoToJail")) {
            return TileType.GO_TO_JAIL;
        } else if (s.equals("Jail")) {
            return TileType.JAIL;
        } else if (s.equals("Go")) {
            return TileType.GO;
        } else if (s.equals("Chance")) {
            return TileType.CHANCE;
        } else if (s.equals("CommunityChest")) {
            return TileType.COMMUNITY_CHEST;
        } else if (s.equals("Railroad")) {
            return TileType.RAILROAD;
        } else if (s.equals("Tax") || s.equals("IncomeTax")) {
            return TileType.TAX;
        } else if (s.equals("Utility")) {
            return TileType.UTILITY;
        } else {
            return null;
        }
	}
	
	public static boolean isAPropertyDerivative(TileType t) {
		if (t == TileType.PROPERTY ||
				t == TileType.RAILROAD ||
				t == TileType.UTILITY) {
			return true;
		}
		
		return false;
	}
}
