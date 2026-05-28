package maven;

import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;

public class RocksDBBaseDatos implements BaseDatos {

	static {
		RocksDB.loadLibrary();
	}

	RocksDB db;

	// Constructor
	public RocksDBBaseDatos(String path) throws RocksDBException {
		try (Options options = new Options()) {
			options.setCreateIfMissing(true);
			this.db = RocksDB.open(options, path);
		}
	}

	// PUT
	@Override
	public void put(byte[] clave, byte[] valor) throws RocksDBException {
		db.put(clave, valor);
	}

	// GET
	@Override
	public byte[] get(byte[] clave) throws RocksDBException {
		return db.get(clave);
	}

	// CLOSE
	@Override
	public void close() {
		db.close();
	}
}
