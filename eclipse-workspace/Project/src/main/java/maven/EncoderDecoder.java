package maven;

import java.nio.ByteBuffer;

public class EncoderDecoder {

		//Clave-->binario
	    public static byte[] encodeKey(int nTiempo, int iTiempo, int nLat, int iLat, int nLon, int iLon, int nProf, int iProf, int nTemp, int iTemp){
	    	
	    	ByteBuffer buffer = ByteBuffer.allocate(45);
	    	
	    	buffer.put((byte) nTiempo); //1 byte por nivel
	    	buffer.putLong(iTiempo); //8 byte por index tile 
	    	buffer.put((byte) nLat);
	    	buffer.putLong(iLat);
	    	buffer.put((byte) nLon);
	    	buffer.putLong(iLon);
	    	buffer.put((byte) nProf);
	    	buffer.putLong(iProf);
	    	buffer.put((byte) nTemp);
	    	buffer.putLong(iTemp);
	    	
	    	return buffer.array(); //???
	    }
/*
	    //Binario-->clave
	    public static int[] decodeKey(byte key[]){ //pasa un array con dimensiones de las celdas en byte 
	    	
	    	ByteBuffer buffer = ByteBuffer.wrap(key);

	        byte nTiempo = buffer.get();
	        long iTiempo = buffer.getLong();
	        byte nLat = buffer.get();
	        long iLat = buffer.getLong();
	        byte nLon = buffer.get();
	        long iLon = buffer.getLong();
	        byte nProf = buffer.get();
	        long iProf = buffer.getLong();
	        byte nTemp = buffer.get();
	        long iTemp = buffer.getLong();
	        
	        return //???
	    }*/

	    //Valor-->binario
	    public static byte[] encodeValue(int cube[][][][][]){
	    	
	    	//L'ordine?!!!
	    	
	    	int array[] = new int[64*64*10*10*24];
	    	
	    	int i = 0;
	    	
	    	//Da 5D a array lineare
	    	for(int tiempo=0; tiempo<24; tiempo++) {
	    		for(int lat=0; lat<64; lat++) {
	    			for(int lon=0; lon<64; lon++) {
	    				for(int prof=0; prof<10; prof++) {
	    					for(int temp=0; temp<10; temp++) {
	    						array[i] = cube[tiempo][lat][lon][prof][temp];
	    						i++;
	    					}
	    				}
	    			}
	    		}
	    	}
	    	
	    	//Da array lineare a byte
	    	ByteBuffer buffer = ByteBuffer.allocate(array.length * 4);
	    	for(int index=0; index<array.length; index++) {
	    		buffer.putInt(array[index]);
	    	}
	    	return buffer.array();
	    }

	    //Binario-->Valor
	    public static int[][][][][] decodeValue(byte[] value){
	    	
	    	ByteBuffer buffer = ByteBuffer.wrap(value);
	    	
	    	int array[] = new int[64*64*10*10*24];
	    	
	    	//Da byte a array lineare
	    	for(int i=0; i<array.length; i++) {
	    		array[i] = buffer.getInt();
	    	}
	    	
	    	int cube [][][][][] = new int [24][64][64][10][10];
	    	int index = 0;
	    	//Da array lineare a 5D
	    	for(int tiempo=0; tiempo<24; tiempo++) {
	    		for(int lat=0; lat<64; lat++) {
	    			for(int lon=0; lon<64; lon++) {
	    				for(int prof=0; prof<10; prof++) {
	    					for(int temp=0; temp<10; temp++) {
	    						cube[tiempo][lat][lon][prof][temp] = array[index];
	    						index++;
	    					}}}}}
	    	return cube;
	    	
	    }
	}

