package castellet.dam.m12.uf2.hibernate;

import java.util.List;
import java.util.Scanner;

import castellet.dam.m12.uf2.model.Deporte;
import castellet.dam.m12.uf2.model.Deportista;

public class MainView {

    private final Scanner scanner;

    public MainView(Scanner scanner) {
        this.scanner = scanner;
    }

    public int showMenu() {
        System.out.println("""

                --------------------------------------
                1. Afegir deporte
                2. Llistar deportes
                3. Afegir deportista
                4. Llistar deportistes
                5. Buscar deportista per nom
                6. Buscar deportistes per ID de deporte
                0. Sortir
                --------------------------------------
                Tria una opció >>>
                """);
        return scanner.nextInt();
    }

    public Deporte deporteForm() {
        System.out.println("\n-------------------------------");

        scanner.nextLine(); // limpiar buffer
        System.out.print("Nom del deporte: ");
        String nombre = scanner.nextLine();

        return new Deporte(nombre);
    }

    public void deporteList(List<Deporte> deportes) {
        System.out.println("\n-------------------------------");
        System.out.println("Llista de deportes:\n");

        if (deportes.isEmpty()) {
            System.out.println("No hi ha deportes.");
            return;
        }

        for (Deporte d : deportes) {
            System.out.println(d.getCod() + " - " + d.getNombre());
        }
    }

    public Deportista deportistaForm(List<Deporte> deportes) {
        System.out.println("\n-------------------------------");

        scanner.nextLine(); // limpiar buffer
        System.out.print("Nom del deportista: ");
        String nombre = scanner.nextLine();
        System.out.println("\n-------------- CODI DEPORTES -------------");
        for (Deporte d : deportes) {
            System.out.println(d.getCod() + " - " + d.getNombre());
        }
        System.out.println("\n-------------------------------");

        System.out.print("ID del deporte (0 si no té): ");
        int deporteId = scanner.nextInt();

        Deporte deporte = null;
        if (deporteId != 0) {
            deporte = Deporte.getById(deporteId);
        }

        return new Deportista(nombre, deporte);
    }

    public String askForDeportistaName() {
        System.out.println("\n-------------------------------");

        scanner.nextLine(); // limpiar buffer
        System.out.print("Nom del deportista (cerca parcial): ");
        return scanner.nextLine();
    }

    public int askForDeporteId(List<Deporte> deportes) {
        System.out.println("-------------- CODI DEPORTES -------------");
        for (Deporte d : deportes) {
            System.out.println(d.getCod() + " - " + d.getNombre());
        }
        System.out.println("\n-------------------------------");
        System.out.print("ID del deporte: ");
        return scanner.nextInt();
    }

    public void deportistaList(List<Deportista> deportistas) {
        System.out.println("\n-------------------------------");
        System.out.println("Llista de deportistes:\n");

        if (deportistas.isEmpty()) {
            System.out.println("No es troben deportistes.");
            return;
        }

        for (Deportista d : deportistas) {
            String deporte = (d.getDeporte() != null)
                    ? d.getDeporte().getNombre()
                    : "Sense deporte";

            System.out.println(
                    d.getCod() + " - " +
                            d.getNombre() + " (" + deporte + ")");
        }
    }
}
