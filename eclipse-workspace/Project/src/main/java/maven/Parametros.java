package maven;

public class Parametros {

	// longitud
	static final int x1 = -14;
	static final double x2 = -4.5;

	// latitud
	static final int y1 = 38;
	static final int y2 = 46;

	// profundidad
	static final int minP = 0;
	static final int maxP = 4000;

	// temperadura
	static final int minT = -10;
	static final int maxT = 30;

	//Cubo
	static final int celdasEspacioCubo = 64;
	static final int celdasPTCubo = 10;

	//NetCDF
	static final int celdasLonNet = 476;
	static final int celdasLatNet = 401;
	static final int arrayProf[] = { 4000, 3000, 2000, 1500, 1000, 500, 400, 250, 150, 125, 75, 35, 20, 10 };
	
	static final int resolucionBuffer = 45;
	static final int resolucionCubo = 64*64*10*10*24;

}
