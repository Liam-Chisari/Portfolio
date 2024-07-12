import pandas as pd

pd.set_option('display.max_rows', None)
pd.set_option('display.max_columns', None)

# Read the TSV file into a DataFrame
df = pd.read_csv('VendorDownload_4.tsv', sep='\t')

# Display the first 5 rows
print(df.head().to_markdown(index=False, numalign="left", stralign="left"))

# Print the column names and their data types
print(df.info())

# Drop null values in `Description`.
df.dropna(subset = ['Description'], inplace=True)

# Filter rows where Description contains 'Coghlan' (case-insensitive)
df_filtered = df[df['Description'].str.contains('Coghlan', case=False)]

# Remove '%' sign from the `Discount` column
df_filtered['Discount'] = df_filtered['Discount'].astype(str).str.replace('%', '', regex=False)

# Convert the `Discount` column to numeric
df_filtered['Discount'] = pd.to_numeric(df_filtered['Discount'])

# Filter based on `Qty Ordered` and `Discount` conditions
df_filtered = df_filtered[(df_filtered['Qty Ordered'] > 5) & (df_filtered['Discount'] > 25)]

if not df_filtered.empty:
  # Print the filtered results
  print(df_filtered.to_markdown(index=False, numalign="left", stralign="left"))
else:
  print("There are no items matching the given criteria.")
