package maven;

import java.io.File;
import java.io.IOException;

import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;

public class RocksDBBaseDatos implements BaseDatos {
	
	RocksDB db;
	
	//Constructor que cuando inizializo el objecto abre el db
	public RocksDBBaseDatos(String file) throws RocksDBException {
		File dir = new File("/Users/valefelici2004/Desktop/file");
		RocksDB.loadLibrary();
		Options options = new Options().setCreateIfMissing(true); //ajustes
		db = RocksDB.open(options, file);
	}
	
	//metodo PUT
	@Override
	public void put(Tile tile) throws RocksDBException, IOException{
		byte clave[] = tile.encodeClave();
		byte valor[] = tile.encodeValor();
		db.put(clave, valor);
	}
	
	//metodo GET
	@Override
	public void get(Tile tile) throws RocksDBException, ClassNotFoundException, IOException {
		byte clave[] = tile.encodeClave();
		byte valor[] = db.get(clave);
		tile.decodeValor(valor);
	}
	
	//metodo CLOSE
	@Override
	public void close() {
		db.close();
	}
}
