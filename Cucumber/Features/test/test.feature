Feature: Adding items to lists

@selenium
Scenario: Add button is present
Given I am on the EclipseIT Ibiza page
When I press button add
Then An item is added to the list
