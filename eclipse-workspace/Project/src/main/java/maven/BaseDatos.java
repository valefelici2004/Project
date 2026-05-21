package maven;

public interface BaseDatos {
	
	//put
	void put(Tile tile) throws Exception;
	
	//get
	void get(Tile tile) throws Exception;
	
	//close
	void close();

	/*
	void put(byte[] clave, byte[] valor);

	byte[] get(byte[] clave);

	
	void put(byte[] clave, byte[] valor);

	byte[] get(byte[] clave);*/
	
}
