import pandas as pd

pd.set_option('display.max_rows', None)
pd.set_option('display.max_columns', None)

# Read the TSV file into a DataFrame
df = pd.read_csv('/home/liam/Downloads/dataAnnotation/Achilles_Data_Vis/Sales_BAME_DATABASE - Sales.tsv', sep='\t')

# Display the first 5 rows
print(df.head().to_markdown(index=False, numalign="left", stralign="left"))

# Print the column names and their data types
print(df.info())

# Remove ',' from the column `Subtotal`.
df['Subtotal'] = df['Subtotal'].astype(str).str.replace(r',', '', regex=True)

# Convert the column `Subtotal` to numeric.
df['Subtotal'] = pd.to_numeric(df['Subtotal'])

# Calculate and print the min, 25th quantile, 50th quantile, 75 quantile, and max of the `Subtotal` column. Round the results to 2 decimal places.
print("The min Subtotal value is: ", round(df['Subtotal'].min(),2))
print("The 25th quantile Subtotal value is: ", round(df['Subtotal'].quantile(.25),2))
print("The 50th quantile Subtotal value is: ", round(df['Subtotal'].quantile(.50),2))
print("The 75th quantile Subtotal value is: ", round(df['Subtotal'].quantile(.75),2))
print("The max Subtotal value is: ", round(df['Subtotal'].max(),2))
