package model.repository;

import model.vo.Dentista;

import java.util.ArrayList;
import java.util.List;

public class ListaDentista {
    private List<Dentista> listaDent = new ArrayList<>();

    public void addDent(Dentista d){
        listaDent.add(d);
    }

    public List<Dentista> listaDent(){
        return listaDent;
    }
}
