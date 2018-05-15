@WebServiceTest @Auto @Positive @ReportAPI_V1
Feature: Check Report Rest End Point V1, Positive scenarios

  Scenario: Access endpoints without params
    Given Rest endpoint '/reports' without params
    When Call rest endpoint
    Then Should show data 'all' 'all' '0.2627854905502873' '0.06467069291025097' '94.96761460984034' '1.9761860430155274'

  Scenario Outline: Access endpoints with Month request param as digit
    Given Rest endpoint '/reports?month={month}' request value '<digit>'
    When Call rest endpoint
    Then Should show data '<month>' '<site>' '<ctr>' '<cr>' '<fill_rate>' '<ecpm>'

    Examples:
      | digit | month   | site | ctr                | cr                  | fill_rate         | ecpm               |
      | 1     | January | all  | 0.2627854905502873 | 0.06467069291025097 | 94.96761460984034 | 1.9761860430155274 |

  Scenario Outline: Access endpoints with Month request param as string
    Given Rest endpoint '/reports?month={month}' request value '<month_full>'
    When Call rest endpoint
    Then Should show data '<month>' '<site>' '<ctr>' '<cr>' '<fill_rate>' '<ecpm>'

    Examples:
      | month_full | month   | site | ctr                | cr                  | fill_rate         | ecpm               |
      | january    | January | all  | 0.2627854905502873 | 0.06467069291025097 | 94.96761460984034 | 1.9761860430155274 |

  Scenario Outline: Access endpoints with Month request param as string(Shorted)
    Given Rest endpoint '/reports?month={month}' request value '<month_short>'
    When Call rest endpoint
    Then Should show data '<month>' '<site>' '<ctr>' '<cr>' '<fill_rate>' '<ecpm>'

    Examples:
      | month_short | month   | site | ctr                | cr                  | fill_rate         | ecpm               |
      | jan         | January | all  | 0.2627854905502873 | 0.06467069291025097 | 94.96761460984034 | 1.9761860430155274 |

  Scenario Outline: Access endpoints with Site request param
    Given Rest endpoint '/reports?site={site}' request value '<site>'
    When Call rest endpoint
    Then Should show data '<month>' '<site>' '<ctr>' '<cr>' '<fill_rate>' '<ecpm>'
    Examples:
      | site        | month | ctr                | cr                  | fill_rate         | ecpm               |
      | desktop web | all   | 0.2609522189871582 | 0.06411511325865653 | 95.05263431934651 | 1.9850959329123994 |

  Scenario Outline: Access endpoints with Month and Site in request param
    Given Rest endpoint '/reports?month={month}&site={site}' request value for month '<param-month>' and for site '<param-site>'
    When Call rest endpoint
    Then Should show data '<month>' '<site>' '<ctr>' '<cr>' '<fill_rate>' '<ecpm>'
    Examples:
      | param-month | param-site | site | month | ctr | cr | fill_rate | ecpm |

