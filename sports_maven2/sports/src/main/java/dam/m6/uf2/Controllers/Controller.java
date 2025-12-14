package dam.m6.uf2.Controllers;

import java.util.Scanner;
import java.sql.Connection;

import dam.m6.uf2.interfaces.*;
import dam.m6.uf2.Main.ConnectionManager;
import dam.m6.uf2.View.MainView;

public class Controller {
	Connection conn;

	public static void main(String[] args) {
		Controller controller = new Controller();
		controller.init();
	}

	private void init() {

		this.conn = ConnectionManager.getConnection("M6UF2/database.xml");
		Scanner scanner = new Scanner(System.in);
		MainView mainView = new MainView(scanner);

		DeportesPgDAO deportesDAO = new DeportesPgDAO(conn);
		DeportistaPgDAO deportistasDAO = new DeportistaPgDAO(conn);

		while (true) {
			int option = mainView.mainMenu();

			switch (option) {
				case 1:
					mainView.showDeportes(deportesDAO.getAll());
					break;
				case 2:
					deportesDAO.add(mainView.addDeporteForm());
					break;
				case 3:
					mainView.showDeportistas(deportistasDAO.getAll());
					break;
				case 4:
					deportistasDAO.add(mainView.addDeportistaForm(deportesDAO.getAll()));
					break;
				case 5:
					String name = mainView.askDeportistaName();
					mainView.showDeportistas(deportistasDAO.getDeportistaByName(name));
					break;
				case 6:
					int sportCode = mainView.askSportID(deportesDAO.getAll());
					mainView.showDeportistas(deportistasDAO.getDeportistaBySportID(sportCode));
					break;
				case 0:
					mainView.bye();
					return;

			}
		}

	}
}
