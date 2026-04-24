package maven;

import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;

public class BaseDato {
	
	RocksDB.loadLibrary();
	
	Options options = new Options().setCreateIfMissing(true);
    RocksDB db = RocksDB.open(options, "mioDB");
    
	
	public void dataBase(byte arrayClave[], byte arrayValor[]) throws RocksDBException {
		
		
        db.put(arrayClave, arrayValor);
     
	}
	
	 public void close() {
	        db.close();
	    }
}
