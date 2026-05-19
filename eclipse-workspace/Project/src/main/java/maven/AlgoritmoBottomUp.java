package maven;

import org.rocksdb.RocksDB;

public class AlgoritmoBottomUp {
	public static void main(String[] args) {
		
		RocksDB db = new RocksDB("/Users/valefelici2004/Desktop/file\")
        
		int mes;
		int año;
		int tiempo = Tiempo.diasMes(año, mes);
		int arrayTileMes[][][][][] = new int[tiempo][Parametros.celdasEspacioCubo][Parametros.celdasEspacioCubo][Parametros.celdasPTCubo][Parametros.celdasPTCubo];

		for(int tiempoMes=0; tiempoMes<tiempo; tiempoMes++) {
		for (int lat = 0; lat < 64; lat++) {
			for (int lon = 0; lon < 64; lon++) {
				for (int prof = 0; prof < 10; prof++) {
					for (int temp = 0; temp < 10; temp++) {
						
						
						//sumo los 31 cubos 
						int suma = 0;
						for(int t=0; t<24; t++) {
						
							suma += 
							
						}
						
						arrayTileMes[tiempoMes][lat][lon][prof][temp] = suma;
					}
				}
			}
		}
		}

	}

}
