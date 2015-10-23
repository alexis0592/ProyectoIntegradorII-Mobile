package co.edu.udea.pi2.ubicameudea.persistance.contract;

/**
 * Created by Alexis on 15/06/15.
 */
public class UnidadContract extends PersistanceContract {

    public static final String TABLE_NAME = "UNIDAD";

    public UnidadContract(){
        super();
    }

    public static final class Column {
        public static final String ID_UNIDAD = "id_unidad";
        public static final String NOMBRE = "nombre";
        public static final String ID_TIPO_UNIDAD = "id_tipo_unidad";

        public Column(){
            super();
        }

        public static final String[] getAllColumns(){
            return (new String[]{ID_UNIDAD, NOMBRE, ID_TIPO_UNIDAD});
        }
    }
}
