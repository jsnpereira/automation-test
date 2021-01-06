# automation-test

Create the automation test to make challenge for CESAR company. Our test will be execute the maven tool, we should be install the maven before it execute our tests.

##How install chromedrive in project
Please, make download the chromedriver in site and put it into project, you can see pic below

![chromedriver](/images/chromedrive_project.png)

##How execute the our test
mvn test -PCucumber

##Instal the depedencies
mvn clean install -DskipTests=true

## Leave comment three steps
I leave comments three comments in steps because it broke the tests that page is loading too long time until time expired. see pic below

![chromedriver](/images/Info_section_loading.png)


You can see the loading in information
