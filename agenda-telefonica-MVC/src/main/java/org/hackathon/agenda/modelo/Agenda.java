package main.java.org.hackathon.agenda.modelo;

import java.util.*;

public class Agenda {

    // Atributos constantes
    private static final int CAPAPACIDAD_POR_DEFECTO = 10;
    private final List<Contacto> contactos;
    private final int capacidadMaxima;


    // Constructores
    /**
     * Crea una agenda con 10 contactos en caso de que no se le
     * de los parametros de tamaño
     */
    public Agenda(){
        this(CAPAPACIDAD_POR_DEFECTO);
    }

    /**
     * Crea una agenda con la capacidad dada como parametro
     */
    public Agenda(int capacidadMaxima){
        if (capacidadMaxima <= 0){
            throw new IllegalArgumentException("La capacidad de la agenda debe ser mayor que cero");
        }
        this.capacidadMaxima = capacidadMaxima;
        this.contactos = new ArrayList<>();
    }

    // Metodos

    /**
     * Metodo para validar si un conctaco existe y no es null
     * @param c
     * @return true
     */
    public boolean existeContacto(Contacto c){
        return c != null && contactos.contains(c);
    }
}
