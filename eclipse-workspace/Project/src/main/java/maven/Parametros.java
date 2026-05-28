package maven;

public class Parametros {

	// longitud
	public static final int x1 = -14;
	public static final double x2 = -4.5;

	// latitud
	public static final int y1 = 38;
	public static final int y2 = 46;

	// profundidad
	public static final int minP = 0;
	public static final int maxP = 4000;

	// temperadura
	public static final int minT = -10;
	public static final int maxT = 30;

	// Cubo
	public static final int celdasEspacioCubo = 64;
	public static final int celdasPTCubo = 10;
	public static final int celdasTiempoCubo = 24;

	// NetCDF
	public static final int celdasLonNet = 476;
	public static final int celdasLatNet = 401;
	public static final int arrayProf[] = { 4000, 3000, 2000, 1500, 1000, 500, 400, 250, 150, 125, 75, 35, 20, 10 };

	public static final int resolucionBuffer = 1 * 4 + 4 * 7; //1 byte por niveles de las 4 dimensiones, 4 bytes por los identificadores
	public static final int resolucionCubo =  64 * 64 * 10 * 10;
	
	public static final int meses = 12;
}
