# Smart Contact Manager

Smart Contact Manager is a Spring Boot web application for efficiently managing contacts. Users can create, read, update, and delete contacts, with support for multiple contact lists per profile. The app offers seamless login with Google and GitHub and uses MySQL for secure data storage. Profile pictures are managed with Cloudinary for reliable cloud storage.

## Functionality

- **User Management**: Create new users, authenticate with Google, GitHub, or via database login.
- **Contact Management**: Perform CRUD operations on contacts and manage multiple contact lists per user.
- **Profile Management**: Upload and manage contact profile pictures using Cloudinary.
- **Google & GitHub Authentication**: Enables easy login through OAuth, enhancing user convenience.
- **Secure Data Handling**: User and contact information is stored securely using MySQL, ensuring data integrity and protection.

## Dependencies

- **Spring Boot Web**: Enables the development of RESTful web applications.
- **Spring Data JPA**: Facilitates interaction with the database using JPA repositories.
- **Spring Security**: Manages user authentication and authorization.
- **Lombok**: Reduces boilerplate code in model classes.
- **MySQL Connector**: Connects the application to the MySQL database for data storage.
- **Cloudinary**: Manages image uploads for contact profile pictures.
- **Tailwind CSS & Flowbite**: Provides a modern UI framework for styling the application.
- **JavaScript**: Handles basic frontend functionalities.

## Technologies Used

- **Backend**: Java, Spring Boot
- **Frontend**: Tailwind CSS, Flowbite, JavaScript
- **Database**: MySQL
- **Cloud Services**: Cloudinary, Google Cloud Console
- **Authentication**: Google OAuth, GitHub OAuth
- **Operations**: CRUD operations for contact management
