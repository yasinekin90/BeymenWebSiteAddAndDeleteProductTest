package pages;



import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.Driver;

import java.util.List;


public class BeymenPage  {

    public BeymenPage(){


        PageFactory.initElements(Driver.getDriver(),this);

    }

    @FindBy(xpath = "//input[@placeholder=\"Ürün, Marka Arayın\"]")
    public WebElement aramaKutusu;

    @FindBy(xpath = "//span[text()=\" Siyah Kesik Yaka Klasik İpek Gömlek\"]")
    public WebElement rastgeleUrun;

    @FindBy(xpath = "//*[@id=\"onetrust-accept-btn-handler\"]")
    public WebElement cerezleriKabul;

    @FindBy(className = "o-productDetail__description")
    public WebElement urunBilgisi1;

    @FindBy(id = "priceNew")
    public WebElement urunFiyati;

    @FindBy(className = "m-productDescription__infoDesc")
    public List<WebElement> urunBilgisi2;

    @FindBy(className = "m-variation__item")
    public WebElement bedenSec;

    @FindBy(id = "addBasket")
    public WebElement sepeteEkle;

    @FindBy(xpath = "//a[@title=\"Sepetim\"]")
    public WebElement sepeteGit;

    @FindBy(className = "m-productPrice__salePrice")
    public WebElement sepetUrunFiyati;

    @FindBy(xpath = "//select[@id=\"quantitySelect0-key-0\"]")
    public WebElement urunAdedi;

    @FindBy(id = "removeCartItemBtn0-key-0")
    public WebElement urunSil;

    @FindBy(className = "m-empty__messageTitle")
    public WebElement sepetBos;






}
