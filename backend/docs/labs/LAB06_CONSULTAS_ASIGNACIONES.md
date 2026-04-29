# LAB06 - Consultas de inversión por empleado y bienes asignados

## Consulta A (SQL)
Conocer cuánto se tiene invertido en personal asignado, por recurso (empleado):

```sql
SELECT
    e.id AS codigo_empleado,
    e.full_name AS nombre_empleado,
    COUNT(a.id) AS cantidad_bienes_asignados,
    COALESCE(SUM(ast.acquisition_cost), 0) AS monto_total_invertido
FROM assignment a
INNER JOIN employee e ON e.id = a.employee_id
INNER JOIN asset ast ON ast.id = a.asset_id
WHERE a.status = 'ACTIVA'
GROUP BY e.id, e.full_name
ORDER BY cantidad_bienes_asignados DESC;
```

## Consulta B (SQL)
Consultar todos los bienes activos asignados a un empleado por código:

```sql
SELECT
    e.id AS codigo_empleado,
    e.full_name AS nombre_empleado,
    ast.asset_code AS codigo_bien_asignado,
    COALESCE(ast.description, ast.name) AS descripcion_bien,
    ast.acquisition_cost AS costo_bien
FROM assignment a
INNER JOIN employee e ON e.id = a.employee_id
INNER JOIN asset ast ON ast.id = a.asset_id
WHERE e.id = :codigoEmpleado
  AND a.status = 'ACTIVA'
ORDER BY ast.asset_code ASC;
```

## Endpoints backend implementados (Consulta B)
- `GET /api/reports/employee/{employeeId}/assigned-assets`
- Respuesta JSON:

```json
[
  {
    "employeeCode": 1,
    "employeeName": "Ana López",
    "assignedAssetCode": "AST-001",
    "assetDescription": "Laptop Dell Latitude 5440",
    "assetCost": 1250.00
  }
]
```

## Endpoint adicional para Consulta A
- `GET /api/reports/employee-investment`

```json
[
  {
    "employeeCode": 1,
    "employeeName": "Ana López",
    "assignedAssetsCount": 3,
    "totalInvested": 4300.00
  },
  {
    "employeeCode": 2,
    "employeeName": "Carlos Pérez",
    "assignedAssetsCount": 1,
    "totalInvested": 980.00
  }
]
```

> Nota: Los JSON anteriores son un ejemplo del formato esperado para validación en Postman.
