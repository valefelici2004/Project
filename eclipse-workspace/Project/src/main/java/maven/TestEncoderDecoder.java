package maven;

public class TestEncoderDecoder {
	public static void main(String[] args) {
		
		Tile tile = new Tile();
		tile.nEspacio = 1;
		tile.iLat = 1;
		tile.iLon = 2;
		tile.nProf = 0;
		tile.iProf = 1;
		tile.nTemp = 2;
		tile.iTemp = 1;
		tile.nTiempo = 0;
		tile.iTiempo = 0;
		
		byte array[] = tile.encodeClave();
		
		for(int i=0; i<tile.encodeClave().length; i++) {
			System.out.print(tile.encodeClave()[i]);
		}
		
		System.out.println();
		tile.decodeClave(array);
		System.out.println(tile.nTiempo+" "+tile.iTiempo+" "+tile.nEspacio+" "+tile.iLat+" "+tile.iLon+" "+tile.nProf+" "+tile.iProf+" "+tile.nTemp+" "+tile.iTemp);				
				
			
	}

}
