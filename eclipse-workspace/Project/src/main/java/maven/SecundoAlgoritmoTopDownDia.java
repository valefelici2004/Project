package maven;

import ucar.ma2.Array;
import ucar.ma2.Index;
import ucar.nc2.NetcdfFile;
import ucar.nc2.NetcdfFiles;
import ucar.nc2.Variable;

public class SecundoAlgoritmoTopDownDia {
	public static void esegui(Query q) throws Exception {

		// Abrir file NetCDF
		NetcdfFile ncfile = NetcdfFiles.open(q.pathNetCDF);

		// ACCEDER ARRAY 4D Y OBTENER EL VALOR DE TEMP
		Variable temperatura = ncfile.findVariable("temp"); // Find a Variable, with the specified (escaped full) name.
		int[] shape = temperatura.getShape();
		int nProfTotale = shape[1];
		int nLatTotale = shape[2];
		int nLonTotale = shape[3];

		TileHoja tile = new TileHoja();

		tile.nTiempo = q.nTiempo;
		tile.iTiempo.año = q.año;
		tile.iTiempo.mes = q.mes;
		tile.iTiempo.dia = q.dia;
		tile.nEspacio = q.nEspacio;
		tile.iLat = q.iLat;
		tile.iLon = q.iLon;
		tile.nProf = q.nProf;
		tile.iProf = q.iProf;
		tile.nTemp = q.nTemp;
		tile.iTemp = q.iTemp;

		int numero = 1;

		// exemplo tile 1 dia
		for (int tiempo = 0; tiempo < Parametros.horas; tiempo++) {

			int[] inicio = new int[] { tiempo, 0, 0, 0 };
			int[] ancho = new int[] { 1, nProfTotale, nLatTotale, nLonTotale };
			Array fetta = temperatura.read(inicio, ancho); // solo 1 ora in RAM
			Index idx = fetta.getIndex();

			for (int lon = 0; lon < Parametros.celdasEspacioCubo; lon++) {

				for (int lat = 0; lat < Parametros.celdasEspacioCubo; lat++) {

					for (int prof = 0; prof < Parametros.celdasPTCubo; prof++) {

						for (int t = 0; t < Parametros.celdasPTCubo; t++) {

							// valores reales desde al cubo al NETcdf, donde cada celda corresponde a una
							// hora
							ValorReal lonRange = tile.longitudCubo(lon);
							ValorReal latRange = tile.latitudCubo(lat);
							ValorReal profRange = tile.profundidadCubo(prof);
							ValorReal tempRange = tile.temperaturaCubo(t);

							// index NETcdf
							int inicioLon = (int) tile.longitudNetCDF(lonRange.min);
							int finLon = (int) tile.longitudNetCDF(lonRange.max);
							// int anchoLon = (finLon - inicioLon + 1);
							int inicioLat = (int) tile.latitudNetCDF(latRange.min);
							int finLat = (int) tile.latitudNetCDF(latRange.max);
							// int anchoLat = (int) (finLat - inicioLat + 1);
							int inicioProf = (int) tile.profundidadNetCDF(profRange.min); // min prof tiene index >
							// porque al reverse
							int finProf = (int) tile.profundidadNetCDF(profRange.max); // max prof tiene index < porque

							// al reverse

							int count = 0;
							// ***********************************************************+

							for (int p = finProf; p <= inicioProf; p++) {
								for (int la = inicioLat; la <= finLat; la++) {
									for (int lo = inicioLon; lo <= finLon; lo++) {

										double valor = fetta.getDouble(idx.set(0, p, la, lo));

										if (valor >= tempRange.min && valor <= tempRange.max) {
											count++;
										}
									}
								}
							}

							// **************************************************************

							tile.arrayTile[tiempo][lat][lon][prof][t] = count;
							System.out.println("ARRAY " + numero);
							numero++;

						}
					}
				}
			}
		}
		ncfile.close();

		RocksDBBaseDatos db = new RocksDBBaseDatos(q.pathDB);
		tile.save(db);
		db.close();
	}
}
