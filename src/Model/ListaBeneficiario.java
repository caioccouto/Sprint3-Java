package Model;

import java.util.ArrayList;
import java.util.List;

public class ListaBeneficiario {
    private List<Beneficiario> listaBenef = new ArrayList<>();

    public void addBenef(Beneficiario b){
        listaBenef.add(b);
    }

    public List<Beneficiario> listaBenef(){
        return listaBenef;
    }
}
