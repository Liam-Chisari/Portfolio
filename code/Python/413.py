import pandas as pd

pd.set_option('display.max_rows', None)
pd.set_option('display.max_columns', None)

# Read the CSV files into Pandas DataFrames
df_alcohol_drug_abuse = pd.read_csv('/home/liam/Downloads/dataAnnotation/Achilles_Data_Vis/Hospital_Survey_Data_Alcohol_Drug_Abuse.csv', skiprows=1)
df_speticemia = pd.read_csv('/home/liam/Downloads/dataAnnotation/Achilles_Data_Vis/Hospital_Survey_Data_Speticemia.csv', skiprows=1)

# Display the first 5 rows of each DataFrame
print("Alcohol and Drug Abuse Data:")
print(df_alcohol_drug_abuse.head().to_markdown(index=False, numalign="left", stralign="left"))

print("\nSepticemia Data:")
print(df_speticemia.head().to_markdown(index=False, numalign="left", stralign="left"))

# Print the column names and their data types for each DataFrame
print("\nAlcohol and Drug Abuse Column Info:")
print(df_alcohol_drug_abuse.info())

print("\nSepticemia Column Info:")
print(df_speticemia.info())

# Select columns of interest and concatenate the DataFrames
columns_of_interest = ['Total Discharges', 'Average Covered Charges ($)', 'Average Total Payments ($)', 'Average Medicare Payments ($)', 'Hospital Rating', 'Provider State']
df_combined = pd.concat([df_alcohol_drug_abuse[columns_of_interest], df_speticemia[columns_of_interest]])

# Convert the columns to numeric
numeric_columns = ['Total Discharges', 'Average Covered Charges ($)', 'Average Total Payments ($)', 'Average Medicare Payments ($)']
for col in numeric_columns:
  df_combined[col] = pd.to_numeric(df_combined[col], errors='coerce')

# Group by `Provider State` and aggregate
df_grouped_sum = df_combined.groupby('Provider State')[numeric_columns].sum().reset_index()
df_grouped_mean = df_combined.groupby('Provider State')['Hospital Rating'].mean().reset_index()

# Combine the grouped dataframes
df_final = pd.merge(df_grouped_sum, df_grouped_mean, on='Provider State')

# Print the first 10 rows of the final dataframe
print(df_final.head(10).to_markdown(index=False, numalign="left", stralign="left"))
