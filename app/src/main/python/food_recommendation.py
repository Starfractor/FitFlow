import pandas as pd
from os.path import join, dirname
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import linear_kernel
from datetime import datetime

def recommend_food(query, max_calories, indian, chinese, italian, japanese, mexican, vegetarian, vegan, healthy_food):
    csv_file_path = join(dirname(__file__), "food_data.csv")

    # Setup the CSV file
    data = pd.read_csv(csv_file_path)
    data.fillna('', inplace=True)

    # Get current time
    current_time = datetime.now().time()

    # Filter meals based on current time
    if current_time < datetime.strptime('6:00:00', '%H:%M:%S').time() or current_time > datetime.strptime('10:00:00', '%H:%M:%S').time():
        data = data[data['Meal_Time'] != 'breakfast']

    if current_time < datetime.strptime('11:00:00', '%H:%M:%S').time() or current_time > datetime.strptime('14:00:00', '%H:%M:%S').time():
        data = data[data['Meal_Time'] != 'lunch']

    if current_time < datetime.strptime('16:00:00', '%H:%M:%S').time() or current_time > datetime.strptime('20:00:00', '%H:%M:%S').time():
        data = data[data['Meal_Time'] != 'dinner']

    # Filter non-vegetarian options if vegan or vegetarian is true
    if vegan or vegetarian:
        data = data[data['Veg_Non'].str.lower().isin(['vegetarian', 'veg'])]

    # Create numerical features using TF-IDF
    data['Features'] = data['Name'] + ' ' + data['Food_Type'] + ' ' + data['Describe']
    tfidf_vectorizer = TfidfVectorizer(stop_words='english')
    tfidf_matrix = tfidf_vectorizer.fit_transform(data['Features'])

    # Compute cosine similarity between query and food items
    query_tfidf = tfidf_vectorizer.transform([query])
    cosine_sim = linear_kernel(query_tfidf, tfidf_matrix).flatten()

    # Adjust similarity scores based on preferences
    for idx, row in data.iterrows():
        if indian and row['Food_Type'].lower() == 'indian':
            cosine_sim[idx] *= 1.2
        if chinese and row['Food_Type'].lower() == 'chinese':
            cosine_sim[idx] *= 1.2
        if italian and row['Food_Type'].lower() == 'italian':
            cosine_sim[idx] *= 1.2
        if japanese and row['Food_Type'].lower() == 'japanese':
            cosine_sim[idx] *= 1.2
        if mexican and row['Food_Type'].lower() == 'mexican':
            cosine_sim[idx] *= 1.2
        if healthy_food and row['Food_Type'].lower() == 'healthy food':
            cosine_sim[idx] *= 1.2

    # Get indices of sorted scores
    sorted_indices = sorted(range(len(cosine_sim)), key=lambda i: cosine_sim[i], reverse=True)

    # Get top 10 food items within the calorie limit
    recommended_foods = []
    for idx in sorted_indices:
        if data['Calories'].iloc[idx] <= max_calories:
            recommended_foods.append(f"{data['Name'].iloc[idx]} - {data['Calories'].iloc[idx]} cals\n")
            if len(recommended_foods) == 10:
                break

    return recommended_foods
