package maven;

import java.time.Year;
import java.time.YearMonth;

public class Tiempo {
	int año;
	int mes;
	int dia;

	public int añoBisesto(int año) {
		boolean x = Year.isLeap(año);
		if (x == true)
			return 366;
		else
			return 365;
	}

	public int diasMes(int año, int mes) {
		YearMonth añoMesObjecto = YearMonth.of(año, mes);
		int diasMes = añoMesObjecto.lengthOfMonth();
		return diasMes;
	}
	
	public int celdasTiempo(Tile tile) {
		int nivel = tile.nTiempo;
		if(nivel==2) {
			return 24;
		}
		else if (nivel==1){
			if(diasMes(tile.iTiempo.año, tile.iTiempo.mes)==31) {
				return 31;
			}
			if(diasMes(tile.iTiempo.año, tile.iTiempo.mes)==30) {
				return 30;
			}
			if(diasMes(tile.iTiempo.año, tile.iTiempo.mes)==29) {
				return 29;
			}
			if(diasMes(tile.iTiempo.año, tile.iTiempo.mes)==28) {
				return 28;
			}
		}
		else if(nivel==0) {
			return 365;
		}
		return 0;
	}
}
