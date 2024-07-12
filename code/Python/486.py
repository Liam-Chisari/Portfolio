import pandas as pd

pd.set_option('display.max_rows', None)
pd.set_option('display.max_columns', None)

# Read the CSV file into a DataFrame
df = pd.read_csv('/home/liam/Downloads/dataAnnotation/Achillies_Data_Vis/Bancos-Bodega.xlsxcsv-Global.csv')

# Display the first 5 rows
print(df.head().to_markdown(index=False, numalign="left", stralign="left"))

# Print the column names and their data types
print(df.info())

# Remove '$', ' ', and ',' from the `Monto` column
df['Monto'] = df['Monto'].astype(str).str.replace(r'[$, ]', '', regex=True)

# Convert the `Monto` column to numeric
df['Monto'] = pd.to_numeric(df['Monto'])

# Calculate the average of `Monto`
average_monto = df['Monto'].mean()

# Print the average rounded to 2 decimals
print(f"The average of monto is: {average_monto:.2f}")
