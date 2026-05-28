

import java.io.File;

import maven.BaseDatos;
import maven.RocksDBBaseDatos;
import maven.Tile;

public class TestBaseDatos {
	public static void main(String[] args) throws Exception {

		BaseDatos db = new RocksDBBaseDatos("TestDB");

		Tile tile = new Tile();

		tile.nTiempo = 1;
		tile.iTiempo.año = 2024;
		tile.iTiempo.mes = 6;
		tile.iTiempo.dia = 15;
		tile.nEspacio = 2;
		tile.iLat = 10;
		tile.iLon = 20;
		tile.nProf = 3;
		tile.iProf = 5;
		tile.nTemp = 4;
		tile.iTemp = 7;

		tile.arrayTile = new int[24][64][64][10][10];

		tile.arrayTile[0][0][0][0][0] = 111;
		tile.arrayTile[1][2][3][3][2] = 999;
		tile.arrayTile[2][3][3][0][0] = 777;

		//SAVE
		tile.save(db);
/*
		//LOAD
		Tile tileCargado = new Tile();

		// Stessa clave del tile salvato
		tileCargado.nTiempo = tileGuardado.nTiempo;
		tileCargado.iTiempo.año = tileGuardado.iTiempo.año;
		tileCargado.iTiempo.mes = tileGuardado.iTiempo.mes;
		tileCargado.iTiempo.dia = tileGuardado.iTiempo.dia;
		tileCargado.nEspacio = tileGuardado.nEspacio;
		tileCargado.iLat = tileGuardado.iLat;
		tileCargado.iLon = tileGuardado.iLon;
		tileCargado.nProf = tileGuardado.nProf;
		tileCargado.iProf = tileGuardado.iProf;
		tileCargado.nTemp = tileGuardado.nTemp;
		tileCargado.iTemp = tileGuardado.iTemp;

		long startLoad = System.nanoTime();
		tileCargado.load(db);
		long endLoad = System.nanoTime();
		System.out.println("✅ LOAD OK — " + (endLoad - startLoad) / 1_000_000.0 + " ms");

		// --- VERIFICA ---
		boolean ok = true;

		ok &= check("[0][0][0][0][0]", 111, tileCargado.arrayTile[0][0][0][0][0]);
		ok &= check("[1][2][3][4][5]", 999, tileCargado.arrayTile[1][2][3][4][5]);
		ok &= check("[23][63][63][9][9]", 777, tileCargado.arrayTile[23][63][63][9][9]);

		if (ok)
			System.out.println("✅ TUTTI I VALORI CORRETTI");
		else
			System.out.println("❌ ALCUNI VALORI NON CORRISPONDONO");
	}

}

	static boolean check(String label, int expected, int actual) {
		if (expected == actual) {
			System.out.println("  ✅ " + label + " = " + actual);
			return true;
		} else {
			System.out.println("  ❌ " + label + " expected=" + expected + " actual=" + actual);
			return false;
		}
		*/
	}
}