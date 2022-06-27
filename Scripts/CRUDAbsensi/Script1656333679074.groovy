import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.ExcelData as ExcelData
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.By as By
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.WebElement as Keys

WebUI.callTestCase(findTestCase('Signin'), [('login_url') : 'http://localhost/cicool/administrator/login', ('valid_uname') : 'mnaauval@gmail.com'
        , ('valid_passwd') : 'admin123', ('invalid_uname') : 'kaeka@gmail.com', ('invalid_passwd') : 'admin567'], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('User Profile/a_CRUD Builder'))

WebUI.click(findTestObject('Crud List/a_see_absensi'))

ExcelData absensi = findTestData('Absensi')

for (def index : (0..absensi.getRowNumbers() - 6)) {
    WebUI.click(findTestObject('Absensi List/a_Add Absensi New'))

    WebUI.setText(findTestObject('Absensi New/input__username'), absensi.internallyGetValue('uname', index))

    WebUI.setText(findTestObject('Absensi New/input__email'), absensi.internallyGetValue('email', index))

    WebUI.setText(findTestObject('Absensi New/input__location'), absensi.internallyGetValue('location', index))

    WebUI.click(findTestObject('Absensi New/a_Save and go to list'))
	
	WebDriver driver = DriverFactory.getWebDriver()
	
	List<WebElement> lstUser = driver.findElements(By.xpath("//*[(text() = '" + absensi.internallyGetValue('uname', index) + "')]"))
	
	printf("%s %s %s \n", absensi.internallyGetValue('uname', index), absensi.internallyGetValue('email', index), absensi.internallyGetValue('location', index))
	
	String nama = lstUser.get(0).text
	
	WebUI.verifyEqual(nama, absensi.internallyGetValue('uname', index))
}

WebUI.closeBrowser()

