package com.hackathon.agenda;

import com.hackathon.agenda.controlador.AgendaControlador;
import com.hackathon.agenda.modelo.Agenda;
import com.hackathon.agenda.vista.VentanaPrincipal;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Agenda Telefónica MVC compiló y se ejecuta correctamente");

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
