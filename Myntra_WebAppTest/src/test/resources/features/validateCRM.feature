Feature: Login to Myntra

  Scenario: Navigate to Myntra.com in Chrome and perform login
    Given launch browser 'firefox'
    When user navigates to the URL 'https://www.myntra.com'
    Then the user Navigates to the Login Page
    And Login with Username "kashishpasrija82@gmail.com" and Password "Kas@0000"
    Then User "kashishpasrija82@gmail.com" gets Logged Into Myntra Account Successfully.
    