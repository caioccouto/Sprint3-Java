package model.repository;

import model.vo.Triagem;

import java.util.ArrayList;
import java.util.List;

public class ListaTriagem {
    List<Triagem> listaTriagem = new ArrayList<>();

    public void addTria(Triagem t){
        listaTriagem.add(t);
    }

    public List<Triagem> listaTriagem(){
        return listaTriagem;
    }
}
