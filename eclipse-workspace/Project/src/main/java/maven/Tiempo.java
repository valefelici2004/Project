package maven;

import java.time.Year;
import java.time.YearMonth;

public class Tiempo {
	int año;
	int mes;
	int dia;

	public static int añoBisesto(int año) {
		boolean x = Year.isLeap(año);
		if (x == true)
			return 366;
		else
			return 365;
	}

	public static int diasMes(int año, int mes) {
		YearMonth añoMesObjecto = YearMonth.of(año, mes);
		int diasMes = añoMesObjecto.lengthOfMonth();
		return diasMes;
	}
}
