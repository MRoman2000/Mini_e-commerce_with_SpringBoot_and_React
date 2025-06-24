# E-commerce Web App

Aplicación web de comercio electrónico desarrollada con React (frontend) y Spring Boot (backend).  
Incluye autenticación segura con JWT (Access Token y Refresh Token), gestión de productos, carrito de compra, lista de deseos y roles de usuario.

---

## Tecnologías

- **Frontend:** React 18, Axios, HTML/CSS puro, React Hooks (useState, useEffect)  
- **Backend:** Spring Boot 3, Spring Data JPA, Spring Security, JWT  
- **Base de datos:** MySQL  
- **Seguridad:** Autenticación y autorización con JWT (Access y Refresh Tokens)  
- **API:** RESTful

---

## Funcionalidades principales

- Registro e inicio de sesión de usuarios con roles (admin/cliente)  
- CRUD completo de productos (creación, lectura, actualización, eliminación)  
- Gestión de carrito de compra  
- Lista de deseos (wishlist)  
- Seguridad mediante JWT con manejo de tokens de acceso y refresh  
- Diseño responsive y usable en móvil y escritorio

---

## Instalación y ejecución

### Backend

1. Configurar base de datos MySQL y crear esquema.  
2. Ajustar `application.properties` con los datos de conexión a la base de datos.  
3. Compilar y ejecutar el backend con Maven o desde IDE:  
   ```bash
   mvn clean install
   mvn spring-boot:run
