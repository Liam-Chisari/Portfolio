from PIL import Image
from PIL import ImageOps
import os

def invert_image(filename):
    try:
        image = Image.open(filename)
        inverted_image = ImageOps.invert(image)
        inverted_image.save(f'{filename}-inverted.jpg')
        print(f"Image {filename} has been inverted and saved as {filename}-inverted.jpg")
    except Exception as e:
        print(f"Error: {e}")

def main():
    while True:
        filename = input("Enter the filename of a JPG file: ")
        if not filename.lower().endswith(".jpg"):
            print("Invalid filename. Please enter a JPG file.")
        else:
            invert_image(filename)
            break

if __name__ == "__main__":
    main()
