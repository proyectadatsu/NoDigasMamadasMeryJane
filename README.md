# 🛠️ Sistema de Gestión de Ferretería - Prueba Técnica JR

Aplicación web y API REST desarrollada con **Spring Boot**, **Spring Data JPA** y **Thymeleaf** para la administración de inventario y herramientas de una ferretería.

---

## 🎯 Objetivo
Desarrollar un sistema de gestión integral que exponga una **API RESTful** para el manejo de inventario, preparado para ser consumido por clientes externos o frontends independientes, e integrando una **interfaz gráfica web dinámica** mediante Thymeleaf para la interacción directa del usuario.

---

## 📦 Modelo de Datos (`Herramienta`)
Cada herramienta o producto dentro del sistema cuenta con las siguientes propiedades:
* **`id`**: Identificador único (Primary Key).
* **`nombre`**: Nombre de la herramienta (ej. *Martillo de galpón*, *Taladro percutor*).
* **`marca`**: Marca del fabricante (ej. *Stanley*, *DeWalt*).
* **`categoria`**: Categoría del producto (ej. *Manual*, *Eléctrica*, *Accesorios*).
* **`precio`**: Precio unitario de venta.
* **`cantidadStock`**: Cantidad de unidades disponibles en inventario.
* **`descripcion`**: Detalle o especificaciones técnicas de la herramienta.

---

## 🛠️ Tecnologías Utilizadas
* **Lenguaje:** Java 17+
* **Framework Backend:** Spring Boot
* **Persistencia & ORM:** Spring Data JPA / Hibernate
* **Base de Datos:** Base de datos relacional (MySQL / H2)
* **Motor de Plantillas (Frontend):** Thymeleaf + HTML5 / CSS3
* **Arquitectura:** Modelo en capas (`Controller`, `Service`, `Repository`, `Entity`)

---

## 🚀 Funcionalidades y Endpoints API REST

### 🛠️ Gestión de Herramientas / Productos (`/api/herramientas`)

| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| `GET` | `/api/herramientas` | Obtener la lista completa de herramientas registradas. |
| `GET` | `/api/herramientas/{id}` | Consultar los detalles de una herramienta específica por su ID. |
| `POST` | `/api/herramientas` | Registrar una nueva herramienta en el sistema. |
| `PUT` | `/api/herramientas/{id}` | Modificar los datos o stock de una herramienta existente. |
| `DELETE` | `/api/herramientas/{id}` | Eliminar una herramienta del sistema. |

---

## 🖥️ Interfaz Gráfica Web (Thymeleaf)
El proyecto incluye un frontend integrado que permite realizar las operaciones del CRUD mediante una interfaz limpia e intuitiva:

* 📋 **Vista Principal:** Listado general de herramientas con stock y precios en tiempo real.
* ➕ **Formulario de Registro:** Alta de nuevos productos.
* ✏️ **Edición:** Modificación rápida de datos y actualización de inventario.
* 🗑️ **Eliminación:** Gestión para dar de baja productos.
