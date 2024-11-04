# MobileAutomation
To Test Mobile App Autoamtion
Tools used: Appium, Java JDK 21,Junit, Maven, Android SDK/Studio , Swag lab app


## Getting started
<h3>Project Description:</h3>
- Project have implemented to Automate the scenarios of Sauce Lab Mobile App
- This project illustrate design of Hybrid Framework with Page Object Model (POM) with Cucumber, Appium and Selenium.

<h3>Project Component:</h3>
- Driver Manager         - Initialize Android and IOS driver
- Component Library      - Provide all required element wrapper methods.
- Hooks                  - Cucumber hooks for setting up and tear down driver and for failure screen capture
- PropertiesFileManager  - Library that read properties from property file and store value in file
- Utils library           - Excel Reader(Read Excel,Write Excel), Send Test Automation Report, Test Data Generator,


    feature Files              : Cucumber Feature file holds all required Cucumber feature file.
                                 /src/test/resources/features
                 
    Step Defination           : Holds respective step definations class.
                                /src/test/java/stepDefinitions
                                
    Page Library              : Page Classes for POM
                                /src/test/java/pages
                                
                                
    Constant Library           : All input data  to be tried out by various test methods woulbe be going into this directory
                                 /src/main/java/com/org/util/Constants.java
                                
    Application Hooks         : Cucumber Hooks with before and after
                                /src/test/java/Hooks/Hooks.java
                                
    Config                    : Holds input data that needs to be pass to Test Method.
                                /src/test/resources/environment/qa/environment.properties
                                /src/test/resources/environment/stage/environment.properties
                                
    Test Runner               : Test Runners in Junit
                                /src/test/java/TestRunner/TestCaseRunner.java
                                
    Reporting Property        : All required input to generate HTML Extent Spark and PDF Report with Test Method results.
                                /src/test/resources/report_manager/ExtentManager.java
                                /src/test/resources/report_manager/ExtentTestManager.java
                                
    
    Utility                 :  All required utility for Constant Reading,Read Data From Excel, Read Properties File, Element Util for Selenium Functions
                                /src/test/java/utils/AppiumServerUtil.java
                                /src/test/java/utils/Constants.java
                                /src/test/java/utils/ElementUtil.java
                                /src/test/java/utils/SwitchContextUtil.java

Technologies Used:

     1. Selenium WebDriver 4+ with Java Language binding
     2. Cucumber 6.x JVM library
     3. JDK 21
     4. Maven (Build tool)
     5. Maven Plugins
     6. Extentreports
     7. Junit 4 Above
     8. IntelliJIDEA (IDE)
	 9. Picocontainer for dependancy injection

<h3>Execution:</h3>

- To Execute All the Feature file navigate to /src/test/java/testRunner/TestCAseRunner.java class and execute with help of TestNG.
- To execute specific Feature file, mention the required feature file in TestRunner.java class and run with TestNG.
- To Execute on Maven   ```mvn clean verify```
  To Run on specified environment with Assertion from env folder File.
  ```mvn clean verify```
  User can specify browser to run TC else default chrome will be chosen for TC automation.
  -Run script by right click on feature file.  