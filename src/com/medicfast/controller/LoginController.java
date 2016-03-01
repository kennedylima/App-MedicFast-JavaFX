/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.medicfast.controller;

import com.medicfast.model.Usuario;
import com.medicfast.service.UsuarioService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements Initializable {
    
    @FXML
    private PasswordField txtPass;

    @FXML
    private TextField txtUser;
        
    static Stage stage = new Stage();
    static ActionEvent evento ;
    
    @FXML
    private void entrar(ActionEvent event) throws IOException {
       
        UsuarioService us = new UsuarioService();
        Usuario u ;
        u = us.indentificarUsuario(txtUser.getText(), txtPass.getText());
        UsuarioLogado.setUsuarioLogado(u);
        
        if(u != null){
        Parent root = FXMLLoader.load(getClass().getResource("/com/medicfast/fxml/Principal.fxml"));
            
            Scene scene = new Scene(root);
            stage.setTitle("Medic Fast");
            stage.setMaximized(true);
            stage.setScene(scene);
            stage.show();
            evento = event;
            
            //fechando a tela de login
            ((Node) (event.getSource())).getScene().getWindow().hide();
            
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Usuario ou Senha estão incorretos!");
            alert.showAndWait();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
