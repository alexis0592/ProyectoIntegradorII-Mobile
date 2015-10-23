package co.edu.udea.pi2.ubicameudea.persistance.contract;

/**
 * Created by Alexis on 15/06/15.
 */
public class UbicacionContract extends PersistanceContract {

    public static final String TABLE_NAME = "UBICACION";

    public UbicacionContract() {
        super();
    }

    public static final class Column {
        public static final String ID_UBICACION = "id_ubicacion";
        public static final String ID_BLOQUE = "id_bloque";
        public static final String OFICINA = "oficina";
        public static final String LATITUD = "latitud";
        public static final String LONGITUD = "longitud";
        public static final String ID_DEPARTAMENTO = "id_departamento";
        public static final String ID_UNIDAD = "id_unidad";

        public Column() {
            super();
        }

        public static final String[] getAllColumns() {
            return (new String[]{ID_UBICACION, ID_BLOQUE, OFICINA, LATITUD, LONGITUD, ID_DEPARTAMENTO, ID_UNIDAD});
        }

    }
}
