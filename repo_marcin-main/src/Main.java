import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        double cenaZlotaUSD = 0;
        double kursUSDPLN = 0;
        // Tutaj będę pobierał cene złota, oryginalnie wyrażoną w USD za uncję ze strony Bankiera przy użyciu Jsoup'a.
        try {
            Document doc = Jsoup.connect("https://www.bankier.pl/inwestowanie/profile/quote.html?symbol=ZLOTO").get();
            Element cenaElement = doc.selectFirst(".profilLast");
            // Teraz konwertuje przecinek na kropke, żeby później móc przekonwertować String na double.
            String cenaZlotaString = cenaElement.text().replace(",", ".");
            // Następnie jako, że selektor wyciąga mi np: "2 342,10 USD/uncja" to usuwam wszystkie znaki, które nie są cyfrą ani kropką.
            cenaZlotaUSD = Double.parseDouble(cenaZlotaString.replaceAll("[^\\d.]", ""));
            System.out.println("Cena złota w USD(za uncje): " + cenaZlotaUSD + " USD");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Tutaj pobieram kurs USD w stosunku do naszej potężnej złotówki.
        try {
            String url = "http://api.nbp.pl/api/exchangerates/rates/A/USD/?format=json";
            /* Teraz Scannerem przetwarzam inputStream'a uzyskanego poprzez openStream z powyższego URL'a.
            Następnie ustalam kodowanie znaków a metodą useDelimiter chce uzyskać pobieranie zawartości jako jednego ciągu znaków*/
            String json = new Scanner(new URL(url).openStream(), "UTF-8").useDelimiter("\\A").next();
            JSONObject jsonObject = new JSONObject(json);
            kursUSDPLN = jsonObject.getJSONArray("rates").getJSONObject(0).getDouble("mid");
            System.out.println("Kurs USD/PLN: " + kursUSDPLN + " PLN");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Teraz sprawdzam czy obie kwoty są większe od zera, jeśli tak to obliczam cene złota w PLN mnożeniem.
        if (cenaZlotaUSD > 0 && kursUSDPLN > 0) {
            double cenaZlotaPLN = cenaZlotaUSD * kursUSDPLN;
            //Jako, że wychodził mi wynik z czterema cyframi po przecinku, co było troche bez sensu przy finalnej cenie(co innego przy kursie), to zaokrągliłem to do 2 miejsc po przecinku.
            String cenaZlotaPLNZaokraglona = String.format("%.2f", cenaZlotaPLN);
            System.out.println("Cena złota w PLN(za uncje): " + cenaZlotaPLNZaokraglona + " PLN");
        }
    }
}



//Zadanie 2
/*
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

                List<String> listaImion = new ArrayList<>(10);
                listaImion.add("Anna");
                listaImion.add("Maria");
                listaImion.add("Jan");
                listaImion.add("Piotr");
                listaImion.add("Ewa");
                listaImion.add("Katarzyna");
                listaImion.add("Tomasz");
                listaImion.add("Mateusz");
                listaImion.add("Magdalena");
                listaImion.add("Karolina");

                Map<Integer, String> mapaImionKobiet = new HashMap<>();

                for (int i = 0; i < listaImion.size(); i++) {
                    String imie = listaImion.get(i);
                    if (czyImieKobiece(imie)) {
                        mapaImionKobiet.put(i, imie);
                    }
                }

                for (Map.Entry<Integer, String> entry : mapaImionKobiet.entrySet()) {
                    System.out.println("Numer na liście: " + entry.getKey() + ", Imię: " + entry.getValue());
                }
            }
            
            public static boolean czyImieKobiece(String imie) {
                return imie.endsWith("a");
            }
        }
*/

// Zadanie 1
/*
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        String zdanie = "Pisze przykladowe zdanie po polsku ale bez polskich znakow, ktore ma wiecej niz 50 znakow.";
        System.out.println("Co trzecie znaki w moim zdaniu to: ");

        StringBuilder coTrzeciZnak = new StringBuilder();
        for (int i = 0; i < zdanie.length(); i++) {
            char znak = zdanie.charAt(i);
            if (znak != ' ' && znak != '\n') {
                if ((i + 1) % 3 == 0) {
                    System.out.print(znak);
                } else {
                    coTrzeciZnak.append(znak);
                }
            }
        }
        System.out.println();

        Map<Character, Integer> pozostaleZnaki = new HashMap<>();
        for (int i = 0; i < coTrzeciZnak.length(); i++) {
            char znak = coTrzeciZnak.charAt(i);
            pozostaleZnaki.put(znak, pozostaleZnaki.getOrDefault(znak, 0) + 1);
        }

        System.out.println("Wynik zliczenia pozostalych znakow:");

        for (Map.Entry<Character, Integer> entry : pozostaleZnaki.entrySet()) {
            System.out.println("Znak " + entry.getKey() + " wystapił " + entry.getValue() + " razy.");
        }

        try {
            FileWriter writer = new FileWriter("wynik.txt");
            for (Map.Entry<Character, Integer> entry : pozostaleZnaki.entrySet()) {
                writer.write("Znak " + entry.getKey() + " wystąpił " + entry.getValue() + " razy.\n");
            }
            writer.close();
            System.out.println("Wynik został zapisany do pliku 'wynik.txt'.");
        } catch (IOException e) {
            System.out.println("Wystąpił błąd podczas zapisu do pliku.");
            e.printStackTrace();
        }
    }
}
*/