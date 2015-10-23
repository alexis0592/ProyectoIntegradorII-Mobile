package co.edu.udea.pi2.ubicameudea.model.dto;

/**
 * Created by Alexis on 18/06/15.
 */
public class Bloque {
    private Integer idBloque;
    private String numBloque;

    public Bloque(){
        super();
    }

    public Integer getIdBloque() {
        return idBloque;
    }

    public void setIdBloque(Integer idBloque) {
        this.idBloque = idBloque;
    }

    public String getNumBloque() {
        return numBloque;
    }

    public void setNumBloque(String numBloque) {
        this.numBloque = numBloque;
    }
}
