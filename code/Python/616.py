import pandas as pd

# Load the uploaded CSV file to take a look at its structure
df = pd.read_csv('/home/liam/Downloads/dataAnnotation/Achilles_Data_Vis/Chainmaille_by_Yael_– Customer_Data_(2023).csv')

# Display the first few rows of the dataframe to understand its structure and columns
df.head()

# Correcting the reference to the column names by including quotation marks

# Categorize and summarize data based on the frequency of wearing jewelry
wear_jewelry_summary = df.groupby('"How often do you wear jewelry?"').size()

# Summarize purchase frequency based on jewelry wearing frequency
purchase_freq_summary = df.groupby('"How often do you wear jewelry?"')["\"How often do you purchase jewelry?\""].value_counts().unstack()

# Summarize product type based on jewelry wearing frequency
product_type_summary = df.groupby('"How often do you wear jewelry?"')["Product Purchased"].value_counts().unstack()

df.columns.tolist()

# Correcting the "Price" column to handle different currency symbols and convert to numeric
# Removing non-numeric characters except for the decimal point
df['Price'] = df['Price'].str.replace('[^\d.]', '', regex=True).astype(float)

# Now, let's reattempt to analyze average price based on jewelry wearing frequency
average_price_summary_corrected = df.groupby('"How often do you wear jewelry?"')["Price"].mean()

print(average_price_summary_corrected)
