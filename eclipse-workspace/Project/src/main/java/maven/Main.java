package maven;

public class Main {
    public static void main(String[] args) throws Exception {
        Query q = new Query();
        q.nTiempo = 2;
        q.año = 2026;
        q.mes = 3;
        q.dia = 21;
        q.nEspacio = 0;
        q.iLat = 0;
        q.iLon = 0;
        q.nProf = 0;
        q.iProf = 0;
        q.nTemp = 0;
        q.iTemp = 0;
        q.pathNetCDF = "/Users/valefelici2004/Desktop/roms_002_20260321_0000.nc4";
        q.pathDB = "/Users/valefelici2004/Desktop/fileDBVALE";
        
        System.out.println("Inizio calcolo...");
        SecundoAlgoritmoTopDownDia.esegui(q);
        System.out.println("Fine!");
    }
}