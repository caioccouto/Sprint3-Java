package model.repository;

import model.vo.Doacao;

import java.util.ArrayList;
import java.util.List;

public class ListaDoacao {
    List<Doacao> listaDoacao = new ArrayList<>();

    public void addDoacao(Doacao d){
        listaDoacao.add(d);
    }

    public List<Doacao> listaDoacao(){
        return listaDoacao;
    }
}
