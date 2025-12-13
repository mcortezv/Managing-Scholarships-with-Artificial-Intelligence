import random
import csv

CATEGORIES = {
    "ESCASOS_RECURSOS": {
        "student_gpa": (60, 95),
        "total_household_income": (4000, 20000),
        "housing_type": ["VIVIENDA_IMPROVISADA", "DEPARTAMENTO"],
        "employment": [0, 1],
        "debts": [0, 1],
    },
    "EXCELENCIA_ACADEMICA": {
        "student_gpa": (90, 100),
        "total_household_income": (15000, 80000),
        "housing_type": ["RESIDENCIA", "DEPARTAMENTO", "CASA_PROPIA"],
        "employment": [0, 1],
        "debts": [0, 1],
    },
    "ESTUDIANTE_TRABAJO": {
        "student_gpa": (70, 95),
        "total_household_income": (7000, 60000),
        "housing_type": ["RESIDENCIA", "DEPARTAMENTO", "VIVIENDA_IMPROVISADA"],
        "employment": [1],
        "debts": [0, 1],
    },
    "CONSTANCIA": {
        "student_gpa": (80, 100),
        "total_household_income": (5000, 50000),
        "housing_type": ["RESIDENCIA", "DEPARTAMENTO"],
        "employment": [0, 1],
        "debts": [0, 1],
    }
}

DEGREE_PROGRAMS = ["INGENIERIA", "LICENCIATURA", "MAESTRIA", "DOCTORADO"]
RESOLUTIONS = ["Aceptada", "Rechazada"]

REASONS = {
    "Aceptada": [
        "Escasos recursos con potencial academico demostrado",
        "Excelencia academica con carga academica completa",
        "Estudiante trabajador con buen desempeño academico",
        "Constancia perfecta y buen rendimiento academico",
        "Constancia en carrera de alta desercion"
    ],
    "Rechazada": [
        "Promedio academico insuficiente",
        "Ingresos superan limite para apoyo economico",
        "Carga academica insuficiente para apoyo",
        "Tipo de vivienda no coincide con escasos recursos",
        "Vivienda en residencia estudiantil con recursos",
        "Deudas educativas previas afectan elegibilidad"
    ]
}

FIELDS = [
    "student_gpa","dropout_rate","failure_rate","semester","degree_program",
    "course_load","total_household_income","housing_type","employment",
    "debts","category","number_of_scholarships_awarded","resolution","reason"
]

def generar_fila(category):
    cfg = CATEGORIES[category]

    resolution = random.choice(RESOLUTIONS)
    reason = random.choice(REASONS[resolution])

    return {
        "student_gpa": round(random.uniform(*cfg["student_gpa"]), 2),
        "dropout_rate": round(random.uniform(0, 20), 2),
        "failure_rate": round(random.uniform(0, 50), 2),
        "semester": random.randint(1, 10),
        "degree_program": random.choice(DEGREE_PROGRAMS),
        "course_load": round(random.uniform(30, 100), 2),
        "total_household_income": round(random.uniform(*cfg["total_household_income"]), 2),
        "housing_type": random.choice(cfg["housing_type"]),
        "employment": random.choice(cfg["employment"]),
        "debts": random.choice(cfg["debts"]),
        "category": category,
        "number_of_scholarships_awarded": random.randint(70, 200),
        "resolution": resolution,
        "reason": reason
    }

def generar_csv(nombre_archivo, filas_por_categoria):
    with open(nombre_archivo, "w", newline="", encoding="utf-8") as f:
        writer = csv.DictWriter(f, fieldnames=FIELDS)
        writer.writeheader()

        for categoria, cantidad in filas_por_categoria.items():
            for _ in range(cantidad):
                writer.writerow(generar_fila(categoria))

if __name__ == "__main__":
    generar_csv(
        "datos_becas_generados.csv",
        {
            "ESCASOS_RECURSOS": 300,
            "EXCELENCIA_ACADEMICA": 200,
            "ESTUDIANTE_TRABAJO": 200,
            "CONSTANCIA": 150
        }
    )
