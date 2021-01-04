package test.Steps;


import com.automation.framework.commons.LocatorType;
import com.automation.framework.exception.NotSelectLocatorType;
import com.automation.framework.models.Hotel;
import com.automation.framework.selenium.base.DriveHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebElement;

import java.util.List;

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
    public void clicarNoBotãoPesquisa() throws NotSelectLocatorType, InterruptedException {
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
        String locator = "("+itemHotelPositionLocator
                .replace("XXX", String.valueOf(position))
                + "//*[contains(@class,'accommodation-list__header')]//h3)[1]";
        String value = DriveHelper.getText(locator, LocatorType.XPATH);
        hotel.setCompanyOffer(value);
    }

    @Then("Mostrar informação do hotel")
    public void mostrarInformaçãoDoHotel() throws InterruptedException {
        System.out.println("======================================");
        System.out.println("Dado do Hotel:");
        System.out.println("Nome: "+hotel.getName()+" Estrelas: "+hotel.getStar());
        System.out.println("Oferta da empresa: "+hotel.getCompanyOffer()+" Preço: "+hotel.getPrice());
        System.out.println("======================================");
    }

    @Given("Selecionar {string} item")
    public void selecionarItem(String position) {
        this.position = Integer.valueOf(position);
    }

    @And("Clicar o link do hotel")
    public void clicarOLinkDoHotel() throws NotSelectLocatorType, InterruptedException {
        String locator1 = itemHotelPositionLocator
                .replace("XXX", String.valueOf(position))
                + "//span[contains(@class,'item-link')]";

        String locator2 = itemHotelPositionLocator
                .replace("XXX", String.valueOf(position))
             //  + "//div[contains(@class,'slideouts-navigation')]";
           + "//div[contains(@class,'dummy-loader info-loader')]";

       DriveHelper.click(locator1, LocatorType.XPATH);
       DriveHelper.waitElementPresented(locator2,LocatorType.XPATH);
       DriveHelper.waitElementNotDisplayed(locator2, LocatorType.XPATH);
    }

    @And("Clicar o botão para visualizar todas as comodidades")
    public void clicarOBotãoParaVisualizarTodasAsComodidades() throws NotSelectLocatorType {
        String locator = itemHotelPositionLocator
                .replace("XXX", String.valueOf(position))
                + "//*[contains(@class,'slideouts__amenities')]" +
                "//button[contains(@class,'btn--icon-trailing slideouts__slideoutBtn')]";

        DriveHelper.scrollToViewElement(locator, LocatorType.XPATH);

    }
}
