/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sudokuh;

import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author zaqia
 *         sarah
 *         zidan
 */
public class SudokuhController implements Initializable {

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private AnchorPane anchorPane;
    
    @FXML
    private TextField index00;

    @FXML
    private TextField index10;

    @FXML
    private TextField index20;

    @FXML
    private TextField index30;

    @FXML
    private TextField index40;

    @FXML
    private TextField index50;

    @FXML
    private TextField index60;

    @FXML
    private TextField index70;

    @FXML
    private TextField index80;
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    void about(ActionEvent event) {

    }

    @FXML
    void aboutMenu(ActionEvent event) {

    }

    @FXML
    void easy(ActionEvent event) {

    }

    @FXML
    void exitGame(ActionEvent event) {

    }

    @FXML
    void hard(ActionEvent event) {

    }

    @FXML
    void help(ActionEvent event) {

    }

    @FXML
    void helpMenu(ActionEvent event) {

    }

    @FXML
    void levelMenu(ActionEvent event) {

    }

    @FXML
    void medium(ActionEvent event) {

    }

    @FXML
    void newMenu(ActionEvent event) {

    }

    @FXML
    void resetGame(ActionEvent event) {

    }

    @FXML
    void restartGame(ActionEvent event) {

    }
}