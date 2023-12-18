# Online Barter Trader (Android)


## Project Overview
The Online Barter Trader app, developed by [Mike Hsin](https://ca.linkedin.com/in/yi-hsien-hsin-a126b9179), is designed for the local exchange of used goods. It connects users within their local areas to trade items such as baby toys, clothes, computer accessories, mobile phones, and furniture without monetary transactions. Users can operate in two roles: provider and receiver, maintaining a balance of give-and-take in the community.

## Features
- Dynamic localization of users to determine trading areas.
- Definition of item categories available for trade.
- Dual-role functionality for each user as both provider and receiver.
- Submission and searching of used goods with detailed information and exchange preferences.
- Real-time alerts for available goods based on user preferences.
- In-app communication between users.
- Visualization of trade history and user reputations.
- Optional valuation service for the items to be traded.

## Iterations and User Stories
- **Iteration 1**: Focuses on user authentication, including login, session management, and account registration.
- **Iteration 2**: Adds functionality for users to add and edit their preferences, and manage their friends list.
- **Iteration 3**: Introduces the ability to post, view, remove, and rate goods.

## Development Approach
The development tasks are structured around specific user stories and acceptance tests, ensuring that each feature provides value to the user and meets their expectations. The engineering tasks are detailed for each acceptance test, with a focus on creating a robust and testable codebase.

## Technology Stack
- **Mobile Platform**: Android
- **Programming Language**: Java
- **Build System**: Gradle
- **Database**: Firebase
- **Testing Frameworks**: JUnit, Espresso
- **Version Control**: Git
- **Minimum Android Version**: 16
- **Dependencies**: 30.0.3

## Getting Started
1. Clone the repository to your local machine.
2. Open the project in Android Studio.
3. Sync the project with Gradle files.
4. Configure Firebase with the project.
5. Run the app on an emulator or a physical device.

## Testing
- Follow the acceptance tests outlined for each user story to verify the app's functionality.
- Unit tests and Espresso tests are provided for various components of the application.

## Citations and notes
1. The regex pattern for validating the username input in RegisterPageActivity.java, URL: https://regex101.com/, Date Accessed: Feb 1 2021, Author: N/A

2. The regex pattern for validating the email input in RegisterPageActivity.java, URL: https://howtodoinjava.com/java/regex/java-regex-validate-email-address/, Date Accessed: Feb 2 2021, Author: Unknown

3. The send and read firebase data in MainActivity and RegisterPageActivity, URL: https://firebase.google.com/docs/database/web/read-and-write, Date Accessed: 3 Feb 2021, Author: Unknown

4. The putExtrea() function from android developer website, URL: https://developer.android.com/reference/android/content/Intent, Date Accessed: Feb 3 2021, Author: Unknown

5. The reference for logo URL: https://www.freelogodesign.org/preview?lang=en&name=Barter%20Trader&logo=52e5b17d-9f49-44a6-afdf-9e8ddcf28c97

6. The Espresso tests from csci3130 a2 solution URL: https://git.cs.dal.ca/masud/csci-3130-a2-sol-winter2021.git

7. The regex pattern from https://www.geeksforgeeks.org/how-to-validate-a-password-using-regular-expressions-in-java/
