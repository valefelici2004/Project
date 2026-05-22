package maven;

public class AlgoritmoBottomUp {
	public static void main(String[] args) throws Exception {

		// abro db
		RocksDBBaseDatos db = new RocksDBBaseDatos("/Users/valefelici2004/Desktop/fileDB");

		int año = 0;
		int mes = 0;
		int tiempo = Tiempo.diasMes(año, mes);
		int arrayTileMes[][][][][] = new int[tiempo][Parametros.celdasEspacioCubo][Parametros.celdasEspacioCubo][Parametros.celdasPTCubo][Parametros.celdasPTCubo];

		for (int tiempoMes = 0; tiempoMes < tiempo; tiempoMes++) {

			Tile tile = new Tile();
			tile.nTiempo = 0;
			tile.iTiempo.año = año;
			tile.iTiempo.mes = mes;
			tile.iTiempo.dia = tiempoMes;
			tile.nEspacio = 0;
			tile.iLat = 0;
			tile.iLon = 0;
			tile.nProf = 0;
			tile.iProf = 0;
			tile.nTemp = 0;
			tile.iTemp = 0;

			tile.load(db);

			for (int lat = 0; lat < Parametros.celdasEspacioCubo; lat++) {
				for (int lon = 0; lon < Parametros.celdasEspacioCubo; lon++) {
					for (int prof = 0; prof < Parametros.celdasPTCubo; prof++) {
						for (int temp = 0; temp < Parametros.celdasPTCubo; temp++) {

							// sumo los 31 cubos
							int suma = 0;
							for (int t = 0; t < Parametros.celdasTiempoCubo; t++) {

								// db.get() tile con id tiempoMes
								suma += tile.arrayTile[t][lat][lon][prof][temp];

							}

							arrayTileMes[tiempoMes][lat][lon][prof][temp] = suma;
						}
					}
				}
			}
		}
		db.close();
	}
}

