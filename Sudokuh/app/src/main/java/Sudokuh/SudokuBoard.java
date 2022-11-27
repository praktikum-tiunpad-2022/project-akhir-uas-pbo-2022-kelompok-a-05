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