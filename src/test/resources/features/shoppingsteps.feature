Feature: Shopping Steps

  Scenario: Customer adds product and goes to cart.
    Given user on the Hepsiburada web page
    When user selects "<elektronik>" in the menu
    And user selects "<Bilgisayar>" and "<Notebook>" in the child menu
    And user select brand "Apple"
    And user select bestsellers
    And user adds the 2nd product in the product list to the cart
    And user goes to the cart and verifies the product
    And user clicks on the complete shopping button
    Then user should see redirected to the login page