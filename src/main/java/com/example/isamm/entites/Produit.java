package com.example.isamm.entites;
import javax.persistence.*;

@Entity
@Table
public class Produit {

    @Id
    @GeneratedValue
    private  Long id ;

    @Column
    private  String resolution ;

    @Column
    private  float prix ;

    @Column
    private String unite ;

    public Produit(Long id, String resolution, float prix, String unite ) {
        this.id= id;
        this.resolution = resolution;
        this.prix = prix;
        this.unite = unite;
    }

    public Produit() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id= id;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }
}
