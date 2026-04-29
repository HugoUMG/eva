# Android App - Base para consumir backend con login local

Este módulo ahora incluye una **estructura base completa** para iniciar una app Android que consume servicios del backend con autenticación local usando **Basic Auth**.

## Estructura propuesta

- `api/`
  - `ApiClient.kt`: configuración Retrofit + OkHttp + interceptor de `Authorization`.
  - `AuthApi.kt`: endpoint `GET /api/auth/me` para validar sesión.
  - `ReportsApi.kt`: endpoint de ejemplo protegido.
- `model/`
  - `AuthUser.kt`: respuesta de perfil autenticado.
  - `LoginRequest.kt`: modelo local de credenciales.
  - `EmployeeAssignedAssetDto.kt`: DTO de reporte.
- `repository/`
  - `AuthRepository.kt`: orquestación de login (guardar credenciales y validar con `/api/auth/me`).
- `session/`
  - `SessionManager.kt`: persistencia local de credenciales en `SharedPreferences`.
- `ui/`
  - `MainActivity.kt`: pantalla de login y demo de consumo de endpoint protegido.

## Flujo principal de login local

1. Usuario ingresa `username` y `password`.
2. Se guardan localmente en `SharedPreferences`.
3. Interceptor agrega header `Authorization: Basic ...` en cada request.
4. Se llama `GET /api/auth/me` para validar credenciales.
5. Si es exitoso, se habilita sección protegida de consulta.

## Configuración rápida

1. Abrir `android-app` en Android Studio.
2. Ajustar `BASE_URL` en `ApiClient.kt`:
   - Emulador Android Studio: `http://10.0.2.2:8080/`
   - Teléfono físico (misma red): `http://<IP_LOCAL_PC>:8080/`
3. Levantar backend.
4. Ejecutar app.

## Próximos pasos recomendados

- Reemplazar `SharedPreferences` por `EncryptedSharedPreferences`.
- Migrar `MainActivity` a arquitectura por capas con `ViewModel`.
- Incorporar navegación por pantallas (`Login`, `Home`, `Módulos`).
- Manejar expiración/reintento y mensajes de error por código HTTP.
