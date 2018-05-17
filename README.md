# crealytics-quest

# Solution


## Technologies
### Language
*   Java 1.8
### Build Tool
*   Maven 3.5
### Frameworks Used
* Spring Boot 2.0.0.RELEASE
* Cucumber 1.2.5'
* JPA Hibernate (H2 Database)

## Test cases
#### To run all test cases please run:
~~~shell
mvn clean verify -P all-test
~~~

### JUnit Test cases
You can find unit test cases under package *quest.crealytics* .
Test cases are written using JUnit to test functionality of methods in classes.

#### To run unit test cases please run:
~~~shell
mvn clean test
~~~
**Coverage Report:** target/coverage-reports/jacoco-ut.exec

**Surefire reports** target/surefire-reports 

**Coverage Html Report:** target/jacoco-ut/index.html

### Behavior-driven development Test cases
You can find *Behavior-driven development(BDD)* cases under package *bdd* and feature files under test resources

Cucumber is used to implement BDD
#### To run BDD test cases please run:
~~~shell
mvn clean verify -P integration-test
~~~
**Coverage Report:** target/coverage-reports/jacoco-ut.exec

**Failsafe reports** target/failsafe-reports

**Coverage Html Report:** target/site/jacoco-it/index.html

## Code Coverage

### Intellij Idea Code Coverage

[![Coverage screenshot](screenshots/intellij_code_coverage.JPG)](screenshots/TestCoverage.JPG)

### Sonar Code coverage
[![Coverage screenshot](screenshots/sonar_code_coverage.JPG)](screenshots/sonar_code_coverage.JPG)


## To run the application please execute following cammand 


### With data from resource folder 
~~~shell
java -jar target/quest.crealytics-development-SNAPSHOT-spring-boot.jar
~~~

### With data from external folder 
~~~shell
java -jar target/quest.crealytics-development-SNAPSHOT-spring-boot.jar -Dcrealytics.data.dir=<directory-to-csv-files>
~~~

## Report Endpoint '/reports'
### Without Request params

#### Request:
```http
GET /reports HTTP/1.1
Accept: application/json;charset=UTF-8
```

#### Response
```http
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{"month":"all","site":"all","requests":68823820,"impressions":64422713,"clicks":184724,"conversions":39477,"revenue":128351.91,"CTR":0.29,"CR":0.06,"fill_rate":93.61,"eCPM":1.99}
```
### With Request params

Both '**month**' and '**site**' are optional params

#### Request:
```http
GET /reports?month=<valid-month>&site=<valid-site> HTTP/1.1
Accept: application/json;charset=UTF-8
```

#### Response
```http
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{"month":"<valid-month>","site":"<valid-site>","requests":68823820,"impressions":64422713,"clicks":184724,"conversions":39477,"revenue":128351.91,"CTR":0.29,"CR":0.06,"fill_rate":93.61,"eCPM":1.99}
```

### With invalid Request params

#### Request:
```http
GET /reports?month=<invalid-month>&site=<invalid-site> HTTP/1.1
Accept: application/json;charset=UTF-8
```
#### Response
```http
HTTP/1.1 400 OK
Content-Type: application/json;charset=UTF-8

{"statusCode":"400","msg":"Invalid value for MonthOfYear: <invalid-month>"}
```
#### Request:
```http
GET /reports?month=<valid-month>&site=<invalid-site> HTTP/1.1
Accept: application/json;charset=UTF-8
```
#### Response
```http
HTTP/1.1 400 OK
Content-Type: application/json;charset=UTF-8

{"statusCode":"400","msg":"Invalid value for Site: <invalid-site>"}
```
