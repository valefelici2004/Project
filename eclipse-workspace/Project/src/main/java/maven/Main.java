package maven;

import java.io.IOException;

import org.rocksdb.RocksDBException;

import ucar.ma2.Array;
import ucar.ma2.IndexIterator;
import ucar.ma2.InvalidRangeException;
import ucar.nc2.NetcdfFile;
import ucar.nc2.NetcdfFiles;
import ucar.nc2.Variable;

public class Main {
	public static void main(String[] args) throws IOException, InvalidRangeException, RocksDBException {

		// Abrir file NetCDF
		String filenombre = "/Users/valefelici2004/Desktop/roms_002_20260321_0000.nc4";
		NetcdfFile ncfile = NetcdfFiles.open(filenombre);

		// ACCEDER ARRAY 4D Y OBTENER EL VALOR DE TEMP
		Variable temperadura = ncfile.findVariable("temp"); // Find a Variable, with the specified (escaped full) name.
		
		Tile tile = new Tile();

		// exemplo tile 1 dia
		for (int lon = 0; lon < 64; lon++) {
			for (int lat = 0; lat < 64; lat++) {
				for (int prof = 0; prof < 10; prof++) {
					for (int t = 0; t < 10; t++) {
						for (int tiempo = 0; tiempo < 24; tiempo++) {

							// valores reales desde al cubo al NETcdf, donde cada celda corresponde a una hora
							ValorReal lonRange = tile.longitudCubo(lon);
							ValorReal latRange = tile.latitudCubo(lat);
							ValorReal profRange = tile.profundidadCubo(prof);
							ValorReal tempRange = tile.temperaturaCubo(t);

							// index NETcdf
							int inicioLon = (int) tile.longitudNetCDF(lonRange.min);
							int finLon = (int) tile.longitudNetCDF(lonRange.max);
							int anchoLon = (finLon - inicioLon + 1);
							int inicioLat = (int) tile.latitudNetCDF(latRange.min);
							int finLat = (int) tile.latitudNetCDF(latRange.max);
							int anchoLat = (finLat - inicioLat + 1);
							int inicioProf = (int) tile.profundidadNetCDF(profRange.min); //min prof tiene index > porque al reverse
							int finProf = (int) tile.profundidadNetCDF(profRange.max); //max prof tiene index < porque al reverse
							int anchoProf = (inicioProf - finProf + 1); //hago fin-inicio porque al reverse por la prof

							int[] inicio = new int[] { tiempo, finProf, inicioLat, inicioLon };
							int[] ancho = new int[] { 1, anchoProf, anchoLat, anchoLon }; //dim 1 de tiempo porque es una celda 

							Array datos = temperadura.read(inicio, ancho); // Read a section of the data for this Variable and return a memory resident Array.
							int count = 0;

							IndexIterator it = datos.getIndexIterator(); // Get an index iterator for traversing the array in canonical order.
							while (it.hasNext()) { // control si hay un proximo valor
								double valor = it.getDoubleNext(); // guarda el valor+va al valor proximo
								if (valor >= tempRange.min && valor <= tempRange.max) {
									count++;
								}
							}
							

							tile.arrayTile[lon][lat][prof][t][tiempo] = count;
							
							System.out.print(lon+" "+lat+" "+prof+" "+t+" "+tiempo+" ");
							System.out.println("  contador:"+tile.arrayTile[lon][lat][prof][t][tiempo]);
						}
					}
				}
			}
		}
		BaseDato b = new BaseDato();
		byte[] key = EncoderDecoder.encodeKey((int)tile.nTiempo, (int)tile.iTiempo, (int)tile.nLat, (int)tile.iLat, (int)tile.nLon, (int)tile.iLon, (int)tile.nProf, (int)tile.iProf, (int)tile.nTemp, (int)tile.iTemp);
		byte[] value = EncoderDecoder.encodeValue(tile.arrayTile);
		b.dataBase(key, value);
		
		ncfile.close();
	}
} 

