# Hospital Regional XYZ - Caso 1

Proyecto de Programación Orientada a Objetos en Java para gestionar pacientes.

## Requisitos implementados
- Programación Orientada a Objetos.
- Abstracción y encapsulamiento.
- Clase `Paciente` con atributos privados.
- Validaciones de identificación, fecha, tipo de sangre, alergias, teléfono y correo.
- Excepción personalizada `DatoInvalidoException`.
- Manejo de excepciones con `try-catch`.
- Ingreso de datos mediante `Scanner`.
- Controlador con `agregarPaciente()` y `listarPacientes()`.
- Lista interna basada en `ArrayList<Paciente>`.
- Prevención de pacientes duplicados por número de identificación.

## Estructura
```text
src/
├── controlador/
│   └── PacienteControlador.java
├── excepciones/
│   └── DatoInvalidoException.java
├── modelo/
│   └── Paciente.java
└── principal/
    └── Main.java
```

## Compilar desde la raíz del proyecto
```bash
javac -d out src/excepciones/DatoInvalidoException.java src/modelo/Paciente.java src/controlador/PacienteControlador.java src/principal/Main.java
```

## Ejecutar
```bash
java -cp out principal.Main
```
Proyecto desarrollado para el caso práctico del Hospital Regional XYZ.
