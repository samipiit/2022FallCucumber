Feature: User Authentication
  Users should be able to authenticate to the ParaBank application using valid credentials

  Background:
    Given user navigates to ParaBank application

  @Smoke
  Scenario Outline: User enters valid username and valid password
    Users should be able to authenticate to the ParaBank application using valid username and valid password

    When user enters "<username>" in username input field
    And user enters "<password>" in password input field
    And user clicks login button
    Then user is navigated to Account Overview page

    Examples:
    | username     | password        |
    | sami@piit.us | PeopleNTech123! |

  @Smoke
  Scenario Outline: User enters invalid credentials
    Users should not be able to authenticate to the ParaBank application using invalid credentials

    When user enters "<username>" in username input field
    And user enters "<password>" in password input field
    And user clicks login button
    Then error message should be visible

    Examples:
    | username    | password        |
    | sami@piit   | asdfgads        |
    | sami@piit   | PeopleNTech123! |
    | sami@piit.us| asdg8&%39!      |