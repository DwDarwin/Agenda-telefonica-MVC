package com.hackathon.agenda.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VentanaPrincipal {

    private JTable tablaContactos;
    private JPanel panelPrincipal;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtTelefono;
    private JLabel lblEstado;
    private JButton btnAgregar;
    private JButton btnBuscar;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnLimpiar;

    public VentanaPrincipal() {
        SwingUtilities.invokeLater(() -> {
            configurarTabla();
        });
    }
    private void configurarTabla() {
        String[] columnas = {"ID", "Nombre", "Apellido", "Teléfono"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaContactos.setModel(modelo);
    }

    public JTextField getTxtNombre() { return txtNombre; }
    public JTextField getTxtApellido() { return txtApellido; }
    public JTextField getTxtTelefono() { return txtTelefono; }
    public JTable getTablaContactos() { return tablaContactos; }
    public JLabel getLblEstado() { return lblEstado; }
    public JButton getBtnAgregar() { return btnAgregar; }
    public JButton getBtnBuscar() { return btnBuscar; }
    public JButton getBtnModificar() { return btnModificar; }
    public JButton getBtnEliminar() { return btnEliminar; }
    public JButton getBtnLimpiar() { return btnLimpiar; }

    public void mostrarMensaje(String mensaje, boolean exitoso) {
        lblEstado.setText(mensaje);
        if (exitoso) {
            lblEstado.setForeground(new Color(34, 139, 34));
        } else {
            lblEstado.setForeground(Color.RED);
        }
    }

    public void actualizarTabla(List<Object> contactos) {
        DefaultTableModel modelo = (DefaultTableModel) tablaContactos.getModel();
        modelo.setRowCount(0);

        mostrarMensaje("Tabla actualizada (" + contactos.size() + " contactos)", true);
    }

    public void limpiarCampos() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtTelefono.setText("");
        txtNombre.requestFocus();
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }


}
