public class Pracownik {

  public Pracownik(int wiek, String imie)
  {
    super();
    this.wiek = wiek;
    this.imie = imie;
  }
 public int wiek;
 public String imie;
 public String typUmowy;

  public String przedstawSie(String imie) {
    return "Nazywam się: " + imie;
  }
  public String przywitajSie() {
    return "Cześć!";
  }
  }
public class pracownikBiurowy extends Pracownik{

  public pracownikBiurowy(int wiek, String imie) {
    super(wiek, imie);
  }
  pracownikBiurowy pracownik1 = new pracownikBiurowy(55,"Bożena");
  pracownikBiurowy pracownik2 = new pracownikBiurowy(50,"Krzysztof");

  public void przedstawSie () {
    pracownik1.przedstawSie("Nazywam się Bożena");
    pracownik1.przywitajSie();
 
    pracownik2.przedstawSie("Nazywam się Krzysztof");
    pracownik2.przywitajSie();
  }
}