package test.Steps;


import com.automation.framework.commons.LocatorType;
import com.automation.framework.exception.NotSelectLocatorType;
import com.automation.framework.models.Hotel;
import com.automation.framework.selenium.base.DriveHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.*;

public class Web02Steps {
    private static int position;
    private String itemHotelPositionLocator = ".//ol[@id='js_itemlist']//li[XXX]";
    private Hotel hotel = new Hotel();

    @And("Digitar {string} no campo de pesquisa")
    public void digitarNoCampoDePesquisa(String value) throws NotSelectLocatorType, InterruptedException {
        DriveHelper.typeInput(
                ".//div[@class='dealform__query-wrapper']//input"
                , LocatorType.XPATH
                , value);
        DriveHelper.waitElementPresented("ssg-suggest", LocatorType.CLASS);
        Thread.sleep(2000);
    }

    @And("Clicar no botão pesquisa")
    public void clicarNoBotãoPesquisa() throws NotSelectLocatorType{
        DriveHelper.click(".//button[contains(@class,'search-button')]", LocatorType.XPATH);
    }

    @And("Clicar no icone cancelar as datas")
    public void clicarNoIconeCancelarAsDatas() throws NotSelectLocatorType {
        DriveHelper.waitElementPresented(".//li[contains(@class,'hotel-item')]", LocatorType.XPATH);
        DriveHelper.waitElementDisplayed("df_container_calendar", LocatorType.CLASS);
        DriveHelper.waitElementDisplayed(".//button[contains(@class,'df_overlay_close_wrap')]", LocatorType.XPATH);
        DriveHelper.click(".//button[contains(@class,'df_overlay_close_wrap')]", LocatorType.XPATH);
        DriveHelper.waitElementNotDisplayed("df_container_calendar", LocatorType.CLASS);
    }

    @And("Selecionar no quarto individual")
    public void selecionarNoQuartoIndividual() throws NotSelectLocatorType {
        DriveHelper.waitElementDisplayed(".//button[contains(@class,'button--guests')]", LocatorType.XPATH);
        DriveHelper.waitElementBeClicked(".//button[contains(@class,'button--guests')]", LocatorType.XPATH);
        DriveHelper.click(".//button[contains(@class,'button--guests')]",
                LocatorType.XPATH);
        DriveHelper.waitElementDisplayed(".//div[contains(@class,'df_overlay')]", LocatorType.XPATH);
        DriveHelper.click(".//div[@class='room-filters']//button[1]", LocatorType.XPATH);
        DriveHelper.click(".//button[contains(@class,'btn--apply-config')]", LocatorType.XPATH);
    }


    @And("Selecionar a opção Ordenar apenas por Distância")
    public void selecionarAOpçãoOrdenarApenasPorDistância() throws NotSelectLocatorType, InterruptedException {
        DriveHelper.selectByItem("mf-select-sortby", LocatorType.ID, "Somente distância");
        DriveHelper.waitElementPresented("//*[contains(@class,'item-list--loading')]", LocatorType.XPATH);
        DriveHelper.waitElementNotDisplayed("//*[contains(@class,'item-list--loading')]", LocatorType.XPATH);
    }

    @And("Imprimir o nome do hotel")
    public void imprimirONomeDoHotel() throws NotSelectLocatorType {
        String locator = itemHotelPositionLocator
                .replace("XXX", String.valueOf(position))
                + "//div[contains(@class,'item__name')]//h3/span";
        String text = DriveHelper.getText(locator, LocatorType.XPATH);
        hotel.setName(text);
    }

    @And("Imprimir a quantidade de estrela")
    public void imprimirAQuantidadeDeEstrela() throws NotSelectLocatorType {
        String locator = itemHotelPositionLocator
                .replace("XXX", String.valueOf(position))
                + "//div[@class='stars-wrp']//span";
        List<WebElement> stars = DriveHelper.getElementList(locator, LocatorType.XPATH);
        hotel.setStar(stars.size());
    }

    @And("Imprimir o valor do quarto")
    public void imprimirOValorDoQuarto() throws NotSelectLocatorType {
        String locator = itemHotelPositionLocator
                .replace("XXX", String.valueOf(position))
                + "//div[contains(@class,'accommodation-list__dealInfo')]" +
                "//div[contains(@class,'accommodation-list__prices')]";
        String value = DriveHelper.getText(locator, LocatorType.XPATH);
        hotel.setPrice(value);
    }

    @And("Imprimir o nome do site da oferta")
    public void imprimirONomeDoSiteDaOferta() throws NotSelectLocatorType {
        String locator = "(" + itemHotelPositionLocator
                .replace("XXX", String.valueOf(position))
                + "//*[contains(@class,'accommodation-list__header')]//h3)[1]";
        String value = DriveHelper.getText(locator, LocatorType.XPATH);
        hotel.setCompanyOffer(value);
    }

    @Then("Mostrar informação do hotel")
    public void mostrarInformaçãoDoHotel()  {
        System.out.println("======================================");
        System.out.println("Dado do Hotel:");
        System.out.println("Nome: " + hotel.getName() + " Estrelas: " + hotel.getStar());
        System.out.println("Oferta da empresa: " + hotel.getCompanyOffer() + " Preço: " + hotel.getPrice());
        printConvenienceList();
        System.out.println("======================================");
    }

    @Given("Selecionar {string} item")
    public void selecionarItem(String position) {
        this.position = Integer.valueOf(position);
    }

    @And("Clicar o link para visualizar todas as comodidades")
    public void clicarOLinkParaVisualizarTodasAsComodidades() throws NotSelectLocatorType {
        String locator = itemHotelPositionLocator
                .replace("XXX", String.valueOf(position))
                + "//div[@class='expand-amenities']";
        DriveHelper.scrollToViewElement(locator, LocatorType.XPATH);

        String locator2 = itemHotelPositionLocator
                .replace("XXX", String.valueOf(position))
                + "//*[@class='expaned']/div";
        DriveHelper.waitElementPresented(locator2, LocatorType.XPATH);
    }

    @And("Imprimir as Comodidades do quarto")
    public void imprimirAsComodidadesDoQuarto() throws NotSelectLocatorType {
        List<WebElement> elements = DriveHelper
                .getElementList(".//div[@class='sl-box__block clearfix']/div"
                        , LocatorType.XPATH);
        HashMap<String, List<String>> conveniences = new HashMap<>();

        for (WebElement element : elements) {
            String title = element.findElement(By.xpath(".//h5")).getText();

            List<WebElement> eConveniences = element.findElements(By.xpath(".//ul/li"));
            List<String> convenienceList = new ArrayList<>();
            for (WebElement convenience : eConveniences) {
                convenienceList.add(convenience.getText());
            }
            conveniences.put(title, convenienceList);
        }
        hotel.setConvenience(conveniences);
    }

    private void printConvenienceList() {
      if(hotel.getConvenience() != null) {
          System.out.println("Comodidades do quarto :");
          Iterator iterator = hotel.getConvenience().entrySet().iterator();

          while (iterator.hasNext()) {
              Map.Entry convenience = (Map.Entry) iterator.next();
              System.out.println("-" + convenience.getKey() + ":");
              List<String> list = (List<String>) convenience.getValue();
              for (String value : list) {
                  System.out.println("    *" + value);
              }
          }
      }
    }

    @And("Clicar o localização")
    public void clicarOLocalização() throws NotSelectLocatorType {
        String locator = itemHotelPositionLocator
                .replace("XXX", String.valueOf(position))
                + "//div[@class='item-link']";
        DriveHelper.click(locator,LocatorType.XPATH);

        String locator2 = itemHotelPositionLocator
                .replace("XXX", String.valueOf(position))
                + "//div[contains(@class,'dummy-loader info-loader')]";
        DriveHelper.waitElementPresented(locator2, LocatorType.XPATH);
        DriveHelper.waitElementNotDisplayed(locator2, LocatorType.XPATH);
    }
}
