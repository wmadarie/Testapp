require 'selenium-webdriver'
driver = Selenium::WebDriver.for :firefox






Given(/^I am on the EclipseIT Ibiza page$/) do
  driver.navigate.to 'http://eclvm04.cloudapp.net:8080/app2/example'
end

When(/^I press button add$/) do
  input = wait.until {
      element = browser.find_element(:name, "deleteitem")
      element if element.displayed?
  }
  input.send_keys("TestTestTest")
end

Then(/^An item is added to the list$/) do
  driver.find_element(:value, 'Delete item by ID').click
  driver.quit
end