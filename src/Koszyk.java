import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Koszyk {
    private final String nazwa;
    // lista przedmiotow w koszyku, Key = przedmiot, Value = ilosc
    private final Map<Akcja, Integer> list;

    public Koszyk(String nazwa) {
        this.nazwa = nazwa;
        this.list = new HashMap<>();
    }
    // dodaje przedmiot do koszyka
    public int dodajDoKoszyka(Akcja akcja, int ilosc){
        // sprawdzam czy przedmiot jest prawdziwy i czy ilosc jest wieksza niz 0
        if ((akcja != null) && (ilosc > 0)){
            // liczba która już znajduje się w koszyku, zapisuje i nadpisuje ilość
            int wKoszyku = list.getOrDefault(akcja, 0);
            // dodaje przedmiot i ilosc do mapy koszyka
            list.put(akcja, ilosc + wKoszyku);
            return wKoszyku;
        }
        return 0;
    }

    public int usunZKoszyka(String nazwa, int ilosc){
        Akcja przedmiot = getPrzedmiot(nazwa);
        if (przedmiot == null){
            return 0;
        }
        if (ilosc > 0){
            for (Map.Entry<Akcja, Integer> m : list.entrySet()) {
                if (m.getKey().getNazwa().equals(nazwa)){
                    int k = m.getValue();
                    k = k - ilosc;
                    list.put(przedmiot, k);
                }
            }
            return ilosc;
        }

        return 0;
    }

    public Akcja getPrzedmiot(String nazwa){
        for (Map.Entry<Akcja, Integer> m : list.entrySet()) {
            if (m.getKey().getNazwa().equals(nazwa)){
                return m.getKey();
            }
        }
        return null;
    }

    public Map<Akcja, Integer> getList() {
        return new LinkedHashMap<>(list);
    }

    public int getIloscWKoszyku(String nazwa){
        for (Map.Entry<Akcja, Integer> m : list.entrySet()) {
            if (m.getKey().getNazwa().equals(nazwa)){
                return m.getValue();
            }
        }
        return 0;
    }

    public Map<Akcja, Integer> Akcja(){
        return Collections.unmodifiableMap(list);
    }

    @Override
    public String toString() {
        String s = "Koszyk " + nazwa + " zawiera " + list.size() + ((list.size() == 1) ? " przedmiot." : " przedmiotow.")+"\n";
        double calkowityKoszt = 0.0;
        for (Map.Entry<Akcja, Integer> przedmiot : list.entrySet()){
            s = s + przedmiot.getKey() + przedmiot.getValue() + " sztuk w koszyku.\n";
            calkowityKoszt = calkowityKoszt + (przedmiot.getKey().getCena() * przedmiot.getValue());
        }
        return s + "\tLaczna cena koszyka: " + calkowityKoszt;
    }
}
