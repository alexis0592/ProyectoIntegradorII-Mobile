package co.edu.udea.pi2.ubicameudea.persistance.contract;

/**
 * Created by Alexis on 15/06/15.
 */
public class BloqueContract extends PersistanceContract {

    public static final String TABLE_NAME = "BLOQUE";

    public BloqueContract(){
        super();
    }

    public static final class Column{

        public static final String ID_BLOQUE = "id_bloque";
        public static final String NUMERO = "numero";

        private Column(){
            super();
        }

        public static final String[] getAllColumns(){
            return (new String[] {ID_BLOQUE, NUMERO});
        }
    }
}
