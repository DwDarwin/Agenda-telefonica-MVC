package org.hackathon.agenda;

import org.hackathon.agenda.controlador.AgendaControlador;
import org.hackathon.agenda.modelo.Agenda;
import org.hackathon.agenda.vista.VentanaPrincipal;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Asegura que la interfaz gráfica corra en el hilo seguro de Swing
        SwingUtilities.invokeLater(() -> {
            // 1. Instanciar el Modelo (la lógica de la agenda con capacidad por defecto)
            Agenda agenda = new Agenda();

            // 2. Instanciar la Vista (la interfaz gráfica)
            VentanaPrincipal vista = new VentanaPrincipal();

            // 3. Instanciar el Controlador uniendo ambos componentes
            new AgendaControlador(agenda, vista);
        });
    }
}