package castellet.dam.m12.uf2.hibernate;

import java.util.List;
import java.util.Scanner;

import castellet.dam.m12.uf2.model.Deporte;
import castellet.dam.m12.uf2.model.Deportista;

public class Controller {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        MainView view = new MainView(scanner);

        int option;

        do {
            option = view.showMenu();

            switch (option) {

                case 1 -> {
                    Deporte d = view.deporteForm();
                    d.save();
                }

                case 2 -> {
                    List<Deporte> deportes = Deporte.getAll();
                    view.deporteList(deportes);
                }

                case 3 -> {
                    List<Deporte> deportes = Deporte.getAll();
                    Deportista dep = view.deportistaForm(deportes);
                    dep.save();
                }

                case 4 -> {
                    List<Deportista> deportistas = Deportista.getAll();
                    view.deportistaList(deportistas);
                }

                case 5 -> {
                    String name = view.askForDeportistaName();
                    List<Deportista> deportistas = Deportista.getByName(name);
                    view.deportistaList(deportistas);
                }

                case 6 -> {
                    List<Deporte> deportes = Deporte.getAll();
                    int deporteId = view.askForDeporteId(deportes);
                    List<Deportista> deportistas = Deportista.getBySportId(deporteId);
                    view.deportistaList(deportistas);
                }

                case 0 -> {
                    System.out.println("Sortint...");
                }

                default -> {
                    System.out.println("Opció no vàlida.");
                }
            }

        } while (option != 0);

        scanner.close();
    }
}
