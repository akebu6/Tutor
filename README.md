# Tutor
A quiz app to help grade 9 students prepare for their mock and final exams

### Overview of the App

## App Features
1. Allows the user to signup for a free account or login to an already existing account.
2. The user gets to choose which subject that they would like to take the quiz in, as well as choose the difficulty level.
3. After the selection is made, the user can start the quiz by clicking the start button.
4. The timer automatically starts immediately the **start** button is clicked.
5. The user can then answer the question, confirm if the selected answer is what they want to submit and then the app tells them if the answer is correct or not
6. If the answer is correct, then the text is highlighted with a green colour while the other wrong answers are highlighted red.
7. If, however, the answer selected is wrong, then the system will show the user the correct answer.
8. The user can also keep a track of their score as they are answering the questions.


#### NOTE
- If, however, the user allows the timer to finish without having selected an answer, then no score is record.
- If, however, an answer was selected and it is correct, then their score is updated.


## Technology Used
1. **Firebase**: for user authentication.
2. **SQLite**: for the database that contains the questions, categories and answers.
3. **Java**: for the actual implementation of the application.


## Modules
1. **SplashScreen**
The first screen shows the user the app logo before it then leads them to the login screen.
2. **Login**
The user can login if they already have an account. This is to allow the user's data be saved so they can continue where they ended.
3. **Signup**
If the user doesn't have an account, then they can signup for one.
4. **HomeScreen**
Shows the home screen with the **start** button right at the centre with 2 spinners below it for selecting the subject and difficulty level.
5. **Questions**
The user can now start playing the quiz and answering the questions.
6. **Scoreboard** (coming soon...)
7. **Account** (functionality coming soon...)
8. **How to play**
A brief overview of how the user can use the app to play the quiz.
9. **About us**
Brief description of what the app is, the version as well as some information about the developers.


## Current App Limitations
The following are some of the limitations that the app has as most of the functionality is still under construction:
1. Only a few questions and answers have been added in the following subjects: English, Math and Science.
2. The user cannot yet compete with their friends.
3. The user cannot make any modifications to their account information as everything is hard-coded.
4. The logged in user's profile is not yet implemented.
5. The high score of each subject has not been fully integrated into the current working system.
