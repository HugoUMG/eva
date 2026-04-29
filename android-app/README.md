# Android Demo App (Kotlin) - Inventario de Bienes

Este módulo contiene una **base mínima** para crear una app Android (celular o tablet) que consume el backend de este repositorio.

## Qué incluye
- Estructura inicial Android (`android-app/app/...`).
- Cliente HTTP con **Retrofit**.
- Modelo para la consulta B (`EmployeeAssignedAssetDto`).
- Pantalla demo (`MainActivity`) para consultar bienes por empleado.
- Layout básico con campo de código de empleado y botón de consulta.

## Endpoints backend usados
- `GET /api/reports/employee/{employeeId}/assigned-assets`

## Requisitos
- Android Studio (Hedgehog o superior recomendado).
- SDK mínimo 24.
- Backend levantado y accesible desde tu teléfono.

## Configuración rápida
1. Abre la carpeta `android-app` en Android Studio.
2. En `ApiClient.kt`, cambia `BASE_URL` por la IP local de tu PC donde corre backend, por ejemplo:
   - `http://192.168.1.50:8080/`
3. Levanta backend (`backend`) en tu máquina.
4. Ejecuta la app en emulador o teléfono.

## Demo en teléfono (misma red Wi‑Fi)
1. Conecta teléfono y PC a la misma red.
2. Obtén IP de tu PC (`ipconfig` en Windows o `ip a` en Linux/macOS).
3. Asegúrate que firewall permita puerto `8080`.
4. Inicia backend en `0.0.0.0:8080` si aplica.
5. En la app, escribe un `employeeId` válido y presiona **Consultar bienes**.
6. Deberías ver JSON formateado en pantalla.

## Notas
- Si tu backend requiere autenticación, agrega interceptor/auth header en Retrofit.
- Esta base está orientada a una **demo inicial** para continuar iterando UI y seguridad.
