package maven;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

public class Tile {
	byte nEspacio = 1;
	int iLon = 1;
	int iLat = 2;
	byte nProf = 1;
	int iProf = 2;
	byte nTemp = 1;
	int iTemp = 1;
	byte nTiempo = 0;
	Tiempo iTiempo = new Tiempo();

	int[][][][][] arrayTile = new int[Parametros.celdasTiempoCubo][Parametros.celdasEspacioCubo][Parametros.celdasEspacioCubo][Parametros.celdasPTCubo][Parametros.celdasPTCubo]; // array 5 dimensiones --> ipercubo

	// N.B.
	// niveles 0,1,2...
	// tiles por el nivel 0:1, por el nivel 1:0,1,2, por el nivel 2:0,1,2,3,4,5,6
	// elementos empiezan desde 0...63, 0...9
	// En input es necesario poner estos numeros

	// LONGITUD
	public ValorReal longitudCubo(int j) {

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

	public double longitudNetCDF(double lon) {

		double resolutionNetCDF = (double)(Parametros.x2 - Parametros.x1) / Parametros.celdasLonNet;
		double indiceArrayNetCDF = Math.floor((lon - Parametros.x1) / resolutionNetCDF);
		if (indiceArrayNetCDF == 476) {
			indiceArrayNetCDF--;
		}
		return indiceArrayNetCDF;
	}

	// LATITUD
	public ValorReal latitudCubo(int j) {

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

	public double latitudNetCDF(double lat) {

		double resolutionNetCDF = (double)(Parametros.y2 - Parametros.y1) / Parametros.celdasLatNet;
		double indiceArrayNetCDF = Math.floor((lat - Parametros.y1) / resolutionNetCDF);
		if (indiceArrayNetCDF == 401) {
			indiceArrayNetCDF--;
		}
		return indiceArrayNetCDF;
	}

	// PROFUNDIDAD
	public ValorReal profundidadCubo(int j) {

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

	public int profundidadNetCDF(double p) {
		for (int i = 0; i < 14; i++) {
			if (p >= Parametros.arrayProf[i])
				return i;
		}
		return 14;
	}

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

		// Objecto buffer
		// A container for data of a specific primitive type
		// A buffer is a linear, finite sequence of elements of a specific primitive
		// type.
		ByteBuffer buffer = ByteBuffer.allocate(Parametros.resolucionBuffer);
		
		

		// Buffer alloca en cadena
		buffer.put((byte) nTiempo); // 1 byte por nivel

		// ***************************
		buffer.putInt(iTiempo.año); // 8 byte por index tile
		buffer.putInt(iTiempo.mes);
		buffer.putInt(iTiempo.dia);
		buffer.put((byte) nEspacio);
		buffer.putInt(iLat);
		buffer.putInt(iLon);
		buffer.put((byte) nProf);
		buffer.putInt(iProf);
		buffer.put((byte) nTemp);
		buffer.putInt(iTemp);

		return buffer.array(); // Returns the byte array that backs this buffer
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
	public byte[] encodeValor() throws IOException {
		
		ByteBuffer buffer = ByteBuffer.allocate(Parametros.resolucionCubo * 4);
		
		int i = 0;
		for (int tiempo = 0; tiempo < 24; tiempo++) {
			for (int lat = 0; lat < 64; lat++) {
				for (int lon = 0; lon < 64; lon++) {
					for (int prof = 0; prof < 10; prof++) {
						for (int temp = 0; temp < 10; temp++) {

							buffer.putInt(arrayTile[tiempo][lat][lon][prof][temp]);
						}
					}
				}
			}
		}
		return buffer.array();
	}

	// BINARIO->VALOR
	public void decodeValor(byte[] valorBin) throws IOException, ClassNotFoundException {
		
		ByteBuffer buffer = ByteBuffer.wrap(valorBin);

		for (int tiempo = 0; tiempo < 24; tiempo++) {
			for (int lat = 0; lat < 64; lat++) {
				for (int lon = 0; lon < 64; lon++) {
					for (int prof = 0; prof < 10; prof++) {
						for (int temp = 0; temp < 10; temp++) {
							arrayTile[tiempo][lat][lon][prof][temp] = buffer.getInt();
							;

						}
					}
				}
			}
		}
		
	}
	
	public void safe(BaseDatos db) throws IOException {
		byte[] clave = encodeClave();
		byte[] valor = encodeValor();
		db.put(clave, valor);
	}
	
	public void load(BaseDatos db) throws ClassNotFoundException, IOException {
		byte[] clave = encodeClave();
		byte[] valor = db.get(clave);
		decodeValor(valor);
	}

}
