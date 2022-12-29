# ***Course Manager& Self Improvement Project***

## *This is a course manager and could help with self-improvement!*

About this *application*:
- This *application* could help people **manage** their courses and self-improvement! 
- This *application* is designed for people who want to **improve** themselves based on self-reflections!
- I want to design this project because I'm going to use it myself, I want to constantly improving myself.

# *User Stories*

- As a user, I would like to be able to add a course to my list
- As a user, I would like to be able to delete a course from my list
- As a user, I would like to be able to view the list of courses on my list
- As a user, I would like to be able to see the total number of courses completed
- As a user, I would like to be able to see the average of completed courses on my list
- As a user, I would like to be able to comment my self reflection on specified courses on my list
- As a user, I want to be able to save my list to file
- As a user, I want to be able to load my list from file

# ***Instructions for Grader***
 - You can add a course, simply enter the course name, grade and comment in the second bottom text-fields and then press the 'Add Course' button on the top.
 - You can remove a course, simply enter the course name in the bottom Remove Course: text-field and then press the 'Remove Course' button on the top.
 - You can check grade for a specific course, simply enter the course name in the bottom Check Course: text-field and then press the 'Check Grade' button on the top.
 - You read comment for a specific course, simply press the 'Read' button on the right.
 - You can calculate the overall average, simply press the 'Calculate Average' button on the top.
 - You can see splash screen is added and displayed when launching this application as well as image icon for this application.
 - You can save your course list, exit the application and a pop-out window will appear asking "Ready to save your course list?", simply select 'Yes'.
 - You can load your course list, launch the application and a pop-out window will appear asking "Ready to load your course list?", simply select 'Yes'.

 -Note: References are displayed on top of the methods.

# ***Phase 4: Task 2***
Tue Nov 22 16:10:45 PST 2022
test added to the list

Tue Nov 22 16:10:45 PST 2022
test2 added to the list

Tue Nov 22 16:10:45 PST 2022
test3 added to the list

Tue Nov 22 16:10:45 PST 2022
test4 added to the list

Tue Nov 22 16:10:52 PST 2022
test4 removed to the list

Tue Nov 22 16:10:55 PST 2022
test3 removed to the list

Tue Nov 22 16:10:56 PST 2022
Viewed comment for test

Tue Nov 22 16:10:59 PST 2022
Viewed comment for test2

Tue Nov 22 16:11:06 PST 2022
Looked up grade for test

Tue Nov 22 16:11:10 PST 2022
Average calculated


# ***Phase 4: Task 2***
In order for this application to work, the course and course list classes are essential. By adding courses into 
the course list in the form of ArrayList, it creates an association. Users can interact and perform actions by using the
GUI which allows user interactions by using buttons, panels as well as visualizing labels that are being added to the 
JFrame. Different panels, buttons, labels and actions have different purposes, by putting them together it creates 
associations, extends and even implementations to form this wonderful application. Json is being used to save the list,
this can be seen by observing that the CourseListApp and CourseListGUI class have associations to JsonReader and 
JsonWriter. It also indicates that those two classes have associations with the course list class. 
Moreover, Event and EventLog classes are used to print out the actions taken during the time of using the application, 
prints include dates, time and what actions were being performed.
If I have more time to do this project, visualization is one of the parts I want to improve on. 
Possibly can add a few cool pictures in the background of this application. On top of that, I would improve my project 
by reducing coupling and increasing cohesion. By doing these, it can improve the readability, maintainability and 
overall quality of my code. To be more specific, for example I would probably refactor out the part of the codes within 
the Course class since it is handling to things, one which is all the basic functionality and property of a course, the
other one is creating a JPanel which also deals with adding buttons and JLabels. I would also perhaps form a composite 
design pattern/structure to improve the overall project.

This projects is licensed under the (MIT License)[#LICENSE].
