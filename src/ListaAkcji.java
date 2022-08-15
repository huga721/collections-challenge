import java.util.*;

public class ListaAkcji {
    private final Map<String, Akcja> lista;

    public ListaAkcji() {
        this.lista = new TreeMap<>();
    }

    public int dodajAkcje(Akcja akcja){
        // if parametr istnieje
        if (akcja != null){
            // temp = obiekt z mapy, istniejaca lista
            Akcja temp = lista.get(akcja.getNazwa());
            // if obiekt z parametru znajduje sie na liscie
            if (temp != null) {
                akcja.zmienIloscAkcji(temp.getIlosc());
            }
            lista.put(akcja.getNazwa(), akcja);
            return akcja.getIlosc();
        }
        return 0;
    }

    public int sprzedajPrzedmiot(Akcja akcja, int ilosc){
        Akcja temp = lista.get(akcja.getNazwa());
        if ((temp != null) && (ilosc > 0) && (temp.getIlosc() >= ilosc)) {
            akcja.zmienIloscAkcji(-ilosc);
            return ilosc;
        }
        return 0;
    }

    public int zarezerwujPrzedmiot(Akcja akcja, int ilosc){
        // temp to obiekt z mapy
        Akcja temp = lista.get(akcja.getNazwa());
        // jesli taki obiekt jest w mapie
        if ((temp != null) && (ilosc > 0) && (temp.getIlosc() >= ilosc)) {
            temp.zarezerwuj(ilosc);
            return ilosc;
        }
        return 0;
    }

    public int odrezerwujPrzedmiot(Akcja akcja, int ilosc){
        Akcja temp = lista.get(akcja.getNazwa());
        if ((temp != null) && (ilosc>0) && (temp.getZarezerwowane() >= ilosc)){
            temp.odrezerwuj(ilosc);
            return ilosc;
        }
        return 0;
    }

    // getter dla klasy Akcja
    public Akcja get(String nazwa){
        return lista.get(nazwa);
    }

    public Map<String, Akcja> akcje(){
        return Collections.unmodifiableMap(lista);
    }

    public Map<String, Double> cenaProduktow(){
        Map<String, Double> ceny = new LinkedHashMap<>();
        for (Map.Entry<String, Akcja> m : lista.entrySet()){
            ceny.put(m.getKey(),m.getValue().getCena());
        }
        return Collections.unmodifiableMap(ceny);
    }

    @Override
    public String toString() {
        String s = "Lista sklepu\n";
        double wartosc = 0.0;
        double wartoscUnreserved = 0.0;
        double wartoscReserved = 0.0;
        for (Map.Entry<String, Akcja> akcja : lista.entrySet()){
                Akcja przedmiot = akcja.getValue();
                double wartoscAkcji = przedmiot.getCena() * (przedmiot.getIlosc() +przedmiot.getZarezerwowane());
                double wartoscZarezerwowana = przedmiot.getCena() * przedmiot.getZarezerwowane();
                double price = przedmiot.getCena() * przedmiot.getIlosc();
                s = s + akcja.getValue()+ "Jest " + przedmiot.getIlosc() +" sztuk"+ ((przedmiot.getZarezerwowane() != 0) ? "( "
                        + przedmiot.getZarezerwowane()+" zarezerwowanych sztuk)":"")
                        +" o lacznej wartości "
                        + wartoscAkcji + "." + "\n";

                wartoscReserved = wartoscReserved + wartoscZarezerwowana;
                wartosc = wartosc + wartoscAkcji;
                wartoscUnreserved = wartoscUnreserved + price;
            }
        return s + "\t Laczna cena towaru: " + wartosc + " | Cena za towar dostępny: " + wartoscUnreserved
                + " | Cena za towar zarezerwowany: " + wartoscReserved;
    }
}
