import pandas as pd

pd.set_option('display.max_rows', None)
pd.set_option('display.max_columns', None)

# Read the CSV file into a DataFrame
df = pd.read_csv('/home/liam/Downloads/dataAnnotation/Achilles_Data_Vis/Data Set #13 report.csv')

# Display the first 5 rows
print(df.head().to_markdown(index=False, numalign="left", stralign="left"))

# Print the column names and their data types
print(df.info())

# Remove ',' from the `AGE OF INVENTORY DAYS` column and convert to numeric
df['AGE OF INVENTORY DAYS'] = df['AGE OF INVENTORY DAYS'].astype(str).str.replace(',', '', regex=False)
df['AGE OF INVENTORY DAYS'] = pd.to_numeric(df['AGE OF INVENTORY DAYS'])

# Select the columns `item_num`, `COST`, `sell_price`, and `AGE OF INVENTORY DAYS`
df_out = df[['item_num', 'COST', 'sell_price', 'AGE OF INVENTORY DAYS']]

# Write the updated dataframe to a new tab-separated file called inventory_data.tsv
df_out.to_csv('inventory_data.tsv', sep='\t', index=False)
