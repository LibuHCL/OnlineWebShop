# OnlineWebShop 

Developed an end to end **Ecommerce OnlineWebShop web Application using Spring MVC** 

### Functionalities:

1. Develop a simple shop web service which satisfies the following requirements: 
* Create a category
* List all categories
* Create an item for a category
* List all items for the category

2. The entities have the following attributes: - A category has a title and a set of items. - An item has a title, text and price.
HTML pages should be styled. You could do it yourself or use some framework. It is an advantage, if the Application would be done accordingly to the Responsive Web Design approach.
The system should be implemented using:

* Java;
* RESTful Web-Service(s);
* A database to store the entities (For this task you don't need to implement the database access, it is adequate just to have an interface);
* A JavaScript code (any library you like, jQuery is fine) that access this API to render the data in HTML;
Please create a unit-test and an integration-test for one(!) of the requirements, a full test-coverage is not necessary. There is no need to do WebDriver tests, like Selenuim. The project should be managed with maven and the tests have to be executable using 'mvn test'.
The application itself does not need to be executable.
Please sent your solution to this puzzle as zip/tgz and not submit into public portals, like GitHub.

### Tools and Technologies:

* **Technology** : Bootstrap, Java, Spring MVC, Hibernate, JSP, Maven.
* **Application Servicer**: Apache Tomcat Server
* **Database** : MySQL Database.

### Installation:

1. Development Platform - Eclipse / IntelliJ Idea
   * [Download Eclipse](https://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/mars2)
   * [Download IntelliJ Idea](https://www.jetbrains.com/idea/download/#section=windows)
2. Server - Apache Tomcat Server

   * [Download Apache Server](https://tomcat.apache.org/download-70.cgi)

3. Build Tool - Maven

   * [Download Maven](https://maven.apache.org/download.cgi)

4. Database - MySQL Database

   * [Download MySQL Database](http://www.mysqldatabase.com/html/download.html)

5. Configuring tomcat with Eclipse (windows) - [Click Here](https://javatutorial.net/run-tomcat-from-eclipse)

6. Installation of maven in eclipse - [Click Here](https://stackoverflow.com/questions/8620127/maven-in-eclipse-step-by-step-installation)

7. Clone the repository and import it to eclipse

8. Run your H2 Database.

9. Configure your databse configuration in **application-context.xml**

   * Database properties:


        <!-- database properties DataSource -->

            <bean id="dataSource"
              class="org.springframework.jdbc.datasource.DriverManagerDataSource">
              <property name="driverClassName" value=YOUR DB DRIVER CLASS NAME" />
              <property name="url" value="YOUR DB URL" />
              <property name="username" value="YOUR DB USERNAME" />
              <property name="password" value="YOUR DB PASSWORD" />
            </bean>

      * Database Dialect:

            <prop key="hibernate.dialect">YOUR DB DIALECT</prop>

10. Run the server.


