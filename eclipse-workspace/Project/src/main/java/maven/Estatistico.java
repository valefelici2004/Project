package maven;

public class Estatistico {

	// media
	public static double media(Tile tile, int tiempo, int lat, int lon, int prof) {
		double suma = 0;
		int total = 0;
		for (int i = 0; i < 10; i++) {
			int freq = tile.arrayTile[tiempo][lat][lon][prof][i];
			double centro = (tile.temperaturaCubo(i).min + tile.temperaturaCubo(i).max) / 2.0;
			suma += freq * centro;
			total += freq;
		}
		return suma / total;
	}

	// mediana
	public static int mediana(Tile tile, int tiempo, int lat, int lon, int prof) {
		int total = 0;
		for (int i = 0; i < 10; i++) {
			total += tile.arrayTile[tiempo][lat][lon][prof][i];
		}
		int midad = total / 2;
		int suma = 0;
		for (int i = 0; i < 10; i++) {
			suma += tile.arrayTile[tiempo][lat][lon][prof][i];
			if (suma > midad) {
				return (int) ((tile.temperaturaCubo(i).min + tile.temperaturaCubo(i).max) / 2.0);
			}
		}
		return 0;
	}

	// min
	public static int min(Tile tile, int tiempo, int lat, int lon, int prof) {
		return (int) tile.temperaturaCubo(0).min;
	}

	// max
	public static int max(Tile tile, int tiempo, int lat, int lon, int prof) {
		return (int) tile.temperaturaCubo(9).max;
	}

	// varianza
	public static double varianza(Tile tile, int tiempo, int lat, int lon, int prof) {
		double varianza = 0;
		double somma = 0;
		double media = media(tile, tiempo, lat, lon, prof);

		for (int i = 0; i < 10; i++) {
			double centro = (tile.temperaturaCubo(i).min + tile.temperaturaCubo(i).max) / 2.0;
			int freq = tile.arrayTile[tiempo][lat][lon][prof][i];

			varianza += freq * Math.pow(centro - media, 2);
			somma += freq;
		}

		return varianza / somma;
	}

	// deviazione standard
	public static double deviazioneStandard(Tile tile, int tiempo, int lat, int lon, int prof) {
		return Math.sqrt(varianza(tile, tiempo, lat, lon, prof));
	}

	//primer cuartil
	public static double primerCuartil(Tile tile, int tiempo, int lat, int lon, int prof) {
		int total = 0;
		for (int i = 0; i < 10; i++)
			total += tile.arrayTile[tiempo][lat][lon][prof][i];

		int q1 = total / 4; // 25%

		int cumulativa = 0;
		for (int i = 0; i < 10; i++) {
			cumulativa += tile.arrayTile[tiempo][lat][lon][prof][i];
			if (cumulativa > q1)
				return (tile.temperaturaCubo(i).min + tile.temperaturaCubo(i).max) / 2.0;
		}
		return 0;
	}

	//tercer cuartil
	public static double tercerCuartil(Tile tile, int tiempo, int lat, int lon, int prof) {
		int total = 0;
		for (int i = 0; i < 10; i++)
			total += tile.arrayTile[tiempo][lat][lon][prof][i];

		int q3 = 3 * total / 4; 

		int cumulativa = 0;
		for (int i = 0; i < 10; i++) {
			cumulativa += tile.arrayTile[tiempo][lat][lon][prof][i];
			if (cumulativa > q3)
				return (tile.temperaturaCubo(i).min + tile.temperaturaCubo(i).max) / 2.0;
		}
		return 0;
	}
	
	/*
	public static double media2(Tile tile, int tiempo, int lat, int lon, int prof) {
		int t0, t1, la0, la1, lo0, lo1, p0, p1;
		if (tiempo == -1) {
			t0 = 0;
			t1 = tile.arrayTile.length;
		} else {
			t0 = tiempo;
			t1 = tiempo + 1;
		}
		if (lat == -1) {
			la0 = 0;
			la1 = Parametros.celdasEspacioCubo;
		} else {
			la0 = lat;
			la1 = lat + 1;
		}
		if (lon == -1) {
			lo0 = 0;
			lo1 = Parametros.celdasEspacioCubo;
		} else {
			lo0 = lon;
			lo1 = lon + 1;
		}
		if (prof == -1) {
			p0 = 0;
			p1 = Parametros.celdasPTCubo;
		} else {
			p0 = prof;
			p1 = prof + 1;
		}
		double x = 0, somma = 0;
		for (int t = t0; t < t1; t++)
			for (int la = la0; la < la1; la++)
				for (int lo = lo0; lo < lo1; lo++)
					for (int p = p0; p < p1; p++)
						for (int temp = 0; temp < Parametros.celdasPTCubo; temp++) {
							double centro = (tile.temperaturaCubo(temp).min + tile.temperaturaCubo(temp).max) / 2.0;
							x += centro * tile.arrayTile[t][la][lo][p][temp];
							somma += tile.arrayTile[t][la][lo][p][temp];
						}
		return x / somma;
	}*/
	
}
