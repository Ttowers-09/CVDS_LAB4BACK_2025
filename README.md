# Sistema de Reservas de Laboratorios

Este proyecto es un sistema de reservas de laboratorios desarrollado en **Java** utilizando **Spring Boot** y **MongoDB** como base de datos NoSQL. Permite a los usuarios registrar laboratorios, realizar reservas y gestionar la disponibilidad de los espacios.

## 📌 Características
- Gestión de usuarios con diferentes roles (administrador, estudiante, profesor).
- Creación, modificación y eliminación de reservas de laboratorios.
- Control de disponibilidad de laboratorios.
- Priorización de reservas según criterios definidos.
- Almacenamiento en **MongoDB**.

## 🚀 Tecnologías utilizadas
- **Java 17+**
- **Spring Boot** (Spring Data MongoDB, Spring Web)
- **MongoDB**
- **Lombok** (para reducir código repetitivo)
- **Jackson** (para serialización JSON)

## 📂 Estructura del Proyecto
```
📦 laboratory-reservations
┣ 📂 src/main/java/com/cvds/eci/laboratoryreservations
┃ ┣ 📂 app_core/model
┃ ┃ ┣ 📜 Booking.java       # Modelo de reserva
┃ ┃ ┣ 📜 Laboratory.java    # Modelo de laboratorio
┃ ┃ ┗ 📜 User.java          # Modelo de usuario
┃ ┣ 📂 repository
┃ ┃ ┣ 📜 BookingRepository.java    # Repositorio para reservas
┃ ┃ ┣ 📜 LaboratoryRepository.java # Repositorio para laboratorios
┃ ┃ ┗ 📜 UserRepository.java        # Repositorio para usuarios
┃ ┣ 📂 controller
┃ ┃ ┣ 📜 BookingController.java    # Controlador para reservas
┃ ┃ ┣ 📜 LaboratoryController.java # Controlador para laboratorios
┃ ┃ ┗ 📜 UserController.java        # Controlador para usuarios
┃ ┣ 📂 service
┃ ┃ ┣ 📜 BookingService.java    # Lógica de negocio de reservas
┃ ┃ ┣ 📜 LaboratoryService.java # Lógica de negocio de laboratorios
┃ ┃ ┗ 📜 UserService.java        # Lógica de negocio de usuarios
┃ ┗ 📜 LaboratoryReservationsApplication.java # Clase principal
┣ 📜 pom.xml (Dependencias de Maven)
┣ 📜 README.md (Este archivo)
```

## 🔧 Instalación y Configuración
### 1️⃣ Requisitos
- Java 17 o superior
- MongoDB instalado y en ejecución
- Maven

### 2️⃣ Clonar el repositorio
```bash
git clone https://github.com/tu-usuario/laboratory-reservations.git
cd laboratory-reservations
```

### 3️⃣ Configurar la base de datos
Asegúrate de que MongoDB está corriendo en `localhost:27017`. Puedes modificar la configuración en `application.properties` o `application.yml`.

### 4️⃣ Ejecutar la aplicación
```bash
mvn spring-boot:run
```

## 🚀 Endpoints

### 🔹 1. BookingController (`/api/bookings`)
Gestiona las reservas de los laboratorios.

| Método | Endpoint | Descripción |
|--------|---------|-------------|
| `GET` | `/api/bookings` | Obtiene todas las reservas |
| `GET` | `/api/bookings/{id}` | Obtiene una reserva por su ID |
| `GET` | `/api/bookings/user/{id}` | Obtiene reservas asociadas a un usuario |
| `POST` | `/api/bookings` | Crea una nueva reserva |
| `DELETE` | `/api/bookings/{id}` | Elimina una reserva |

📌 **Relaciones:**
- `Booking` → Representa una reserva.
- `User` → Relacionado a la reserva mediante `userId`.
- `Laboratory` → Relacionado a la reserva mediante `labId`.

---

### 🔹 2. LaboratoryController (`/api/labs`)
Gestiona los laboratorios disponibles.

| Método | Endpoint | Descripción |
|--------|---------|-------------|
| `GET` | `/api/labs` | Obtiene todos los laboratorios |
| `POST` | `/api/labs` | Agrega un nuevo laboratorio |
| `PUT` | `/api/labs/{id}` | Actualiza la información de un laboratorio |
| `DELETE` | `/api/labs/{id}` | Elimina un laboratorio |

📌 **Relaciones:**
- `Laboratory` → Representa un laboratorio.
- `Booking` → Un laboratorio puede estar asociado a varias reservas.

---

### 🔹 3. UserController (`/api/users`)
Gestiona los usuarios y su autenticación.

| Método | Endpoint | Descripción |
|--------|---------|-------------|
| `GET` | `/api/users` | Obtiene todos los usuarios |
| `GET` | `/api/users/{id}` | Obtiene un usuario por ID |
| `GET` | `/api/users/get/{name}` | Obtiene un usuario por nombre |
| `POST` | `/api/users/add/user` | Crea un nuevo usuario |
| `POST` | `/api/users/login` | Inicia sesión |
| `PUT` | `/api/users/{id}` | Actualiza un usuario |
| `DELETE` | `/api/users/{id}` | Elimina un usuario |

📌 **Relaciones:**
- `User` → Representa a un usuario.
- `Booking` → Un usuario puede hacer múltiples reservas.

---

## 📂 Modelos

### **User**
```java
public class User {
    private String id;
    private String name;
    private String email;
    private String password;
    private String role;
}
```
📍 Un usuario puede hacer múltiples reservas (`Booking`).

### **Laboratory**
```java
public class Laboratory {
    private String id;
    private String name;
    private String location;
    private int capacity;
}
```
📍 Un laboratorio puede ser reservado varias veces (`Booking`).

### **Booking**
```java
public class Booking {
    private String id;
    private String userId;
    private String labId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
```
📍 Relaciona un usuario (`userId`) con un laboratorio (`labId`).

---

# Servicios de la API de Reservas de Laboratorios

## BookingService

Este servicio gestiona las reservas de laboratorios en la aplicación, proporcionando funcionalidades para crear, consultar y eliminar reservas.

### Métodos:
- **`getAllBookings()`**: Obtiene todas las reservas registradas en el sistema. Lanza una excepción si no hay reservas.
- **`addBooking(Booking booking)`**: Crea una nueva reserva validando que no haya conflictos de horario. Lanza una excepción si el laboratorio no existe o si el horario está ocupado.
- **`deleteBooking(String id)`**: Elimina una reserva por su ID. Lanza una excepción si la reserva no existe.
- **`findById(String id)`**: Busca una reserva por su ID. Lanza una excepción si la reserva no existe.
- **`findAllBookingsByUserId(String userId)`**: Busca todas las reservas realizadas por un usuario específico. Lanza una excepción si el usuario no existe.

---

## LaboratoryService

Este servicio administra los laboratorios, permitiendo su consulta, creación, eliminación y actualización.

### Métodos:
- **`getLaboratories()`**: Obtiene la lista de laboratorios registrados. Lanza una excepción si no hay laboratorios.
- **`addLaboratory(Laboratory laboratory)`**: Agrega un nuevo laboratorio. Lanza una excepción si el laboratorio ya existe.
- **`deleteLaboratory(String id)`**: Elimina un laboratorio por su ID. Lanza una excepción si el laboratorio no existe.
- **`updateLaboratory(String idLaboratory, Laboratory updatedLab)`**: Actualiza la información de un laboratorio existente. Lanza una excepción si el laboratorio no se encuentra.

---

## UserService

Este servicio gestiona la autenticación y administración de usuarios.

### Métodos:
- **`getAllUsers()`**: Obtiene la lista de todos los usuarios registrados. Lanza una excepción si no hay usuarios.
- **`getUserById(String id)`**: Obtiene un usuario por su ID. Lanza una excepción si el usuario no existe.
- **`getUserByName(String name)`**: Obtiene un usuario por su nombre. Lanza una excepción si el usuario no existe.
- **`addUser(User user)`**: Registra un nuevo usuario con una contraseña encriptada. Lanza una excepción si el usuario ya existe.
- **`deleteUser(String id)`**: Elimina un usuario por su ID. Lanza una excepción si el usuario no existe.
- **`updateUser(String userId, User user)`**: Actualiza la información de un usuario. Lanza una excepción si el usuario no se encuentra.
- **`verifyUserRole(User user, String requiredRole)`**: Verifica la autenticación de un usuario y su rol antes de generar un token JWT. Lanza una excepción si la autenticación falla o el rol no es el requerido.
- **`verifyUserCredentials(User user)`**: Verifica las credenciales de un usuario. Si son correctas, genera un token JWT con el rol correspondiente; de lo contrario, lanza una excepción.

# Base de Datos en Laboratory Reservations

## Descripción General

En este proyecto se utiliza **MongoDB** como sistema de base de datos NoSQL para gestionar la información de reservas de laboratorios, usuarios y laboratorios disponibles. MongoDB permite almacenar datos en documentos flexibles en formato JSON, lo que facilita el manejo de estructuras cambiantes sin afectar la escalabilidad ni el rendimiento.

## Estructura del Proyecto

Se ha introducido una carpeta llamada **repository** que contiene las interfaces de acceso a los datos mediante el uso de **Spring Data MongoDB**. Estas interfaces permiten realizar consultas y operaciones sobre los documentos almacenados en MongoDB de manera eficiente y sencilla.

### Repositorios Implementados

#### 1. **BookingRepository**
Ubicada en `com.cvds.eci.laboratoryreservations.app_core.repository`, esta interfaz gestiona las reservas de los laboratorios y proporciona métodos personalizados para realizar consultas específicas.

- **`findByLabNameAndDateAndInitHourLessThanAndFinalHourGreaterThan(String labName, LocalDate date, LocalTime finalHour, LocalTime initHour)`**
  - Busca reservas en un laboratorio específico dentro de un rango de tiempo determinado.
- **`findByUserId(String userId)`**
  - Encuentra una reserva por el identificador de usuario.
- **`findAllByUserId(String userId)`**
  - Obtiene todas las reservas realizadas por un usuario específico.

#### 2. **LaboratoryRepository**
Ubicada en `com.cvds.eci.laboratoryreservations.app_core.repository`, esta interfaz permite acceder a la información de los laboratorios disponibles.

- **`findByName(String name)`**
  - Obtiene un laboratorio a partir de su nombre.

#### 3. **UserRepository**
Ubicada en `com.cvds.eci.laboratoryreservations.app_core.repository`, esta interfaz facilita la gestión de los usuarios.

- **`findByName(String name)`**
  - Busca un usuario por su nombre.
- **`findByEmail(String email)`**
  - Encuentra un usuario por su correo electrónico.
- **`findByEmailAndName(String email, String name)`**
  - Recupera un usuario a partir de su correo y nombre.


## Cambios en la Estructura del Proyecto

Inicialmente, las clases relacionadas con la base de datos estaban dispersas en diferentes ubicaciones. Ahora se ha reorganizado la estructura del proyecto y se ha creado la carpeta **repository** para agrupar todas las interfaces de acceso a los datos, mejorando la modularidad y mantenibilidad del código.


```
project-root/
│── src/
│   ├── main/
│   │   ├── java/com/cvds/eci/laboratoryreservations/
│   │   │   ├── app_core/
│   │   │   │   ├── model/             # Modelos de datos
│   │   │   │   ├── repository/        # Repositorios de base de datos
│   │   │   │   │   ├── BookingRepository.java
│   │   │   │   │   ├── LaboratoryRepository.java
│   │   │   │   │   ├── UserRepository.java
│   │   │   │   ├── service/           # Servicios de la aplicación
│   │   │   │   ├── controller/        # Controladores REST
│   │   │   │   ├── config/            # Configuración de Spring Boot
│── pom.xml                            # Configuración de Maven
│── application.properties              # Configuración de la aplicación
```


# JACOCO Y DOCKER

mvn verify sonar:sonar -D sonar.token=squ_6a17525a64e3b3e95e521e550478e75cd4710aff

![image](https://github.com/user-attachments/assets/5a3bdbea-8565-435e-8632-8ffae190deb3)




---
📌 **Desarrollado por: Salomon Baena , Camilo Quintero, Ivan Forero, Sebastian Beltrán**

