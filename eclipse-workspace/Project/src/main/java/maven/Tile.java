package maven;

import java.nio.ByteBuffer;

public class Tile {
	int nEspacio;
	int iLon;
	int iLat;
	int nProf;
	int iProf;
	int nTemp;
	int iTemp;
	int nTiempo;
	int iTiempo;

	int[][][][][] arrayTile = new int[64][64][10][10][24]; // array 5 dimensiones

	// N.B.
	// niveles 0,1,2...
	// tiles por el nivel 0:1, por el nivel 1:0,1,2, por el nivel 2:0,1,2,3,4,5,6
	// elementos empiezan desde 0...63, 0...9
	// En input es necesario poner estos numeros

	// LONGITUD
	ValorReal longitudCubo(int j) {

		double nTiles = Math.pow(2, nEspacio + 1) - 1; // calculo el numero de tiles
		double resolucionTile = (Parametros.x2 - Parametros.x1) / (Math.ceil(nTiles / 2)); // resolucion 1 tile
		double inicioTile = (Parametros.x1 + iLon * resolucionTile * (0.5)); // valor real inicio tile
		double finTile = (Parametros.x1 + iLon * resolucionTile * (0.5) + resolucionTile); // valor real fin tile
		if (inicioTile < Parametros.x1 || finTile > Parametros.x2) { // control que dado el input el tile esta en el
																		// range
			throw new IllegalArgumentException("Tile fuera de el range");
		}
		double resolutionElemento = resolucionTile / Parametros.celdasEspacioCubo; // resolucion 1 elemento
		double inicioElemento = inicioTile + j * resolutionElemento; // valor real inicio elemento
		double finElemento = inicioTile + (j + 1) * resolutionElemento; // valor real fin elemento
		if (inicioElemento < Parametros.x1 || finElemento > Parametros.x2) { // control que dado el input el elemento
																				// esta en el range
			throw new IllegalArgumentException("Elemento fuera de el range");
		}

		ValorReal valores = new ValorReal(); // creo objecto valores
		valores.min = inicioElemento; // contiene el inicio elemento
		valores.max = finElemento; // contiene la fin elemento

		return valores;
	}

	double longitudNetCDF(double lon) {

		double resolutionNetCDF = (Parametros.x2 - Parametros.x1) / Parametros.celdasLonNet;
		double indiceArrayNetCDF = Math.floor((lon - Parametros.x1) / resolutionNetCDF);
		if (indiceArrayNetCDF == 476) {
			indiceArrayNetCDF--;
		}
		return indiceArrayNetCDF;
	}

	// LATITUD
	ValorReal latitudCubo(int j) {

		double nTiles = Math.pow(2, nEspacio + 1) - 1;
		double resolucionTile = (Parametros.y2 - Parametros.y1) / (Math.ceil(nTiles / 2));
		double inicioTile = (Parametros.y1 + iLat * resolucionTile * (0.5));
		double finTile = (Parametros.y1 + iLat * resolucionTile * (0.5) + resolucionTile);
		if (inicioTile < Parametros.y1 || finTile > Parametros.y2) { // control que dado el input el tile esta en el
																		// range
			throw new IllegalArgumentException("Tile fuera de el range");
		}
		double resolutionElemento = resolucionTile / Parametros.celdasEspacioCubo;
		double inicioElemento = inicioTile + j * resolutionElemento;
		double finElemento = inicioTile + (j + 1) * resolutionElemento;
		if (inicioElemento < Parametros.y1 || finElemento > Parametros.y2) { // control que dado el input el elemento
																				// esta en el range
			throw new IllegalArgumentException("Elemento fuera de el range");
		}

		ValorReal valores = new ValorReal();
		valores.min = inicioElemento;
		valores.max = finElemento;

		return valores;
	}

	double latitudNetCDF(double lat) {

		double resolutionNetCDF = (Parametros.y2 - Parametros.y1) / Parametros.celdasLatNet;
		double indiceArrayNetCDF = Math.floor((lat - Parametros.y1) / resolutionNetCDF);
		if (indiceArrayNetCDF == 401) {
			indiceArrayNetCDF--;
		}
		return indiceArrayNetCDF;
	}

	// PROFUNDIDAD
	ValorReal profundidadCubo(int j) {

		double nTiles = Math.pow(2, nProf + 1) - 1;
		double resolucionTile = (Parametros.maxP - Parametros.minP) / ((Math.ceil(nTiles / 2)));
		double inicioTile = (Parametros.minP + iProf * resolucionTile * (0.5));
		double finTile = (Parametros.minP + iProf * resolucionTile * (0.5) + resolucionTile);
		if (inicioTile < Parametros.minP || finTile > Parametros.maxP) { // control que dado el input el tile esta en el
																			// range
			throw new IllegalArgumentException("Tile fuera de el range");
		}
		double resolutionElemento = resolucionTile / Parametros.celdasPTCubo;
		double inicioElemento = inicioTile + j * resolutionElemento;
		double finElemento = inicioTile + (j + 1) * resolutionElemento;
		if (inicioElemento < Parametros.minP || finElemento > Parametros.maxP) { // control que dado el input el
																					// elemento esta en el range
			throw new IllegalArgumentException("Elemento fuera de el range");
		}

		ValorReal valores = new ValorReal();
		valores.min = inicioElemento;
		valores.max = finElemento;

		return valores;
	}

	int profundidadNetCDF(double p) {
		for (int i = 0; i < 14; i++) {
			if (p >= Parametros.arrayProf[i])
				return i;
		}
		return 14;
	}

	// TEMPERATURA
	ValorReal temperaturaCubo(int j) {

		double nTiles = Math.pow(2, nTemp + 1) - 1;
		double resolucionTile = (Parametros.maxT - Parametros.minT) / ((Math.ceil(nTiles / 2)));
		double inicioTile = (Parametros.minT + iTemp * resolucionTile * (0.5));
		double finTile = (Parametros.minT + iTemp * resolucionTile * (0.5) + resolucionTile);
		if (inicioTile < Parametros.minT || finTile > Parametros.maxT) { // control que dado el input el tile esta en el
																			// range
			throw new IllegalArgumentException("Tile fuera de el range");
		}
		double resolutionElemento = resolucionTile / Parametros.celdasPTCubo;
		double inicioElemento = inicioTile + j * resolutionElemento;
		double finElemento = inicioTile + (j + 1) * resolutionElemento;
		if (inicioElemento < Parametros.minT || finElemento > Parametros.maxT) { // control que dado el input el
																					// elemento esta en el range
			throw new IllegalArgumentException("Elemento fuera de el range");
		}

		ValorReal valores = new ValorReal();
		valores.min = inicioElemento;
		valores.max = finElemento;

		return valores;
	}
	
	// clave --> binario 
		public static byte[] encodeClave(Tile tile) {
			
			// Objecto buffer
			// A container for data of a specific primitive type
			// A buffer is a linear, finite sequence of elements of a specific primitive
			// type.
			ByteBuffer buffer = ByteBuffer.allocate(Parametros.resolucionBuffer);

			// Buffer alloca en cadena
			buffer.put((byte) tile.nTiempo); // 1 byte por nivel
			
			//***************************
			buffer.putLong(tile.iTiempo); // 8 byte por index tile
			
			buffer.put((byte) tile.nEspacio);
			buffer.putLong(tile.iLat);
			buffer.putLong(tile.iLon);
			buffer.put((byte) tile.nProf);
			buffer.putLong(tile.iProf);
			buffer.put((byte) tile.nTemp);
			buffer.putLong(tile.iTemp);

			return buffer.array(); // Returns the byte array that backs this buffer
		}
		
		
		// binario --> clave decoder 
		public Tile(byte clave[]) {
			ByteBuffer buffer = ByteBuffer.wrap(clave);
			this.nTiempo = buffer.get();
			this.iTiempo = (int) buffer.getLong();
			this.nEspacio = buffer.get();
			this.iLat = (int) buffer.getLong();
			this.iLon = (int) buffer.getLong();
			this.nProf = buffer.get();
			this.iProf = (int) buffer.getLong();
			this.nTemp = buffer.get();
		    this.iTemp = (int) buffer.getLong();
		}


		// valor --> binario
		public static byte[] encodeValor(Tile tile ) {


			int array[] = new int[Parametros.resolucionCubo];

			//ORDINE!!!
			// Da 5D a array lineare
			int i = 0;
			for (int tiempo = 0; tiempo < 24; tiempo++) {
				for (int lat = 0; lat < 64; lat++) {
					for (int lon = 0; lon < 64; lon++) {
						for (int prof = 0; prof < 10; prof++) {
							for (int temp = 0; temp < 10; temp++) {
								
								array[i] = tile.arrayTile[tiempo][lat][lon][prof][temp];
								i++;
							}
						}
					}
				}
			}

			// Da array lineare a byte
			ByteBuffer buffer = ByteBuffer.allocate(array.length * 4);
			for (int index = 0; index < array.length; index++) {
				buffer.putInt(array[index]);
			}
			return buffer.array();
		}

		// binario --> valor
		public static void decodeValor(byte[] value) {

			ByteBuffer buffer = ByteBuffer.wrap(value);

			int array[] = new int[Parametros.resolucionCubo];

			// Da byte a array lineare
			for (int i = 0; i < array.length; i++) {
				array[i] = buffer.getInt();
			}

			//ORDINE!!!
			int cube[][][][][] = new int[24][64][64][10][10];
			int index = 0;
			// Da array lineare a 5D
			for (int tiempo = 0; tiempo < 24; tiempo++) {
				for (int lat = 0; lat < 64; lat++) {
					for (int lon = 0; lon < 64; lon++) {
						for (int prof = 0; prof < 10; prof++) {
							for (int temp = 0; temp < 10; temp++) {
								cube[tiempo][lat][lon][prof][temp] = array[index];
								index++;
							}
						}
					}
				}
			}
		}


}
