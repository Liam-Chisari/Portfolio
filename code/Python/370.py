import pandas as pd

pd.set_option('display.max_rows', None)
pd.set_option('display.max_columns', None)

# Read the CSV file into a DataFrame
df = pd.read_csv('/home/liam/Downloads/dataAnnotation/Achilles_Data_Vis/Trends_in_Job_Application_Methods.csv')

# Display the first 5 rows
print(df.head().to_markdown(index=False, numalign="left", stralign="left"))

# Print the column names and their data types
print(df.info())

# Calculate and print the mean value of the column `How influential do you believe your social media presence is in the job application process?` rounded to 2 decimal places.
average_influence = round(df['How influential do you believe your social media presence is in the job application process?'].mean(),2)

print(f'The average of the influence of social media across all records in the dataset is: {average_influence}')
