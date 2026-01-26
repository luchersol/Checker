Ideas de funcionalidades para checker-cli (priorizadas)
He pensado en funcionalidades útiles para usuarios que usen el módulo checker-core. Para cada idea incluyo: descripción, ejemplo de comando, formato de salida y notas sobre cambios en checker-core.

Validar objetos desde JSON/CSV (alta prioridad)
Descripción: permitir al CLI recibir un JSON (o CSV) que represente un objeto y aplicar uno o varios "Checker" del core para validar reglas (ej. campos obligatorios, formatos, rangos).
Comando: checker-cli validate --checker PersonChecker --input person.json --format json
Salida: JSON con resultados por regla { "ok": true, "results": [{ "rule":"nameNotEmpty","ok":true,...}] }
Cambios en core: exponer interfaces para ejecutar un Checker sobre una instancia, y utilidades de serialización/POJO<->JSON (si no existen).
Criterios aceptacion: devuelve exit code 0 si todo ok; 2 si hay fallos; imprime resumen human-friendly y opcionalmente JSON con detalle (--output-format json).
Generador de tests/unitarios a partir de plantillas de Checker (alta)
Descripción: generar clases de test (JUnit) que invoquen checkers con casos de ejemplo (positivos/negativos).
Comando: checker-cli generate-tests --checker PersonChecker --out src/test/java/...
Salida: archivo Java con plantilla de tests.
Cambios en core: quizá incluir métodos de ejemplo o metadatos sobre checkers (reglas, mensajes).
Criterios: genera compilable skeleton; incluye imports y asserts básicos.
Inspección y documentación de Checkers (docgen) (alta)
Descripción: listar checkers disponibles, sus reglas, mensajes y ejemplos en formato Markdown/HTML.
Comando: checker-cli doc --checker PersonChecker --format md --out docs/
Salida: Markdown con sección por regla, severidad, mensaje.
Cambios en core: añadir anotaciones o metadata en constructors/métodos para exponer nombres de reglas y descripciones (si no existen).
Criterios: salida legible; incluye índice de checkers.
Ejecutar checkers sobre proyectos (scan) (media)
Descripción: escanear un directorio/proyecto (p. ej. recursos JSON, clases .class o fuentes) y aplicar reglas; útil para CI.
Comando: checker-cli scan --path src/main/resources --checker All --report report.json
Salida: reporte agregable (JSON/HTML) y resumen en consola.
Cambios en core: facilitar ejecución en lote y manejar paths de entrada variados.
Criterios: soporta múltiples ficheros y paralelismo opcional (--parallel).
Modo interactivo / REPL (media)
Descripción: abrir un prompt interactivo donde probar checkers en objetos ad-hoc; autocompletado de nombres de checker.
Comando: checker-cli repl
Salida: prompt con comandos validate, show, list.
Cambios en core: no necesarios. Añadir dependencia al CLI (JLine).
Criterios: permite validar pequeñas muestras sin ficheros.
Conversión/Plantillas de POJOs (scaffold) (media)
Descripción: generar POJOs con anotaciones y checkers base desde un esquema (JSON Schema) o desde un Checker.
Comando: checker-cli scaffold --from-schema schema.json --package com.example
Salida: archivos .java en src/
Cambios core: exponer mappings y plantillas.
Criterios: genera compilable y documentado.
Integración CI / salida compatible SARIF (baja)
Descripción: generar reportes en formatos estandarizados (SARIF) para integrarse con GitHub code scanning.
Comando: checker-cli validate ... --report-sarif report.sarif
Cambios: mapear resultados a SARIF.
Criterios: SARIF válido; incluye region/file si disponible.
CLI para medir cobertura de reglas / metrics (baja)
Descripción: analizar cuántas reglas se ejercitan con un corpus de ejemplos; reportar métricas.
Uso: útil para mejorar reglas.
Cambios core: quizá registrar métricas por regla.
Criterios: output CSV/JSON con counts.
Migrador de mensajes/plantillas i18n (opcional)
Descripción: exportar/importar los mensajes (messages.properties) para traducción.
Comando: checker-cli i18n export --out messages.csv
Cambios core: usar resource bundles estándar.
Criterios: roundtrip sin pérdida.
Modo "dry-run" / "repair suggestions" (experimental)
Descripción: además de comprobar, proponer correcciones automáticas (sugerencias).
Uso: p. ej. para strings formateados o normalización de datos.
Requiere: heurísticos en core o reglas con "fixers".
Criterios: output con suggestedFix y posibilidad --apply-fixes (cautela).
Ejemplos concretos de comandos y formatos
Listar checkers:
checker-cli list
Output compact: "PersonChecker - com.example.checkers.PersonChecker - 12 reglas"
Validar y salida JSON:
checker-cli validate --checker PersonChecker --input person.json --format json --output report.json
report.json: { "summary":{ "total":12,"failed":2 }, "results":[...]}
Generar tests:
checker-cli generate-tests --checker PersonChecker --out tests/generated/
Roadmap corto y primer feature recomendado
Prioridad inmediata (implementables rápido): 1) Validate from JSON/CSV; 2) List/inspect checkers (docgen minimal); 3) Generate test skeleton.
Recomendación: empezar por "list & validate desde JSON" — es muy útil, pequeño alcance y no rompe API pública.
Tareas técnicas para el primer feature (validate + list):
Añadir en checker-core (si falta) una interfaz pública: CheckerRunner.run(Object) -> Result (serializable). También utilidades de deserialización JSON -> POJO (usar Jackson).
En checker-cli: añadir comandos:
list : descubre checkers en classpath (usando ServiceLoader o scanning de paquete).
validate : acepta --checker, --input, --format, --output-format, --fail-on-error.
Añadir dependencias en pom.xml: Jackson (com.fasterxml.jackson.core:jackson-databind) y quizá picocli (si no hay CLI framework).
Tests mínimos: unit test para list y validate happy path + one failing case.
Posibles edge-cases a cubrir
Input inválido / JSON mal formado -> exit code + mensaje claro.
Checker no encontrado -> lista de opciones y exit code.
Reglas con efectos laterales o estado -> ejecutar en instancias nuevas (stateless) o documentar requisito stateful.
Grandes colecciones -> soportar streaming o límites.
¿Puedo implementar el primer feature?
Puedo:

Añadir un comando list y validate al módulo checker-cli.
Modificar checker-core si falta la interfaz de ejecución (haré supuestos mínimos si no veo la API exacta).
Añadir dependencias (Jackson, picocli/JCommander) y tests.
Antes de implementar necesito confirmación sobre 2 puntos (asumibles si no contestas):

¿Quieres que use Picocli (común en CLIs Java) o mantener el código simple con parsing manual de args? Asumo Picocli si no dices lo contrario.
¿Prefieres salida JSON por defecto para validate o salida human-friendly? Propongo human-friendly por defecto y soporte --output-format json.
Si confirmas, empezaré implementando:

checker-cli:
Nuevo comando list (muestra checkers detectados).
Nuevo comando validate (lee JSON y ejecuta checker).
Añadir dependencias en pom.xml.
Tests básicos.
checker-core:
Si no existe, añadir interfaz CheckerRunner o exponer método para ejecutar Checker sobre instancias. Haré cambios mínimos compatibles retroactivamente.
Dime si quieres que empiece (confirmando las suposiciones) y cuál de las ideas te gustaría priorizar si no es la recomendada.
