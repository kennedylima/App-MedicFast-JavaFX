package com.medicfast.controller;

import com.medicfast.model.Medicamento;
import com.medicfast.service.MedicamentoService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CadMedicamentoController implements Initializable {

    @FXML
    private TextField txtSintomas;

    @FXML
    private TextField txtPeso;


    @FXML
    private TextField txtQtd;

    @FXML
    private TextArea txtObs;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtFabricante;

    @FXML
    private Button btnLimpar;
        
    @FXML
    private Button btnConsultar;
    
    @FXML
    private Button btnSalvar;

    private static boolean valida = false;
    private static Medicamento med;

    public static boolean isValida() {
        return valida;
    }

    public static void setValida(boolean valida) {
        CadMedicamentoController.valida = valida;
    }
    
    
    
    @FXML
    void limpar(ActionEvent event) {
        limpar();
    }

    @FXML
    void consultar(ActionEvent event){
        TelaController tela = new TelaController();
        tela.inserirTela("ConsMedicamento");
    }
    
    @FXML
    void salvar(ActionEvent event) {

        Medicamento m = new Medicamento();
        m.setNome(txtNome.getText());
        m.setFabricante(txtFabricante.getText());
        m.setPeso(txtPeso.getText());
        m.setQuantidade(Integer.parseInt(txtQtd.getText()));
        m.setSintoma(txtSintomas.getText());
        m.setObs(txtObs.getText());

        MedicamentoService ms = new MedicamentoService();
        String msg = ms.salvar(m);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(msg);
        alert.showAndWait();
        
        limpar();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (valida == true) {
            txtFabricante.setText(med.getFabricante());
            txtNome.setText(med.getNome());
            txtObs.setText(med.getObs());
            txtObs.setText(med.getObs());
            txtPeso.setText(med.getPeso());
            txtQtd.setText(med.getQuantidade().toString());
            txtSintomas.setText(med.getSintoma());
            btnSalvar.setOnAction(event -> Editar());
        }
    }

    void TelaEditar(Medicamento m) {
        med = m;
        valida = true;
        TelaController tc = new TelaController();
        tc.inserirTela("CadMedicamento");
    }

    private void Editar() {

        Medicamento m = med;
        m.setNome(txtNome.getText());
        m.setFabricante(txtFabricante.getText());
        m.setPeso(txtPeso.getText());
        m.setQuantidade(Integer.parseInt(txtQtd.getText()));
        m.setSintoma(txtSintomas.getText());
        m.setObs(txtObs.getText());

        MedicamentoService ms = new MedicamentoService();
        String msg = ms.salvar(m);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(msg);
        alert.showAndWait();
        limpar();
        TelaController tc = new TelaController();
        tc.inserirTela("ConsMedicamento");
        
        valida = false;
        
        
    }
    
    public void limpar(){
        txtFabricante.setText(null);
        txtNome.setText(null);
        txtObs.setText(null);
        txtObs.setText(null);
        txtPeso.setText(null);
        txtQtd.setText(null);
        txtSintomas.setText(null);
    }

}
