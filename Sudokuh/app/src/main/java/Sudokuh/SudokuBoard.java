package Sudokuh;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

public class SudokuBoard extends JFrame {

    public final int SQUARE_COUNT = 9;
    public Squares [] squares = new Squares[SQUARE_COUNT];
    
    public SudokuBoard() {
        super("Sudoku");
        setSize(600,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        JPanel panel = new JPanel(new GridLayout(3,3));
        for(int i = 0; i < SQUARE_COUNT; i++) {
            squares[i] = new Squares();
            panel.add(squares[i]);
        }
 
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
            helpMenu.add(help);
	    menuBar.add(helpMenu);
        
        JMenu mainMenu = new JMenu("Menu");
            JMenuItem gameLevel = new JMenuItem("Level Game");
            mainMenu.add(gameLevel);
            JMenuItem restartGame = new JMenuItem("Restart Game");
            mainMenu.add(restartGame);
            JMenuItem resetGame = new JMenuItem("Reset Game");
            mainMenu.add(resetGame);
            JMenuItem exitGame = new JMenuItem("Exit Game");
            mainMenu.add(exitGame);
	    menuBar.add(mainMenu);
        
        add(panel, BorderLayout.CENTER);
 
        setVisible(true);
        setLocationRelativeTo(null);
    }
 
    public class exitaction implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            System.exit(0);
        }
    }
    
    public static void main(String[] args) {
        SudokuBoard board = new SudokuBoard();
    }
}