package dam.m6.uf2;

import java.sql.Connection;

public class Controller {
	Connection conn;

	public static void main(String[] args) {
		System.out.println("Current directory (aquí heu de posar el XML): " + System.getProperty("user.dir"));
		Controller controller = new Controller();
		controller.init();
	}

	private void init() {

		this.conn = ConnectionManager.getConnection("database.xml");
		MainView mainView = new MainView();

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
					deportistasDAO.add(mainView.addDeportistaForm());
					break;
				case 5:
					String name = mainView.askDeportistaName();
					mainView.showDeportistas(deportistasDAO.getDeportistaByName(name));
					break;
				case 6:
					int sportCode = mainView.askSportID();
					mainView.showDeportistas(deportistasDAO.getDeportistaBySportID(sportCode));
					break;
				case 0:
					System.out.println("Fins aviat!");
					return;

			}
		}

	}
}
