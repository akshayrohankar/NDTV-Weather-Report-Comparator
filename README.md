# NDTV Weather Report Comparator
Weather Reporting Comparator that compares weather reporting done by 2 sources. 
NDTV Weather Report Comparator.

# Automation Requirements:
Phase 1
1. Use any UI automation tool to automate https://www.ndtv.com/
2. Reach the weather section of the website.
3. Use the ` Pin your city ` section on the left of the screen to search & select any
   given city and validate that the corresponding city is available on the map with
   temperature information.
4. Validate that selecting any city on the map itself reveals the weather details.

Phase 2
1. Use the APIs listed here ( https://openweathermap.org/current ) for getting current
weather data for any city
NOTE: Please use this value as API key in the request :
“ 7fe67bf08c80ded756e598d6f8fedaea' ”
2. Automate the above REST API using any Rest client

Phase 3
1. Create a comparator that matches the temperature information from the UI in
phase 1 against the API response(ensure both are using the same temperature
unit) in phase 2
2. Build a variance logic(should be configurable) that returns a success if
temperature difference is within a specified range, else return a matcher
exception

# Example Workflow
1. Visit ndtv website’s weather page and search for Bangalore
2. Store weather object 1 w.r.t this Bangalore (e.g. temp value as 33 degree
celsius, humidity level as 70% etc.)
3. Get response from the weather API for Bangalore
4. Store the API response and build the weather object 2
5. Specify the variance logic - for e.g. 2 degree celsius for temperature & 10% for
humidity
6. Compare weather objects 1 and 2 along with the variance and mark tests as
pass or fail based on comparator response

# Running the tests
Test can be run from testing.xml file.
Run this file as TestNG suite and it will Weather Reporting Comparator to test end to end functionality.
Test data is provided from properties file located under com.framework.Resources package.

# Built With
•	Maven - Dependency Management

•	TestNG – Testing Framework

•	Page Object Model – Framework Design Pattern

# Reporting
Basic TestNG html report is used for demo.

# Author
•	Akshay Rohankar  
