Framework used:
TestnG with Maven as built tool.

Dependencies included in pom.xml file:
Selenium Java
Testng
Apache poi

Plugins included in pom.xml file:
compiler plugin
surefire plugin

Execution of Test:
To execute the test, right click on pom.xml file > run as > maven test.
Also, another way for execution could be right click on testng.xml file> run as > test suite

To check report :
Open test output folder > check and copy path of index.html file > paste the path in browser and view the html report.

Results:
Testcase 1: formSubmission is passed
Testcase 2 : searchGovernValidation fails due to assertion error.