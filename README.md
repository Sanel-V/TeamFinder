# TeamFinder
A tool for finding team members for school projects.

## Documentation

You can find the current docs in teamfinder/docs

## Build

Requirements:
 - Java 11+
 - Maven 3.6.3+
 - (!)node and npm
 
`mvn clean verify`

**Note**: This app will use React.js as a frontend framework.
Because of this, <u>pom.xml was configured to **download** node and npm</u> 
into your local repo upon compiling project with Maven (more info on the plugin @ https://github.com/eirslett/frontend-maven-plugin).

If you wish to build the back- and frontend separately, comment out **frontend-maven-plugin section** in your pom.xml (lines 59 to 92).
You can then build backend with Maven, frontend with npm.

## Run

After building, execute the .jar file in teamfinder/target to run the backend.

Run frontend in dev mode with `npm start` from teamfinder/src/frontend

**Note**: Frontend is yet to be connected to any backend endpoints.
As of now, the frontend build in React doesn't do anything.

## Contributing

For this semester, outside contributions are closed, and only the members of an assigned team are allowed to contribure.

This project is an assigment for a course at ELTE. 