package model.vo;

import java.time.LocalDate;

public class Triagem {
    private int id;
    private static int contador = 1;
    private int idBenef;
    private int idVol;
    private LocalDate dtTriagem;
    private String resultado;

    public Triagem(int idBenef, int idVol, LocalDate dtTriagem, String resultado) {
        this.id = contador++;
        this.idBenef = idBenef;
        this.idVol = idVol;
        this.dtTriagem = dtTriagem;
        this.resultado = resultado;
    }

    public static void setContador(int valor){
        contador = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdBenef() {
        return idBenef;
    }

    public void setIdBenef(int idBenef) {
        this.idBenef = idBenef;
    }

    public int getIdVol() {
        return idVol;
    }

    public void setIdVol(int idVol) {
        this.idVol = idVol;
    }

    public LocalDate getDtTriagem() {
        return dtTriagem;
    }

    public void setDtTriagem(LocalDate dtTriagem) {
        this.dtTriagem = dtTriagem;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                ", ID Benef: " + idBenef +
                ", ID Vol: " + idVol +
                ", DT Triagem: " + dtTriagem +
                ", Resultado: " + resultado;
    }
}
