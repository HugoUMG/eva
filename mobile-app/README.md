# Mobile App (Cascarón UI) - Paridad visual con frontend

Se ajustó el cascarón para que la experiencia sea lo más cercana posible al frontend web actual:

- Tarjetas con bordes suaves, gradiente de fondo, inputs, botones y spacing similares.
- Login con estilo de card centrada.
- Menú por rol y módulos.
- Formularios por módulo con secciones y campos equivalentes a los componentes web.

## Módulos replicados

1. **Adquisiciones**: factura, total, proveedor, partida, notas.
2. **Inventario**: alta de activo (nombre, serie, fecha, costo, etiqueta, ubicación, factura).
3. **Asignaciones**: alta de asignación, devolución e historial.
4. **Bajas**: solicitud, aprobación/rechazo.
5. **Reportes**: filtros por empleado/departamento/fechas + exportaciones.
6. **Catálogos**: proveedores y partidas presupuestarias.
7. **Administración de empleados**.
8. **Portal empleado / mis activos**.

## Estado actual

- UI 100% cascarón: navegación y captura local.
- Acciones "Guardar borrador" y "Enviar" simuladas (sin backend todavía).

## Ejecutar

1. Abrir `mobile-app` en Android Studio.
2. Sincronizar Gradle.
3. Ejecutar en dispositivo o emulador.
4. Iniciar sesión y navegar por todos los módulos.
