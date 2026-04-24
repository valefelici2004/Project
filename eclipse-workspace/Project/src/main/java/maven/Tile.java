package maven;

public class Tile {
	int nLon;
	int iLon;
	int nLat;
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
	ValorReal longitudCubo(double j) {

		double nTiles = Math.pow(2, nLon + 1) - 1; // calculo el numero de tiles
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
	ValorReal latitudCubo(double j) {

		double nTiles = Math.pow(2, nLat + 1) - 1;
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
	ValorReal profundidadCubo(double j) {

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
	ValorReal temperaturaCubo(double j) {

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

}
