import pandas as pd

pd.set_option('display.max_rows', None)
pd.set_option('display.max_columns', None)

# Read the CSV file into a DataFrame
df = pd.read_csv('/home/liam/Downloads/dataAnnotation/Achilles_Data_Vis/Ventas_Julio-Octubre-wines.xlsxcsv-Julio-Octubre.csv')

# Display the first 5 rows
print(df.head().to_markdown(index=False, numalign="left", stralign="left"))

# Print the column names and their data types
print(df.info())

# Filter the data for the year 2023 and the varietal "Sauvignon Blanc"
filtered_df_2023 = df[(df['Comp. - Año / Mes Contab. (AAAA/MM)'].str.startswith('2023')) & (df['Varietal'] == 'Sauvignon Blanc')]

# Calculate the quantity in UM1
filtered_df_2023['Quantity_UM1'] = filtered_df_2023['Ítem - Cant. UM 2'] * filtered_df_2023['Artículo - Factor Conv.'] + filtered_df_2023['Ítem - Cant. UM 1']

# Calculate the total quantity in UM1
total_quantity_UM1_2023 = filtered_df_2023['Quantity_UM1'].sum()

# Print the total quantity sold in 2023
print(f'The total number of units of Sauvignon Blanc sold in 2023 is: {total_quantity_UM1_2023}')
