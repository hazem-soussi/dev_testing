package com.example.isamm.service;

import com.example.isamm.entites.Produit;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@FixMethodOrder(MethodSorters.DEFAULT)
public class ProduitServiceImplTest {

    @Autowired
    private ProduitServiceImpl produitService ;


    @Test
    void Testcase_1() {
        List<Produit> produits = produitService.listProduit();
        assertEquals(produits.size(), 0 );
    }

    @Test
    void Testcase_2() {
        Produit produitData = new Produit(null , "asus",122 ,"DT");
        Produit produit = produitService.saveProduit(produitData);
        assertNotNull(produit);
        assertNotNull(produit.getId());
    }
    @Test
    void Testcase_3() {
        List<Produit> produits = produitService.listProduit();
        assertEquals(produits.size(),1 );
    }

    @Test
    void Testcase_4() {
        List<Produit> produits = produitService.listProduit();
        Produit produit = produitService.getProduitById(produits.get(0).getId());
        assertEquals(produit.getId() , produits.get(0).getId());

    }

    @Test
    void Testcase_5() {
        Produit produitData = produitService.saveProduit(new Produit(null , "asus",122 ,"DT"));
        produitData.setPrix(200);
        produitData.setResolution("HP");
        produitData.setUnite("$");
        produitService.updateProduit(produitData);

        Produit produit = produitService.getProduitById(produitData.getId());

        assertEquals(produitData.getId(), produit.getId());
        assertEquals(produitData.getPrix(),produit.getPrix());
        assertEquals(produitData.getResolution(),produit.getResolution());

    }

    @Test
    void Testcase_6() {
        List<Produit> produits = produitService.listProduit();
        int nb = produits.size();
        produitService.deleteProduitById(produits.get(0).getId());
        produits=produitService.listProduit();
        assertEquals(produits.size(),nb-1);
    }

}