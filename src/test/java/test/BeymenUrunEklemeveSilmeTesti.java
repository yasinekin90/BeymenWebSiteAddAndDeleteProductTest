package test;

import logger.Log;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import pages.BeymenPage;
import testlogger.TestResultLogger;
import utilities.ConfigurationReader;
import utilities.Driver;
import utilities.ReusableMethods;

import java.util.List;



@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(TestResultLogger.class)
public class BeymenUrunEklemeveSilmeTesti  {

       BeymenPage beymenPage=new BeymenPage();
      WebDriver driver=Driver.getDriver();
      Log log=new Log();
       @Test()
       @Order(1)
       public void ana_Sayfaya_Gidildigini_Dogrula(){

           driver.get(ConfigurationReader.getProperty("url"));
           log.info("Siteye gidildi");
           String expectedUrl="https://www.beymen.com/";
           String actualUrl=driver.getCurrentUrl();

           Assertions.assertTrue(expectedUrl.equals(actualUrl),"Ana sayfaya gidilemedi");
           log.info("Site dogrulamasi yapildi.");
           beymenPage.cerezleriKabul.click();


       }


    @Test
     @Order(2)
    public void arama_Kutusuna_Sort_Yaz_Ve_Sil() throws InterruptedException {


           String path="beymenUrun.xlsx";
        String ilkUrun = ReusableMethods.cellCall(path, "Sayfa1", 0, 0).getStringCellValue();


        beymenPage.aramaKutusu.sendKeys(ilkUrun);
        log.info("arama kutusuna sort yazildi");

        Thread.sleep(3000);
        beymenPage.aramaKutusu.sendKeys(Keys.LEFT_CONTROL+""+Keys.BACK_SPACE);
        log.info("arama kutusundan sort silindi");
       }

    @Test
    @Order(3)
    public void arama_Kutusuna_Gomlek_Yaz_Ve_Enter_Bas(){
        String path="beymenUrun.xlsx";
        String ikinciUrun = ReusableMethods.cellCall(path, "Sayfa1", 0, 1).getStringCellValue();
        beymenPage.aramaKutusu.sendKeys(ikinciUrun, Keys.ENTER);
        log.info("arama kutusuna gomlek yazildi ve entera basildi");
    }

    String oncekiUrunFiyati;
    @Test
    @Order(4)
    public void rastgele_Urun_Secip_Bilgisini_Al(){



        ReusableMethods.hover(beymenPage.rastgeleUrun);

       beymenPage.rastgeleUrun.click();
       log.info("Urune gidilip tiklanildi");
        oncekiUrunFiyati=beymenPage.urunFiyati.getText();
        String text = "Urun Adi : "+beymenPage.urunBilgisi1.getText();
        List<String> elementsText = ReusableMethods.getElementsText(beymenPage.urunBilgisi2);
        String text1 ="Urun Fiyati : "+ oncekiUrunFiyati;
          elementsText.add(0,text);
          elementsText.add(1,text1);
       ReusableMethods.writeToTxt(elementsText);
        log.info("Urun bilgileri txt olarak kaydedildi");
    }

    @Test
    @Order(5)
    public void secilen_Urunu_Sepete_Ekle() throws InterruptedException {
       beymenPage.bedenSec.click();
       beymenPage.sepeteEkle.click();
       Thread.sleep(5000);
       beymenPage.sepeteGit.click();
       log.info("Urun sepete eklendi ve sepete gidildi");
    }

    @Test
    @Order(6)
    public void urun_Fiyat_Karsilastir(){
      String sepettekiUrununFiyati=beymenPage.sepetUrunFiyati.getText();
      String urunSayfasiFiyati=oncekiUrunFiyati;
      Assertions.assertEquals(sepettekiUrununFiyati,urunSayfasiFiyati,"Fiyatlar Eşit değil");
      log.info("Sepetteki urun ile sayfadaki urunun fiyatlari karsilastirildi.");
    }

    @Test
    @Order(7)
    public void urun_Adedi_Artir_Ve_Dogrula(){
       driver.navigate().back();
       beymenPage.bedenSec.click();
       beymenPage.sepeteEkle.click();
       beymenPage.sepeteGit.click();
        Select select=new Select(beymenPage.urunAdedi);
        String urunAdedi = select.getFirstSelectedOption().getText();
        Assertions.assertEquals(urunAdedi,"2 adet","Urun adedi iki değildir.");
        log.info("Urun adedi artirildi ve 2 adet oldugu dogrulandi");
    }

    @Test
    @Order(8)
    public void urun_Sil_Ve_Sepetin_Bos_Oldugunu_Dogrula(){
      beymenPage.urunSil.click();
      Assertions.assertTrue(beymenPage.sepetBos.isDisplayed(),"Sepetin bos oldugu dogrulanamadi");
      log.info("sepetteki urunler silindi ve sepetin bos oldugu dogrulandi.");
    }

}
