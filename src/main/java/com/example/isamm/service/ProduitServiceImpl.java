package com.example.isamm.service;

import com.example.isamm.exception.ProduitNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.isamm.repository.ProduitRepository;
import com.example.isamm.entites.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitServiceImpl implements ProduitService {
    @Autowired
    private ProduitRepository produitRepository ;

    @Override
    public List<Produit> listProduit() {

        return produitRepository.findAll();
    }

    @Override
    public Produit saveProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    @Override
    public Produit getProduitById(Long id) {
        Optional<Produit> opt = produitRepository.findById(id);
        if(opt.isPresent()) {
            return opt.get();
        } else {
            throw new ProduitNotFoundException("Produit with Id : "+id+" Not Found");
        }
    }

    @Override
    public void deleteProduitById(Long id) {
        produitRepository.delete(getProduitById(id));
    }

    @Override
    public void updateProduit(Produit produit) {
        produitRepository.save(produit);
    }

    @Override
    public Page<Produit> getPageProduits(int pageSize, int offset) {
        return   produitRepository.findAll(PageRequest.of(offset,pageSize)) ;

    }
}