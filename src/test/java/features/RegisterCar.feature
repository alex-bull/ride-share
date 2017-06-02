Feature: Register a car

    Scenario: A new car is registered to the user
        Given a car has a set of details
        When details for a new car is submitted
        Then the car is registered to a user

    Scenario: A user can register as many cars to a user as they want
        Given a car has a set of details
        When details for a new car is submitted 4 times
        Then four seperate vehicles are registered to the user and their car collection grows by 4