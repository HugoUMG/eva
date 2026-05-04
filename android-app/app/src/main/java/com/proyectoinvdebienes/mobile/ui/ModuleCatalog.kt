package com.proyectoinvdebienes.mobile.ui

object ModuleCatalog {

    private val allModules = listOf(
        ModuleCard("adquisiciones", "Adquisiciones", "Compras y altas", "Registro de compras, facturas, proveedores y partidas presupuestarias."),
        ModuleCard("inventario", "Inventario", "Control de bienes", "Listado de activos, estatus, etiquetas y ubicación por departamento."),
        ModuleCard("asignaciones", "Asignaciones", "Entrega y recepción", "Asignación de activos a empleados y confirmación de entregas."),
        ModuleCard("bajas", "Bajas", "Desincorporación", "Gestión de solicitudes de baja y su aprobación/rechazo."),
        ModuleCard("reportes", "Reportes", "Indicadores", "Consultas de inversión por empleado, activos asignados y métricas clave."),
        ModuleCard("catalogos", "Catálogos", "Datos maestros", "Mantenimiento de proveedores y partidas presupuestarias."),
        ModuleCard("admin-empleados", "Empleados", "Administración", "Creación y actualización de usuarios/empleados del sistema."),
        ModuleCard("empleado", "Mis Activos", "Portal de empleado", "Vista personal de activos recibidos y estado de asignaciones.")
    )

    fun modulesForRole(role: UserRole): List<ModuleCard> = when (role) {
        UserRole.ADMINISTRADOR -> allModules
        UserRole.COMPRAS -> allModules.filter { it.key == "adquisiciones" }
        UserRole.INVENTARIO -> allModules.filter { it.key in setOf("inventario", "asignaciones", "bajas", "reportes", "catalogos") }
        UserRole.FINANZAS -> allModules.filter { it.key == "reportes" }
        UserRole.EMPLEADO -> allModules.filter { it.key == "empleado" }
    }
}
