# Ideas de funcionalidades para `checker-cli` (priorizadas)

He pensado en funcionalidades útiles para usuarios que usen el módulo `checker-core`. Para cada idea incluyo: descripción, ejemplo de comando, formato de salida y notas sobre cambios en `checker-core`.

---

## 🔴 Validar objetos desde JSON/CSV (alta prioridad)

**Descripción**  
Permitir al CLI recibir un JSON (o CSV) que represente un objeto y aplicar uno o varios *Checker* del core para validar reglas (ej. campos obligatorios, formatos, rangos).

**Comando**
checker-cli validate --checker PersonChecker --input person.json --format json

**Salida**
```json
{
  "ok": true,
  "results": [
    { "rule": "nameNotEmpty", "ok": true }
  ]
}
```

**Cambios en core**
- Exponer interfaces para ejecutar un Checker sobre una instancia
- Utilidades de serialización (POJO ↔ JSON)

**Criterios de aceptación**
- Exit code `0` si todo OK
- Exit code `2` si hay fallos
- Salida human-friendly + opcional JSON (`--output-format json`)

---

## 🔴 Generador de tests/unitarios (alta)

**Descripción**  
Generar clases de test (JUnit) que invoquen checkers con casos de ejemplo (positivos/negativos).

**Comando**
checker-cli generate-tests --checker PersonChecker --out src/test/java/...

**Salida**
- Archivo Java con plantilla de tests.

**Cambios en core**
- Métodos de ejemplo o metadatos sobre checkers (reglas, mensajes).

**Criterios**
- Genera skeleton compilable con imports y asserts básicos.

---

## 🔴 Inspección y documentación de Checkers (docgen) (alta)

**Descripción**  
Listar checkers disponibles, sus reglas, mensajes y ejemplos en formato Markdown/HTML.

**Comando**
checker-cli doc --checker PersonChecker --format md --out docs/

**Salida**
- Markdown con sección por regla, severidad y mensaje.

**Cambios en core**
- Añadir anotaciones o metadata para reglas.

**Criterios**
- Salida legible con índice de checkers.

---

## 🟡 Ejecutar checkers sobre proyectos (scan) (media)

**Descripción**  
Escanear un directorio/proyecto y aplicar reglas.

**Comando**
checker-cli scan --path src/main/resources --checker All --report report.json

**Salida**
- Reporte JSON/HTML + resumen en consola.

**Cambios en core**
- Ejecución en lote y manejo de múltiples paths.

**Criterios**
- Soporte múltiples ficheros y paralelismo opcional.

---

## 🟡 Modo interactivo / REPL (media)

**Descripción**  
Prompt interactivo para probar checkers.

**Comando**
checker-cli repl

**Salida**
- Prompt con comandos básicos.

---

## 🟡 Conversión/Plantillas de POJOs (scaffold) (media)

**Descripción**  
Generar POJOs desde schema o checker.

**Comando**
checker-cli scaffold --from-schema schema.json --package com.example

**Salida**
- Archivos .java generados.

---

## 🔵 Integración CI / SARIF (baja)

**Descripción**  
Generar reportes SARIF.

**Comando**
checker-cli validate ... --report-sarif report.sarif

---

## 🔵 Métricas / cobertura de reglas (baja)

**Descripción**  
Medir uso de reglas.

**Salida**
- CSV / JSON.

---

## ⚪ i18n (opcional)

**Descripción**  
Exportar/importar mensajes.

**Comando**
checker-cli i18n export --out messages.csv

---

## ⚪ Dry-run / sugerencias (experimental)

**Descripción**  
Proponer fixes automáticos.

**Salida**
{
  "rule": "emailFormat",
  "ok": false,
  "suggestedFix": "user@example.com"
}

---

# Ejemplos de comandos

## Listar checkers
checker-cli list

Output:
PersonChecker - com.example.checkers.PersonChecker - 12 reglas

---

## Validar con salida JSON
checker-cli validate --checker PersonChecker --input person.json --format json --output report.json

report.json:
{
  "summary": { "total": 12, "failed": 2 },
  "results": []
}

---

## Generar tests
checker-cli generate-tests --checker PersonChecker --out tests/generated/

---

# 🚀 Roadmap corto

Prioridad inmediata:
1. Validate desde JSON/CSV
2. List / inspect checkers
3. Generate test skeleton

Recomendación:
Empezar por list + validate desde JSON

---

# 🛠️ Tareas técnicas

checker-core:
- CheckerRunner.run(Object) -> Result
- JSON → POJO (Jackson)

checker-cli:
- list
- validate

Dependencias:
- Jackson
- Picocli

Tests:
- Happy path
- Failing case

---

# ⚠️ Edge cases

- JSON inválido
- Checker no encontrado
- Checkers con estado
- Grandes colecciones

---

# ▶️ Siguiente paso

Implementar:
- checker-cli: list, validate
- checker-core: CheckerRunner