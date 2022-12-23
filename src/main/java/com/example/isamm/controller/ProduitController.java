package com.example.isamm.controller;

import com.example.isamm.entites.Produit;
import com.example.isamm.exception.ProduitNotFoundException;
import com.example.isamm.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProduitController {
    @Autowired
    private ProduitService vm ;

    @GetMapping("/produits")
    public String f(@RequestParam(value = "message", required = false) String message ,
                    @RequestParam(name = "pageSize" , required = false, defaultValue = "100")int pageSize ,
                    @RequestParam(name = "offset" , required = false , defaultValue = "0")int offset,
                    Model m){
        Page<Produit> produitsListe = vm.getPageProduits(pageSize,offset);
        m.addAttribute("produits", produitsListe) ;
        m.addAttribute("message", message);
        return "produits/produits" ;
    }

    @GetMapping("/produits/new")
    public String f2(Model m){
        Produit produit = new Produit() ;
        m.addAttribute("produit",produit);
        return "/produits/produitsForm" ;
    }
    
    @PostMapping("/produits/save")
    public String saveProduit( @ModelAttribute Produit produit, Model model) {
        vm.saveProduit(produit);
        Long id = vm.saveProduit(produit).getId();
        String message = "Record with id : '"+id+"' is saved successfully !";
        model.addAttribute("message", message);
        return "/produits/produitsForm" ;
    }
    @GetMapping("/produits/edit")
    public String getEditPage(Model model, RedirectAttributes attributes, @RequestParam Long id) {
        String page = null;
        try {
            Produit produit = vm.getProduitById(id);
            model.addAttribute("produit", produit);
            page="/produits/editProduitPage";
        } catch (ProduitNotFoundException e) {
            e.printStackTrace();
            attributes.addAttribute("message", e.getMessage());
            page="redirect:/produits";
        }
        return page;
    }
    @PostMapping("/produits/update")
    public String updateProduit(@ModelAttribute Produit produit, RedirectAttributes attributes) {
        vm.updateProduit(produit);
        Long id = produit.getId();
        attributes.addAttribute("message", "Produit with id: '"+id+"' is updated successfully !");
        return "redirect:/produits";
    }

    @GetMapping("/produits/delete")
    public String deleteProduit(@RequestParam Long id, RedirectAttributes attributes) {
        try {
            vm.deleteProduitById(id);
            attributes.addAttribute("message", "Produit with Id : '"+id+"' is removed successfully!");
        } catch (ProduitNotFoundException e) {
            e.printStackTrace();
            attributes.addAttribute("message", e.getMessage());
        }
        return "redirect:/produits";
    }
}
