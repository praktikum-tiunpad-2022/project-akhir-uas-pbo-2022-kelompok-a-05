package sudokuh;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Sudoku extends Application {
    @Override
	public void start(Stage primaryStage) throws Exception {
        /* Memuat sudoku.fxml dari file dan menetapkannya ke scene root object */
        Parent root = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
        /* Menetapkan root ke scene baru dan mendefinisikan dimensinya */
        Scene scene = new Scene(root, 762, 518);
        /* Mengisi judul pada stage ("Window") */
        primaryStage.setTitle("Mr.Sudoku");
        /* Mengatur Scene dari stage ke pembaruan pembuatan dari tampilan scene */
        primaryStage.setScene(scene);
        /* Menunjukkan show */
        primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}