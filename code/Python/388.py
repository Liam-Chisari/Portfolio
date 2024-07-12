import pandas as pd

pd.set_option('display.max_rows', None)
pd.set_option('display.max_columns', None)

# Read the CSV files into Pandas DataFrames.
df_south_america = pd.read_csv('/home/liam/Downloads/dataAnnotation/Achilles_Data_Vis/population_and_age - South America.csv')
df_asia = pd.read_csv('/home/liam/Downloads/dataAnnotation/Achilles_Data_Vis/population_and_age - Asia.csv')
df_europe = pd.read_csv('/home/liam/Downloads/dataAnnotation/Achilles_Data_Vis/population_and_age - Europe.csv')

# Display the first 5 rows of each DataFrame.
print("First 5 rows of South America data:")
print(df_south_america.head().to_markdown(index=False,numalign="left", stralign="left"))

print("\nFirst 5 rows of Asia data:")
print(df_asia.head().to_markdown(index=False,numalign="left", stralign="left"))

print("\nFirst 5 rows of Europe data:")
print(df_europe.head().to_markdown(index=False,numalign="left", stralign="left"))

# Print the column names and their data types for each DataFrame.
print("\nColumn names and datatypes of South America data:")
print(df_south_america.info())

print("\nColumn names and datatypes of Asia data:")
print(df_asia.info())

print("\nColumn names and datatypes of Europe data:")
print(df_europe.info())

# Standardize column names across DataFrames.
df_south_america = df_south_america.rename(columns={'Average Age': 'Average Age (Years)'})
df_asia = df_asia.rename(columns={'Population (Approx.)': 'Population'})
df_europe = df_europe.rename(columns={'Population (Million)': 'Population'})

# Convert population to millions in df_south_america and df_asia
df_south_america['Population'] = df_south_america['Population'].replace(',', '', regex=True).astype(int) / 1000000
df_asia['Population'] = df_asia['Population'].replace(',', '', regex=True).astype(int) / 1000000

# Combine the dataframes
df_combined = pd.concat([df_south_america, df_asia, df_europe], ignore_index=True)

# Calculate the mean of 'Average Age (Years)' and 'Population'
average_age = df_combined['Average Age (Years)'].mean()
average_population = df_combined['Population'].mean()

# Print the results rounded to 2 decimal places
print("Average Age (Years):", round(average_age, 2))
print("Average Population (Millions):", round(average_population, 2))
