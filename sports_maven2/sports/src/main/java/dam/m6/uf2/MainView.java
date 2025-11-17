package dam.m6.uf2;

import java.util.List;
import java.util.Scanner;

import dam.m6.uf2.model.Deporte;
import dam.m6.uf2.model.Deportista;

public class MainView {

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
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    public void showDeportes(List<Deporte> list) {
        System.out.println("========== Llista d'esports ==========");
        for (Deporte d : list) {
            System.out.println(d.getCod() + " - " + d.getNombre());
        }
        System.out.println("======================================");
    }

    public void showDeportistas(List<Deportista> list) {
        System.out.println("======== Llista de deportistes ========");
        for (Deportista d : list) {
            System.out.println(
                    d.getCod() + " - " + d.getNombre() + " (" + d.getCodDeporte() + ") " + d.getDeporteNombre());
        }
        System.out.println("======================================");
    }

    public Deporte addDeporteForm() {
        System.out.println("======== Afegir esport Formulari ========");
        Scanner sc = new Scanner(System.in);
        System.out.println("Nom esport: ");
        return new Deporte(sc.nextLine());
    }

    public Deportista addDeportistaForm() {
        System.out.println("====== Afegir deportista Formulari ======");
        Scanner sc = new Scanner(System.in);
        System.out.println("Nom deportista: ");
        String nombre = sc.nextLine();

        System.out.println("Cod esport (null si buit): ");
        String line = sc.nextLine();
        Integer codDep = line.isBlank() ? null : Integer.parseInt(line);

        return new Deportista(nombre, codDep);
    }

    public String askDeportistaName() {
        System.out.println("====== Cercar deportista per nom ======");
        Scanner sc = new Scanner(System.in);
        System.out.println("Introdueix el nom a cercar: ");
        return sc.nextLine();
    }

    public int askSportID() {
        System.out.println("====== Llistar deportistes per esport ======");
        Scanner sc = new Scanner(System.in);
        System.out.println("Introdueix ID d'esport: ");
        return sc.nextInt();
    }
}