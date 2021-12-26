# Hotel Reservation Application Project
The hotel reservation application will allow customers to find and book a hotel room based on room availability. This project will demonstrate the abilities to design classes using OOP, organize and process data with collections, and use common Java types.

##Flow Chart
![](/Users/szeyichan/Desktop/Screen Shot 2021-12-25 at 5.55.10 PM.png)

# APP FEATURES:

## 1. User Scenarios
* **Creating a customer account.**
  * The user needs to first create a customer account before they can create a reservation.

* **Searching for rooms.** 
  * Allow the user to search for available rooms based on provided checkin and checkout dates. 
  * If the application has available rooms for the specified date range, a list of the corresponding rooms will be displayed to the user for choosing.

* **Booking a room.** 
  * Once the user has chosen a room, the app will allow them to book the room and create a reservation.

* **Viewing reservations.** 
  * After booking a room, the app allows customers to view a list of all their reservations.

## 2. Admin Scenarios

* Displaying all customers accounts.
* Viewing all of the rooms in the hotel.
* Viewing all of the hotel reservations.
* Adding a room to the hotel application.

## 3. Reserving a Room

* Avoid conflicting reservations. 
* A single room may only be reserved by a single customer per a checkin and checkout date range.
* Search for recommended rooms. 
  * If there are no available rooms for the customer's date range, a search will be performed that displays recommended rooms on alternative dates.


## 4. Room 
* **Room cost.** 
  * Rooms will contain a price per night. 
* **Unique room numbers.** 
  * Each room will have a unique room number, meaning that no two rooms can have the same room number.
* **Room type.** 
  * Rooms can be either single occupant or double occupant.

## 5. Customer

* A unique email for the customer. 
  * (i.e., name@domain.com).
* A first name and last name.


## 6. Error Requirements
* No crashing. 
  * The application does not crash based on user input.
* No unhandled exceptions. 
  * The app has try and catch blocks that are used to capture exceptions and provide useful information to the user. 
  * There are no unhandled exceptions.
