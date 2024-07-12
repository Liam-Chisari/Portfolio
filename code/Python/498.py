import pandas as pd

pd.set_option('display.max_rows', None)
pd.set_option('display.max_columns', None)

# Read the CSV file into a DataFrame
df = pd.read_csv('/home/liam/Downloads/dataAnnotation/Achillies_Data_Vis/Premium_Collection_Audit_-_Sheet1.csv')

# Aggregate by 'Homeowners Policy' and 'Days of Delinquent Payment'
homeowner_agg = df.groupby(['Homeowners Policy', 'Days of Delinquent Payment']).agg(
    Number_of_Delinquencies=('Days of Delinquent Payment', 'count'),
    Total_Premium=('Total Premium', 'sum')
).reset_index().rename(columns={'index':'Index'})

# Rename the column 'Days of Delinquent Payment' to 'Delinquency Days'
homeowner_agg = homeowner_agg.rename(columns={'Days of Delinquent Payment':'Delinquency Days'})

# Sort the dataframe by 'Homeowners Policy' and then 'Delinquency Days'
homeowner_agg_sorted = homeowner_agg.sort_values(by=['Homeowners Policy', 'Delinquency Days'])

# Write the final dataframe to a csv
homeowner_agg_sorted.to_csv('homeowner_table.csv', index=False)
