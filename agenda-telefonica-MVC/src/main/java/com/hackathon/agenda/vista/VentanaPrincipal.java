package com.hackathon.agenda.vista;

import com.hackathon.agenda.modelo.Contacto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VentanaPrincipal extends JFrame {

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
    private JLabel contador;

    public VentanaPrincipal() {

        setContentPane(panelPrincipal);
        setTitle("Agenda Telefenica");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        configurarTabla();
    }

    private void configurarTabla() {
        String[] columnas = {"Nombre", "Apellido", "Teléfono"};
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
    public JLabel getContador() {
        return contador;
    }

    public void cargarCamposDesdeTabla() {
        int filaSeleccionada = tablaContactos.getSelectedRow();
        if (filaSeleccionada != -1) {
            txtNombre.setText(tablaContactos.getValueAt(filaSeleccionada, 1).toString());
            txtApellido.setText(tablaContactos.getValueAt(filaSeleccionada, 2).toString());
            txtTelefono.setText(tablaContactos.getValueAt(filaSeleccionada, 3).toString());
        }
    }

    public void mostrarMensaje(String mensaje, boolean exitoso) {
        lblEstado.setText(mensaje);
        if (exitoso) {
            lblEstado.setForeground(new Color(34, 139, 34));
        } else {
            lblEstado.setForeground(Color.RED);
        }
    }

    public void actualizarTabla(List<Contacto> contactos) {
        DefaultTableModel modelo = (DefaultTableModel) tablaContactos.getModel();
        modelo.setRowCount(0);

        int id = 1;
        for (Contacto c : contactos) {
            Object[] fila = { id++, c.getNombre(), c.getApellido(), c.getTelefono() };
            modelo.addRow(fila);
        }

        if (contador != null) {
            contador.setText("Total de contactos: " + contactos.size());
        }

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

