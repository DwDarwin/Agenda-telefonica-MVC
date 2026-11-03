# Documentación: ajuste de paquetes e imports a `com.hackathon.agenda`

## Problema

IntelliJ reportaba el siguiente error al abrir el proyecto:

```
Package name 'com.hackathon.agenda.modelo' does not correspond to the file path 'main.java.com.hackathon.agenda.modelo'
```

El error se presentaba aunque la estructura física de archivos estuviera correcta en disco y `./gradlew build` compilara sin errores.

## Causa raíz

Dos causas concurrentes:

1. **Paquetes Java declarados en `org.hackathon.agenda`** mientras el README exige el paquete principal `com.hackathon.agenda` bajo `src/main/java/com/hackathon/agenda/`.
2. **Configuración de IntelliJ desactualizada:** el archivo `.idea/hackaton2.iml` tenía marcado como *Sources Root* la carpeta `agenda-telefonica-MVC/src` en lugar de `agenda-telefonica-MVC/src/main/java`. Eso hacía que IntelliJ calculara el paquete como `main.java.com.hackathon.agenda.modelo` (incluía `main/java` dentro del nombre del paquete).

## Pasos realizados

### 1. Mover los archivos al paquete correcto

Se renombró la carpeta física y se actualizó cada declaración `package`:

```
src/main/java/org/hackathon/agenda/
└── ...   →   src/main/java/com/hackathon/agenda/
```

Archivos afectados:

| Archivo | Paquete antes | Paquete después |
|---|---|---|
| `Main.java` | `org.hackathon.agenda` | `com.hackathon.agenda` |
| `modelo/Contacto.java` | `org.hackathon.agenda.modelo` | `com.hackathon.agenda.modelo` |
| `modelo/Agenda.java` | `org.hackathon.agenda.modelo` | `com.hackathon.agenda.modelo` |
| `vista/VentanaPrincipal.java` | `org.hackathon.agenda.vista` | `com.hackathon.agenda.vista` |
| `controlador/AgendaControlador.java` | `org.hackathon.agenda.controlador` | `com.hackathon.agenda.controlador` |

También se actualizó el binding del GUI Designer de IntelliJ en `VentanaPrincipal.form`:

```xml
bind-to-class="com.hackathon.agenda.vista.VentanaPrincipal"
```

### 2. Corregir la raíz de fuentes en IntelliJ

Se editó `.idea/hackaton2.iml` para que el `sourceFolder` apunte a la carpeta correcta:

```xml
<sourceFolder url="file://$MODULE_DIR$/agenda-telefonica-MVC/src/main/java" isTestSource="false" />
```

> **Nota:** Si el proyecto se abrió como módulo Java plano, IntelliJ puede seguir mostrando el error hasta que se refresque la configuración. Lo recomendado es reabrirlo como proyecto Gradle:
> `File → Open → agenda-telefonica-MVC/build.gradle.kts → Open as Project`

### 3. Configurar Gradle según el README

Se actualizó `agenda-telefonica-MVC/build.gradle.kts`:

```kotlin
plugins {
    id("java")
    application
}

group = "com.hackathon.agenda"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("com.hackathon.agenda.Main")
}
```

Esto permite ejecutar `./gradlew run` tal como documenta el README.

### 4. Print de verificación en `Main.java`

Se agregó un mensaje de salida para confirmar compilación y ejecución:

```java
public static void main(String[] args) {
    System.out.println("Agenda Telefónica MVC compiló y se ejecuta correctamente");
}
```

### 5. Limpiar artefactos residuales y `.gitignore`

- Se eliminó el directorio `out/` de la raíz (contendría clases compiladas bajo el paquete viejo `org/hackathon`).
- Se agregó al `.gitignore` de la raíz:
  ```
  out/
  *.class
  ```

## Verificación

Tras los cambios se ejecutaron los comandos de Gradle con resultado exitoso:

```
$ ./gradlew build
BUILD SUCCESSFUL

$ ./gradlew run
Agenda Telefónica MVC compiló y se ejecuta correctamente
BUILD SUCCESSFUL
```

## Qué queda pendiente (opcional, solo IDE)

Para que IntelliJ deje de marcar el error de paquete de forma permanente:

1. Abrir el proyecto importando el archivo de Gradle:
   `File → Open → agenda-telefonica-MVC/build.gradle.kts → Open as Project`
2. O, si se mantiene el proyecto abierto como módulo plano, marcar `src/main/java` como *Sources Root*:
   Click derecho sobre `src/main/java` → `Mark Directory as` → `Sources Root`

## Commit

Todos los cambios se commitearon en la rama `argie/fix-package-imports`:

```
commit 07e57b2 refactor: migra paquetes a com.hackathon.agenda y configura Gradle application
```
