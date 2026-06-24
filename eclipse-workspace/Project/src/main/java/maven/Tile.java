package maven;

import java.nio.ByteBuffer;

public class Tile {
	byte nTiempo;
	Tiempo iTiempo = new Tiempo();
	
	byte nEspacio;
	int iLon;
	int iLat;
	byte nProf;
	int iProf;
	byte nTemp;
	int iTemp;

	int[][][][][] arrayTile;
	
	// TEMPERATURA
			public ValorReal temperaturaCubo(int j) {

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
	
	// CLAVE->BINARIO
	public byte[] encodeClave() {

		ByteBuffer buffer = ByteBuffer.allocate(Parametros.resolucionClave); //Objecto buffer - container for data
																			  // Buffer alloca en cadena
		buffer.put((byte) nTiempo); // 1 byte por nivel
		buffer.putInt(iTiempo.año); // 4 byte por index 
		buffer.putInt(iTiempo.mes);
		buffer.putInt(iTiempo.dia);
		buffer.put((byte) nEspacio);
		buffer.putInt(iLat);
		buffer.putInt(iLon);
		buffer.put((byte) nProf);
		buffer.putInt(iProf);
		buffer.put((byte) nTemp);
		buffer.putInt(iTemp);

		return buffer.array(); // Devuelve l'array de bytes 
	}

	// BINARIO->CLAVE
	public void decodeClave(byte claveBin[]) {

		ByteBuffer buffer = ByteBuffer.wrap(claveBin);

		nTiempo = buffer.get();
		iTiempo.año = buffer.getInt();
		iTiempo.mes = buffer.getInt();
		iTiempo.dia = buffer.getInt();
		nEspacio = buffer.get();
		iLat = buffer.getInt();
		iLon = buffer.getInt();
		nProf = buffer.get();
		iProf = buffer.getInt();
		nTemp = buffer.get();
		iTemp = buffer.getInt();
	}

	// VALOR->BINARIO
	public byte[] encodeValor() {

		ByteBuffer buffer = ByteBuffer.allocate(arrayTile.length * Parametros.LonLatProfTemp * 4);
	
		for (int tiempo = 0; tiempo < arrayTile.length; tiempo++) {
			for (int lat = 0; lat < Parametros.celdasEspacioCubo; lat++) {
				for (int lon = 0; lon < Parametros.celdasEspacioCubo; lon++) {
					for (int prof = 0; prof < Parametros.celdasPTCubo; prof++) {
						for (int temp = 0; temp < Parametros.celdasPTCubo; temp++) {

							buffer.putInt(arrayTile[tiempo][lat][lon][prof][temp]);
						}
					}
				}
			}
		}
		return buffer.array();
	}

	// BINARIO->VALOR
	public void decodeValor(byte[] valorBin) {

		ByteBuffer buffer = ByteBuffer.wrap(valorBin);

		for (int tiempo = 0; tiempo < arrayTile.length; tiempo++) {
			for (int lat = 0; lat < Parametros.celdasEspacioCubo; lat++) {
				for (int lon = 0; lon < Parametros.celdasEspacioCubo; lon++) {
					for (int prof = 0; prof < Parametros.celdasPTCubo; prof++) {
						for (int temp = 0; temp < Parametros.celdasPTCubo; temp++) {
							arrayTile[tiempo][lat][lon][prof][temp] = buffer.getInt();

						}
					}
				}
			}
		}
	}
	
	// SAVE - Guarda tile (clave+valor) en db
	public void save(BaseDatos db) throws Exception {
		byte[] clave = encodeClave();
		byte[] valor = encodeValor();
		db.put(clave, valor);
	}

	// LOAD - Carga array contadores del tile
	public void load(BaseDatos db) throws Exception {
		byte[] clave = encodeClave();
		byte[] valor = db.get(clave);
		decodeValor(valor);
	}
}
