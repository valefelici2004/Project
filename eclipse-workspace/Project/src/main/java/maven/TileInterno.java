package maven;

public class TileInterno extends Tile{

	// Costruttore per il livello mensile
    public TileInterno(int año, int mes) {
        this.nTiempo = 1;
        iTiempo.año = año;
        iTiempo.mes = mes;
        arrayTile = new int[Tiempo.diasMes(año, mes)]
                           [Parametros.celdasEspacioCubo]
                           [Parametros.celdasEspacioCubo]
                           [Parametros.celdasPTCubo]
                           [Parametros.celdasPTCubo];
    }

    // Costruttore per il livello annuale
    public TileInterno(int año) {
        this.nTiempo = 0;
        iTiempo.año = año;
        arrayTile = new int[Tiempo.añoBisesto(año)]
                           [Parametros.celdasEspacioCubo]
                           [Parametros.celdasEspacioCubo]
                           [Parametros.celdasPTCubo]
                           [Parametros.celdasPTCubo];
    }
    
}
