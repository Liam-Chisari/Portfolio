import re
emails = [
    "John Doe <johndoe@example.com>",
    "Jane Smith <janesmith@gmail.com>",
    "Mike Davis <mike.davis@yahoo.co.uk>",
    "Alice Roberts <alice@example.org>",
    "Invalid Email: no@at.all",
    "Valid Email: abc@def.com"
]
  
email_pattern = r"(?:[a-zA-Z0-9._%+-]+)@(?:[a-zA-Z0-9.-]+\.)+(?:[a-zA-Z]{2,4})"

for email in emails:
    match = re.search(email_pattern, email)
    if match:
        print(f"Found valid email: {match.group(0)}")
    else:
        print(f"Invalid email: {email}")
