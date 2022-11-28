package Sudokuh;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;

import javax.swing.border.BevelBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.util.Random;

public class App extends JFrame {

    public static final int grid = SudokuPuzzle.grid; 
	public static final int subgrid = SudokuPuzzle.subgrid; 

    public static final int ukuran = 60;
	public static final int lebar = ukuran * grid;
	public static final int tinggi = ukuran * grid;

    public static int level; 
	public static int cell;

	public static final Color color_blank = Color.GREEN; 
	public static final Color color_true = Color.BLUE; 
	public static final Color color_false = Color.RED; 
	public static final Color color_fill = Color.WHITE; 
	public static final Color color_number = Color.BLACK;
	public static final Font font_number = new Font("Tahoma", Font.BOLD, 20);


	JTextField[][] tampilan = new JTextField[grid][grid];

    private int[][] sudokupuzzle = SudokuPuzzle.getPuzzle();
    private static boolean[][] restgame = {    //temp restart
			{ false, false, false, false, false, false, false, false, false },
			{ false, false, false, false, false, false, false, false, false },
			{ false, false, false, false, false, false, false, false, false },
			{ false, false, false, false, false, false, false, false, false },
			{ false, false, false, false, false, false, false, false, false },
			{ false, false, false, false, false, false, false, false, false },
			{ false, false, false, false, false, false, false, false, false },
			{ false, false, false, false, false, false, false, false, false },
			{ false, false, false, false, false, false, false, false, false } };
	private static boolean[][] game = {
			{ false, false, false, false, false, false, false, false, false },
			{ false, false, false, false, false, false, false, false, false },
			{ false, false, false, false, false, false, false, false, false },
			{ false, false, false, false, false, false, false, false, false },
			{ false, false, false, false, false, false, false, false, false },
			{ false, false, false, false, false, false, false, false, false },
			{ false, false, false, false, false, false, false, false, false },
			{ false, false, false, false, false, false, false, false, false },
			{ false, false, false, false, false, false, false, false, false } };


	public static JPanel sudokuboard;
	public static JPanel PanelBoard;
	public static JLabel LabelBoard;

    public final int SQUARE_COUNT = 9;
    public Squares [] squares = new Squares[SQUARE_COUNT];
    
    public App() {
        super("Sudoku");

		Container contentp = getContentPane();
		contentp.setLayout(new BorderLayout());

		//SudokuBoard
		sudokuboard = new JPanel();
		sudokuboard.setLayout(new GridLayout(grid, grid));
		sudokuboard.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.black));
		contentp.add(sudokuboard, BorderLayout.CENTER);

		//membuatPanel
		PanelBoard = new JPanel();
		PanelBoard.setBorder(new BevelBorder(BevelBorder.RAISED));
		PanelBoard.setPreferredSize(new Dimension(contentp.getWidth(), 25));
		PanelBoard.setLayout(new BoxLayout(PanelBoard, BoxLayout.X_AXIS));
		LabelBoard = new JLabel("Cells remaining: " + cell);
		LabelBoard.setHorizontalAlignment(SwingConstants.LEFT);
		PanelBoard.add(LabelBoard);
		contentp.add(PanelBoard, BorderLayout.SOUTH);

 
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
 
        JMenu aboutMenu = new JMenu("About");
            JMenuItem about = new JMenuItem("Sudoku Game");
            about.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null,"Sudoku adalah singkatan bahasa Jepang, yaitu Suuji wa dokushin ni kagiru, artinya angka-angkanya\nharus tetap tunggal. Dikenal juga sebagai Number Place atau Nanpure yang merupakan teka-teki logika.\nBertujuan untuk mengisikan angka-angka dari 1 sampai 9 ke dalam kotak berukuran 9 x 9 yang terdiri\ndari 9 kotak 3 x 3 tanpa ada angka yang berulang di satu baris, kolom atau kotak.");
                }
            });
            aboutMenu.add(about);
	    menuBar.add(aboutMenu);
 
        JMenu helpMenu = new JMenu("Help");
            JMenuItem help = new JMenuItem("How To Play");
            help.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null,"1. Tiap baris harus diisi oleh angka 1-9 dan tidak boleh ada angka yang sama dalam satu baris.\n2. Tiap kolom juga harus diisi oleh angka 1-9 dan tidak boleh ada angka yang sama dalam satu kolom.\n3. Tiap kotak dengan ukuran 3 x 3 yang berisi 9 kotak-kotak kecil harus diisi oleh angka 1-9 dan tidak boleh ada angka yang sama.");
                }
            });
            helpMenu.add(help);
	    menuBar.add(helpMenu);
        
        JMenu mainMenu = new JMenu("Menu");
            JMenu Baru = new JMenu("New");

            JMenu gameLevel = new JMenu("Level Game");
            JMenuItem easy = new JMenuItem("Easy");
            JMenuItem medium = new JMenuItem("Medium");
            JMenuItem hard = new JMenuItem("Hard");

            Difficult lvlListener = new Difficult();
            easy.addActionListener(lvlListener);
            medium.addActionListener(lvlListener);
            hard.addActionListener(lvlListener);

            gameLevel.add(easy);
            gameLevel.add(medium);
            gameLevel.add(hard);

            Baru.add(gameLevel);
            mainMenu.add(Baru);

            JMenuItem restartGame = new JMenuItem("Restart Game");
            mainMenu.add(restartGame);
            JMenuItem resetGame = new JMenuItem("Reset Game");
            mainMenu.add(resetGame);
            JMenuItem exitGame = new JMenuItem("Exit Game");
            mainMenu.add(exitGame);

			resetGame.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					for (int row = 0; row < grid; ++row) {
						for (int col = 0; col < grid; ++col) {
							if (restgame[row][col]) {
								tampilan[row][col].setText(""); // set ke string kosong
								tampilan[row][col].setEditable(true);	// dapat diedit
								tampilan[row][col].setBackground(color_blank);
							}
							else {
								tampilan[row][col].setText(sudokupuzzle[row][col] + ""); // sudah berisi angka
								tampilan[row][col].setEditable(false); // tidak dapat diedit
								tampilan[row][col].setBackground(color_fill);
								tampilan[row][col].setForeground(color_number);
							}
						 }
					}
		
				}
			});

			restartGame.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
					setLevel (5);
					new App();
				}
			});

            exitGame.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0); //exit
                }
            });
	    menuBar.add(mainMenu);
		initGame(sudokuboard);
        contentp.setPreferredSize(new Dimension(lebar, tinggi));
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
 
        setVisible(true);
    }
 
    public class exitaction implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            System.exit(0);
        }
    }

    private class Difficult implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "Easy":
				JOptionPane.showMessageDialog(null, "Level Mudah");
                setLevel(5);
				dispose();
				new App();
				break;
			case "Medium":
				JOptionPane.showMessageDialog(null, "Level Standar");
                setLevel(7);
				dispose();
				new App();
				break;
			case "Hard":
				JOptionPane.showMessageDialog(null, "Level Gak Ngotak");
                setLevel(8);
				dispose();
				new App();
				break;
			default:
				JOptionPane.showMessageDialog(null, "Level");
                setLevel(5);
				dispose();
				new App();
				break;
			}
		}
    }

    private void initGame(JPanel sudokuboard) {

		for (int bar = 0; bar < grid; ++bar) {
			for (int kol= 0; kol< grid; ++kol) {
				tampilan[bar][kol] = new JTextField(); 

				if (kol== 3 || kol== 6 || bar == 3 || bar == 6) {
					if ((kol== 3 || kol== 6) && (bar == 3 || bar == 6)) {
						tampilan[bar][kol].setBorder(BorderFactory.createMatteBorder(5, 5, 1, 1, Color.black));
					} else if (kol!= 3 && kol!= 6) {
						tampilan[bar][kol].setBorder(BorderFactory.createMatteBorder(5, 1, 1, 1, Color.black));
					} else if (bar != 3 && bar != 6) {
						tampilan[bar][kol].setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.black));
					}
				} else {
					tampilan[bar][kol].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
				}
				tampilan[bar][kol].setHorizontalAlignment(JTextField.CENTER);
				tampilan[bar][kol].setFont(font_number);
				sudokuboard.add(tampilan[bar][kol]); 

				if (game[bar][kol]) {
					tampilan[bar][kol].setText("");
					tampilan[bar][kol].setEditable(true);
					tampilan[bar][kol].setBackground(color_blank);
				} 
				else {
					tampilan[bar][kol].setText(sudokupuzzle[bar][kol] + "");
					tampilan[bar][kol].setEditable(false);
					tampilan[bar][kol].setBackground(color_fill);
					tampilan[bar][kol].setForeground(color_number);
				}
			}
		}
	}

	
	

    public static void setLevel (int level) {
		for (int bar = 0; bar < grid; ++bar) {
			for (int kol= 0; kol< grid; ++kol){
				game[bar][kol] = false;
				restgame[bar][kol] = false;
			}
		}
		cell = level * 9;
        Random random = new Random();
		int randombar = -1;
		int randomkol= -1;

		for (int i = 0; i < cell; i++) {
			randombar = random.nextInt(grid);
			randomkol= random.nextInt(grid);
			if (!game[randombar][randomkol]) {
                game[randombar][randomkol] = true;
                restgame[randombar][randomkol] = true;
			} else {
				i--;
			}
        }
        
	}
        
    
    public static void main(String[] args) {
		App test = new App();
    }
}