
# Postcode Distance Calculator


This REST service application is the assignment for a job interview.

## Features

- Returns the geographic (straight line) distance between two postal codes in the UK.
- Has unit tests!
- Authentication
- Updatable postal codes

### Instructions

1. Download / clone source code
2. Build:
   `mvn clean install`
3. Run 1st time:
   `java -jar target\postcode-distance-0.0.1-SNAPSHOT.jar`
The H2 database file will be created in the data/ folder. Then exit the application.
4. Open SQL editor and connect to the DB file. I used [DBeaver](http://dbeaver.io).
5. Copy the unzipped [ukpostcodes](https://data.freemaptools.com/download/full-uk-postcodes/ukpostcodes.zip) file in C:\data\. Execute the following SQL command in the SQL editor:
 `CREATE TABLE postcode_location AS SELECT * FROM csvread('c:\data\ukpostcodes.csv')`
6. Run the web application again.
7. To execute the web services, the exported postman collection is included in the project.


