package sudokuh;

import java.util.Arrays;

public class SudokuBoard {
	/* Array yang berisikan solusi penyelesaian ke papan sudoku */
	private int[][] solusi;
	/* Array yang berisikan hanya angka yang tergambariskan dan pemain tidak dapat mengubahnya */
	private int[][] init;
	/* Array yang dapat diisi pemain */
	private int[][] pemain;

	public SudokuBoard() {
		solusi = new int[][] {
			{0,3,8,4,6,1,7,9,2},
			{6,9,7,3,2,5,8,1,4},
			{2,1,4,7,8,9,5,6,3},
			{9,4,1,2,7,8,6,3,5},
			{7,6,2,1,5,3,9,4,8},
			{8,5,3,9,4,6,1,2,7},
			{3,8,9,5,1,2,4,7,6},
			{4,2,6,8,9,7,3,5,1},
			{1,7,5,6,3,4,2,8,9}
		};

		// Angka 0 merupakan bagian yang bisa diisi pemain
		init = new int[][] {
			{5,0,0,4,0,0,0,9,0},
			{6,0,7,0,0,0,8,0,4},
			{0,1,0,7,0,9,0,0,3},
			{9,0,1,0,7,0,0,3,0},
			{0,0,2,0,0,3,0,0,0},
			{0,5,0,0,4,0,1,0,7},
			{3,0,0,5,0,2,0,7,0},
			{4,0,6,0,0,0,3,0,1},
			{0,7,0,0,0,4,0,0,0}

		};

		// inisiasi array pemain dengan 0
		pemain = new int[9][9];
	}

	// Mengembalikan array solusi
	public int[][] getSolusi() {
		return solusi;
	}

	// Mengembalikan array init
	public int[][] getInit() {
		return init;
	}

	// Mengembalikan array pemain
	public int[][] getPemain() {
		return pemain;
	}

	public void modifPemain(int angka, int baris, int kolom) {
		// Pengecekan jika array init mempunyai nilai 0
		// di posisi ketika kita ingin menginisisiasi pemain
		// ini cara untuk menghindari persimpangan antara keduanya
		if (init[baris][kolom] == 0) {
			if(angka >= 0 && angka <= 9) // Hanya nilai 0 -> 9 yang diijinkan
				pemain[baris][kolom] = angka;
			else // Menampilkan pesan jika error
				System.out.println("Nilai yang diisi Pemain melebihi kapasitas");
		}
	}

	public void clearPemain(int angka) {
		// Pengecekan jika array init mempunyai nilai 0
		// di posisi ketika kita ingin menginisisiasi pemain
		// ini cara untuk menghindari persimpangan antara keduanya
		for(int baris = 0; baris < 9; baris++) {
			for(int kolom = 0; kolom < 9; kolom++) {
				if (init[baris][kolom] == 0) {
					if(angka >= 0 && angka <= 9) // Hanya nilai 0 -> 9 yang diijinkan
						pemain[baris][kolom] = angka;
					else // Menampilkan pesan jika error
						System.out.println("Nilai yang diisi Pemain melebihi kapasitas");
				}
			}
		}
	}

	public void clearInit(int angka) {
		// Pengecekan jika array init mempunyai nilai 0
		// di posisi ketika kita ingin menginisisiasi pemain
		// ini cara untuk menghindari persimpangan antara keduanya
		for(int baris = 0; baris < 9; baris++) {
			for(int kolom = 0; kolom < 9; kolom++) {
				if (pemain[baris][kolom] == 0) {
					if(angka >= 0 && angka <= 9) // Hanya nilai 0 -> 9 yang diijinkan
						init[baris][kolom] = angka;
					else // Menampilkan pesan jika error
						System.out.println("Nilai yang diisi Pemain melebihi kapasitas");
				}
			}
		}
	}

	//Mengembalikan nilai benar jika pemain mengisi benar, dan False jika salah
	public boolean cekJawaban() {
		for(int baris = 0; baris < 9; baris++) {
			for(int kolom = 0; kolom < 9; kolom++) {
				// Jika nilai dari init array = 0 Maka
				// Pemain dapat menginput angka pada kotak
				if(init[baris][kolom] == 0) {
					// Mengecek jika pemain nilai dari pemain sama dengan nilai jawaban
					// dan jika tidak :
					if(pemain[baris][kolom] != solusi[baris][kolom]) {
						// Mereturn False, jika pemain melakukan kesalahan dengan menempatkan angka yang salah
						return false;
					}
				}
			}
		}
		return true; // Jika Jawaban Benar
	}
	
	//M engembalikan nilai true jika jawaban pemain benar sesui dengan peraturan sudoku
	public boolean cekJawabanUmum() {
		// Menggabungkan array ini dan pemain
		// Inisiasi nilai 0 ke array 9x9  
		int[][] gabung = new int[9][9];
		// Mengisinya dengan kombinasi antara nilai angka dan jawaban pemain
		for(int baris = 0; baris < 9; baris++) {
			for(int kolom = 0; kolom < 9; kolom++) {
				// Jika ada nilai pada array init
				if(init[baris][kolom] != 0) {
					// Menambahkannya pada posisi yang sama
					gabung[baris][kolom] = init[baris][kolom];
					// Jika tidak ada
				} else {
					//Menambahkannya pada posisi yang sama pada pemain array
					gabung[baris][kolom] = pemain[baris][kolom];
				}
			}
		}
		// cek jika jumlah dari angka pada setiap baris ini
		// sama dengan 45 (Penjumlahan angka dari 1 -> 9)
		for(int baris = 0; baris < 9; baris++) {
			// Pada baris itu, buat variabel penjumlahan
			int sum = 0;
			// Menambahkan semua nilai pada baris
			for(int kolom = 0; kolom < 9; kolom++) {
				sum = sum + gabung[baris][kolom];
			}
			// Jika jumlahnya tidak 45, maka baris tak dapat diisi 
			if(sum != 45) {
				return false;
			}
		}
		
		// cek jika jumlah dari angka pada setiap kolom ini
		// sama dengan 45 (Penjumlahan angka dari 1 -> 9)
		for(int kolom = 0; kolom < 9; kolom++) { 
			// untuk kolom, buat variabel penjumlahan
			int sum = 0;
			//Menambahkan semua nilai pada kolom
			for(int baris = 0; baris < 9; baris++) {
				sum = sum + gabung[baris][kolom];
			}
			// Jika jumlahnya tidak 45, maka baris tak dapat diisi 
			if(sum != 45) {
				return false;
			}
		}


		// cek jika numlah dari nilai di setiap 3x3 kotak unik
		// pada penjumlahan papan 9x9  = 45
		// Membuat sebuat batasan dari 3 kotak dari setiap pengecekan
		
		// Mengincrement pada 3 baris batas setiap waktu
		for (int baris_batas = 0; baris_batas < 9; baris_batas+=3) { 
				// Mengincrement pada 3 kolom batas setiap waktu
			for(int kolom_batas = 0; kolom_batas < 9; kolom_batas+=3) { 
				// untuk setiap 3x3 kelompok, membuat jumkah nilai
				int sum = 0;
				//Menambahkan semua nilai dari cluster dari 3x3
				for (int baris = 0; baris < 3; baris++) {
					for (int kolom = 0; kolom < 3; kolom++) {
						sum = sum + gabung[baris + baris_batas][kolom + kolom_batas];
					}
				}
				//Jika penjumlahan tidak sama dengan 45 maka kelompok 3x3 invalid
				if(sum != 45) {
					return false;
				}
			}
		}
		//Jika tidak ada cek yang sudah ke triggered mengembalikan statement salah
		//Dijalankan dengan aman dan Mengembalikan nilai true
		return true;
	}

}