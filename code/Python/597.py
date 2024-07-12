
import pandas as pd

# Read the CSV files into Pandas Dataframes
df_alcohol_drug_abuse = pd.read_csv('/home/liam/Downloads/dataAnnotation/Achilles_Data_Vis/Hospital_Survey_Data_Alcohol_Drug_Abuse.csv', skiprows=1)
df_speticemia = pd.read_csv('/home/liam/Downloads/dataAnnotation/Achilles_Data_Vis/Hospital_Survey_Data_Speticemia.csv', skiprows=1)

# Display the first 5 rows of each DataFrame
print(df_alcohol_drug_abuse.head().to_string(index=False))
print("\n")
print(df_speticemia.head().to_string(index=False))

# Get information about the columns in each of the DataFrames, including their data types
print("\n")
print(df_alcohol_drug_abuse.info())
print("\n")
print(df_speticemia.info())

# Filter data for California providers and patients who left AMA
filtered_df = df_alcohol_drug_abuse[
    (df_alcohol_drug_abuse['DRG Definition'].str.contains('LEFT AMA'))
    & (df_alcohol_drug_abuse['Provider State'] == 'CA')
]

if filtered_df.empty:
    print('There are no providers in California with instances of patients leaving AMA in this dataset.')
else:
    # Calculate the average hospital rating
    average_rating = filtered_df['Hospital Rating'].mean()

    # Round the average rating to two decimal places
    average_rating_rounded = round(average_rating, 2)

    # Print the average rating
    print(f'The average rating for providers in California for those who left AMA is: {average_rating_rounded}')


