import joblib
import numpy as np
import pandas as pd
from fastapi import FastAPI
from pydantic import BaseModel
from starlette.middleware.cors import CORSMiddleware


def main():

    resolution_model = joblib.load('core/saved/resolution_model.pkl')
    reason_model = joblib.load('core/saved/reason_model.pkl')

    app = FastAPI()
    app.add_middleware(
        CORSMiddleware,
        allow_origins=["*"],
        allow_credentials=True,
        allow_methods=["*"],
        allow_headers=["*"]
    )

    class Solicitud(BaseModel):
        student_gpa: float
        dropout_rate: float
        failure_rate: float
        semester: int
        degree_program: str
        course_load: float
        total_household_income: float
        housing_type: str
        employment: int
        debts: int
        category: str
        number_of_scholarships_awarded: int

    @app.post("/predecir")
    def predecir(data: Solicitud):
        input_df = pd.DataFrame([data.dict()])

        le_degree = joblib.load('core/saved/le_degree.pkl')
        le_housing = joblib.load('core/saved/le_housing.pkl')
        le_category = joblib.load('core/saved/le_category.pkl')
        le_reason = joblib.load('core/saved/le_reason.pkl')

        input_df['degree_program'] = le_degree.fit_transform(input_df['degree_program'])
        input_df['housing_type'] = le_housing.fit_transform(input_df['housing_type'])
        input_df['category'] = le_category.fit_transform(input_df['category'])
        X = input_df.values

        resolution = resolution_model.predict(X)[0]
        reason_pred = reason_model.predict(X)[0]
        reason = le_reason.inverse_transform([reason_pred])[0]

        resolution_confidence = np.max(resolution_model.predict_proba(X)[0]) * 100
        reason_confidence = np.max(reason_model.predict_proba(X)[0]) * 100
        percentage = (resolution_confidence + reason_confidence) / 2

        return {
            "resolution": str(resolution),
            "reason": str(reason),
            "resolution_confidence": float(resolution_confidence),
            "reason_confidence": float(reason_confidence),
            "percentage": float(percentage)
        }

    return app

app = main()


if __name__ == '__main__':
    import uvicorn
    print('Cargando modelos...')
    print('Cargando API config...')
    print('Corriendo en [0.0.0.0] port 8000')
    uvicorn.run("main:app", host="0.0.0.0", port=8000, reload=True)
