package ohtu.verkkokauppa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Kauppa {

    private Sailytys varasto;
    private Maksu pankki;
    private Ostoskori ostoskori;
    private Viite viitegeneraattori;
    private String kaupanTili;

    public Kauppa() {
        kaupanTili = "33333-44455";
    }

    @Autowired
    public Kauppa(Sailytys varasto, Maksu pankki, Viite viitegeneraattori) {
        this.varasto = varasto;
        this.pankki = pankki;
        this.viitegeneraattori = viitegeneraattori;
        kaupanTili = "33333-44455";
    }

    public void setSailytys(Sailytys sailytys) {
        this.varasto = sailytys;
    }

    public void setMaksu(Maksu maksu) {
        this.pankki = maksu;
    }

    public void setViite(Viite viite) {
        viitegeneraattori = viite;
    }

    public void aloitaAsiointi() {
        ostoskori = new Ostoskori();
    }

    public void poistaKorista(int id) {
        Tuote t = varasto.haeTuote(id);
        varasto.palautaVarastoon(t);
    }

    public void lisaaKoriin(int id) {
        if (varasto.saldo(id) > 0) {
            Tuote t = varasto.haeTuote(id);
            ostoskori.lisaa(t);
            varasto.otaVarastosta(t);
        }
    }

    public boolean tilimaksu(String nimi, String tiliNumero) {
        int viite = viitegeneraattori.uusi();
        int summa = ostoskori.hinta();

        return pankki.tilisiirto(nimi, viite, tiliNumero, kaupanTili, summa);
    }

}
