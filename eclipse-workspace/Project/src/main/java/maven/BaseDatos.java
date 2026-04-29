package maven;

import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;

public class BaseDatos {
	
	//metodo put
	static void Put(Tile tile) throws RocksDBException {
		RocksDB.loadLibrary(); //carga las librerias
		Options options = new Options().setCreateIfMissing(true); //ajustes
		RocksDB db = RocksDB.open(options, "mioDB"); //abre la base de datos
		
		byte[] clave = Tile.encodeClave(tile);
		byte[] valor = Tile.encodeValor(tile);
		db.put(clave, valor); //guarda el valor en byte, lo que calcule antes, clave-valor 
		
		db.close(); 
	}
	//metodo get 
	static Tile Get(Tile tile) throws RocksDBException {
		RocksDB.loadLibrary(); //carga las librerias
		Options options = new Options().setCreateIfMissing(true); //ajustes
		RocksDB db = RocksDB.open(options, "mioDB"); //abre la base de datos
		
		byte[] clave = Tile.encodeClave(tile);
		byte[] value = db.get(clave); //guarda el valor en byte, lo que calcule antes, clave-valor 
		Tile.decodeValor(value);
		
		db.close();
		return tile;
	}
}
