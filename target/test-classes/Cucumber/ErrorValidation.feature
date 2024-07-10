
@tag
Feature: Error Validation
  I want to use this template for my feature file
	
	
  @Regression
  Scenario: Evaluvating the login credentials
   Given I landed on Ecommerce page
    And Logged in with username <email> and password <password>
    Then "Incorrect email or password." error message is displayed
 

    Examples: 
      | email  						 | password 			 | status  |
      | jaishree@gmail.com | Jaishree123 		 | success |

