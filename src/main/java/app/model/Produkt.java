package app.model;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;


@Entity
public class Produkt {
    @Id
    private String Hauptartikelnr;
    private String Artikelname;
    private String Hersteller;
    private String Beschreibung;
    private String Materialangaben;
    private String Geschlecht;
    private String Produktart;
    private String Ärmel;
    private String Bein;
    private String Kragen;
    private String Herstellung;
    private String Taschenart;
    private String Grammatur;
    private String Material;
    private String Ursprungsland;
    private String Bildname;


    public String getHauptartikelnr() {
        return Hauptartikelnr;
    }


    public void setHauptartikelnr(String hauptartikelnr) {
        Hauptartikelnr = hauptartikelnr;
    }


    public String getArtikelname() {
        return Artikelname;
    }


    public void setArtikelname(String artikelname) {
        Artikelname = artikelname;
    }


    public String getHersteller() {
        return Hersteller;
    }


    public void setHersteller(String hersteller) {
        Hersteller = hersteller;
    }


    public String getBeschreibung() {
        return Beschreibung;
    }


    public void setBeschreibung(String beschreibung) {
        Beschreibung = beschreibung;
    }


    public String getMaterialangaben() {
        return Materialangaben;
    }


    public void setMaterialangaben(String materialangaben) {
        Materialangaben = materialangaben;
    }


    public String getGeschlecht() {
        return Geschlecht;
    }


    public void setGeschlecht(String geschlecht) {
        Geschlecht = geschlecht;
    }


    public String getProduktart() {
        return Produktart;
    }


    public void setProduktart(String produktart) {
        Produktart = produktart;
    }


    public String getÄrmel() {
        return Ärmel;
    }


    public void setÄrmel(String ärmel) {
        Ärmel = ärmel;
    }


    public String getBein() {
        return Bein;
    }


    public void setBein(String bein) {
        Bein = bein;
    }


    public String getKragen() {
        return Kragen;
    }


    public void setKragen(String kragen) {
        Kragen = kragen;
    }


    public String getHerstellung() {
        return Herstellung;
    }


    public void setHerstellung(String herstellung) {
        Herstellung = herstellung;
    }


    public String getTaschenart() {
        return Taschenart;
    }


    public void setTaschenart(String taschenart) {
        Taschenart = taschenart;
    }


    public String getGrammatur() {
        return Grammatur;
    }


    public void setGrammatur(String grammatur) {
        Grammatur = grammatur;
    }


    public String getMaterial() {
        return Material;
    }


    public void setMaterial(String material) {
        Material = material;
    }


    public String getUrsprungsland() {
        return Ursprungsland;
    }


    public void setUrsprungsland(String ursprungsland) {
        Ursprungsland = ursprungsland;
    }


    public String getBildname() {
        return Bildname;
    }


    public void setBildname(String bildname) {
        Bildname = bildname;
    }
}
