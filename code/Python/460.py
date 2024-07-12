import pandas as pd

pd.set_option('display.max_rows', None)
pd.set_option('display.max_columns', None)

# Read the CSV file into a DataFrame
df = pd.read_csv('/home/liam/Downloads/dataAnnotation/Achilles_Data_Vis/internet.csv')

# Display the first 5 rows
print(df.head().to_string(index=False))

# Print the column names and their data types
print(df.info())

import altair as alt

# Calculate the mobile users per 100 people
df['mobile_users_per_100'] = df['mobile_users'] / df['internet_users_per_100_people'] * 100

# Convert the `mobile_users_per_100` values into units of millions
df['mobile_users_per_100'] = df['mobile_users_per_100'] / 1000000

# Sort by `mobile_users_per_100` in descending order
df_sorted = df.sort_values(by='mobile_users_per_100', ascending=False)

# Create the line chart
chart = alt.Chart(df_sorted).mark_line(point=True).encode(
    x=alt.X('country:N', axis=alt.Axis(labelAngle=-45)),
    y=alt.Y('mobile_users_per_100:Q', title='Mobile Users per 100 People (in millions)'),
    tooltip = ['country', 'mobile_users_per_100']
).properties(
    title='Mobile Users per 100 People by Country'
).interactive()

# Save the chart
chart.save('mobile_users_per_100_people_by_country_line_chart.json')
chart.save('chart.html')

# Print first five rows of the dataframe
print(df_sorted.head().to_string(index=False))
