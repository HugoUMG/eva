package com.proyectoinvdebienes.mobile.ui

data class FieldSpec(
    val label: String,
    val hint: String
)

data class SectionSpec(
    val title: String,
    val fields: List<FieldSpec>
)

object ModuleFormTemplate {
    fun sectionsFor(moduleKey: String): List<SectionSpec> = when (moduleKey) {
        "adquisiciones" -> listOf(
            SectionSpec("Datos del activo", listOf(
                FieldSpec("Código patrimonial", "Ej: ACT-2026-0001"),
                FieldSpec("Nombre del activo", "Laptop Dell Latitude"),
                FieldSpec("Descripción", "Descripción técnica del activo"),
                FieldSpec("Valor de compra", "0.00")
            )),
            SectionSpec("Compra", listOf(
                FieldSpec("Número de factura", "F-001-2026"),
                FieldSpec("Fecha de compra", "YYYY-MM-DD"),
                FieldSpec("Proveedor", "Nombre del proveedor"),
                FieldSpec("Partida presupuestaria", "Código de partida")
            ))
        )
        "inventario" -> listOf(
            SectionSpec("Filtro de inventario", listOf(
                FieldSpec("Estado del activo", "ACTIVO / ASIGNADO / BAJA"),
                FieldSpec("Tipo de etiqueta", "QR / RFID"),
                FieldSpec("Departamento", "Nombre del departamento")
            ))
        )
        "asignaciones" -> listOf(
            SectionSpec("Crear asignación", listOf(
                FieldSpec("ID activo", "ID numérico del activo"),
                FieldSpec("ID empleado", "ID del empleado receptor"),
                FieldSpec("Fecha de asignación", "YYYY-MM-DD"),
                FieldSpec("Observaciones", "Notas de entrega")
            )),
            SectionSpec("Confirmación", listOf(
                FieldSpec("ID asignación", "ID a confirmar"),
                FieldSpec("Estado", "CONFIRMADA / PENDIENTE")
            ))
        )
        "bajas" -> listOf(
            SectionSpec("Solicitud de baja", listOf(
                FieldSpec("ID activo", "ID del activo"),
                FieldSpec("Motivo", "Deterioro, robo, obsolescencia..."),
                FieldSpec("Fecha solicitud", "YYYY-MM-DD")
            )),
            SectionSpec("Aprobación", listOf(
                FieldSpec("ID solicitud", "ID de baja"),
                FieldSpec("Decisión", "APROBAR / RECHAZAR"),
                FieldSpec("Comentario", "Justificación")
            ))
        )
        "reportes" -> listOf(
            SectionSpec("Consultas", listOf(
                FieldSpec("ID empleado", "Resumen de inversión por empleado"),
                FieldSpec("ID departamento", "Filtro opcional"),
                FieldSpec("Rango de fechas", "YYYY-MM-DD a YYYY-MM-DD")
            ))
        )
        "catalogos" -> listOf(
            SectionSpec("Proveedor", listOf(
                FieldSpec("Nombre proveedor", "Razón social"),
                FieldSpec("NIT", "Número tributario"),
                FieldSpec("Contacto", "Teléfono/correo")
            )),
            SectionSpec("Partida presupuestaria", listOf(
                FieldSpec("Código", "Código único"),
                FieldSpec("Nombre", "Nombre de partida"),
                FieldSpec("Monto", "0.00")
            ))
        )
        "admin-empleados" -> listOf(
            SectionSpec("Usuario", listOf(
                FieldSpec("Username", "usuario"),
                FieldSpec("Password", "contraseña"),
                FieldSpec("Rol", "ADMINISTRADOR/COMPRAS/INVENTARIO/EMPLEADO/FINANZAS")
            )),
            SectionSpec("Empleado", listOf(
                FieldSpec("Código empleado", "EMP-001"),
                FieldSpec("Nombre completo", "Nombre y apellido"),
                FieldSpec("Departamento", "Nombre departamento")
            ))
        )
        "empleado" -> listOf(
            SectionSpec("Consulta personal", listOf(
                FieldSpec("ID empleado", "Tu ID interno"),
                FieldSpec("Estado de asignaciones", "Activas / históricas")
            ))
        )
        else -> emptyList()
    }
}
