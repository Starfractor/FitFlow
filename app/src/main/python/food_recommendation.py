import pandas as pd
from os.path import dirname, join
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import linear_kernel

def recommend_food(query, max_calories):
    csv_file_path = join(dirname(__file__), "food_data.csv")

    # Read the CSV file
    data = pd.read_csv(csv_file_path)

    # Handling missing values
    data.fillna('', inplace=True)

    # Step 2: Feature Extraction
    # Combine description and food type to create input for TF-IDF
    data['Features'] = data['Name'] + ' ' + data['Food_Type'] + ' ' + data['Describe']

    # Convert text data (name + food type + description) into numerical features using TF-IDF
    tfidf_vectorizer = TfidfVectorizer(stop_words='english')
    tfidf_matrix = tfidf_vectorizer.fit_transform(data['Features'])

    # Step 3: Similarity Calculation
    # Compute cosine similarity matrix
    cosine_sim = linear_kernel(tfidf_matrix, tfidf_matrix)

    # Calculate the similarity scores for each food item based on name, food type, and description
    scores = {}
    for index, row in data.iterrows():
        score = 0
        if query.lower() in row['Name'].lower():
            score += 1.5
        if query.lower() in row['Food_Type'].lower():
            score += 1
        if query.lower() in row['Describe'].lower():
            score += 1
        scores[index] = score
    
    # Sort the food items based on the calculated scores
    sorted_scores = sorted(scores.items(), key=lambda x: x[1], reverse=True)
    
    # Get the top 10 food items within the calorie limit
    recommended_foods = []
    for index, score in sorted_scores:
        if data['Calories'].iloc[index] <= max_calories:
            recommended_foods.append(data['Name'].iloc[index])
            if len(recommended_foods) == 10:
                break

    return recommended_foods
