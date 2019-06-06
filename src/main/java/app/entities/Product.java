package app.entities;


import com.opencsv.bean.CsvBindByName;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.List;


@Entity
@Table(name = "produkte")
public class Product {

    @Id
    @CsvBindByName(column = "Hauptartikelnr")
    private String hauptartikelnr;
    @CsvBindByName(column = "Artikelname")
    private String artikelname;
    @CsvBindByName(column = "Hersteller")
    private String hersteller;
    @CsvBindByName(column = "Beschreibung")
    private String beschreibung;
    @CsvBindByName(column = "Materialangaben")
    private String materialangaben;
    @CsvBindByName(column = "Geschlecht")
    private String geschlecht;
    @CsvBindByName(column = "Produktart")
    private String produktart;
    @CsvBindByName(column = "Ärmel")
    private String aermel;
    @CsvBindByName(column = "Bein")
    private String bein;
    @CsvBindByName(column = "Kragen")
    private String kragen;
    @CsvBindByName(column = "Herstellung")
    private String herstellung;
    @CsvBindByName(column = "Taschenart")
    private String taschenart;
    @CsvBindByName(column = "Grammatur")
    private String grammatur;
    @CsvBindByName(column = "Material")
    private String material;
    @CsvBindByName(column = "Ursprungsland")
    private String ursprungsland;
    @CsvBindByName(column = "Bildname")
    private String bildname;

    public Product() {
    }

    public Product(List<String> values) {
        if (values.size() == 16) {
            this.hauptartikelnr = values.get(0);
            this.artikelname = values.get(1);
            this.hersteller = values.get(2);
            this.beschreibung = values.get(3);
            this.materialangaben = values.get(4);
            this.geschlecht = values.get(5);
            this.produktart = values.get(6);
            this.aermel = values.get(7);
            this.bein = values.get(8);
            this.kragen = values.get(9);
            this.herstellung = values.get(10);
            this.taschenart = values.get(11);
            this.grammatur = values.get(12);
            this.material = values.get(13);
            this.ursprungsland = values.get(14);
            this.bildname = values.get(15);
        }
    }


    public String getHauptartikelnr() {
        return hauptartikelnr;
    }


    public void setHauptartikelnr(String hauptartikelnr) {
        this.hauptartikelnr = hauptartikelnr;
    }


    public String getArtikelname() {
        return artikelname;
    }


    public void setArtikelname(String artikelname) {
        this.artikelname = artikelname;
    }


    public String getHersteller() {
        return hersteller;
    }


    public void setHersteller(String hersteller) {
        this.hersteller = hersteller;
    }


    public String getBeschreibung() {
        return beschreibung;
    }


    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }


    public String getMaterialangaben() {
        return materialangaben;
    }


    public void setMaterialangaben(String materialangaben) {
        this.materialangaben = materialangaben;
    }


    public String getGeschlecht() {
        return geschlecht;
    }


    public void setGeschlecht(String geschlecht) {
        this.geschlecht = geschlecht;
    }


    public String getProduktart() {
        return produktart;
    }


    public void setProduktart(String produktart) {
        this.produktart = produktart;
    }


    public String getAermel() {
        return aermel;
    }


    public void setAermel(String aermel) {
        this.aermel = aermel;
    }


    public String getBein() {
        return bein;
    }


    public void setBein(String bein) {
        this.bein = bein;
    }


    public String getKragen() {
        return kragen;
    }


    public void setKragen(String kragen) {
        this.kragen = kragen;
    }


    public String getHerstellung() {
        return herstellung;
    }


    public void setHerstellung(String herstellung) {
        this.herstellung = herstellung;
    }


    public String getTaschenart() {
        return taschenart;
    }


    public void setTaschenart(String taschenart) {
        this.taschenart = taschenart;
    }


    public String getGrammatur() {
        return grammatur;
    }


    public void setGrammatur(String grammatur) {
        this.grammatur = grammatur;
    }


    public String getMaterial() {
        return material;
    }


    public void setMaterial(String material) {
        this.material = material;
    }


    public String getUrsprungsland() {
        return ursprungsland;
    }


    public void setUrsprungsland(String ursprungsland) {
        this.ursprungsland = ursprungsland;
    }


    public String getBildname() {
        return bildname;
    }


    public void setBildname(String bildname) {
        this.bildname = bildname;
    }


    public void copyFields(Product copyProduct) {
        this.hauptartikelnr = copyProduct.getHauptartikelnr();
        this.artikelname = copyProduct.getArtikelname();
        this.hersteller = copyProduct.getHersteller();
        this.beschreibung = copyProduct.getBeschreibung();
        this.materialangaben = copyProduct.getMaterialangaben();
        this.geschlecht = copyProduct.getGeschlecht();
        this.produktart = copyProduct.getProduktart();
        this.aermel = copyProduct.getAermel();
        this.bein = copyProduct.getBein();
        this.kragen = copyProduct.getKragen();
        this.herstellung = copyProduct.getHerstellung();
        this.taschenart = copyProduct.getTaschenart();
        this.grammatur = copyProduct.getGrammatur();
        this.material = copyProduct.getMaterial();
        this.ursprungsland = copyProduct.getUrsprungsland();
        this.bildname = copyProduct.getBildname();
    }
}
