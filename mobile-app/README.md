# Android App (Cascarón UI) - Inventario de Bienes

Esta versión es un **cascarón visual completo** basado en la estructura del frontend web:
- Login
- Menú por rol
- Módulos visibles según tipo de usuario
- Vista de detalle de módulo

> En esta etapa **no hay conexión al backend**. Todo es UI/flujo local para validar diseño y navegación base.

## Roles y módulos (igual que web)

- `ADMINISTRADOR`: todos los módulos.
- `COMPRAS`: Adquisiciones.
- `INVENTARIO`: Inventario, Asignaciones, Bajas, Reportes, Catálogos.
- `FINANZAS`: Reportes.
- `EMPLEADO`: Mis Activos.

## Estructura implementada

- `ui/MainActivity.kt`: login + home en una sola pantalla con cambio de panel.
- `ui/UserRole.kt`: roles del sistema.
- `ui/UserSession.kt`: sesión local simulada.
- `ui/ModuleCard.kt`: modelo de tarjetas.
- `ui/ModuleCatalog.kt`: catálogo de módulos y reglas por rol.
- `ui/ModuleCardAdapter.kt`: grid de módulos.
- `res/layout/activity_main.xml`: diseño principal (login + home).
- `res/layout/item_module_card.xml`: tarjeta visual de módulo.

## Cómo usar el cascarón

1. Abrir `mobile-app` en Android Studio.
2. Ejecutar en emulador o dispositivo.
3. En login, escribir usuario/contraseña (simulados) y elegir rol.
4. Presionar **Entrar**.
5. Se muestra el menú solo con módulos permitidos para ese rol.
6. Tocar una tarjeta para ver su descripción funcional.
