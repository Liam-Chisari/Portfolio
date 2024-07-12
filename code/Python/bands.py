from itertools import permutations

def divide_variables(bands):
  """Divides all variables from the list excluding the multiplication on itself.
  For example:
  divide_variables(['Band 1', 'Band 2', 'Band 3']) == ['Band 1 / Band 2', 'Band 1 / Band 3', 'Band 2 / Band 1', 'Band 2 / Band 3', 'Band 3 / Band 1', 'Band 3 / Band 2']
  """

  result = []
  for band1, band2 in permutations(bands, 2):
    if band1 != band2:
      result.append(f'{band1} / {band2}')
  return result

bands = ['Band 1', 'Band 2', 'Band 3', 'Band 4']
result = divide_variables(bands)
print(result)
                
