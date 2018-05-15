@WebServiceTest @Auto @Negative @ReportAPI_V1
Feature: Check Report Rest End Point V1, Negative scenarios

  Scenario Outline: Access endpoints invalid month params
    Given Rest endpoint '/reports?month={month}' request value '<invalid-month>'
    When Call rest endpoint
    Then Rejected response with status code '<status-code>' and message '<error-msg>'

    Examples:
      | invalid-month | status-code | error-msg |
      | -1 | 400 | Invalid value for MonthOfYear: -1 |
      | 13 | 400 | Invalid value for MonthOfYear: 13 |
      | marc | 400 | Invalid value for MonthOfYear: marc |
      | march1 | 400 | Invalid value for MonthOfYear: march1 |

  Scenario Outline: Access endpoints invalid site params
    Given Rest endpoint '/reports?site={site}' request value '<invalid-site>'
    When Call rest endpoint
    Then Rejected response with status code '<status-code>' and message '<error-msg>'

    Examples:
      | invalid-site | status-code | error-msg |
      | ioss | 400 | Invalid value for Site: ioss |
      | mobile-web | 400 | Invalid value for Site: mobile-web |
      | desktop-web | 400 | Invalid value for Site: desktop-web |
