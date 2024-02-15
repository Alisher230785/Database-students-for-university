to start the programm, launch the Main class and authorise to the system
before authorising, you must register to the system, providing the name, password, and privilege('student' or 'admin')

HandleDBMethods interface:
- Select() - extraction the fields from the table
- Create() - Creating a new field about the student in the table
- Update() - Updating the information about the student in the table
- Delete() - Delete a row about the student
- SelectStipendHolders() - Select students with scholarship
- Analyze() - Analyze a student by GPA

Auth class:
- used to authorise the user in the system

PostgreDB class:
- used to return connection to Main class

HandleDB class:
- used to send and receive a response from the database

HandleStudent class:
- used to get and send an student's Inputs and Outputs in the console

HandleAdmin class:
- used to get and send an student's Inputs and Outputs in the console

User class:
- describing the field of students
