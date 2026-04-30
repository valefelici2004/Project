package maven;

import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;

public class RocksDBBaseDatos implements BaseDatos {
	
	RocksDB db;
	
	//Constructor que cuando inizializo el objecto abre el db
	public RocksDBBaseDatos(String file) throws RocksDBException {
	RocksDB.loadLibrary();
	Options options = new Options().setCreateIfMissing(true); //ajustes
	db = RocksDB.open(options, file);
	}
	
	//metodo PUT
	@Override
	public void put(Tile tile) throws RocksDBException{
		byte clave[] = tile.encodeClave();
		byte valor[] = tile.encodeValor();
		db.put(clave, valor);
	}
	
	//metodo GET
	
	
	//metodo CLOSE
	@Override
	public void close() {
		db.close();
	}
}
