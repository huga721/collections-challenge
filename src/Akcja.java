import java.util.Comparator;

public class Akcja implements Comparable<Akcja> {
    private final String nazwa;
    private double cena;
    private int ilosc;
    private int zarezerwowane;

    public Akcja(String nazwa, double cena) {
        this.nazwa = nazwa;
        this.cena = cena;
        this.ilosc = 0;
    }

    public Akcja(String nazwa, double cena, int ilosc) {
        this.nazwa = nazwa;
        this.cena = cena;
        this.ilosc = ilosc;
    }

    public String getNazwa() {
        return nazwa;
    }

    public double getCena() {
        return cena;
    }

    public int getIlosc() {
        return ilosc - zarezerwowane;
    }

    public int getZarezerwowane(){
        return zarezerwowane;
    }

    public void setCena(double cena) {
        if (cena > 0) {
            this.cena = cena;
        }
    }

    public int sprzedajAkcje(int ilosc){
        int k = getZarezerwowane();
        if (ilosc > 0 && k >= ilosc){
            this.zarezerwowane = k - ilosc;
            return ilosc;
        }
        return 0;
    }

    public void zmienIloscAkcji(int ilosc){
        // aktualna ilosc przedmiotow
        int nowaIlosc = this.ilosc + ilosc;
        if (nowaIlosc >= 0){
            this.ilosc = nowaIlosc;
        }
    }

    public int zarezerwuj(int ilosc){
        int nowaIlosc = this.zarezerwowane + ilosc;
        if (ilosc <= this.ilosc){
            zmienIloscAkcji(-ilosc);
            zarezerwowane += ilosc;
            return ilosc;
        }
        return 0;
    }

    public int odrezerwuj(int ilosc){
        int nowaIlosc = this.zarezerwowane - ilosc;
        if (ilosc > 0){
            zmienIloscAkcji(ilosc);
            this.zarezerwowane = nowaIlosc;
            return ilosc;
        }
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this){
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()){
            return false;
        }
        String nazwaObiektu = ((Akcja) obj).getNazwa();

        return this.nazwa.equals(nazwaObiektu);
    }

    @Override
    public int hashCode() {
        return this.nazwa.hashCode();
    }

    @Override
    public int compareTo(Akcja o) {
        if (this == o){
            return 0;
        }

        if (o != null){
            this.nazwa.compareTo(o.getNazwa());
        }
        throw new NullPointerException();
    }

    @Override
    public String toString() {
        return nazwa + " kosztuje " + cena + " | ";
    }
}
