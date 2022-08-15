import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainChallenge {
    private static ListaAkcji lista = new ListaAkcji();
    public static void main(String[] args) {
        Koszyk koszyk = new Koszyk("Hubert");

        Akcja przedmiot = new Akcja("Cola", 4.50, 10);
        lista.dodajAkcje(przedmiot);

        przedmiot = new Akcja("Baton", 2, 20);
        lista.dodajAkcje(przedmiot);

        przedmiot = new Akcja("Woda", 1.50, 25);
        lista.dodajAkcje(przedmiot);

        przedmiot = new Akcja("Paliwo", 8, 100);
        lista.dodajAkcje(przedmiot);

        przedmiot = new Akcja("Ziemniaki", 10, 30);
        lista.dodajAkcje(przedmiot);
        System.out.println(lista+"\n");

        dodajPrzedmiot(koszyk, "Ziemniaki", 10);
        dodajPrzedmiot(koszyk, "Ziemniaki", 20);
        dodajPrzedmiot(koszyk, "Paliwo", 20);
        dodajPrzedmiot(koszyk, "Cola", 2);

        usunPrzedmiot(koszyk,"Paliwo", 11);
        usunPrzedmiot(koszyk,"Cola", 1);
        System.out.println("KOSZYK PRZED CHECKOUTEM");
        System.out.println(koszyk);
        checkOut(koszyk);

        System.out.println("KOSZYK PO CHECKOUCIE");
        System.out.println(koszyk);
        System.out.println("\n"+lista+"\n");

    }

    public static boolean checkOut(Koszyk koszyk){
        Map<Akcja, Integer> listaWKoszyku = koszyk.getList();
        if (listaWKoszyku == null) {
            System.out.println("Koszyk nie istnieje, badz nic w nim nie ma");
            return false;
        } else {
            for (Map.Entry<Akcja, Integer> m : listaWKoszyku.entrySet()){
                Akcja przedmiot = m.getKey();
                if (przedmiot.sprzedajAkcje(m.getValue()) > 0) {
                    System.out.println(m.getValue());
                    koszyk.usunZKoszyka(przedmiot.getNazwa(), m.getValue());
                }


            }
            return true;
        }
    }

    public static int dodajPrzedmiot(Koszyk koszyk, String nazwa, int ilosc){
        Akcja przedmiot = lista.get(nazwa);
        if (przedmiot == null) {
            System.out.println("Nie sprzedajemy tego przedmiotu: " + nazwa);
            return 0;
        }
        if (lista.zarezerwujPrzedmiot(przedmiot, ilosc) != 0){
            koszyk.dodajDoKoszyka(przedmiot,ilosc);
            return ilosc;
        }
        return 0;
    }

    public static int usunPrzedmiot(Koszyk koszyk, String nazwa, int ilosc){
        Akcja przedmiot = koszyk.getPrzedmiot(nazwa);
        if (przedmiot == null){
            System.out.println("Nie ma takiego przedmiotu");
            return 0;
        }
        if (lista.odrezerwujPrzedmiot(przedmiot, ilosc) > 0) {
            koszyk.usunZKoszyka(nazwa,ilosc);
            return ilosc;
        }
        return 0;
    }
}
