package id.ac.tazkia.dosen.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class SuratTugasFormPage {

    @FindBy(name = "noSurat")
    private WebElement inputNoSurat;

    @FindBy(name = "tanggalMulai")
    private WebElement inputTanggalMulai;

    @FindBy(name = "tanggalSelesai")
    private WebElement inputTanggalSelesai;

    @FindBy(name = "penerima")
    private WebElement inputPenerima;

    @FindBy(name = "jenisSurat")
    private WebElement inputJenisSurat;

    public void submitInvalid(){
        inputNoSurat.submit();
    }

    public void submitValid(String noSurat,
                            String tanggalMulai,
                            String tanggalSelesai,
                            String penerima,
                            String jenisSurat){

        inputNoSurat.sendKeys(noSurat);
        inputTanggalMulai.sendKeys(tanggalMulai);
        inputTanggalSelesai.sendKeys(tanggalSelesai);
        new Select(inputPenerima).selectByVisibleText(penerima);
        new Select(inputJenisSurat).selectByVisibleText(jenisSurat);
        inputNoSurat.submit();
    }
}
