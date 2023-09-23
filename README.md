# Budget Manager

This project works as a "budget manager" for monthly purchases, where it allows 
the user to add their income and save the purchases that they have made. It provides the 
ability to save inputted purchases and to view the balance.

When the program is run, the user is greeted with a menu to:

1. Add their income
2. Add a purchase based on its type (available types are: Food, entertainment, clothes, other)
3. Show the list of purchases provided
4. Show the balance (income - total price of inputted purchases)
5. Save the purchases added to a file
6. Load the purchases previously saved to a file into the program (allows the user to register purchases inputted and 
saved in a previous run of the program)

# General Info

To learn more about this project, please visit [HyperSkill Website - Budget Manager](https://hyperskill.org/projects/76).

This project's difficulty has been labelled as __Hard__ where this is how 
HyperSkill describes each of its four available difficulty levels:

- __Easy Projects__ - if you're just starting
- __Medium Projects__ - to build upon the basics
- __Hard Projects__ - to practice all the basic concepts and learn new ones
- __Challenging Projects__ - to perfect your knowledge with challenging tasks

This repository contains

    budget package
        - Contains the budget.Main java class that contains the main method to run the project
        - Contains the budget.Menu java class that is used to store the purchases provided by the user
        - Contains the budget.PurchaseType abstract java class and its subclasses that store the purchases provided by the user by type
        - Contains the budget.GetUserInput java class that contains utility methods used to get correct user input

Project was built using java version 8 update 381

# How to Run

Download the budget repository to your machine. Create a new project in IntelliJ IDEA, then 
move the downloaded budget repository to the src folder, then run the budget.Main java class.
