# automation-test

Create the automation test to make challenge for CESAR company. Our test will be execute the maven tool, we should be install the maven before it execute our tests.

## How install chromedrive in project
Please, make download the chromedriver in site and put it into project, you can see pic below

![chromedriver](/images/chromedrive_project.png)

## How execute the our test
mvn test -PCucumber

## Instal the depedencies
mvn clean install -DskipTests=true

## Dependencies

* Java 8
* selenium-java: 3.141.59
* testng: 6.14.2
* cucumber-testng: 6.6.0
* cucumber-java: 6.6.0
* selenium-chrome-driver: 3.141.59

# How I tested the tests on my machine

* macOS Catalina
* Intellij IDEA 2020.3
* Maven 3.6.3
* Chrome browser 87.0.4280.88


## Note important
I leave three comments in steps because it broke the tests that page is loading too long time until time expired. see pic below

![chromedriver](/images/Info_section_loading.png)

You can see the loading in Info section, it means we not able to continue until show print in the .

The steps are: 
 * Clicar o localização
 * Clicar o link para visualizar todas as comodidades
 * Imprimir as Comodidades do quarto
 
 If you wish to see this steps running, uncommented and run the test.
