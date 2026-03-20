# 🐾 Vet - Sistema de Gestión Veterinaria

**Vet** es una solución robusta desarrollada con **Spring Boot** diseñada para digitalizar la operación diaria de clínicas veterinarias. El sistema centraliza la gestión de pacientes, propietarios y la agenda de citas, garantizando la integridad de la información y la eficiencia en la atención.

---

## 🚀 Requerimientos Funcionales

El sistema se ha construido bajo una arquitectura lógica, cumpliendo con los siguientes puntos atómicos:

### Gestión de Usuarios y Seguridad
* Autenticación de usuarios del sistema.

### Módulo de Clientes y Mascotas
* Registro y consulta de clientes.
* Gestión de mascotas vinculadas a su dueño.
* Clasificación de mascotas por Especie y Raza.

### Control de Citas Médicas
* Agendamiento de citas con validación de disponibilidad horaria.
* Seguimiento de estados de cita (PENDIENTE, COMPLETADA, CANCELADA).
* Filtrado de citas por fecha específica.
* Consulta de historial de citas por mascota.

---

## 🛠️ Tecnologías Utilizadas

El stack tecnológico seleccionado asegura escalabilidad y facilidad de mantenimiento:

* **Java 21**: Lenguaje principal de desarrollo.
* **Spring Boot 3.5.11**: Framework para la creación de la API REST.
* **Spring Data JPA**: Abstracción para la persistencia de datos.
* **PostgreSQL**: Motor de base de datos relacional.
* **Lombok**: Biblioteca para reducir el código repetitivo.
* **Maven**: Gestor de dependencias y construcción del proyecto.

---

## ⚙️ Cómo Levantar el Proyecto

Sigue estos pasos para ejecutar el sistema en tu entorno local:

1.  **Clonar el repositorio:**
    ```bash
    git clone [https://github.com/tu-usuario/vet.git](https://github.com/tu-usuario/vet.git)
    ```

2.  **Configurar la Base de Datos:**
    * Crea una base de datos llamada `vet_db`.
    * Configura tus credenciales en el archivo `src/main/resources/application.properties`.

3.  **Compilar el proyecto:**
    ```bash
    mvn clean install
    ```

4.  **Ejecutar la aplicación:**
    ```bash
    mvn spring-boot:run
    ```
    *La API estará disponible en `http://localhost:8080`*

---

## 👤 Desarrollado por

Este sistema ha sido diseñado e implementado con dedicación por:

* **Jhoan Aronith Muñoz**
* [LinkedIn](https://www.linkedin.com/in/jhoan-muñoz) | [GitHub](https://github.com/JhoanAronith)

---

> *Este proyecto se encuentra en constante evolución. Actualmente en la versión v1.0.0 (Core System).*