package bstrom.akimts.demo_mvc.models;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
public class Produit {

    private int id;
    private String marque;
    private String modele;
    private double prix;
    private boolean enVente;

}
