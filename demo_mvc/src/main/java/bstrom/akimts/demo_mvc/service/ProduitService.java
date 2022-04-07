package bstrom.akimts.demo_mvc.service;

import bstrom.akimts.demo_mvc.models.Produit;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProduitService {

    private final List<Produit> produits;

    public ProduitService() {

        produits = new ArrayList<>();
        produits.add(
                Produit.builder()
                        .id(1)
                        .marque("marque 1")
                        .modele("1p1")
                        .prix(10)
                        .enVente(true)
                        .build()
        );
        produits.add(
                Produit.builder()
                        .id(2)
                        .marque("marque 1")
                        .modele("1p2")
                        .prix(12)
                        .enVente(true)
                        .build()
        );
        produits.add(
                Produit.builder()
                        .id(3)
                        .marque("marque 2")
                        .modele("2p1")
                        .prix(5)
                        .enVente(false)
                        .build()
        );
        produits.add(
                Produit.builder()
                        .id(4)
                        .marque("marque 2")
                        .modele("2p2")
                        .prix(50)
                        .enVente(true)
                        .build()
        );

    }

    public Produit getOne(int id){
        return produits.stream().filter(e -> e.getId() == id)
                .findFirst()
                .orElseThrow(); // NoSuchElementException
    }

    public List<Produit> getAll(){
        return new ArrayList<>(produits);
    }

    public List<Produit> getByMarque(String marque){

        List<Produit> primaryList  = getAll();
        List<Produit> lastList = new ArrayList<>();

        for(Produit tempList : primaryList) {
            if (tempList.getMarque().equals(marque)){//.compareTo à voir
                lastList.add(tempList);
            }
        }

        return lastList;

    }


}
