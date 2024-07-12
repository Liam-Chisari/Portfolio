import pandas as pd

pd.set_option('display.max_rows', None)
pd.set_option('display.max_columns', None)

# Read the CSV file into a DataFrame
df = pd.read_csv('/home/liam/Downloads/dataAnnotation/Achilles_Data_Vis/top_200_youtubers.csv')

# Display the first 5 rows
print(df.head().to_string(index=False))

# Print the column names and their data types
print(df.info())
                

# Define a list of Asian countries
asian_countries = ['IN', 'JP', 'KR', 'VN', 'CN', 'ID', 'TH', 'MY', 'SG', 'PH']

# Filter the data to keep only the rows where the `Country` column's value is one of 'US' or any of the Asian countries, drop rows where the country value is null.
df_filtered = df[df['Country'].isin(asian_countries + ['US'])].dropna(subset=['Country'])

# Group by `Country` and calculate the mean of `Engagement Rate`, `Engagement Rate 60days` and `Views` columns
avg_data = df_filtered.groupby('Country')[['Engagement Rate', 'Engagement Rate 60days', 'Views']].mean()

# Group by `Country` and count the number of rows
youtuber_count = df_filtered.groupby('Country').size().reset_index(name='Youtuber Count')

# Combine these dataframes, keeping all rows and columns
combined_data = pd.merge(avg_data, youtuber_count, on='Country', how='outer')

# Round the float values to two decimal places
combined_data = combined_data.round(2)

# Add a column `Region` with value 'Asia' for all countries except 'US' which will be 'North America'
combined_data['Region'] = combined_data['Country'].apply(lambda x: 'Asia' if x in asian_countries else 'North America')

# Display the final dataframe
print(combined_data)

# Format the float values with thousand separators
formatted_data = combined_data.copy()
for col in ['Engagement Rate', 'Engagement Rate 60days', 'Views']:
    formatted_data[col] = formatted_data[col].apply(lambda x: f'{x:,.2f}')

#Print the formatted data
print(formatted_data)

# Iterate over the unique regions
for region in formatted_data['Region'].unique():
    # Filter the data for the current region
    region_data = formatted_data[formatted_data['Region'] == region]

    # Print the region name
    print(region)

    # Print the filtered data
    print(region_data.to_string(index=False))

    # Print an empty line
    print('\n')
