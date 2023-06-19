package Stepdefinition;

import io.cucumber.java.en.*;
import junit.framework.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    public static WebDriver driver;
    public static double  actual_price;
   // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    @Given("User is on the home page")
    public void userIsOnTheHomePage() throws InterruptedException
    {
        driver = new ChromeDriver();
        driver.navigate().to("https://www.mailtravel.co.uk/");
        driver.manage().window().maximize();
        // Get the Page Title -  Home Page | Mail Travel

        String exp_pagetitle = driver.getTitle();
        String Actual_title = "Home Page | Mail Travel";
        org.junit.Assert.assertEquals(exp_pagetitle,Actual_title);

        // Accept Cookies in the Home Page

        Thread.sleep(2000);
     /*  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'COMPOSE')]")));
        wait.until(ExpectedConditions.elementToBeSelected(By.xpath("//div[contains(text(),'COMPOSE')]")));*/
        WebElement Cookie_Close = driver.findElement(By.id("onetrust-accept-btn-handler"));

        try {
            if ((Cookie_Close).isDisplayed()) {
                Cookie_Close.click();

            } else
                System.out.println("Element is present but not displayed");
        } catch (NoSuchElementException e) {
            System.out.println("Element is not present, hence not displayed as well");
        }
    }

    @When("User searches for {string}")
    public void userSearchesFor(String arg0) throws InterruptedException
    {
        // search for india in search bar

        WebElement Search_text = driver.findElement(By.id("searchtext_freetext_search_form"));
        Search_text.sendKeys("India");
        Thread.sleep(2000);
        Search_text.sendKeys(Keys.ARROW_DOWN);
        Search_text.sendKeys(Keys.ENTER);

        // Click on the Book online button

        WebElement book_online1 = driver.findElement(By.id("buttons-ctr"));
        book_online1.click();
        // Scroll to the bottom of the page and select the next available date.

        WebElement datepicker = driver.findElement(By.id("calendar-cont"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", datepicker);
        Thread.sleep(500);

        //make note of Departure Airport

        WebElement departure_airport = driver.findElement(By.xpath("//*[@class='nbf_tpl_pms_departure_select_title']/following::span[1]"));
        String depart_airport = departure_airport.getText();
        System.out.println(depart_airport);

    }

    @Then("User should be able to select a date")
    public void userShouldBeAbleToSelectADate() throws InterruptedException
    {
          // Select 2 adults

        Select adults = new Select(driver.findElement(By.name("numAdults")));
        adults.selectByValue("2");

          // Select the first available date

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,-150)", "");
        WebElement next = driver.findElement(By.xpath("//*[@class='nbf_tpl_pms_calendar_next nbf_tpl_pms_calendar_update']"));
        next.click();
        //WebElement availableDate = driver.findElement((By.id("calendar-cont")));
        WebElement availableDate = driver.findElement(By.className("nbf_tpl_pms_calendar_inner"));
        Thread.sleep(2000);
        availableDate.click();
        WebElement date = driver.findElement(By.xpath("//*[@type='radio']"));
        date.click();
        String ticket_price = date.getAttribute("data-price");
        System.out.println(ticket_price);
        double Dticket_price = Double.parseDouble(ticket_price);
        System.out.println("Price of one ticket : " + Dticket_price);
        actual_price = Dticket_price * 2;
        System.out.println("Price of two tickets : " + actual_price);

    }

    @And("User should see the correct ticket price")
    public void userShouldSeeTheCorrectTicketPrice() throws InterruptedException
    {
        // Validate the price calculation
        WebElement exp_price = driver.findElement(By.xpath("//*[@id='tour-price']/child::span"));
        String expected_result = exp_price.getAttribute("data-amt");
        double expected_price = Double.parseDouble(expected_result);
        System.out.println("Displayed price : " + expected_price);
        HomePage hp=new HomePage();
        double ap= hp.actual_price;
        System.out.println("Displayed price : " + ap);
        Assert.assertEquals(expected_price,ap);
        Thread.sleep(2000);
        WebElement book_online = driver.findElement(By.id("book-button"));
        book_online.click();
    }

    @And("User should be able to proceed to booking")
    public void userShouldBeAbleToProceedToBooking() throws InterruptedException
    {
        // Click on the Departure

        Thread.sleep(8000);
        WebElement depart_view = driver.findElement(By.className("nbf_tpl_pms_bf_panel__title__links"));
        depart_view.click();

        //  In Accommodation select the number as 1

        Thread.sleep(2000);
        Select Acc_select = new Select(driver.findElement(By.id("room-1673582-numselect")));
        Acc_select.selectByValue("1");

        //  Click on select your room

        WebElement select_room = driver.findElement(By.xpath("//*[text()='Select your room and continue']"));
        select_room.click();

    }


    @And("User should be able to register and click on the continue button")
    public void userShouldBeAbleToRegisterAndClickOnTheContinueButton() throws InterruptedException
    {

        //  Fill the register details

        Thread.sleep(8000);
        Select select_title = new Select(driver.findElement(By.id("pax-a-title-1")));
        select_title.selectByValue("Mrs");
        WebElement first_name = driver.findElement(By.id("pax-a-first-1"));
        first_name.sendKeys("ramya");
        WebElement last_name = driver.findElement(By.id("pax-a-last-1"));
        last_name.sendKeys("arjun");
        Select sel_date = new Select(driver.findElement(By.id("pax-a-dobd-1")));
        sel_date.selectByValue("6");
        Select sel_month = new Select(driver.findElement(By.id("pax-a-dobm-1")));
        sel_month.selectByValue("7");
        Select sel_year = new Select(driver.findElement(By.id("pax-a-doby-1")));
        sel_year.selectByValue("2005");
        Select select_title1 = new Select(driver.findElement(By.id("pax-a-title-2")));
        select_title1.selectByValue("Mrs");
        WebElement first_name1 = driver.findElement(By.id("pax-a-first-2"));
        first_name1.sendKeys("arunesh");
        WebElement last_name1 = driver.findElement(By.id("pax-a-last-2"));
        last_name1.sendKeys("ramesh");
        Select sel_date1 = new Select(driver.findElement(By.id("pax-a-dobd-2")));
        sel_date1.selectByValue("8");
        Select sel_month1 = new Select(driver.findElement(By.id("pax-a-dobm-2")));
        sel_month1.selectByValue("9");
        Select sel_year1 = new Select(driver.findElement(By.id("pax-a-doby-2")));
        sel_year1.selectByValue("2002");
        WebElement mob = driver.findElement(By.id("contact-mobile"));
        mob.sendKeys("0753789998");
        WebElement cont_email = driver.findElement(By.id("contact-email"));
        cont_email.sendKeys("ramya@gmail.com");
        WebElement address1 = driver.findElement(By.id("contact-address1"));
        address1.sendKeys("address1");
        WebElement address2 = driver.findElement((By.id("contact-address2")));
        address2.sendKeys("address2");
        WebElement cont_city = driver.findElement(By.id("contact-city"));
        cont_city.sendKeys("city");
        WebElement postcode = driver.findElement(By.id("contact-postcode"));
        postcode.sendKeys("postcode");
        Select sel_country = new Select(driver.findElement(By.id("contact-country")));
        sel_country.selectByVisibleText("United Kingdom");
        Select sel_hearabout = new Select(driver.findElement(By.id("contact-hearabout")));
        sel_hearabout.selectByVisibleText("Email");
        WebElement click_continue = driver.findElement(By.xpath("//*[@id=\"paxform-select\"]/button"));
        click_continue.click();

    }

    @And("User should see the booking page")
    public void userShouldSeeTheBookingPage() throws InterruptedException
    {

        // Verify the label text matches the expected value

        Thread.sleep(8000);
        WebElement lbl_confirm = driver.findElement(By.xpath("//div[@class='nbf_stage_current']/following::span[1]"));
        String confirm_label = lbl_confirm.getText();
        String lbl_expected = "Confirm Details + Book";
        org.junit.Assert.assertEquals(lbl_expected,confirm_label);
        System.out.println(confirm_label);

        //  Verify the book now button is enabled

        WebElement book_now = driver.findElement(By.id("nbf_booknow"));
        if(book_now.isEnabled())
        {
            System.out.println("Book now is enabled");
        }else
        {
            System.out.println("Book now is disabled");
        }
        driver.quit();

    }
}

