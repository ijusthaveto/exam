# Exam

This is a front-end and back-end separated project based on SpringBoot and Vue, supporting:

- Single-choice, multiple-choice, and true/false questions. 
- Setting the start time and duration of an exam. 
- Allowing the import of a student list. 
- Randomly selecting questions for each student before the exam according to the exam rules.
  - Questions for the same student cannot be repeated. 
  - Random order of questions. 
  - Random order of options for multiple-choice questions. 
- Anti-refresh feature. 
- Countdown function: automatically collect exam papers when the exam time is up. 
- Automatic scoring for students. 

## Requirements

1. **Back-end:**
   - Java Runtime Environment (JRE) installed: 1.8
   - Maven: 3.8.7
2. **Front-end:**
   - Node.js installed for package management: v20.10.0
   - Vue.js framework and related dependencies (installed using npm or yarn): 9.5.1
3. **Database:**
   - MySQL database system installed and configured: 8.0.31

## Setup

Please use the SQL scripts in the "sql" directory to create the database, and replace your own database account information as follows:

```yml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: your_username
    password: your_password
    url: jdbc:mysql://localhost:your_port/your_schema
```

Navigate to the root directory of the back-end (Spring Boot) project in the command line.

Run the following command to start the back-end service:

```bash
./mvnw spring-boot:run
```

or:

```bash
mvn spring-boot:run
```

Open a new command line window, navigate to the root directory of the front-end (Vue) project.

Run the following command to start the front-end application:

```bash
npm install
npm run dev
```

Access the port specified for the front-end application in your browser to view the application.
