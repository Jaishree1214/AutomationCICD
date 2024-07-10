
@tag
Feature: Purchase an order from ecommerce website
  I want to use this template for my feature file

	Background:
	Given I landed on Ecommerce page

  @tag2
  Scenario Outline:  Positive test of submitting order
    Given Logged in with username <email> and password <password>
    When I add product <productname> to cart
    And  Checkout <productname> and submit order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmation page

    Examples: 
      | email  											 | password 					| productname		|
      | jaishreevari1497@gmail.com   | Jaishree@123   		| ZARA COAT 3   |
      | jaishree@gmail.com   				 | Jaishree@123   		| ZARA COAT 3   |
 
