import pandas as pd

# Load the data from the CSV file
df = pd.read_csv('./food_data.csv')

# User's data
calories_already_ate = 1500  # replace with actual value
calories_needed = 2000  # replace with actual value
available_time = 30  # replace with actual value in minutes

# Calculate remaining calories needed
remaining_calories = calories_needed - calories_already_ate

# Filter the data based on the remaining calories and available time
df_filtered = df[(df['Calories'] <= remaining_calories) & (df['Preparation Time (minutes)'] <= available_time)]

# If there are no foods that meet the criteria, print a message
if df_filtered.empty:
    print("No foods found that meet your criteria.")
else:
    # Sort the filtered data by Calories and Preparation Time
    df_sorted = df_filtered.sort_values(by=['Calories', 'Preparation Time (minutes)'])

    # Recommend the top 5 foods with the least amount of calories and preparation time
    recommended_foods = df_sorted.iloc[:5]

    for i, food in recommended_foods.iterrows():
        print(f"Recommended food {i+1}: {food['Food Name']}")
        print(f"Calories: {food['Calories']}")
        print(f"Preparation Time: {food['Preparation Time (minutes)']} minutes")
        print()
