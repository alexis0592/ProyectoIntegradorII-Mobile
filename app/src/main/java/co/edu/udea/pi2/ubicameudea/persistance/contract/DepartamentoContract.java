package co.edu.udea.pi2.ubicameudea.persistance.contract;

/**
 * Created by Alexis on 15/06/15.
 */
public class DepartamentoContract extends PersistanceContract {

    public static final String TABLE_NAME = "DEPARTAMENTO";

    public DepartamentoContract(){
        super();
    }

    public static final class Column{

        public static final String ID_DEPARTAMENTO = "id_departamento";
        public static final String NOMBRE = "nombre";
        public static final String ID_UNIDAD = "id_unidad";

        public Column(){
            super();
        }

        public static final String[] getAllColumns(){
            return (new String[] {ID_DEPARTAMENTO, NOMBRE, ID_UNIDAD});
        }
    }
}
