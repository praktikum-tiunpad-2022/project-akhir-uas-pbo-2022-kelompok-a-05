/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
/**
 *
 * @author zaqia
 */
public class Squares extends JPanel {
    public final int CELL_COUNT = 9;
    public Cell [] cells = new Cell[CELL_COUNT];
    
    public Squares() {
        this.setLayout(new GridLayout(3, 3));
        this.setBorder(new LineBorder(Color.PINK,2));
        for(int i = 0; i < CELL_COUNT; i++) {
            cells[i] = new Cell();
            this.add(cells[i]);
        }
    }
    
    public class Cell extends JTextField {
        public Cell() {
            
        }
    }
}
