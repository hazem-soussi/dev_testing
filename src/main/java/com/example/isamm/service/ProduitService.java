package com.example.isamm.service;

import com.example.isamm.entites.Produit;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProduitService {
    public List<Produit> listProduit() ;
    public Produit saveProduit(Produit produit);
    public Produit getProduitById(Long id);
    public void deleteProduitById(Long id);
    public void updateProduit(Produit produit);

    public Page<Produit> getPageProduits(int num , int taille ) ;
}

