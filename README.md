# Managing School Applications and Scholarships with Artificial Intelligence

---

## Descripción del proyecto

Este proyecto nació como un **sistema de gestión de becas** y ha evolucionado a una **plataforma integral de administración educativa**, permitiendo la gestión de:

- Becas y solicitudes de becas
- Tutorías
- Materias y carteleras por materia
- Pagos de servicios educativos

Adicionalmente, incluye un **modelo de Inteligencia Artificial** desarrollado en Python para la **evaluación de solicitudes de becas**, utilizando **FastAPI** para la integración con la aplicación de escritorio en Java.

El diseño de interfaces se encuentra disponible en **Figma**:  
[Figma - Solicitudes de Becas](https://www.figma.com/design/BfJg17arzK6wUWBKQ760om/Becas?node-id=0-1&p=f&t=F3Q4DWxIFlz2v1Jm-0)

---

## Arquitectura del sistema

- Aplicación de escritorio en **Java (Swing)** para la interacción del usuario.
- **Python + Scikit-Learn** para la lógica de predicción de solicitudes.
- Comunicación mediante **FastAPI** como intermediario para consultas y evaluaciones.
- Patrón de arquitectura **MVC clásico** para separar lógica, presentación y datos.

---

## Tecnologías utilizadas

| Tecnología   | Propósito                                 |
| ------------ | ----------------------------------------- |
| Java (Swing) | Interfaz de usuario de escritorio         |
| Python 3.x   | Modelos de IA supervisada                 |
| Scikit-Learn | Entrenamiento y predicción de solicitudes |
| FastAPI      | API para comunicar Java ↔ Python          |
| Figma        | Diseño de interfaces y prototipos         |

---

# Entrenamiento Supervisado del Modelo de Becas

El entrenamiento del modelo toma como base las siguientes variables, de las cuales para cada categoría se define un rango y un peso distinto para controlar el comportamiento de las predicciones.  
Estos valores se han establecido a criterio propio para otorgar mayor flexibilidad y capacidad de generalización a los resultados.

---

## Tabla 1.1 – Variables para Beca

| Variable                           | Descripción                                                | Tipado   | Rango                                                                  | Peso          |
| ---------------------------------- | ---------------------------------------------------------- | -------- | ---------------------------------------------------------------------- | ------------- |
| **Category**                       | Tipo de beca que solicita el estudiante                    | `String` | EXCELENCIA_ACADEMICA, ESCASOS_RECURSOS, CONSTANCIA, ESTUDIANTE_TRABAJO | Alto          |
| **Number of Scholarships Awarded** | Número entero de becas disponibles para dicha convocatoria | `int`    | 0 - 10,000                                                             | Distinto de 0 |

---

## Tabla 1.2 – Historial Académico

| Variable           | Descripción                      | Tipado                 | Rango                                                                                                                                                                                                                                                                                                    | Peso  |
| ------------------ | -------------------------------- | ---------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----- |
| **Student GPA**    | Promedio del estudiante          | `double (2 decimales)` | Escala 0 - 100                                                                                                                                                                                                                                                                                           | Alto  |
| **Dropout Rate**   | Porcentaje de bajas de materias  | `double (2 decimales)` | Escala 0 - 100                                                                                                                                                                                                                                                                                           | Alto  |
| **Failure Rate**   | Índice de reprobación            | `double (2 decimales)` | Escala 0 - 100                                                                                                                                                                                                                                                                                           | Alto  |
| **Semester**       | Semestre que cursa el estudiante | `int`                  | 1 - 12                                                                                                                                                                                                                                                                                                   | Medio |
| **Course Load**    | Carga académica del estudiante   | `double (2 decimales)` | Escala 0 - 100                                                                                                                                                                                                                                                                                           | Medio |
| **Degree Program** | Carrera del estudiante           | `String`               | INGENIERIA, LICENCIATURA, DOCTORADO, MAESTRIA | Bajo  |

---

## Tabla 1.3 – Información Socioeconómica

| Variable                   | Descripción                                                          | Tipado          | Rango                                                       | Peso  |
| -------------------------- | -------------------------------------------------------------------- | --------------- | ----------------------------------------------------------- | ----- |
| **Total Household Income** | Ingreso total mensual de la familia a la que pertenece el estudiante | `BigDecimal`    | 8,364.00 – 209,100.00                                       | Alto  |
| **Housing Type**           | Tipo de vivienda del estudiante                                      | `String`        | CASA_PROPIA, DEPARTAMENTO, RESIDENCIA, VIVIENDA_IMPROVISADA | Medio |
| **Employment**             | Indica si el estudiante trabaja o solo estudia                       | `boolean (1/0)` | 1 / 0                                                       | Alto  |
| **Debts**                  | Indica si el estudiante tiene o no deudas                            | `boolean (1/0)` | 1 / 0                                                       | Medio |

---

## Tabla 1.4 – Resoluciones

| Variable       | Descripción                                                                                           | Tipado   | Rango                         |
| -------------- | ----------------------------------------------------------------------------------------------------- | -------- | ----------------------------- |
| **Resolution** | Recomendación del modelo sobre si la solicitud debe ser **Aceptada**, **Rechazada** o **Devuelta**    | `String` | Aceptada, Rechazada, Devuelta |
| **Reason**     | Motivo por el cual se optó por dicha resolución (categoría definida según las reglas de elegibilidad) | `String` | Reglas de elegibilidad        |

---

## Reglas de Elegibilidad

- **Excelencia Académica:**  
  Se prioriza el historial académico del estudiante, especialmente el **GPA**, la **carga académica** y los **índices bajos de reprobación** o bajas.

- **Escasos Recursos:**  
  Se prioriza la **información socioeconómica**, principalmente el **ingreso total familiar** y el **tipo de vivienda**.

- **Constancia:**  
  Destinada a estudiantes con desempeño académico medio pero **sin bajas, reprobaciones ni deudas**, manteniendo constancia en sus estudios.

- **Estudiante que Trabaja:**  
  Se otorga prioridad si el estudiante **trabaja actualmente**, ponderando además su carga académica y desempeño.

- **Historial Académico (ponderaciones adicionales):**  
  Mayor prioridad a estudiantes de **semestres iniciales** y con **mayor carga académica**.  
  El programa de estudios influye mínimamente.

- **Información Socioeconómica:**  
  Se consideran variables clave el **ingreso familiar mensual** y el **tipo de vivienda** como principales indicadores de necesidad.

- **Disponibilidad de Becas:**  
  Si el número de becas disponibles es 0, el modelo puede determinar si el estudiante cumple con los requisitos, pero debe marcar la resolución como **sin disponibilidad** o **rechazada**.

- **Motivos de Resolución:**  
  Los motivos (`Reason`) están predefinidos por categorías específicas.  
  El modelo no genera interpretaciones de lenguaje natural (NLG), sino que clasifica la causa de la resolución dentro de una categoría explicativa.
