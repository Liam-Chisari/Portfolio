from PIL import Image
with Image.open("fish.jpg") as im:
    im.rotate(45).show()
