package maven;

public class AlgoritmoBottomUpMes {
	public static void esegui(Query q) throws Exception {

		// abro db
		RocksDBBaseDatos db = new RocksDBBaseDatos(q.pathDB);
	
		TileInterno tileMes = new TileInterno(q.año, q.mes);
		
		tileMes.nEspacio = q.nEspacio;
        tileMes.iLat = q.iLat;
        tileMes.iLon = q.iLon;
        tileMes.nProf = q.nProf;
        tileMes.iProf = q.iProf;
        tileMes.nTemp = q.nTemp;
        tileMes.iTemp = q.iTemp;
		
		int tiempo = Tiempo.diasMes(tileMes.iTiempo.año, tileMes.iTiempo.mes);
	
		for (int tiempoMes = 0; tiempoMes < tiempo; tiempoMes++) {

			TileHoja tileDia = new TileHoja();
			tileDia.nTiempo = 2;
			tileDia.iTiempo.año = q.año;
			tileDia.iTiempo.mes = q.mes;
			tileDia.iTiempo.dia = tiempoMes;
			tileDia.nEspacio = 0;
			tileDia.iLat = 0;
			tileDia.iLon = 0;
			tileDia.nProf = 0;
			tileDia.iProf = 0;
			tileDia.nTemp = 0;
			tileDia.iTemp = 0;

			tileDia.load(db);

			for (int lat = 0; lat < Parametros.celdasEspacioCubo; lat++) {
				for (int lon = 0; lon < Parametros.celdasEspacioCubo; lon++) {
					for (int prof = 0; prof < Parametros.celdasPTCubo; prof++) {
						for (int temp = 0; temp < Parametros.celdasPTCubo; temp++) {

							// sumo 
							int suma = 0;
							for (int t = 0; t < Parametros.celdasTiempoCubo; t++) {
								suma += tileDia.arrayTile[t][lat][lon][prof][temp];
							}
							tileMes.arrayTile[tiempoMes][lat][lon][prof][temp] = suma;
						}
					}
				}
			}
		}
		tileMes.save(db);
		db.close();
	}
}
