# Android App - Login contra backend

Estructura Android completa para iniciar sesión contra el backend del sistema con `Basic Auth` y consumir endpoints protegidos.

## Estructura

- `settings.gradle.kts`, `build.gradle.kts`, `gradle.properties` (raíz Android).
- `app/build.gradle.kts` (módulo app).
- `api/` (`ApiClient`, `AuthApi`, `ReportsApi`).
- `model/` (`AuthUser`, `LoginRequest`, `EmployeeAssignedAssetDto`).
- `session/` (`SessionManager`).
- `repository/` (`AuthRepository`).
- `ui/login/` (`LoginViewModel`, `LoginViewModelFactory`).
- `ui/MainActivity` (pantalla demo con login + endpoint protegido).

## Base URL para dispositivo real

La app está configurada para probar desde un teléfono físico con:

- `http://192.168.1.4:8080/`

Archivo: `app/src/main/java/com/proyectoinvdebienes/mobile/api/ApiClient.kt`

## Flujo de login

1. Usuario ingresa usuario y contraseña.
2. `SessionManager` guarda credenciales localmente.
3. Interceptor OkHttp agrega header `Authorization: Basic ...`.
4. `AuthRepository` llama `GET /api/auth/me`.
5. Si backend responde OK, se habilita la sección protegida.

## Ejecutar en Android Studio

1. Abrir carpeta `android-app` como proyecto.
2. Esperar `Gradle Sync`.
3. Verificar JDK 17 en `Settings > Build, Execution, Deployment > Build Tools > Gradle`.
4. Encender un dispositivo físico con `Depuración USB` o emulador.
5. Seleccionar configuración `app` y presionar `Run`.

## Requisitos backend

- Backend corriendo en tu PC puerto `8080`.
- Celular y PC en la misma red Wi-Fi.
- Firewall permitiendo el puerto `8080`.
