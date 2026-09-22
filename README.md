# Java AST Parser & Code Analysis with ANTLR 4 🔬

[![Java](https://img.shields.io/badge/Java-11%20%2F%208-ED8B00?logo=openjdk&logoColor=white)](https://openjdk.org/)
[![ANTLR](https://img.shields.io/badge/ANTLR-v4-red?logo=target&logoColor=white)](https://www.antlr.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-Framework-6DB33F?logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)

Herramienta de análisis sintáctico y extracción de **AST (Abstract Syntax Tree)** para código fuente Java, desarrollada con **ANTLR v4** y **Spring Boot**. 

Este proyecto formó parte de la línea de investigación y desarrollo de software orientada al **Desarrollo Dirigido por Modelos (MDD)** y la automatización de generación de código en arquitecturas modernas.

---

## 🎯 Objetivo y Capacidades

El analizador procesa archivos y estructuras de código fuente Java para descomponer su sintaxis, interpretar relaciones de herencia e interfaces, y extraer metadata estructural hacia un modelo de objetos interno (*Parse Units*):

* **Análisis Léxico y Sintáctico:** Gramáticas formales de ANTLR 4 (`JavaLexer.g4` y `JavaParser.g4`).
* **Recorrido del Árbol Sintáctico (AST):** Implementación de listeners personalizados (`JavaParserBaseListener`, `JavaListener`) para capturar eventos de análisis de forma reactiva.
* **Extracción Estructural Completa:**
  - Clases, Interfaces y Enums (`ClassDefinition`, `InterfaceDefinition`, `EnumDefinition`).
  - Jerarquías de herencia, implementación y tipos genéricos (`<A, B>`, wildcards y bounds).
  - Atributos, modificadores de acceso (`public`, `protected`, `private`, `static`, `final`) y tipos de retorno.
  - Métodos, constructores y parámetros formales con sus firmas completas.
  - Reconocimiento de anotaciones (incluyendo metadata de JPA/Hibernate como `@Entity`, `@ManyToOne`, `@ManyToMany`, `@JoinColumn`, etc.).

---

## 🏗️ Arquitectura de Componentes

```
com.antrl
├── CodeStripperApplication.java       # Punto de entrada Spring Boot
├── recognizer.java
│   ├── grammars/
│   │   ├── JavaLexer.g4              # Gramática léxica de Java
│   │   └── JavaParser.g4             # Gramática sintáctica de reglas de producción
│   ├── listener/
│   │   └── JavaListener.java         # Listener principal para inspección de nodos AST
│   └── parseUnit/                    # Modelo de representación estructural interna
│       ├── ClassDefinition.java      # Definición de clases capturadas
│       ├── InterfaceDefinition.java  # Definición de interfaces
│       ├── EnumDefinition.java       # Definición de enumeraciones
│       └── member/                   # Componentes atómicos (Method, Attribute, Annotation, etc.)
```

---

## 🚀 Flujo de Ejecución

1. **Tokenización:** El código fuente Java es analizado por el `JavaLexer`, generando un flujo de tokens.
2. **Generación del AST:** El `JavaParser` construye el árbol de análisis sintáctico según las reglas de la gramática.
3. **Paseo y Extracción (Listener Pattern):** A medida que el parser entra y sale de los contextos (declaración de clases, métodos, campos, anotaciones), el `JavaListener` recolecta la metadata y construye las instancias correspondientes en `com.antrl.recognizer.java.parseUnit`.
4. **Disponibilidad para MDD:** Las estructuras resultantes quedan preparadas para ser transformadas mediante motores de plantillas (como StringTemplate ST4) o exportadas hacia formatos estándar como XMI.

---

## 🛠️ Tecnologías Utilizadas

- **Java** (Lenguaje core)
- **ANTLR v4** (Parser Generator & Tree Traversal)
- **Spring Boot & Maven** (Estructura de proyecto y gestión de dependencias)
- **Lombok** (Productividad y boilerplate reduction)
- **JUnit** (Casos de prueba para validación de estructuras complejas y anotaciones JPA)
