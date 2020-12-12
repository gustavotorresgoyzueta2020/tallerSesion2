import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MobileWhenDoTest {
    private AppiumDriver driver;
    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("deviceName","RedmiJC");
        capabilities.setCapability("platformVersion","10");
        capabilities.setCapability("appPackage","com.vrproductiveapps.whendo");
        capabilities.setCapability("appActivity",".ui.HomeActivity");
        capabilities.setCapability("platformName","Android");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);

        // implicit wait  (tiempo para todos los controles)
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    @After
    public void cleanUp(){
        driver.quit();
    }

    @Test
    public void createNoteBook() throws InterruptedException {

        Thread.sleep(3000);
        // seleccionar Menu
        driver.findElement(By.xpath("//android.widget.ImageButton[@index='0']")).click();
        Thread.sleep(1000);

        //Crear un nuevo cuaderno
        driver.findElement(By.xpath("//android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/androidx.appcompat.widget.LinearLayoutCompat/android.widget.CheckedTextView[@text='Administrar Cuadernos']")).click();

        //Seleccionar el botón +
//        for (int i = 1; i < 5; i++) {
//            String noteBook = "Cuaderno_Taller " + i;
        String cuaderno="Cuaderno_Taller2";

        driver.findElement(By.id("com.vrproductiveapps.whendo:id/addNotebookItem")).click();
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/notebookTitle")).sendKeys(cuaderno);
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/saveItem")).click();
        driver.findElement(By.xpath("//android.widget.ListView/android.widget.LinearLayout[2]/android.widget.RadioButton")).click();
//        }

        // Crear Tareas
        for (int i = 1; i < 20; i++) {
            String titleText = "T2_Tarea_Numero " + i;
            String notetext = "T2_Nota " + i;
            driver.findElement(By.id("com.vrproductiveapps.whendo:id/fab")).click();
            driver.findElement(By.id("com.vrproductiveapps.whendo:id/noteTextTitle")).sendKeys(titleText);
            driver.findElement(By.id("com.vrproductiveapps.whendo:id/noteTextNotes")).sendKeys(notetext);
            driver.findElement(By.id("com.vrproductiveapps.whendo:id/saveItem")).click();
            Thread.sleep(500);
        }


        // seleccionar Menu
        driver.findElement(By.xpath("//android.widget.ImageButton[@index='0']")).click();
        Thread.sleep(1000);

        //Crear un nuevo cuaderno
        driver.findElement(By.xpath("//android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/androidx.appcompat.widget.LinearLayoutCompat/android.widget.CheckedTextView[@text='Administrar Cuadernos']")).click();

       String name = "T2_Tarea_Numero 15";
        WebDriverWait explicit = new WebDriverWait(driver, 10);
        explicit.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='" + cuaderno + "']")));
        explicit.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[@text='" + cuaderno + "']")));

        driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"Navegar hacia arriba\"]")).click();


        TouchAction action = new TouchAction(driver);
        WebElement start = driver.findElement(By.xpath("(//android.widget.ImageButton[@content-desc=\"Marcar como Hecha\"])[8]"));
        WebElement end = driver.findElement(By.xpath("(//android.widget.ImageButton[@content-desc=\"Marcar como Hecha\"])[1]"));

        int startX = start.getLocation().getX();
        int startY = start.getLocation().getY();

        int endX = end.getLocation().getX();
        int endY = end.getLocation().getY();

        action.press(PointOption.point(new Point(startX, startY)))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(new Point(endX, endY)))
                .release().perform();


//        driver.findElement(By.id("")).click();
//        driver.findElement(By.id("")).click();
//        driver.findElement(By.id("")).click();
//        driver.findElement(By.id("")).click();
//        driver.findElement(By.xpath("")).click();
//        driver.findElement(By.xpath("")).click();


//        String expected = "14";
//        String obtained = driver.findElement(By.id("com.sec.android.app.popupcalculator:id/calc_edt_formula")).getText();
//        Assert.assertEquals("ERROR la Suma es incorrecta ! : ", expected, obtained);
    }
}