@MobileAppTest
Feature: Shop for products in Sauce Labs mobile App

  Background: User login to the sauce lab mobile app
    Given user installs the Sauce Labs app
    When user login to the app with valid credentials
    Then user is navigated to homepage
    Given user clicks on 'Menu' icon

  @AddOrder @SauceLabAppTC
  Scenario: User places an order for a product on Sauce Labs app
    Given user clicks on 'ALL ITEMS' in the menu
    Given user clicks on Filter icon
    When user clicks on 'Name (A to Z)' option to apply filter
    Given user clicks on 'Sauce Labs Bike Light' product
    When user scroll 'down' with scroll ratio 0.5
    When user clicks on 'ADD TO CART' button
    When user clicks on 'Cart' icon
    Then user should be displayed with Cart page
    Then user should be displayed with 'Sauce Labs Bike Light' product in the cart
    Then user should be displayed with price $9.99 in the cart
    Given user clicks on 'CHECKOUT' button
    Then user should be displayed with Checkout page
    When user enter required details on the Checkout page
      | FirstName | Suraj    |
      | LastName  | Salunkhe |
      | ZipCode   | 411050   |
    When user clicks on 'CONTINUE' button
    When user scroll 'down' with scroll ratio 0.5
    When user clicks on 'FINISH' button
    Then user should be able to place order successfully

  @WebView @SauceLabAppTC
  Scenario: User navigate to Web view and adds a product to cart in amazon site
    Given user clicks on 'WEBVIEW' in the menu
    Then user should be displayed with WebView page
    Given user enter url in the url section
    When user clicks on 'GO TO SITE' button
    When user change context to 'WEBVIEW'
    Then user should be displayed with Amazon website homepage
    Given user search for 'Samsung Mobile M53' at Amazon website
    When user scroll 'down' in web view page
    And user select the second item from search results
    Then user should be displayed with item selected
    When user clicks on add to cart button on amazon web view page
    Then product should be added to cart successfully