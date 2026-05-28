package maven;

public class AlgoritmoBottomUpAño {
	public static void esegui(Query q) throws Exception {

		// abro db
		RocksDBBaseDatos db = new RocksDBBaseDatos(q.pathDB);
	
		TileInterno tileAño= new TileInterno(q.año);
		
		tileAño.nTiempo = 0;
		tileAño.nEspacio = q.nEspacio;
        tileAño.iLat = q.iLat;
        tileAño.iLon = q.iLon;
        tileAño.nProf = q.nProf;
        tileAño.iProf = q.iProf;
        tileAño.nTemp = q.nTemp;
        tileAño.iTemp = q.iTemp;

		
	
		for (int tiempoMes = 1; tiempoMes <= Parametros.meses; tiempoMes++) {

			TileInterno tileMes = new TileInterno(q.año, tiempoMes);
			tileMes.nTiempo = 1;
			tileMes.iTiempo.año = q.año;
			tileMes.iTiempo.mes = tiempoMes;
			tileMes.nEspacio = q.nEspacio;
			 tileAño.iLat = q.iLat;
		        tileAño.iLon = q.iLon;
		        tileAño.nProf = q.nProf;
		        tileAño.iProf = q.iProf;
		        tileAño.nTemp = q.nTemp;
		        tileAño.iTemp = q.iTemp;
			tileMes.load(db);

			for (int lat = 0; lat < Parametros.celdasEspacioCubo; lat++) {
				for (int lon = 0; lon < Parametros.celdasEspacioCubo; lon++) {
					for (int prof = 0; prof < Parametros.celdasPTCubo; prof++) {
						for (int temp = 0; temp < Parametros.celdasPTCubo; temp++) {

							// sumo 
							int suma = 0;
							for (int t = 0; t < tileMes.arrayTile.length; t++) {
								suma += tileMes.arrayTile[t][lat][lon][prof][temp];
							}
							tileAño.arrayTile[tiempoMes][lat][lon][prof][temp] = suma;
						}
					}
				}
			}
		}
		tileAño.save(db);
		db.close();
	}
}
