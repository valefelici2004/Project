package maven;

import java.util.Scanner;

public class Cliente {
	public static void main(String args[]) throws Exception {

		Scanner scanner = new Scanner(System.in);

		System.out.println("Nivel Tiempo: 0=Año 1=Mes 2=Dia");
		int tipo = scanner.nextByte();

		Tile tile;
		if (tipo == 2) {
			TileHoja t = new TileHoja();
			t.nTiempo = 2;
			System.out.print("Año: ");
			t.iTiempo.año = scanner.nextInt();
			System.out.print("Mes: ");
			t.iTiempo.mes = scanner.nextInt();
			System.out.print("Dia: ");
			t.iTiempo.dia = scanner.nextInt();
			tile = t;
		} else if (tipo == 1) {
			System.out.print("Año: ");
			int año = scanner.nextInt();
			System.out.print("Mes: ");
			int mes = scanner.nextInt();
			TileInterno t = new TileInterno(año, mes);
			t.nTiempo = 1;
			tile = t;
		} else {
			System.out.print("Año: ");
			int año = scanner.nextInt();
			TileInterno t = new TileInterno(año);
			t.nTiempo = 0;
			tile = t;
		}

		System.out.print("Nivel Espacio: ");
		tile.nEspacio = scanner.nextByte();

		System.out.print("Id latitud: ");
		tile.iLat = scanner.nextInt();

		System.out.print("Id longitud: ");
		tile.iLon = scanner.nextInt();

		System.out.print("Nivel profundidad: ");
		tile.nProf = scanner.nextByte();

		System.out.print("Id profundidad: ");
		tile.iProf = scanner.nextInt();

		System.out.print("Nivel Temperatura: ");
		tile.nTemp = scanner.nextByte();

		System.out.print("Id Temperatura: ");
		tile.iTemp = scanner.nextInt();

		// carico el cubo
		RocksDBBaseDatos db = new RocksDBBaseDatos("/Users/valefelici2004/Desktop/fileDBVALE");
		tile.load(db);
		db.close();

		System.out.println("Escribe los elementos, si quieres considerar todo el ancho escribe -1");
		System.out.print("Elemento tiempo (0-9): ");
		int ElemTiempo = scanner.nextInt();

		System.out.print("Elemento latitud (0-63): ");
		int ElemLat = scanner.nextInt();

		System.out.print("Elemento lngitud (0-63): ");
		int ElemLon = scanner.nextInt();

		System.out.print("Elemento profundidad (0-9): ");
		int ElemProf = scanner.nextInt();

		System.out.println("Estatistico: 0=Media, 1=Mediana, 2=Min, 3=Max, 4=Varianza, 5=DevStd, 6=Q1, 7=Q3");
		int stat = scanner.nextInt();

		switch (stat) {
		case 0:
			System.out.println("Media: " + Estatistico.media(tile, ElemTiempo, ElemLat, ElemLon, ElemProf));
			break;
		case 1:
			System.out.println("Mediana: " + Estatistico.mediana(tile, ElemTiempo, ElemLat, ElemLon, ElemProf));
			break;
		case 2:
			System.out.println("Min: " + Estatistico.min(tile, ElemTiempo, ElemLat, ElemLon, ElemProf));
			break;
		case 3:
			System.out.println("Max: " + Estatistico.max(tile, ElemTiempo, ElemLat, ElemLon, ElemProf));
			break;
		case 4:
			System.out.println("Varianza: " + Estatistico.varianza(tile, ElemTiempo, ElemLat, ElemLon, ElemProf));
			break;
		case 5:
			System.out.println("Dev. Std: " + Estatistico.deviazioneStandard(tile, ElemTiempo, ElemLat, ElemLon, ElemProf));
			break;
		case 6:
			System.out.println("Q1: " + Estatistico.primerCuartil(tile, ElemTiempo, ElemLat, ElemLon, ElemProf));
			break;
		case 7:
			System.out.println("Q3: " + Estatistico.tercerCuartil(tile, ElemTiempo, ElemLat, ElemLon, ElemProf));
			break;
		}

		scanner.close();
		
		/*
		for (int i = 0; i < 10; i++) {
		    System.out.println("BIN " + i + ": " + tile.arrayTile[ElemTiempo][ElemLat][ElemLon][ElemProf][i] 
		        + "    range: " + tile.temperaturaCubo(i).min 
		        + " - " + tile.temperaturaCubo(i).max);
		}*/
	}

}
