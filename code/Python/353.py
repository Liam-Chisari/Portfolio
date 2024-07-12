import pandas as pd

pd.set_option('display.max_rows', None)
pd.set_option('display.max_columns', None)

# Read the CSV file into a DataFrame
df_real_estate = pd.read_csv("/home/liam/Downloads/dataAnnotation/Achilles_Data_Vis/Real Estate Mumbai Database - Rgdcvvvh.csv", encoding='latin-1')

# Display the first 5 rows
print(df_real_estate.head().to_markdown(index=False, numalign="left", stralign="left"))

# Print the column names and their data types
print(df_real_estate.info())

# Convert `PROPERTY ADDRESS` to string.
df_real_estate['PROPERTY ADDRESS'] = df_real_estate['PROPERTY ADDRESS'].astype(str)

# Combine `PROPERTY STREET` and `PROPERTY ADDRESS` with a comma and space in between to create a new column called `Address`.
df_real_estate['Address'] = df_real_estate['PROPERTY STREET'] + ', ' + df_real_estate['PROPERTY ADDRESS']


# Combine `PROPERTY STREET` and `PROPERTY ADDRESS` with a comma and space in between to create a new column called `Address`.
df_real_estate['Address'] = df_real_estate['PROPERTY STREET'] + ', ' + df_real_estate['PROPERTY ADDRESS']

# Show the first 5 rows of `Address`
print(df_real_estate['Address'].head().to_markdown(index=False, numalign="left", stralign="left"))
