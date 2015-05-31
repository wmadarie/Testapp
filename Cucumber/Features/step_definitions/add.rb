require 'rubygems'
require 'selenium-webdriver'

def setup
  @headless = Headless.new
  @headless.start
  @driver = Selenium::WebDriver.for :firefox
end

def teardown
  @driver.quit
  @headless.destroy
end

def run
  setup
  yield
  teardown
end


Given(/^I am on the EclipseIT Ibiza page$/) do
  driver.navigate.to "http://eclvm04.cloudapp.net:8080/app2/example"
end

When(/^I press button add$/) do

element = driver.find_element(:name, 'deleteitem')
element.send_keys "TESTTESTTEST!"
element.submit

end

Then(/^An item is added to the list$/) do
  driver.find_element(:value, 'Delete item by ID').click
  driver.quit
end





