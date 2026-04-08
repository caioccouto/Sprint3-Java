package model.repository;

import model.vo.Doador;

import java.util.ArrayList;
import java.util.List;

public class ListaDoador {
    private List<Doador> listaDoador = new ArrayList<>();

    public void addDoador(Doador d){
        listaDoador.add(d);
    }

    public List<Doador> listaDoador(){
        return listaDoador;
    }
}
