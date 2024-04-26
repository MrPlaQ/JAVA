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