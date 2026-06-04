package maven;

public class Main {
    public static void main(String[] args) throws Exception {
        Query q = new Query();
        q.año = 2026;
        q.mes = 3;
        q.nEspacio = 0;
        q.iLat = 0;
        q.iLon = 0;
        q.nProf = 0;
        q.iProf = 0;
        q.nTemp = 0;
        q.iTemp = 0;
        q.pathNetCDF = "/Users/valefelici2004/Desktop/roms_002_20260321_0000.nc4";
        q.pathDB = "/Users/valefelici2004/Desktop/fileDB";
        /*
        System.out.println("Inizio calcolo...");
        SecundoAlgoritmoTopDownDia.esegui(q);
        System.out.println("Fine!");
     */
      
     // Verifica lettura dal DB
        RocksDBBaseDatos db = new RocksDBBaseDatos(q.pathDB);

        TileHoja tileRiletto = new TileHoja();
        // Setta la stessa clave usata nel salvataggio
        tileRiletto.nTiempo      = q.nTiempo;
        tileRiletto.iTiempo.año  = q.año;
        tileRiletto.iTiempo.mes  = q.mes;
        tileRiletto.iTiempo.dia  = q.dia;
        tileRiletto.nEspacio     = q.nEspacio;
        tileRiletto.iLat         = q.iLat;
        tileRiletto.iLon         = q.iLon;
        tileRiletto.nProf        = q.nProf;
        tileRiletto.iProf        = q.iProf;
        tileRiletto.nTemp        = q.nTemp;
        tileRiletto.iTemp        = q.iTemp;

        tileRiletto.load(db);
        System.out.println("Valore [0][0][0][0][0]: " + tileRiletto.arrayTile[0][0][0][0][6]);

        db.close();
    }
}