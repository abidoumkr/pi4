import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public final class SumCalculator {
    private SumCalculator() {
    }

    public static void main(String[] args) {
        System.out.println("Calculatrice de somme (terminal).");
        System.out.println("Entrez des nombres séparés par des espaces ou des lignes.");
        System.out.println("Tapez 'total' pour afficher la somme, ou 'exit' pour quitter.");

        double sum = 0.0;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String trimmed = line.trim();
                if (trimmed.isEmpty()) {
                    continue;
                }
                if (trimmed.equalsIgnoreCase("exit")) {
                    break;
                }
                if (trimmed.equalsIgnoreCase("total")) {
                    System.out.printf("Somme actuelle: %.2f%n", sum);
                    continue;
                }

                String[] tokens = trimmed.split("\\s+");
                boolean parsedAny = false;
                for (String token : tokens) {
                    try {
                        sum += Double.parseDouble(token);
                        parsedAny = true;
                    } catch (NumberFormatException ex) {
                        System.out.printf("Valeur ignorée (non numérique): '%s'%n", token);
                    }
                }
                if (parsedAny) {
                    System.out.printf("Somme actuelle: %.2f%n", sum);
                }
            }
        } catch (IOException ex) {
            System.err.println("Erreur de lecture: " + ex.getMessage());
            System.exit(1);
        }

        System.out.printf("Somme finale: %.2f%n", sum);
    }
}
