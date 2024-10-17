import time

import pytest
from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC, wait
from webdriver_manager.chrome import ChromeDriverManager

@pytest.fixture(scope="module")
def driver():
    # Initialize the ChromeDriver service
    service = Service(ChromeDriverManager().install())

    # Initialize the ChromeDriver with the service
    driver = webdriver.Chrome(service=service)
    yield driver
    # Teardown: quit the WebDriver
    driver.quit()

def test_makemytrips(driver):
    driver.get("https://www.makemytrip.com/")
    driver.maximize_window()
    wait = WebDriverWait(driver, 10)

    # Perform actions in the main content
    element_in_main_content = WebDriverWait(driver, 10).until(
        EC.element_to_be_clickable((By.CSS_SELECTOR, "span[class='commonModal__close']"))
    )
    element_in_main_content.click()

    # Find the 'Flights' option and click on it
    flights_button = wait.until(EC.visibility_of_element_located((By.XPATH, "//span[text()='Flights']")))
    flights_button.click()

    # Find the 'ROUND TRIP' option and click on it (Please verify the ID in the HTML)
    round_trip_button = wait.until(EC.visibility_of_element_located((By.XPATH, "//li[text()='Round Trip']")))
    round_trip_button.click()

    # Find the 'FROM' input field, enter 'HYD', and select the suggestion
    from_input = wait.until(EC.visibility_of_element_located((By.XPATH, "//span[text()='From']")))
    from_input.click()
    from_city = wait.until(EC.visibility_of_element_located((By.XPATH, "//input[@placeholder='From']")))
    from_city.send_keys("HYD")
    from_select_city = wait.until(EC.element_to_be_clickable((By.XPATH, "//p[text()='Hyderabad, India']")))
    from_select_city.click()

    # Find the 'TO' input field, enter 'MAA', and select the suggestion
    to_input = wait.until(EC.visibility_of_element_located((By.XPATH, "//span[text()='To']")))
    to_input.click()
    to_city = wait.until(EC.visibility_of_element_located((By.XPATH, "//input[@placeholder='To']")))
    to_city.send_keys("MAA")
    to_select_city = wait.until(EC.element_to_be_clickable((By.XPATH, "//p[text()='Chennai, India']")))
    to_select_city.click()

    # Select departure date
    departure_date_input = wait.until(EC.element_to_be_clickable((By.XPATH, "//div[@aria-label='Wed Oct 09 2024']")))
    departure_date_input.click()

    # Select return date
    return_date_input = wait.until(EC.element_to_be_clickable((By.XPATH, "//div[@aria-label='Wed Oct 23 2024']")))
    return_date_input.click()

    # Find and click the 'Search' button
    search_button = wait.until(EC.element_to_be_clickable((By.XPATH, "//a[text()='Search']")))
    search_button.click()

    # Check the page title
    assert "MakeMyTrip" in driver.title
    # Get the page title
    actual_title = driver.title
    print(driver.title)

    if actual_title == driver.title:
        print("Search page is displayed as expected")
    else:
        print("Search page is not displayed as expected")
