# Selenium imports
from selenium import webdriver
from selenium.webdriver.common.keys import Keys

def login_firefox():
    # Set up a new firefox profile
    usr = raw_input('Enter Facebook User Name: ')
    pwd = raw_input('Enter Facebook Password(This is not stored anywhere but memory): ')

    firefox_profile = webdriver.FirefoxProfile()
    driver = webdriver.Firefox(firefox_profile=firefox_profile)

    # navigate to Facebook
    driver.get("http://www.facebook.com")
    assert "Facebook" in driver.title
    elem = driver.find_element_by_name("email")
    elem.send_keys(usr)
    elem = driver.find_element_by_name("pass")
    elem.send_keys(pwd)
    elem.send_keys(Keys.RETURN)
  
    while True:
        try:
            loginSignifier = driver.find_element_by_class_name("innerWrap")
            break
        except:
            continue

    # get the cookies and quit
    cookies = driver.get_cookies()
    driver.quit()

    # desired format for requests library
    formatted= {}
    for cook in cookies:
        formatted[cook['name']] = cook['value']

    return formatted
