package sudokuh;

import Timer.Time;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.control.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;



public class SudokuhController implements Initializable {

	@FXML Button button_satu; 
	@FXML Button button_dua;
	@FXML Button button_tiga;
	@FXML Button button_empat;
	@FXML Button button_lima;
	@FXML Button button_enam;
	@FXML Button button_tujuh;
	@FXML Button button_delapan;
	@FXML Button button_sembilan;
	@FXML Canvas canvas;
	@FXML private Text timee;

	Time time = new Time("0:00:00");

	Timeline timeline = new Timeline(
		new KeyFrame(Duration.seconds(0.1),
		e -> {
			time.oneSecondPassed();
			timee.setText(time.getWaktuSekarang());
		}));
	
	//Method membersihkan angka pemain yang telah diisi
	public void clear(ActionEvent event) {
		sudokuboard.clearPemain(0);
		drawOnCanvas(canvas.getGraphicsContext2D());
	}
	
	//Method memberikan tentang Mr.Sudoku
	public void tentangClicked(ActionEvent event){
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("TENTANG MR.SUDOKU");
		alert.setHeaderText("Sudoku adalah singkatan bahasa Jepang, yaitu Suuji wa dokushin ni kagiru, artinya angka-angkanya\nharus tetap tunggal. Dikenal juga sebagai Number Place atau Nanpure yang merupakan teka-teki logika.\nBertujuan untuk mengisikan angka-angka dari 1 sampai 9 ke dalam kotak berukuran 9 x 9 yang terdiri\ndari 9 kotak 3 x 3 tanpa ada angka yang berulang di satu baris, kolom atau kotak.");
		alert.setContentText("Terimakasih Telah Membaca!");
		alert.showAndWait();
	}
			
	
	//Method memberikan bantuan dari Mr.Sudoku
	public void helpClicked(ActionEvent event){
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("KAMU NANYEAA?? - MR.SUDOKU");
		alert.setHeaderText("1. Tiap baris harus diisi oleh angka 1-9 dan tidak boleh ada angka yang sama dalam satu baris.\n2. Tiap kolom juga harus diisi oleh angka 1-9 dan tidak boleh ada angka yang sama dalam satu kolom.\n3. Tiap kotak dengan ukuran 3 x 3 yang berisi 9 kotak-kotak kecil harus diisi oleh angka 1-9 dan tidak boleh ada angka yang sama.");
		alert.setContentText("Terimakasih Telah Membaca!");
		alert.showAndWait();
	}

	//Method Keluar dari program
	public void exitClicked(ActionEvent event){
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("PESAN DARI MR.SUDOKU");
		alert.setHeaderText("Anda terkeluarkan dari game");
		alert.setContentText("Terimakasih Telah bermain!");
		alert.showAndWait();
		Platform.exit();
		System.exit(0);
	}


	// Membuat deklarasi Sudoku Board
	SudokuBoard sudokuboard;
	// Pemain diseleksi berdasarkan baris dan Kolom
	int seleksiBarisPemain;
	int seleksiKolomPemain;

	/***
	 * Pada tampilan loading, dilakukan inisiasi sudokuBoard dan memanggil method canvas 
	 * dan inisiasi cell yang dipilih
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//Membuat sudokuBoard
		sudokuboard = new SudokuBoard();
		//Memanggil GraphicsContext pada canvas
		GraphicsContext context = canvas.getGraphicsContext2D();
		//Memanggil method DrawOnCanvasCall dengan context yang kita dapatkan dari canvas
		drawOnCanvas(context);
		// Insiasiasi pemain secara default;
		seleksiBarisPemain = 0;
		seleksiKolomPemain = 0;

		timee.setText(time.getWaktuSekarang());

		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
	}

	public void drawOnCanvas(GraphicsContext context) {

		context.clearRect(0, 0, 450, 450);
		// Menggambarkan Kotak bundar putih untuk papan sudoku
		for(int baris = 0; baris<9; baris++) {
			for(int kolom = 0; kolom<9; kolom++) {
				// Menemukan posisi sumbu y pada cell, dengan mengalikan baris nomor dengan 50, yang mana itu adalah tinggi baris di pixel
				// Kemudian menambahkan 2, tu menambahkan beberapa batasan
				int sumbuY = baris * 50 + 2;
				// Menemukan posisi sumbu x pada cell, dengan mengalikan baris nomor dengan 50, yang mana itu adalah tinggi baris di pixel
				// Kemudian menambahkan 2, tu menambahkan beberapa batasan
				int sumbuX = kolom * 50 + 2;
				//Mendefinisikan lebar kotak dengan 46 bukan 50, untuk memperhitungkan total 4px dari area kosong yang disebabkan oleh batasan
				//Oleh karena itu kita menggambarkan kota dengan tinggi yang sama
				int width = 46;
				// memberikan warna putih
				context.setFill(Color.WHITE);
				//Menggambarkan sebuah kotak bundar dengan kalkulasi posisi dan lebar. Untuk argumen terakhir yang 10, 10 yakni membuat kotak bundar deari lebar dan tinggi 
				context.fillRoundRect(sumbuX, sumbuY, width, width, 10, 10);
			}
		}

		// Menggambarkan warna merah untuk cell yang dipilih
		context.setStroke(Color.RED);
		// menginisiasi lebar garis dengan 5px
		context.setLineWidth(5);
		// Menggambarkan strokeRoundRect dengan menggunakan teknik yang sama dengan yang kita gunakan untuk menggambar pada papan Sudoku
		context.strokeRoundRect(seleksiKolomPemain * 50 + 2, seleksiBarisPemain * 50 + 2, 46, 46, 10, 10);

		//Menuliskan angka inisial dari Papan sudoku
		int[][] inisial = sudokuboard.getInit();
		for(int baris = 0; baris<9; baris++) {
			for(int kolom = 0; kolom<9; kolom++) {
				// Menemukan sumbu y dari cell, dengan mengalikan 50, yang mana itu tinggi dari baris di piksel
				// Kemudian menambahkan 30, untuk menambahkan beberapa batasan
				int sumbuY = baris * 50 + 30;
				// Menemukan sumbu y dari cell, dengan mengalikan 50, yang mana itu tinggi dari kolom di piksel
				// Kemudian menambahkan 20, untuk menambahkan beberapa batasan
				int sumbuX = kolom * 50 + 20;
				// Mengisi warna hitam
				context.setFill(Color.BLACK);
				// Mengatur font, dari font baru, dengan ukuran huruf 20
				context.setFont(new Font(20));
				// Cek jika nilai sesuai dengan posisi array tidak 0
				if(inisial[baris][kolom]!=0) {
					// menuliskan angka
					context.fillText(inisial[baris][kolom] + "", sumbuX, sumbuY);
				}
			}
		}

		//Menuliskan nomor pemain dari sudoku Board
		int[][] pemain = sudokuboard.getPemain();
		for(int baris = 0; baris<9; baris++) {
			for(int kolom = 0; kolom<9; kolom++) {
				// Menemukan sumbu y dari cell, dengan mengalikan 50, yang mana itu tinggi dari baris di piksel
				// Kemudian menambahkan 30, untuk menambahkan beberapa batasan
				int sumbuY = baris * 50 + 30;
				// Menemukan sumbu y dari cell, dengan mengalikan 50, yang mana itu tinggi dari kolom di piksel
				// Kemudian menambahkan 20, untuk menambahkan beberapa batasan
				int sumbuX = kolom * 50 + 20;
				// Mengisi warna ungu
				context.setFill(Color.GREEN);
				// Mengatur font, dari font baru, dengan ukuran huruf 20
				context.setFont(new Font(22));
				// Cek jika nilai sesuai dengan posisi array tidak 0
				if(pemain[baris][kolom]!=0) {
					// menuliskan angka
					context.fillText(pemain[baris][kolom] + "", sumbuX, sumbuY);
				}
			}
		}


		// Ketika sudokuBoard mengembalikan nilai true dari method cekSukses 
		// Berarti itu tidak ditemukan kesalahan
		if(sudokuboard.cekJawabanUmum() == true) {
			// Membersihkan canvas
			context.clearRect(0, 0, 450, 450);
			// Mengisi dengan warna hijau
			context.setFill(Color.GREEN);
			// Mengatur font dengan ukuran 36
			context.setFont(new Font(36));
			// Menampilkan SUKSESS
			context.fillText("SUKSESS!", 150, 250);
		}


	}

	/***
	 * Method untuk menyambungkan ke canvas dengan onclick event handler
	 */
	public void canvasMouseClicked() {
		canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				int mouseX = (int) event.getX();
				int mouseY = (int) event.getY();

				// Mengkonversikan mouseX dan mouseY kedalam baris dan kolom
				// Kita akan mengambil keuntungan dari cara integers dan kita bakal membaginay dengan lebar cell yakni 50
				// Ini cara untuk beberapa nilai diantara 0 -> 449 untuk x dan y yang dicasting integers dari 0 - 8
				seleksiBarisPemain = (int) (mouseY / 50);
				seleksiKolomPemain = (int) (mouseX / 50);

				//Mengambil cavas graphics context dan redraw

				drawOnCanvas(canvas.getGraphicsContext2D());
			}
		});
	}

	/***
	 * Method untuk menyambungkan dengan tombol onclick event handler
	 */
	public void buttonOnePressed() {
		sudokuboard.modifPemain(1, seleksiBarisPemain, seleksiKolomPemain);

		// Mengrefesh canvas
		drawOnCanvas(canvas.getGraphicsContext2D());
	}

	/***
	 * Method untuk menyambungkan dengan tombol onclick event handler
	 */
	public void buttonTwoPressed() {
		sudokuboard.modifPemain(2, seleksiBarisPemain, seleksiKolomPemain);
		drawOnCanvas(canvas.getGraphicsContext2D());
	}

	/***
	 * Method untuk menyambungkan dengan tombol onclick event handler
	 */
	public void buttonThreePressed() {
		sudokuboard.modifPemain(3, seleksiBarisPemain, seleksiKolomPemain);
		drawOnCanvas(canvas.getGraphicsContext2D());
	}

	/***
	 * Method untuk menyambungkan dengan tombol onclick event handler
	 */
	public void buttonFourPressed() {
		sudokuboard.modifPemain(4, seleksiBarisPemain, seleksiKolomPemain);
		drawOnCanvas(canvas.getGraphicsContext2D());
	}

	/***
	 * Method untuk menyambungkan dengan tombol onclick event handler
	 */
	public void buttonFivePressed() {
		sudokuboard.modifPemain(5, seleksiBarisPemain, seleksiKolomPemain);
		drawOnCanvas(canvas.getGraphicsContext2D());
	}

	/***
	 * Method untuk menyambungkan dengan tombol onclick event handler
	 */
	public void buttonSixPressed() {
		sudokuboard.modifPemain(6, seleksiBarisPemain, seleksiKolomPemain);
		drawOnCanvas(canvas.getGraphicsContext2D());
	}

	/***
	 * Method untuk menyambungkan dengan tombol onclick event handler
	 */
	public void buttonSevenPressed() {
		sudokuboard.modifPemain(7, seleksiBarisPemain, seleksiKolomPemain);
		drawOnCanvas(canvas.getGraphicsContext2D());
	}

	/***
	 * Method untuk menyambungkan dengan tombol onclick event handler
	 */
	public void buttonEightPressed() {
		sudokuboard.modifPemain(8, seleksiBarisPemain, seleksiKolomPemain);
		drawOnCanvas(canvas.getGraphicsContext2D());
	}

	/***
	 * Method untuk menyambungkan dengan tombol onclick event handler
	 */
	public void buttonNinePressed() {
		sudokuboard.modifPemain(9, seleksiBarisPemain, seleksiKolomPemain);
		drawOnCanvas(canvas.getGraphicsContext2D());
	}

}