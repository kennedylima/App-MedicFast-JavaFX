
package com.medicfast.controller;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class TelaController {

    public void inserirTela(String tela) {
        try {
            Parent root;
            switch (tela) {
                case "CadEspecialidade":
                    root = FXMLLoader.load(getClass().getResource("/com/medicfast/fxml/CadEspecialidade.fxml"));
                    TelaPrincipalController.conteudo.getChildren().clear();
                    TelaPrincipalController.conteudo.getChildren().add(root);
                    break;
                    
                case "CadMedico":
                    root = FXMLLoader.load(getClass().getResource("/com/medicfast/fxml/CadMedico.fxml"));
                    TelaPrincipalController.conteudo.getChildren().clear();
                    TelaPrincipalController.conteudo.getChildren().add(root);
                    break;

                case "CadMedicamento":
                    root = FXMLLoader.load(getClass().getResource("/com/medicfast/fxml/CadMedicamento.fxml"));
                    TelaPrincipalController.conteudo.getChildren().clear();
                    TelaPrincipalController.conteudo.getChildren().add(root);
                    break;

                case "CadPontoAtendimento":
                    root = FXMLLoader.load(getClass().getResource("/com/medicfast/fxml/CadPontoAtendimento.fxml"));
                    TelaPrincipalController.conteudo.getChildren().clear();
                    TelaPrincipalController.conteudo.getChildren().add(root);
                    break;
                    
                case "CadTipoOcorrencia":
                    root = FXMLLoader.load(getClass().getResource("/com/medicfast/fxml/CadTipoOcorrencias.fxml"));
                    TelaPrincipalController.conteudo.getChildren().clear();
                    TelaPrincipalController.conteudo.getChildren().add(root);
                    break;
                
                case "Senha":
                    root = FXMLLoader.load(getClass().getResource("/com/medicfast/fxml/Senha.fxml"));
                    TelaPrincipalController.conteudo.getChildren().clear();
                    TelaPrincipalController.conteudo.getChildren().add(root);
                    break;    
                
                case "ConsMedico":
                    root= FXMLLoader.load(getClass().getResource("/com/medicfast/fxml/ConsMedico.fxml"));
                    TelaPrincipalController.conteudo.getChildren().clear();
                    TelaPrincipalController.conteudo.getChildren().add(root);
                    break;
                    
                case "ConsMedicamento":
                    root=FXMLLoader.load(getClass().getResource("/com/medicfast/fxml/ConsMedicamento.fxml"));
                    TelaPrincipalController.conteudo.getChildren().clear();
                    TelaPrincipalController.conteudo.getChildren().add(root);
                    break;
                    
                case "ConsPonto":
                    root=FXMLLoader.load(getClass().getResource("/com/medicfast/fxml/ConsPontoAtendimento.fxml"));
                    TelaPrincipalController.conteudo.getChildren().clear();
                    TelaPrincipalController.conteudo.getChildren().add(root);
                    break;
                    
                case "ConsEspecialidade":
                    root=FXMLLoader.load(getClass().getResource("/com/medicfast/fxml/ConsEspecialidade.fxml"));
                    TelaPrincipalController.conteudo.getChildren().clear();
                    TelaPrincipalController.conteudo.getChildren().add(root);
                    break;    
                
                case "ConsTipoOcorrencia":
                    root = FXMLLoader.load(getClass().getResource("/com/medicfast/fxml/ConsTipoOcorrencia.fxml"));
                    TelaPrincipalController.conteudo.getChildren().clear();
                    TelaPrincipalController.conteudo.getChildren().add(root);
                    break;
                    
                case "CadUsuario":
                    root=FXMLLoader.load(getClass().getResource("/com/medicfast/fxml/CadUsuario.fxml"));
                    TelaPrincipalController.conteudo.getChildren().clear();
                    TelaPrincipalController.conteudo.getChildren().add(root);
                    break;    
                  
                case "ConsUsuario":
                    root=FXMLLoader.load(getClass().getResource("/com/medicfast/fxml/ConsUsuario.fxml"));
                    TelaPrincipalController.conteudo.getChildren().clear();
                    TelaPrincipalController.conteudo.getChildren().add(root);
                    break;    
                  
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }
}
