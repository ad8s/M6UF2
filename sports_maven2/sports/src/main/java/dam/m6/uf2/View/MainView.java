package dam.m6.uf2.View;

import java.util.List;
import java.util.Scanner;

import dam.m6.uf2.model.Deporte;
import dam.m6.uf2.model.Deportista;

public class MainView {
    private final Scanner scanner;

    public MainView(Scanner scanner) {
        this.scanner = scanner;
    }

    public int mainMenu() {
        System.out.println("========== Menú Principal ==========");
        System.out.println("1. Llistar esports");
        System.out.println("2. Afegir esport");
        System.out.println("3. Llistar deportistes");
        System.out.println("4. Afegir deportista");
        System.out.println("5. Cercar deportista per nom");
        System.out.println("6. Llistar per esport");
        System.out.println("0. Sortir");
        System.out.print("Tria una opció: ");
        return scanner.nextInt();
    }

    public void showDeportes(List<Deporte> deportes) {
        System.out.println("========== Llista d'esports ==========");
        for (Deporte d : deportes) {
            System.out.println(d.getCod() + " - " + d.getNombre());
        }
        System.out.println("======================================");
    }

    public void showDeportistas(List<Deportista> deportistas) {
        System.out.println("======== Llista de deportistes ========");
        for (Deportista d : deportistas) {
            System.out.println(
                    d.getCod() + " - " + d.getNombre() + " (" + d.getCodDeporte() + ") " + d.getDeporteNombre());
        }
        System.out.println("======================================");
    }

    public Deporte addDeporteForm() {
        System.out.println("======== Afegir esport Formulari ========");
        System.out.println("Nom esport: ");
        return new Deporte(scanner.nextLine());
    }

    public Deportista addDeportistaForm(List<Deporte> deportes) {
        System.out.println("====== Afegir deportista Formulari ======");
        System.out.println("Nom deportista: ");
        String nombre = scanner.nextLine();

        System.out.println("======== Esports disponibles ========");

        for (Deporte d : deportes) {
            System.out.println(d.getCod() + " - " + d.getNombre());
        }
        System.out.println("Cod esport (null si buit): ");
        String line = scanner.nextLine();
        Integer codDep = line.isBlank() ? null : Integer.parseInt(line);

        return new Deportista(nombre, codDep);
    }

    public String askDeportistaName() {
        System.out.println("====== Cercar deportista per nom ======");
        System.out.println("Introdueix el nom a cercar: ");
        return scanner.nextLine();
    }

    public int askSportID(List<Deporte> deportes) {
        System.out.println("======== Esports disponibles ========");
        for (Deporte d : deportes) {
            System.out.println(d.getCod() + " - " + d.getNombre());
        }
        System.out.println("====== Llistar deportistes per esport ======");
        System.out.println("Introdueix ID d'esport: ");
        return scanner.nextInt();
    }

    public void bye() {
        System.out.println("Fins aviat!");
    }
}