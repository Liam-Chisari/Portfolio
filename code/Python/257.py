import pandas as pd

pd.set_option('display.max_rows', None)
pd.set_option('display.max_columns', None)

# Read the CSV files into Pandas Dataframes
df_alcohol_drug_abuse = pd.read_csv('/home/liam/Downloads/dataAnnotation/Achillies_Data_Vis/Hospital_Survey_Data_Alcohol_Drug_Abuse.csv', skiprows=1)
df_speticemia = pd.read_csv('/home/liam/Downloads/dataAnnotation/Achillies_Data_Vis/Hospital_Survey_Data_Speticemia.csv', skiprows=1)


# Display the first 5 rows
print("First 5 rows of Alcohol and Drug Abuse data:")
print(df_alcohol_drug_abuse.head().to_markdown(index=False, numalign="left", stralign="left"))

print("\nFirst 5 rows of Speticemia data:")
print(df_speticemia.head().to_markdown(index=False, numalign="left", stralign="left"))


# Print the column names and their data types
print("\nAlcohol and Drug Abuse Column Information:")
print(df_alcohol_drug_abuse.info())

print("\nSpeticemia Column Information:")
print(df_speticemia.info())

#Filter alcohol and drug abuse data where the DRG Definition contains "W REHABILITATION THERAPY"
rehab_therapy_alcohol_drug_abuse = df_alcohol_drug_abuse[df_alcohol_drug_abuse['DRG Definition'].str.contains('W REHABILITATION THERAPY')]
print("First 5 rows of Alcohol and Drug Abuse data WITH rehabilitation therapy:")
print(rehab_therapy_alcohol_drug_abuse.head().to_markdown(index=False, numalign="left", stralign="left"))

# Calculate the total discharges
total_discharges_rehab_therapy = rehab_therapy_alcohol_drug_abuse['Total Discharges'].sum()
print("\nTotal Discharges with Rehabilitation Therapy in Alcohol and Drug Abuse DRGs:", total_discharges_rehab_therapy)

#Filter alcohol and drug abuse data where the DRG Definition contains "W/O REHABILITATION THERAPY"
no_rehab_therapy_alcohol_drug_abuse = df_alcohol_drug_abuse[df_alcohol_drug_abuse['DRG Definition'].str.contains('W/O REHABILITATION THERAPY')]
print("\nFirst 5 rows of Alcohol and Drug Abuse data WITHOUT rehabilitation therapy:")
print(no_rehab_therapy_alcohol_drug_abuse.head().to_markdown(index=False, numalign="left", stralign="left"))

# Calculate the total discharges
total_discharges_no_rehab_therapy = no_rehab_therapy_alcohol_drug_abuse['Total Discharges'].sum()
print("\nTotal Discharges without Rehabilitation Therapy in Alcohol and Drug Abuse DRGs:", total_discharges_no_rehab_therapy)


#Filter speticemia data where the DRG Definition contains "W REHABILITATION THERAPY"
rehab_therapy_speticemia = df_speticemia[df_speticemia['DRG Definition'].str.contains('W REHABILITATION THERAPY')]
print("\nFirst 5 rows of Speticemia data WITH rehabilitation therapy:")
if not rehab_therapy_speticemia.empty:
    print(rehab_therapy_speticemia.head().to_markdown(index=False, numalign="left", stralign="left"))
else:
    print("No cases of Speticemia with Rehabilitation Therapy found.")

# Calculate the total discharges
total_discharges_rehab_therapy = rehab_therapy_speticemia['Total Discharges'].sum()
print("\nTotal Discharges with Rehabilitation Therapy in Speticemia DRGs:", total_discharges_rehab_therapy)

#Filter speticemia data where the DRG Definition contains "W/O REHABILITATION THERAPY"
no_rehab_therapy_speticemia = df_speticemia[df_speticemia['DRG Definition'].str.contains('W/O REHABILITATION THERAPY')]
print("\nFirst 5 rows of Speticemia data WITHOUT rehabilitation therapy:")
if not no_rehab_therapy_speticemia.empty:
    print(no_rehab_therapy_speticemia.head().to_markdown(index=False, numalign="left", stralign="left"))
else:
    print("No cases of Speticemia without Rehabilitation Therapy found.")

# Calculate the total discharges
total_discharges_no_rehab_therapy = no_rehab_therapy_speticemia['Total Discharges'].sum()
print("\nTotal Discharges without Rehabilitation Therapy in Speticemia DRGs:", total_discharges_no_rehab_therapy)
