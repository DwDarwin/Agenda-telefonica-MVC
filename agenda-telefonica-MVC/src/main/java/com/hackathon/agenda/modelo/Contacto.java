package com.hackathon.agenda.modelo;

import java.util.Objects;
import java.util.regex.Pattern;

public class Contacto {

  // RegEx para validar teléfonos (ej: +34 600 000 000, 600000000)
  /*
   * Ejemplos válidos que acepta este Regex:
   * - 600000000          (9 dígitos continuos)[cite: 2]
   * - 600 000 000        (9 dígitos separados en bloques de 3)
   * - +34 600 000 000    (Con prefijo internacional y espacios)[cite: 2]
   * - +34600000000       (Con prefijo internacional sin espacios)
   * - +51 987 654 321    (Con otro código de país válido de 1 a 3 dígitos)
   */
  private static final Pattern TELEFONO_PATTERN = Pattern.compile("^(\\+\\d{1,3}\\s?)?\\d{3}(\\s?\\d{3}){2}$|^\\d{9}$");

  private String nombre;
  private String apellido;
  private String telefono;

  public Contacto(String nombre, String apellido, String telefono) {
    setNombre(nombre);
    setApellido(apellido);
    setTelefono(telefono);
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    if (nombre == null || nombre.trim().isEmpty()) {
      throw new IllegalArgumentException("El nombre no puede estar vacío ni contener solo espacios en blanco.");
    }
    this.nombre = nombre.trim();
  }

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    if (apellido == null || apellido.trim().isEmpty()) {
      throw new IllegalArgumentException("El apellido no puede estar vacío ni contener solo espacios en blanco.");
    }
    this.apellido = apellido.trim();
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    if (telefono == null || !TELEFONO_PATTERN.matcher(telefono.trim()).matches()) {
      throw new IllegalArgumentException("El teléfono no es válido. Formatos admitidos: '+34 600 000 000' o '600000000'.");
    }
    this.telefono = telefono.trim();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Contacto)) return false;
    Contacto contacto = (Contacto) o;
    return Objects.equals(nombre.toLowerCase(), contacto.nombre.toLowerCase())
        && Objects.equals(apellido.toLowerCase(), contacto.apellido.toLowerCase());
  }

  @Override
  public int hashCode() {
    return Objects.hash(nombre.toLowerCase(), apellido.toLowerCase());
  }

  @Override
  public String toString() {
    return nombre + " " + apellido + " - " + telefono;
  }
}
