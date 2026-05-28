package maven;

public interface BaseDatos extends AutoCloseable {

	// PUT
	void put(byte[] clave, byte[] valor) throws Exception;

	// GET
	byte[] get(byte[] clave) throws Exception;
}
