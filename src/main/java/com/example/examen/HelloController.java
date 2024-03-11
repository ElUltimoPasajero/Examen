package com.example.examen;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;


import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.swing.JRViewer;

import javax.swing.*;

public class HelloController implements Initializable {

    @FXML
    private ComboBox<String> comboSexo;
    @FXML
    private TextField txtPeso;
    @FXML
    private ComboBox <String> comboActividad;
    @FXML
    private Button buttonGuardar;
    @FXML
    private Label labelInformacion;
    @FXML
    private TextField txtEdad;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtTalla;
    @FXML
    private Button btnDescargar;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<String> tiposActividdad = FXCollections.observableArrayList("Sedentario", "Moderado", "Activo", "MuyActivo");

        ObservableList<String> sexos = FXCollections.observableArrayList("Hombre", "Mujer");
        comboActividad.setItems(tiposActividdad);
        comboSexo.setItems(sexos);
        comboSexo.getSelectionModel().selectFirst();
        comboActividad.getSelectionModel().selectFirst();






    }

    private int calcularGer(Cliente cliente) {
        double clienteGer = 0;
        if ("Hombre".equals(cliente.getSexo())) {
            clienteGer = 66.473 + 13.751 * cliente.getPeso() + 5.00033 * cliente.getTalla() - 6.755 * cliente.getEdad();
        } else if ("Mujer".equals(cliente.getSexo())) {
            clienteGer = 655.0955 + 9463 * cliente.getPeso() + 1.8496 * cliente.getTalla() - 4.6756 * cliente.getEdad();
        }
        return (int) clienteGer;
    }

    private int calcularGeT(Cliente cliente, int ger) {
        double clienteGet = 0;

        if ("Hombre".equals(cliente.getSexo())) {
            if ("Sedentario".equals(cliente.getTipoActividad())) {
                clienteGet = ger * 1.3;
            } else if ("Moderado".equals(cliente.getTipoActividad())) {
                clienteGet = ger * 1.6;
            } else if ("Activo".equals(cliente.getTipoActividad())) {
                clienteGet = ger * 1.7;
            } else if ("Muy Activo".equals(cliente.getTipoActividad())) {
                clienteGet = ger * 2.1;
            }
        } else if ("Mujer".equals(cliente.getSexo())) {
            if ("Sedentario".equals(cliente.getTipoActividad())) {
                clienteGet = ger * 1.3;
            } else if ("Moderado".equals(cliente.getTipoActividad())) {
                clienteGet = ger * 1.5;
            } else if ("Activo".equals(cliente.getTipoActividad())) {
                clienteGet = ger * 1.6;
            } else if ("Muy Activo".equals(cliente.getTipoActividad())) {
                clienteGet = ger * 1.9;
            }
        }
        return (int) clienteGet;
    }

        @FXML
    public void guardarCliente(ActionEvent actionEvent) {

        Cliente c = new Cliente();

            if (txtNombre.getText().isEmpty() || txtEdad.getText().isEmpty() ||
                    txtPeso.getText().isEmpty() || txtTalla.getText().isEmpty()) {
                labelInformacion.setText("Tiene que rellenar todos los campos");
                return;
            }


       c.setNombre(txtNombre.getText());
       c.setEdad(Integer.parseInt(txtEdad.getText()));
       c.setPeso(Integer.parseInt(txtPeso.getText()));
       c.setTalla(Integer.parseInt(txtTalla.getText()));
       c.setSexo(comboSexo.getValue());
       c.setTipoActividad(comboActividad.getValue());

       int clienteGER=calcularGer(c);
       int clienteGET=calcularGeT(c,clienteGER);




            System.out.println(clienteGER);
            System.out.println(clienteGET);

            labelInformacion.setText("El Cliente "+ c.getNombre() +" tiene un GER de " + clienteGER + " y un GET de " + clienteGET);


    }

    @FXML
    public void descargarInforme(ActionEvent actionEvent) throws JRException, SQLException {




        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/clientespesos", "root", "");

        HashMap<String, Object> hashMap = new HashMap<>();



        var jasperPrint = JasperFillManager.fillReport("ClientesPEso.jasper", hashMap, connection);



        JRViewer viewer = new JRViewer(jasperPrint);

        JFrame frame = new JFrame("Listado de Clientes");
        frame.getContentPane().add(viewer);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.pack();
        frame.setVisible(true);

        System.out.print("Done!");

        JRPdfExporter exp = new JRPdfExporter();
        exp.setExporterInput(new SimpleExporterInput(jasperPrint));
        exp.setExporterOutput(new SimpleOutputStreamExporterOutput("clientes.pdf"));
        exp.setConfiguration(new SimplePdfExporterConfiguration());
        exp.exportReport();

        System.out.print("Done!");

    }
}