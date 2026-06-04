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
