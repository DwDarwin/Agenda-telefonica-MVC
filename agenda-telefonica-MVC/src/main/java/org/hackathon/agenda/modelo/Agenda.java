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

    /**
     * Calcula el tamaño de la lista y lo compara con la capacidad maxima
     * para saber si ya llena la agenda
     * @return
     */
    public boolean agendaLlena(){
        return contactos.size() >= capacidadMaxima;
    }

    /**
     * Añade un contacto si no existe, si no es nulo y si la agenda
     * no esta llena
     * @param c
     */
    public void añadirConctacto(Contacto c){
        if (c == null){
            throw new IllegalArgumentException("El contacto no puede ser nulo");
        }
        if (existeContacto(c)){
            throw new IllegalArgumentException("El contacto ya existe en la agenda");
        }
        if (agendaLlena()){
            throw new IllegalArgumentException("La agenda esta llena");
        }
        contactos.add(c);
    }

    /**
     * Devuelve una copia de la lista ordenada con el metodo sort()
     * @return
     */
    public List<Contacto> listarContactos(){
        List<Contacto> copiaLista = new ArrayList<>(contactos);

        copiaLista.sort(Comparator.comparing(Contacto::getApellido, String.CASE_INSENSITIVE_ORDER).thenComparing(Contacto::getNombre, String.CASE_INSENSITIVE_ORDER));
        return copiaLista;
    }
}
