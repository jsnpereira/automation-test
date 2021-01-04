package test.Steps;

import com.automation.framework.commons.LocatorType;
import com.automation.framework.exception.NotSelectLocatorType;
import com.automation.framework.models.Topic;
import com.automation.framework.selenium.base.DriveHelper;
import com.automation.framework.utils.RegexUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.*;

public class Web01Steps {

    @And("Clicar Demo link")
    public void clicarDemoLink() throws NotSelectLocatorType {
        DriveHelper.click(".//a[text()='Demo']", LocatorType.XPATH);
    }

    @And("Tab está aberta")
    public void tabIsOpened() {
        DriveHelper.switchTabTo(1);
        DriveHelper.loading();
    }

    @And("Fazer scroll até o final da página")
    public void fazerScrollAtéOFinalDaPágina() throws InterruptedException {
        DriveHelper.scrollToFindText("There are no more latest topics.");
    }

    @Then("Mostrar a lista do topicos fechados")
    public void mostrarAListaDoTopicosFechados() throws NotSelectLocatorType {
        int count = 1;
        List<WebElement> elements = DriveHelper.getElementList(".//span[contains(@title, 'This topic is closed')]/ancestor-or-self::td", LocatorType.XPATH);
        System.out.println("===============================================");
        System.out.println("A descrição de todos tópicos fechados:");
        for (WebElement element : elements) {
            System.out.println("Tópico: "+count);
            System.out.println("    Nome do Tópico: '"+element.findElement(By.xpath(".//span[@class=\"link-top-line\"]/a")).getText()+"'");
            System.out.println("    Categoria: '"+element.findElement(By.xpath(".//div[@class='link-bottom-line']")).getText()+"'");
            System.out.println("    Posts: '"+element.findElement(By.xpath(".//following-sibling::td[contains(@class,'num posts')]")).getText()+"'");
            System.out.println("    Views: '"+element.findElement(By.xpath(".//following-sibling::td[contains(@class,'num views')]")).getText()+"'");
            System.out.println("    Data de criação: '"+element.findElement(By.xpath(".//following-sibling::td[contains(@class,'num age')]")).getText()+"'");
            count++;
        }
        System.out.println("===============================================");
    }

    @And("Mostrar quantidades tópicos em cada categoria")
    public void mostrarQuantidadesTópicosEmCadaCategoria() throws NotSelectLocatorType {
        Hashtable<String, Integer> statics = new Hashtable<>();
        List<WebElement> elements = DriveHelper.getElementList(".//span[@class='category-name']", LocatorType.XPATH);

        for (WebElement element : elements) {
            String category = element.getText();
            if (statics.containsKey(category)) {
                Integer count = statics.get(category);
                count++;
                statics.put(category, count);
            } else {
                int count = 1;
                statics.put(category, count);
            }
        }

       int total = DriveHelper.getElementList(".//table[contains(@class,'topic-list')]/tbody/tr",LocatorType.XPATH).size();
       int noCategory = total - elements.size();
        Iterator iterator = statics.entrySet().iterator();
        System.out.println("===============================================");
        System.out.println("Estática das categorias:");
        while (iterator.hasNext()){
            Map.Entry categoryCount = (Map.Entry) iterator.next();
            System.out.println("    "+categoryCount.getKey() +": "+categoryCount.getValue());
        }
        System.out.println( "   Não tem categoria: "+noCategory);
        System.out.println("===============================================");
    }

    @And("Mostrar qual titulo do tópico mais visualizados")
    public void mostrarQualTituloDoTópicoMaisVisualizados() throws NotSelectLocatorType {
        String patterMatcherViewsNumber = "this topic has been viewed (.*) times";
        String item = ".//table[contains(@class,'topic-list')]/tbody/tr";
        String views = "./td[contains(@class,'views')]/span";
        String topicLocator = "./td[contains(@class,'main-link')]/span";
        List<WebElement> elements = DriveHelper.getElementList(item,LocatorType.XPATH);

        Topic topic = new Topic();
        topic.setViews(0);

        for (WebElement element: elements) {
            String topicValue = element.findElement(By.xpath(topicLocator)).getText();
            String viewsValue = element.findElement(By.xpath(views)).getAttribute("title");
            int viewsNumber = RegexUtil.matchNumber(patterMatcherViewsNumber,viewsValue);

            if (viewsNumber > topic.getViews()){
                topic.setTitle(topicValue);
                topic.setViews(viewsNumber);
            }
        }

        System.out.println("===============================================");
        System.out.println("Tópico mais visualizado:");
        System.out.println("Topico: '"+topic.getTitle()+" - Visualizado: "+topic.getViews());
        System.out.println("===============================================");
    }

}
