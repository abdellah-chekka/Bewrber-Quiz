import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BewerberQuiz {
    
    // Arrays für Fragen, Antwortmöglichkeiten und richtige Antworten
    static String[] fragen = {
        "Was ist Java?",
        "Was ist eine Klasse?",
        "Was bedeutet OOP?",
        "Wofür wird 'main' in Java verwendet?",
        "Was ist ein Konstruktor?"
    };
    
    static String[][] optionen = {
        {"Eine Programmiersprache", "Eine Kaffeesorte", "Ein Betriebssystem", "Ein Browser"},
        {"Ein Objekt", "Ein Bauplan für Objekte", "Eine Variable", "Eine Schleife"},
        {"Object-Oriented Programming", "Only Official Programs", "Open Office Protocol", "Objekt-Ordnungs-Prinzip"},
        {"Als Startpunkt des Programms", "Für Kommentare", "Für Fehler", "Für Schleifen"},
        {"Eine Methode zum Erstellen von Objekten", "Eine normale Variable", "Eine Schleife", "Ein Kommentar"}
    };
    
    static int[] richtigeAntworten = {1, 2, 1, 1, 1}; // 1 = erste Option, 2 = zweite Option, etc.
    static String[] kategorien = {"Grundlagen", "OOP", "OOP", "Syntax", "OOP"};
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int punkte = 0;
        
        System.out.println("\n==========================================");
        System.out.println(" BEWERBER-QUIZ - u-form Testsysteme");
        System.out.println("==========================================");
        System.out.println("Beantworte die folgenden 5 Fragen:");
        
        // Fragen durchgehen
        for (int i = 0; i < fragen.length; i++) {
            System.out.println("\n" + (i+1) + ". [" + kategorien[i] + "] " + fragen[i]);
            
            // Antwortmöglichkeiten anzeigen
            for (int j = 0; j < optionen[i].length; j++) {
                System.out.println("   " + (j+1) + ". " + optionen[i][j]);
            }
            
            // Antwort einlesen
            System.out.print("Deine Antwort (1-4): ");
            int antwort = scanner.nextInt();
            
            // Prüfen ob richtig
            if (antwort == richtigeAntworten[i]) {
                System.out.println("    Richtig!");
                punkte++;
            } else {
                System.out.println("    Falsch. Richtig wäre: " + richtigeAntworten[i] + ". " + 
                                 optionen[i][richtigeAntworten[i]-1]);
            }
        }
        
        // Ergebnis anzeigen
        double prozent = (punkte * 100.0) / fragen.length;
        
        System.out.println("\n==========================================");
        System.out.println(" DEIN ERGEBNIS");
        System.out.println("==========================================");
        System.out.println("Punkte: " + punkte + " von " + fragen.length);
        System.out.printf("Erfolg: %.1f%%\n", prozent);
        
        String bewertung;
        if (prozent >= 80) bewertung = "Sehr gut! ";
        else if (prozent >= 60) bewertung = "Gut! ";
        else if (prozent >= 40) bewertung = "Befriedigend";
        else bewertung = "Übungsbedarf";
        
        System.out.println("Bewertung: " + bewertung);
        
        // Ergebnis speichern
        try {
            String dateiname = "quiz_ergebnis_" + 
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".txt";
            
            FileWriter writer = new FileWriter(dateiname);
            writer.write("BEWERBER-QUIZ ERGEBNIS\n");
            writer.write("======================\n");
            writer.write("Datum: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")) + "\n");
            writer.write("Punkte: " + punkte + "/" + fragen.length + "\n");
            writer.write("Erfolg: " + String.format("%.1f", prozent) + "%\n");
            writer.write("Bewertung: " + bewertung + "\n");
            writer.close();
            
            System.out.println("\n Ergebnis gespeichert in: " + dateiname);
            
        } catch (IOException e) {
            System.out.println("\n Fehler beim Speichern");
        }
        
        scanner.close();
        System.out.println("\n Quiz beendet. Danke fürs Mitmachen!");
    }
}