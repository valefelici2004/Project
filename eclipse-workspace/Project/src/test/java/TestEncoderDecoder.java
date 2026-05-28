

import java.io.IOException;

public class TestEncoderDecoder {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		Tile tile = new Tile();
		tile.nTiempo = 0;
		tile.iTiempo.año = 2025;
		tile.iTiempo.mes = 3;
		tile.iTiempo.dia = 4;
		tile.nEspacio = 1;
		tile.iLat = 1;
		tile.iLon = 2;
		tile.nProf = 0;
		tile.iProf = 1;
		tile.nTemp = 2;
		tile.iTemp = 1;
		
		byte[] arr = tile.encodeClave();

		System.out.println("BUFFER:");

		for (int i = 0; i < arr.length; i++) {

		    String bin = Integer.toBinaryString(arr[i] & 0xFF);

		    while (bin.length() < 8) {
		        bin = "0" + bin;
		    }

		    System.out.print(bin + " ");
		}
		
		byte array[] = tile.encodeClave();
		
		//STAMPA IL TILE
		System.out.println();
		System.out.print("Tile:  ");
		tile.decodeClave(array);
		System.out.println(tile.nTiempo+" "+tile.iTiempo.año+" "+tile.iTiempo.mes+" "+tile.iTiempo.dia+" "+tile.nEspacio+" "+tile.iLat+" "+tile.iLon+" "+tile.nProf+" "+tile.iProf+" "+tile.nTemp+" "+tile.iTemp);				
		
		
		
		byte array2[] = tile.encodeValor();
		
		System.out.println();
		tile.decodeValor(array2);
		System.out.println(tile.arrayTile[0]);
		
		for(int i=0; i<tile.encodeValor().length; i++) {
			System.out.print(tile.encodeValor()[i]);
		}	
		
		System.out.println();
		tile.decodeValor(array2);
		System.out.println(tile.arrayTile[0]);
	}

}
