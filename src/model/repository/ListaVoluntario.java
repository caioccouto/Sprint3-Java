package model.repository;

import model.vo.Voluntario;

import java.util.ArrayList;
import java.util.List;

public class ListaVoluntario {
    List<Voluntario> listaVol = new ArrayList<>();

    public void addVol(Voluntario v){
        listaVol.add(v);
    }

    public List<Voluntario> listaVol(){
        return listaVol;
    }
}
