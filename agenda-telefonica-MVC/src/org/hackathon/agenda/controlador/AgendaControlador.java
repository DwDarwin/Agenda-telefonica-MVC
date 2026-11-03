package org.hackathon.agenda.controlador;

import org.hackathon.agenda.modelo.Agenda;
import org.hackathon.agenda.modelo.Contacto;
import org.hackathon.agenda.vista.VentanaPrincipal;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AgendaControlador implements ActionListener {

    private Agenda agenda;
    private VentanaPrincipal vista;

    public AgendaControlador(Agenda agenda, VentanaPrincipal vista) {
        this.agenda = agenda;
        this.vista = vista;

        // Inicializar botones listener
        this.vista.getBtnAgregar().addActionListener(this);
        this.vista.getBtnBuscar().addActionListener(this);
        this.vista.getBtnModificar().addActionListener(this);
        this.vista.getBtnEliminar().addActionListener(this);
        this.vista.getBtnLimpiar().addActionListener(this);

        // Escucha el la tabla con las filas
        this.vista.getTablaContactos().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    seleccionarContacto();
                }
            }
        });

        iniciar();
    }

    public void iniciar() {
        actualizarListaContactos();
        // Nota: Asegúrate de que tu VentanaPrincipal herede de JFrame o exponga un método para hacerse visible
        vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnAgregar()) {
            agregarContacto();
        } else if (e.getSource() == vista.getBtnBuscar()) {
            buscarContacto();
        } else if (e.getSource() == vista.getBtnModificar()) {
            modificarTelefono();
        } else if (e.getSource() == vista.getBtnEliminar()) {
            eliminarContacto();
        } else if (e.getSource() == vista.getBtnLimpiar()) {
            vista.limpiarCampos();
            vista.mostrarMensaje("Campos limpiados.", true);
        }
    }

    public void agregarContacto() {
        try {
            String nombre = vista.getTxtNombre().getText().trim();
            String apellido = vista.getTxtApellido().getText().trim();
            String telefono = vista.getTxtTelefono().getText().trim();

            // Validar campos vacíos visualmente antes de instanciar
            if (nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty()) {
                vista.mostrarMensaje("Error: Todos los campos son obligatorios.", false);
                return;
            }

            Contacto nuevoContacto = new Contacto(nombre, apellido, telefono);

            // Coincide exactamente con el método de tu clase Agenda
            agenda.añadirConctacto(nuevoContacto);

            actualizarListaContactos();
            vista.limpiarCampos();
            vista.mostrarMensaje("Contacto agregado exitosamente.", true);

        } catch (IllegalArgumentException ex) {
            vista.mostrarMensaje("Error de validación: " + ex.getMessage(), false);
        } catch (Exception ex) {
            vista.mostrarMensaje("Error: " + ex.getMessage(), false);
        }
    }

    public void buscarContacto() {
        String nombre = vista.getTxtNombre().getText().trim();
        String apellido = vista.getTxtApellido().getText().trim();

        if (nombre.isEmpty() || apellido.isEmpty()) {
            vista.mostrarMensaje("Ingrese nombre y apellido para buscar.", false);
            return;
        }

        String telefono = agenda.buscarContacto(nombre, apellido);
        if (telefono != null) {
            vista.getTxtTelefono().setText(telefono);
            vista.mostrarMensaje("Contacto encontrado. Teléfono: " + telefono, true);
        } else {
            vista.mostrarMensaje("El contacto no se encuentra registrado.", false);
        }
    }

    public void modificarTelefono() {
        String nombre = vista.getTxtNombre().getText().trim();
        String apellido = vista.getTxtApellido().getText().trim();
        String nuevoTelefono = vista.getTxtTelefono().getText().trim();

        if (nombre.isEmpty() || apellido.isEmpty() || nuevoTelefono.isEmpty()) {
            vista.mostrarMensaje("Complete nombre, apellido y nuevo teléfono.", false);
            return;
        }

        boolean modificado = agenda.modificarTelefono(nombre, apellido, nuevoTelefono);
        if (modificado) {
            actualizarListaContactos();
            vista.limpiarCampos();
            vista.mostrarMensaje("Teléfono modificado correctamente.", true);
        } else {
            vista.mostrarMensaje("No se pudo modificar. Verifique los datos.", false);
        }
    }

    public void eliminarContacto() {
        int filaSeleccionada = vista.getTablaContactos().getSelectedRow();
        if (filaSeleccionada == -1) {
            vista.mostrarMensaje("Seleccione un contacto de la tabla para eliminar.", false);
            return;
        }

        // Obtener datos de la fila seleccionada en la tabla
        String nombre = (String) vista.getTablaContactos().getValueAt(filaSeleccionada, 0);
        String apellido = (String) vista.getTablaContactos().getValueAt(filaSeleccionada, 1);
        String telefono = (String) vista.getTablaContactos().getValueAt(filaSeleccionada, 2);

        Contacto contactoAEliminar = new Contacto(nombre, apellido, telefono);
        boolean eliminado = agenda.eliminarContacto(contactoAEliminar);

        if (eliminado) {
            actualizarListaContactos();
            vista.limpiarCampos();
            vista.mostrarMensaje("Contacto eliminado con éxito.", true);
        } else {
            vista.mostrarMensaje("No se pudo eliminar el contacto.", false);
        }
    }

    public void seleccionarContacto() {
        int filaSeleccionada = vista.getTablaContactos().getSelectedRow();
        if (filaSeleccionada != -1) {
            String nombre = (String) vista.getTablaContactos().getValueAt(filaSeleccionada, 0);
            String apellido = (String) vista.getTablaContactos().getValueAt(filaSeleccionada, 1);
            String telefono = (String) vista.getTablaContactos().getValueAt(filaSeleccionada, 2);

            vista.getTxtNombre().setText(nombre);
            vista.getTxtApellido().setText(apellido);
            vista.getTxtTelefono().setText(telefono);
        }
    }

    public void actualizarListaContactos() {
        List<Contacto> lista = agenda.listarContactos();

        // Llena la JTable directamente usando el DefaultTableModel basado en la lista de contactos
        DefaultTableModel modelo = (DefaultTableModel) vista.getTablaContactos().getModel();
        modelo.setRowCount(0); // Limpiar filas anteriores

        for (Contacto c : lista) {
            modelo.addRow(new Object[]{c.getNombre(), c.getApellido(), c.getTelefono()});
        }

        vista.mostrarMensaje("Tabla actualizada (" + lista.size() + " contactos)", true);
    }
}