import pandas as pd
import altair as alt

pd.set_option('display.max_rows', None)
pd.set_option('display.max_columns', None)

# Read the CSV file into a DataFrame
df = pd.read_csv('/home/liam/Downloads/dataAnnotation/Achilles_Data_Vis/cars.csv')

# Display the first 5 rows
print(df.head().to_markdown(index=False, numalign="left", stralign="left"))

# Print the column names and their data types
print(df.info())


# Create the chart
chart = alt.Chart(df).mark_circle(size=60).encode(
    x=alt.X('horsepower:Q', axis=alt.Axis(title='Horsepower')),
    y=alt.Y('weight:Q', axis=alt.Axis(title='Weight')),
    color='origin',
    tooltip=['horsepower', 'weight', 'origin']
).properties(title='Horsepower vs. Weight by Origin').interactive()

chart.save('horsepower_vs_weight_scatter_plot.html')
