# 📞 Agenda Telefónica MVC

Aplicación de escritorio para la gestión de una agenda telefónica desarrollada en **Java Swing**, utilizando **Gradle** como gestor de automatización y construida bajo el patrón arquitectónico **Modelo-Vista-Controlador (MVC)**.

---

## 🛠️ Tecnologías y Herramientas

* **Lenguaje:** Java (JDK 17+)
* **GUI Framework:** Java Swing
* **Build Tool:** Gradle (utilizando `gradlew` wrapper para portabilidad)
* **IDE Recomendado:** IntelliJ IDEA

---

## 🏗️ Estructura del Proyecto (Patrón MVC)

El proyecto sigue una separación estricta de responsabilidades en el paquete `com.hackathon.agenda`:

```
src/main/java/com/hackathon.agenda/
│
├── modelo/
│   ├── Contacto.java           # Entidad base (atributos, validaciones Regex y métodos equals/hashCode)
│   └── Agenda.java             # Lógica de negocio (almacenamiento, ordenamiento y operaciones CRUD)
│
├── vista/
│   ├── VentanaPrincipal.java   # Interfaz gráfica principal (JFrame, JTable, campos y panel de estado)
│   └── ComponentesGraficos.java# (Opcional) Paneles y componentes visuales reutilizables
│
├── controlador/
│   └── AgendaControlador.java  # Listener de eventos, enlace entre la Vista y el Modelo
│
└── Main.java                   # Orquestador del sistema (Instancia e integra Modelo, Vista y Controlador)
```

> **Regla de Desacoplamiento:**
> * El paquete `modelo` **no importa** bibliotecas de la interfaz de usuario (`javax.swing.*`).
> * El paquete `vista` no contiene lógica de control; expone componentes para que el controlador les asigne los `ActionListeners`.

---

## 📋 Requisitos del Sistema y Funcionalidades

### 1. Modelo de Datos (`Contacto`)
* **Atributos:** `nombre`, `apellido`, `telefono`.
* **Validaciones:**
  * Nombre y apellido no pueden estar vacíos ni formados por solo espacios en blanco.
  * Teléfono validado mediante **Expresiones Regulares (Regex)** (soporta formatos como `+34 600 000 000` o `600000000`).
* **Criterio de Igualdad:** Dos contactos son iguales si coinciden en `nombre` y `apellido` (ignorando mayúsculas y minúsculas). Sobrescribe los métodos `equals()` y `hashCode()`.

### 2. Gestión de la Agenda (`Agenda`)
* **Almacenamiento:** Basado en `List` / estructuras internas.
* **Capacidad:** Configurable al inicializar (capacidad por defecto: 10 contactos).
* **Métodos Obligatorios:**
  * `añadirContacto(Contacto c)`: Inserta un contacto tras validar espacio y duplicados.
  * `existeContacto(Contacto c)`: Verifica duplicados por nombre y apellido.
  * `listarContactos()`: Devuelve la lista ordenada alfabéticamente.
  * `buscarContacto(String nombre, String apellido)`: Retorna el teléfono del contacto.
  * `eliminarContacto(Contacto c)`: Remueve el contacto si existe.
  * `modificarTelefono(String nombre, String apellido, String nuevoTelefono)`: Actualiza el teléfono tras validación Regex.
  * `agendaLlena()` y `espaciosLibres()`: Métodos de control de capacidad.

### 3. Interfaz Gráfica (`VentanaPrincipal`)
* Single Frame (`JFrame`).
* **JTable & Sincronización:** Al seleccionar una fila en la tabla de contactos, sus datos se cargan automáticamente en el formulario para facilitar edición o borrado.
* **Panel de Estado:** 
  * Errores (campos vacíos, formato inválido, capacidad llena, duplicados): Muestra mensaje descriptivo en **color rojo**.
  * Éxito en operaciones: Muestra mensaje informativo en **color verde**.

---

## 🚀 Instalación y Ejecución

Al hacer uso del **Gradle Wrapper**, no requieres tener Gradle instalado globalmente en tu sistema.

1. **Clonar el repositorio:**
   ```bash
   git clone <URL_DEL_REPOSITORIO>
   cd <NOMBRE_DEL_REPOSITORIO>
   ```

2. **Compilar el proyecto:**
   * En Linux / macOS:
     ```bash
     ./gradlew build
     ```
   * En Windows (CMD / PowerShell):
     ```cmd
     gradlew.bat build
     ```

3. **Ejecutar la aplicación:**
   * En Linux / macOS:
     ```bash
     ./gradlew run
     ```
   * En Windows (CMD / PowerShell):
     ```cmd
     gradlew.bat run
     ```

---

## 👥 Repartición del Equipo (5 Integrantes)

| Integrante | Rol / Módulo | Responsabilidades Clave |
| :--- | :--- | :--- |
| **Integrante 1** | Modelo (`Contacto.java`) | Entidad base, validación Regex de teléfono, `equals()` y `hashCode()`. |
| **Integrante 2** | Modelo (`Agenda.java`) | Lógica backend, lista de almacenamiento, ordenamiento alfabético y operaciones CRUD. |
| **Integrante 3** | Vista (`VentanaPrincipal.java`) | Diseño del JFrame, JTable, campos del formulario y Panel de Estado (rojo/verde). |
| **Integrante 4** | Controlador (`AgendaControlador.java`) | Captura de ActionListeners, sincronización selección-tabla y flujo de eventos. |
| **Integrante 5** | Orquestador y QA (`Main.java` / Gradle) | Configuración de Gradle Wrapper, inicialización MVC y pruebas de robustez/calidad. |

---

## ⚖️ Criterios de Evaluación

- **30% - Arquitectura MVC y OOP:** Separación estricta de capas y encapsulamiento.
- **25% - Lógica del Backend:** Cumplimiento de operaciones, Regex y ordenamiento alfabético.
- **20% - GUI e Interacción Swing:** Manejo de `DefaultTableModel`, eventos y layout limpio.
- **15% - Validaciones Visuales y Robustez:** Manejo limpio de errores sin lanzar excepciones en consola.
- **10% - Sustentación y README:** Calidad de la documentación y presentación del proyecto.