# CodeFellowship Project - Class Overview

This is a brief overview of some key classes and methods in the CodeFellowship project:

## `UserSite` Class

- `UserSite(String userName, String password, String firstname, String lastname, String dateOfBirth, String bio)`: Constructor for creating a new `UserSite` instance with the provided details.

- `setFirstName(String firstName)`: Setter method to update the first name of the user.

- `setLastName(String lastName)`: Setter method to update the last name of the user.

- `getId()`: Getter method to retrieve the unique identifier of the user.

- `getUserName()`: Getter method to retrieve the username of the user.

- `getPassword()`: Getter method to retrieve the encrypted password of the user.

- `setUserName(String userName)`: Setter method to update the username of the user.

- `setPassword(String password)`: Setter method to update the password of the user.

- `getDateOfBirth()`: Getter method to retrieve the date of birth of the user.

- `getBio()`: Getter method to retrieve the bio of the user.

- `setDateOfBirth(String dateOfBirth)`: Setter method to update the date of birth of the user.

- `setBio(String bio)`: Setter method to update the bio of the user.

## `UserSiteRepository` Interface

- `findByUserName(String userName)`: Method to find a user by their username.

## `UserDetailsServiceImpl` Class

- `loadUserByUsername(String username)`: Implements the `loadUserByUsername` method from the `UserDetailsService` interface to load user details from the database based on the provided username.

## `UserSiteController` Class

- `createUser(String username, String password, String dateOfBirth, String lastname, String bio, String firstname)`: Method for handling user registration. It creates a new `UserSite` instance, sets its properties, encrypts the password, and saves it to the database.

- `authWithHttpServletRequest(String username, String password)`: Method to authenticate a user with the provided username and password using Spring Security.

## `WebSecurityConfig` Class

- `configure(AuthenticationManagerBuilder auth)`: Configuration method for setting up authentication with the `UserDetailsServiceImpl` and password encoder.

- `configure(HttpSecurity http)`: Configuration method for setting up security rules, including permit-all routes and login/logout configurations.


