import pandas as pd

import numpy as np

pd.set_option('display.max_rows', None)
pd.set_option('display.max_columns', None)

# Read the CSV file into a DataFrame
df = pd.read_csv('/home/liam/Downloads/dataAnnotation/Achilles_Data_Vis/FIDUCIA REBOUNDS - Pi Design.csv')

# Display the first 5 rows
print(df.head().to_markdown(index=False, numalign="left", stralign="left"))

# Print the column names and their data types
print(df.info())

import numpy as np

# Convert to datetime with format='mixed' to handle multiple datetime formats
df['DATE ATTENDED'] = pd.to_datetime(df['DATE ATTENDED'], format='mixed', errors='coerce')
df['DATE DELIVERED'] = pd.to_datetime(df['DATE DELIVERED'], format='mixed', errors='coerce')

# Get all unique non-datetime values from `DATE ATTENDED`
non_datetime_date_attended_value = df[pd.to_datetime(df['DATE ATTENDED'], errors='coerce').isna()]['DATE ATTENDED'].unique()

if (len(non_datetime_date_attended_value) > 20):
  # Sample 20 of them if there are too many unique values
  print(f"Non-datetime values in DATE ATTENDED : {np.random.choice(non_datetime_date_attended_value, 20, replace=False)}")

else:
  # Otherwise print all unique non-datetime values from `DATE ATTENDED`
  print(f"Non-datetime values in DATE ATTENDED : {non_datetime_date_attended_value}")

# Get all unique non-datetime values from `DATE DELIVERED`
non_datetime_date_delivered_value = df[pd.to_datetime(df['DATE DELIVERED'], errors='coerce').isna()]['DATE DELIVERED'].unique()

if (len(non_datetime_date_delivered_value) > 20):
  # Sample 20 of them if there are too many unique values
  print(f"Non-datetime values in DATE DELIVERED : {np.random.choice(non_datetime_date_delivered_value, 20, replace=False)}")

else:
  # Otherwise print all unique non-datetime values from `DATE DELIVERED`
  print(f"Non-datetime values in DATE DELIVERED : {non_datetime_date_delivered_value}")

# Calculate `DAYS TO DELIVER` by subtracting `DATE ATTENDED` from `DATE DELIVERED`
df['DAYS TO DELIVER'] = (df['DATE DELIVERED'] - df['DATE ATTENDED']).dt.days

# Convert `DAYS TO DELIVER` to integer
df['DAYS TO DELIVER'] = df['DAYS TO DELIVER'].astype('Int64')

# Print the resulting table
print(df[['DATE ATTENDED', 'DATE DELIVERED', 'DAYS TO DELIVER']].head().to_markdown(index=False, numalign="left", stralign="left"))
