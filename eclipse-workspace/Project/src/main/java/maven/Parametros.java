package maven;

public class Parametros {

	// longitud
	public static double x1 = -14;
	public static double x2 = -4.5;

	// latitud
	public static double y1 = 38;
	public static double y2 = 46;

	// profundidad
	public static double minP = 0;
	public static double maxP = 4000;

	// temperadura
	public static double minT = -10;
	public static double maxT = 30;

	//Cubo
	public static double celdasEspacioCubo = 64;
	public static double celdasPTCubo = 10;

	//NetCDF
	public static double celdasLonNet = 476;
	public static double celdasLatNet = 401;
	
	public static int arrayProf[] = { 4000, 3000, 2000, 1500, 1000, 500, 400, 250, 150, 125, 75, 35, 20, 10 };
	
	public static int resolucionBuffer = 45;
	public static int resolucionCubo = 64*64*10*10*24;

}
