from datetime import date

class Booking:
    def __init__(self,booking_ID, name, num_guests, check_in_date, check_out_date, notes):
        self.booking_ID = booking_ID
        self.name = name
        self.num_guests = num_guests
        self.check_in_date = check_in_date
        self.check_out_date = check_out_date
        self.notes = notes

    def print_booking(self):
        print()
        print("Booking ID: %d " %(self.booking_ID))
        print("Name: " + self.name)
        print("Number of Guests: %d" % (self.num_guests))
        print("Check-in Date: " + self.check_in_date.isoformat())
        print("Check-out Date: " + self.check_out_date.isoformat())
        print("Notes: " + self.notes)
        print()


class Menu:
    def __init__(self, title, options):
        self.title = title
        self.options = options
        # Make num_options equal to options.length
        num_options = len(options)
        self.separator = "-------------"

    # print menu function here
    def show_menu(self):
        print(self.separator)
        print(self.title)
        for i, item in enumerate(self.options):
            print("%d) %s " % (i + 1, item))
        print(self.separator)


class BookingSystem:
    def __init__(self):
        self.bookings = list()


    def add_booking(self):

        while True:

            try: 
                booking_ID = len(self.bookings)
                name = input("Enter customer name:")
                num_guests = int(input("Enter number of guests:"))
                check_in_date = date.fromisoformat(input("Enter check-in date:  (YYYY-MM-DD)"))
                check_out_date = date.fromisoformat(input("Enter check-out date: (YYYY-MM-DD)"))
                notes = input("Enter notes:")
                if(check_out_date < check_in_date):
                    raise TypeError()
                break
            except ValueError:
                print("Invalid input! Please try again.")
            except TypeError:
                print("Check-out date cannot be before check-in date!")

        self.bookings.append(Booking(booking_ID, name, num_guests, check_in_date,
                    check_out_date, notes))


    def delete_booking(self):


        while True:
            try:
                target_booking_ID = int(input("Enter ID of booking to delete:"))
                break
            except ValueError:
                print("Please enter a number.")


        for booking in self.bookings:
            if(booking.booking_ID == target_booking_ID):
                self.bookings.remove(booking)
                print("Removed booking.")
                return

        print("No booking with ID %d found." % (target_booking_ID))




    def show_bookings(self):
        print("Current bookings:")
        print()
        for booking in self.bookings:
            booking.print_booking()

def main():
    booking_system = BookingSystem()

    main_menu = Menu("Main Menu", list(("Add Booking","Delete Bookings",
        "Show Bookings", "Exit")))
    choice = 0

    
    while(choice != 4):

        main_menu.show_menu()

        try:
            choice = int(input("Enter a selection:"))
        except ValueError:
            print("Input must be numeric!")


        if choice == 1:
            print("Add booking")
            booking_system.add_booking()
        elif choice == 2:
            print("Delete booking")
            booking_system.delete_booking()
        elif choice == 3:
            booking_system.show_bookings()
        elif choice == 4:
            print("Exit")
        else:
            print("Please enter a valid menu option")

         


if __name__ == "__main__":
    main()
