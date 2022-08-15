import java.util.List;
import java.util.Map;

public class Main {
    private static ListaAkcji lista = new ListaAkcji();
    public static void main(String[] args) {

        Akcja elon = new Akcja("Tesla", 2000,5);
        lista.dodajAkcje(elon);

        elon = new Akcja("Ciastko", 10, 8);
        lista.dodajAkcje(elon);

        elon = new Akcja("Maslo", 5, 3);
        lista.dodajAkcje(elon);

        elon = new Akcja("Herbatka", 4, 16);
        lista.dodajAkcje(elon);

        elon = new Akcja("Czekolada", 6, 1);
        lista.dodajAkcje(elon);

        elon = new Akcja("Mleko", 3, 6);
        lista.dodajAkcje(elon);

        elon = new Akcja("Filet z kurczaka", 20, 1);
        lista.dodajAkcje(elon);

        elon = new Akcja("Filet z kurczaka", 500, 1);
        lista.dodajAkcje(elon);

        System.out.println("=========================ff");

        Koszyk koszyk = new Koszyk("Hubert");

        sprzedajKoszyk(koszyk, "Mleko", 6);
//        sprzedajPrzedmiot(koszyk, "Herbatka", 2);

        System.out.println(koszyk);
        System.out.println("\n"+lista + "xd");

//        elon = new Akcja("Gumka", 2);
//        lista.akcje().put(elon.getNazwa(), elon);

        System.out.println("=========================");
        lista.get("Maslo").zmienIloscAkcji(4);
        System.out.println(lista);
        System.out.println("=========================");
        for (Map.Entry<String, Double> k : lista.cenaProduktow().entrySet()){
            System.out.println(k.getKey() +", cena: "+ k.getValue());
        }
    }

    public static int sprzedajKoszyk(Koszyk koszyk, String nazwa, int ilosc){
        Akcja akcja = lista.get(nazwa);
        if (akcja == null){
            System.out.println("Nie sprzedajemy " + nazwa);
            return 0;
        }
        if (lista.zarezerwujPrzedmiot(akcja, ilosc) != 0){
            koszyk.dodajDoKoszyka(akcja, ilosc);
            return ilosc;
        }
        return 0;
    }
}
