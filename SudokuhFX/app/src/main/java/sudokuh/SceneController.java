package sudokuh;

import java.beans.EventHandler;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.application.Platform;

public class SceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void gameClicked(ActionEvent event) throws IOException{
        root = FXMLLoader.load(
        getClass().getResource("/View/sudoku.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void aboutyClicked(ActionEvent event){
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("TENTANG MR.SUDOKU");
		alert.setHeaderText("Sudoku adalah singkatan bahasa Jepang, yaitu Suuji wa dokushin ni kagiru, artinya angka-angkanya\nharus tetap tunggal. Dikenal juga sebagai Number Place atau Nanpure yang merupakan teka-teki logika.\nBertujuan untuk mengisikan angka-angka dari 1 sampai 9 ke dalam kotak berukuran 9 x 9 yang terdiri\ndari 9 kotak 3 x 3 tanpa ada angka yang berulang di satu baris, kolom atau kotak.");
		alert.setContentText("Terimakasih Telah Membaca!");
		alert.showAndWait();
	}
	
	//Method Keluar dari program
	public void keluarClicked(ActionEvent event){
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("PESAN DARI MR.SUDOKU");
		alert.setHeaderText("Anda terkeluarkan dari game");
		alert.setContentText("Terimakasih Telah bermain!");
		alert.showAndWait();
		Platform.exit();
	}


}



