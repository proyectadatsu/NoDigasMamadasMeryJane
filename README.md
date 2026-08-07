# 🛠️ PRO-BUILD Ferretería / MasterTools - Sistema de Gestión de Inventario y Tienda Virtual

Aplicación web e-commerce y API RESTful desarrollada con Java y Spring Boot para la administración profesional de inventario, herramientas y productos de ferretería, con control de acceso basado en roles y un frontend moderno con Thymeleaf.

---

## 🎯 Objetivo del Proyecto

Desarrollar una solución integral para el sector ferretero que exponga una **API RESTful** para consumo JSON de aplicaciones cliente externas, combinada con un **Frontend Web administrativo/e-commerce** dinámico, modular y responsive.

---

## 🚀 Características Principales

* 🔐 **Seguridad & Roles (Spring Security):**
  * `ROLE_ADMIN` (Empleado): Acceso total para crear, editar, reabastecer stock y eliminar productos.
  * `ROLE_USER` (Cliente): Modo lectura interactivo para explorar el catálogo de herramientas.
* 📦 **Gestión de Inventario (CRUD Completo):**
  * Registro y actualización con validaciones estrictas (`@Min`, `@Max`, `@PositiveOrZero`) para evitar stock negativo o valores irrealistas.
* 🎨 **Interfaz E-Commerce Moderna:**
  * Diseño industrial vanguardista en tonos naranja (#ff6b00) y azul oscuro.
  * Selector de **Modo Oscuro / Modo Claro** persistente sin parpadeos visuales.
* 🧩 **Arquitectura Web Modular:**
  * Separación limpia de código HTML, estilos CSS (`/static/css/styles.css`) y scripts JS (`/static/js/main.js`).
  * Vistas independientes para el Catálogo (`productos-list.html`) y Formulario dedicado (`producto-form.html`) con apertura en pestañas independientes (`target="_blank"`).

---

## 📦 Modelo de Datos (`Producto`)

* `id` (Integer/Long): Identificador único autoincrementable.
* `nombre` (String): Nombre de la herramienta o insumo.
* `marca` (String): Fabricante (ej. DeWalt, Stanley, Bosch, Truper, Milwaukee).
* `categoria` (String): Categorización técnica (Herramientas Manuales, Eléctricas, Consumibles, Medición).
* `precio` (Double/BigDecimal): Precio unitario de venta.
* `stock` / `cantidadDisponible` (Integer): Control de existencias con restricciones numéricas reales.
* `descripcion` (String): Especificaciones técnicas.

---

## 🛠️ Stack Tecnológico

* **Lenguaje:** Java 17+
* **Framework Backend:** Spring Boot (Spring MVC, Spring Data JPA, Spring Security, Bean Validation)
* **Motor de Plantillas:** Thymeleaf
* **Persistencia / BD:** Relacional (SQL Server / H2)
* **Frontend:** HTML5, CSS3, JavaScript ES6
* **Control de Versiones:** Git & GitHub

---

## 🔌 Endpoints de la API REST (`/api/productos`)

| Método | Endpoint | Descripción | Acceso |
| :--- | :--- | :--- | :--- |
| `GET` | `/api/productos` | Obtener catálogo completo en JSON | Público / CLIENTE |
| `GET` | `/api/productos/{id}` | Consultar detalle de un producto por ID | Público / CLIENTE |
| `POST` | `/api/productos` | Registrar un nuevo producto | Exclusivo ADMIN |
| `PUT` | `/api/productos/{id}` | Modificar producto o actualizar stock | Exclusivo ADMIN |
| `DELETE` | `/api/productos/{id}` | Eliminar un producto del inventario | Exclusivo ADMIN |

---

## 🖥️ Navegación de la Interfaz Web (`/productos`)

* `/login`: Pantalla de inicio de sesión con validación de credenciales y diseño e-commerce.
* `/productos`: Catálogo principal con tarjetas de productos, vista en tabla y toggle de modo oscuro.
* `/productos/nuevo`: Formulario de alta en página/pestaña dedicada (Exclusivo ADMIN).
* `/productos/actualizar/{id}`: Formulario de edición de herramientas (Exclusivo ADMIN).
