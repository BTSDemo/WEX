# Currency Conversion Application

This application allows users to store and convert purchases to different currencies based on Treasury Reporting Rates of Exchange. 

## Building and Installing

1. Unzip the zip file to a folder:  
  
2. Open a command prompt and move to that folder:

   cd transaction-manager

3. To Build the project using Maven:

   mvn clean install

   (in transaction-manager folder, where pom file exists)

4. After a successful build, navigate to the target directory

   cd target

5. Run the application

   java -jar transaction-manager-1.0.0.jar

6. Access the application through a web browser

   at http://localhost:8080

## Usage

1. **Add Purchases:**

   - Navigate to the main menu and select "Add Purchases."
   - Fill in the required details to create a new purchase entry.

2. **List Purchases:**

   - From the main menu, choose "List Purchases" to view all the purchases.

3. **Convert Purchase to Another Currency:**

   - On the Purchase list screen, select the "Convert" option for the desired purchase.
   - Choose the target currency and view the conversion results.

The application runs on port 8080 by default. Please make sure that no other application is using port 8080 before running the application. 
The application is using in-memory database.  

Enjoy using the Currency Conversion Application!