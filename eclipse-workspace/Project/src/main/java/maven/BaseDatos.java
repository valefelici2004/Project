package maven;

public interface BaseDatos {

	// PUT
	void put(byte[] clave, byte[] valor) throws Exception;

	// GET
	byte[] get(byte[] clave) throws Exception;

	// CLOSE
	void close();
}
