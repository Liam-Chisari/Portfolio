import pandas as pd

pd.set_option('display.max_rows', None)
pd.set_option('display.max_columns', None)

# Read the TSV file into a DataFrame
df = pd.read_csv('/home/liam/Downloads/dataAnnotation/Achilles_Data_Vis/customer data - contracts.tsv', sep='\t')

# Display the first 5 rows
print(df.head().to_markdown(index=False, numalign="left", stralign="left"))

# Print the column names and their data types
print(df.info())

# Split the `Start Date` column into two columns, `Date` and `Time`, using the "T" as the delimiter.
df[['Date','Time']] = df['Start Date'].str.split('T', expand=True)

# Write the updated DataFrame to a new CSV file
df.to_csv('contracts_date_time.csv', index=False)

# Print confirmation
print("The table 'contracts_date_time.csv' has been successfully created.")
                  
