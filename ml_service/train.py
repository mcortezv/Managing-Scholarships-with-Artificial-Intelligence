import joblib
import pandas as pd
from imblearn.over_sampling import SMOTE
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler, LabelEncoder
from core.resolution_model import resolution_model
from core.reason_model import reason_model


def train():
    scaler = StandardScaler()
    smote = SMOTE(random_state=42, k_neighbors=1)
    data = pd.read_csv('core/data/dataset_becas.csv')

    X = data[['student_gpa', 'dropout_rate', 'failure_rate', 'semester',
              'degree_program', 'course_load', 'total_household_income', 'housing_type',
              'employment', 'debts', 'category', 'number_of_scholarships_awarded']]

    le_degree = LabelEncoder()
    joblib.dump(le_degree, 'core/saved/le_degree.pkl')

    le_housing = LabelEncoder()
    joblib.dump(le_housing, 'core/saved/le_housing.pkl')

    le_category = LabelEncoder()
    joblib.dump(le_category, 'core/saved/le_category.pkl')

    X.loc[:, 'degree_program'] = le_degree.fit_transform(X['degree_program'])
    X.loc[:, 'housing_type'] = le_housing.fit_transform(X['housing_type'])
    X.loc[:, 'category'] = le_category.fit_transform(X['category'])

    Y_resolution = data['resolution']
    Y_reason = data['reason']

    X_train, X_temp, Y_train_resolution, Y_temp_resolution, Y_train_reason, Y_temp_reason = (
        train_test_split(X, Y_resolution, Y_reason, test_size=0.2, random_state=42, stratify=Y_resolution))

    X_val, X_test, Y_val_resolution, Y_test_resolution, Y_val_reason, Y_test_reason = (
        train_test_split(X_temp, Y_temp_resolution, Y_temp_reason, test_size=0.2, random_state=42, stratify=Y_temp_resolution))

    X_train_scaled = scaler.fit_transform(X_train)
    X_val_scaled = scaler.transform(X_val)
    X_test_scaled = scaler.transform(X_test)

    X_train_smote_resolution, Y_train_smote_resolution = smote.fit_resample(X_train_scaled, Y_train_resolution)

    le_reason = LabelEncoder()
    Y_train_reason = le_reason.fit_transform(Y_train_reason)
    Y_val_reason = le_reason.transform(Y_val_reason)
    Y_test_reason = le_reason.transform(Y_test_reason)
    joblib.dump(le_reason, 'core/saved/le_reason.pkl')

    resolution_model(X_train_smote_resolution, X_val_scaled, X_test_scaled, Y_train_smote_resolution, Y_val_resolution, Y_test_resolution)
    reason_model(X_train_scaled, X_val_scaled, X_test_scaled, Y_train_reason, Y_val_reason, Y_test_reason)

if __name__ == '__main__':
    train()
