package maven;

import java.nio.ByteBuffer;

public class EncoderDecoder {

	// clave --> binario
	public static byte[] encodeClave(Tile tile) {
		
		// Objecto buffer
		// A container for data of a specific primitive type
		// A buffer is a linear, finite sequence of elements of a specific primitive
		// type.
		ByteBuffer buffer = ByteBuffer.allocate(Parametros.resolucionBuffer);

		// Buffer alloca en cadena
		buffer.put((byte) tile.nTiempo); // 1 byte por nivel
		buffer.putLong(tile.iTiempo); // 8 byte por index tile
		buffer.put((byte) tile.nLat);
		buffer.putLong(tile.iLat);
		buffer.put((byte) tile.nLon);
		buffer.putLong(tile.iLon);
		buffer.put((byte) tile.nProf);
		buffer.putLong(tile.iProf);
		buffer.put((byte) tile.nTemp);
		buffer.putLong(tile.iTemp);

		return buffer.array(); // Returns the byte array that backs this buffer
	}

	// binario --> clave
	Tile decodeClave(byte clave[]){ 

		ByteBuffer buffer = ByteBuffer.wrap(clave); //Wraps a byte array into a buffer.

		byte nTiempo = buffer.get(); 
		long iTiempo = buffer.getLong(); 
		byte nLat = buffer.get(); 
		long iLat = buffer.getLong(); 
		byte nLon = buffer.get(); 
		long iLon = buffer.getLong(); 
		byte nProf = buffer.get(); 
		long iProf = buffer.getLong();
		byte nTemp = buffer.get(); 
		long iTemp = buffer.getLong();
		
		Tile tile = new Tile();
		tile.nTiempo = (int)nTiempo;
		tile.iTiempo = (int)iTiempo;
		tile.nLat = (int)nLat;
		tile.iLat = (int)iLat;
		tile.nLon = (int)nLon;
		tile.iLon = (int)iLon;
		tile.nProf = (int)nProf;
		tile.iProf = (int)iProf;
		tile.nTemp = (int)nTemp;
		tile.iTemp = (int)iTemp;
		
		return tile;
	}


	// valor --> binario
	public static byte[] encodeValor(int cube[][][][][]) {


		int array[] = new int[Parametros.resolucionCubo];

		//ORDINE!!!
		// Da 5D a array lineare
		int i = 0;
		for (int tiempo = 0; tiempo < 24; tiempo++) {
			for (int lat = 0; lat < 64; lat++) {
				for (int lon = 0; lon < 64; lon++) {
					for (int prof = 0; prof < 10; prof++) {
						for (int temp = 0; temp < 10; temp++) {
							
							array[i] = cube[tiempo][lat][lon][prof][temp];
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
	public static int[][][][][] decodeValor(byte[] value) {

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
		return cube;

	}


}
