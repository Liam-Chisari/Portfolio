import pandas as pd

# Load the provided CSV file to examine its contents
file_path = '/home/liam/Downloads/dataAnnotation/Achilles_Data_Vis/blizzard_games.csv'
blizzard_games = pd.read_csv(file_path)

# Display the first few rows of the dataframe to understand its structure
blizzard_games.head()

# Filter games that are available on Mac OS platforms and have user score data
mac_os_games = blizzard_games[blizzard_games['Platform(s)'].str.contains('Mac OS', case=False, na=False)]
mac_os_games_with_scores = mac_os_games.dropna(subset=['User Score']).sort_values(by='User Score', ascending=False)

# Display the filtered and sorted games
print(mac_os_games_with_scores[['Name', 'Platform(s)', 'Release', 'Genre(s)', 'User Score']])


