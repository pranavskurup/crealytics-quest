@WebServiceTest @Auto @Positive @ReportAPI_V1
Feature: Check Report Rest End Point V1, Positive scenarios

  Scenario: Access endpoints without params
    Given Rest endpoint '/reports' without params
    When Call rest endpoint
    Then Should show data 'all' 'all' '0.26' '0.06' '94.97' '1.98'

  Scenario Outline: Access endpoints with Month request param as digit
    Given Rest endpoint '/reports?month={month}' request value '<digit>'
    When Call rest endpoint
    Then Should show data '<month>' '<site>' '<ctr>' '<cr>' '<fill_rate>' '<ecpm>'

    Examples:
      | digit | month     | site | ctr  | cr   | fill_rate | ecpm |
      | 1     | January   | all  | 0.26 | 0.06 | 94.97     | 1.98 |
      | 2     | February  | all  | 0.26 | 0.06 | 94.97     | 1.98 |
      | 3     | March     | all  | 0.26 | 0.06 | 94.97     | 1.98 |
      | 4     | April     | all  | 0.26 | 0.06 | 94.97     | 1.98 |
      | 5     | May       | all  | 0.0  | 0.0  | 0.0       | 0.0  |
      | 6     | June      | all  | 0.0  | 0.0  | 0.0       | 0.0  |
      | 7     | July      | all  | 0.0  | 0.0  | 0.0       | 0.0  |
      | 8     | August    | all  | 0.0  | 0.0  | 0.0       | 0.0  |
      | 9     | September | all  | 0.0  | 0.0  | 0.0       | 0.0  |
      | 10    | October   | all  | 0.0  | 0.0  | 0.0       | 0.0  |
      | 11    | November  | all  | 0.0  | 0.0  | 0.0       | 0.0  |
      | 12    | December  | all  | 0.0  | 0.0  | 0.0       | 0.0  |


  Scenario Outline: Access endpoints with Month request param as string
    Given Rest endpoint '/reports?month={month}' request value '<month_full>'
    When Call rest endpoint
    Then Should show data '<month>' '<site>' '<ctr>' '<cr>' '<fill_rate>' '<ecpm>'

    Examples:
      | month_full | month     | site | ctr  | cr   | fill_rate | ecpm |
      | january    | January   | all  | 0.26 | 0.06 | 94.97     | 1.98 |
      | february   | February  | all  | 0.26 | 0.06 | 94.97     | 1.98 |
      | march      | March     | all  | 0.26 | 0.06 | 94.97     | 1.98 |
      | april      | April     | all  | 0.26 | 0.06 | 94.97     | 1.98 |
      | may        | May       | all  | 0.0  | 0.0  | 0.0       | 0.0  |
      | june       | June      | all  | 0.0  | 0.0  | 0.0       | 0.0  |
      | july       | July      | all  | 0.0  | 0.0  | 0.0       | 0.0  |
      | august     | August    | all  | 0.0  | 0.0  | 0.0       | 0.0  |
      | september  | September | all  | 0.0  | 0.0  | 0.0       | 0.0  |
      | october    | October   | all  | 0.0  | 0.0  | 0.0       | 0.0  |
      | november   | November  | all  | 0.0  | 0.0  | 0.0       | 0.0  |
      | december   | December  | all  | 0.0  | 0.0  | 0.0       | 0.0  |

  Scenario Outline: Access endpoints with Month request param as string(Shorted)
    Given Rest endpoint '/reports?month={month}' request value '<month_short>'
    When Call rest endpoint
    Then Should show data '<month>' '<site>' '<ctr>' '<cr>' '<fill_rate>' '<ecpm>'

    Examples:
      | month_short | month     | site | ctr  | cr   | fill_rate | ecpm |
      | jan         | January   | all  | 0.26 | 0.06 | 94.97     | 1.98 |
      | feb         | February  | all  | 0.26 | 0.06 | 94.97     | 1.98 |
      | mar         | March     | all  | 0.26 | 0.06 | 94.97     | 1.98 |
      | apr         | April     | all  | 0.26 | 0.06 | 94.97     | 1.98 |
      | may         | May       | all  | 0.0  | 0.0  | 0.0       | 0.0  |
      | jun         | June      | all  | 0.0  | 0.0  | 0.0       | 0.0  |
      | jul         | July      | all  | 0.0  | 0.0  | 0.0       | 0.0  |
      | aug         | August    | all  | 0.0  | 0.0  | 0.0       | 0.0  |
      | sep         | September | all  | 0.0  | 0.0  | 0.0       | 0.0  |
      | oct         | October   | all  | 0.0  | 0.0  | 0.0       | 0.0  |
      | nov         | November  | all  | 0.0  | 0.0  | 0.0       | 0.0  |
      | dec         | December  | all  | 0.0  | 0.0  | 0.0       | 0.0  |


  Scenario Outline: Access endpoints with Site request param
    Given Rest endpoint '/reports?site={site}' request value '<site>'
    When Call rest endpoint
    Then Should show data '<month>' '<site>' '<ctr>' '<cr>' '<fill_rate>' '<ecpm>'
    Examples:
      | site        | month | ctr  | cr   | fill_rate | ecpm |
      | desktop web | all   | 0.26 | 0.06 | 95.05     | 1.99 |
      | mobile web  | all   | 0.27 | 0.07 | 94.9      | 2.03 |
      | android     | all   | 0.26 | 0.06 | 94.95     | 1.92 |
      | iOS         | all   | 0.26 | 0.06 | 94.89     | 1.94 |


  Scenario Outline: Access endpoints with Month and Site in request param
    Given Rest endpoint '/reports?month={month}&site={site}' request value for month '<param-month>' and for site '<param-site>'
    When Call rest endpoint
    Then Should show data '<month>' '<site>' '<ctr>' '<cr>' '<fill_rate>' '<ecpm>'
    Examples:
      | param-month | param-site  | site        | month    | ctr  | cr   | fill_rate | ecpm |
      | 1           | desktop web | desktop web | January  | 0.26 | 0.06 | 95.05     | 1.99 |
      | 1           | mobile web  | mobile web  | January  | 0.27 | 0.07 | 94.9      | 2.03 |
      | 1           | android     | android     | January  | 0.26 | 0.06 | 94.95     | 1.92 |
      | 1           | iOS         | iOS         | January  | 0.26 | 0.06 | 94.89     | 1.94 |
      | jan         | desktop web | desktop web | January  | 0.26 | 0.06 | 95.05     | 1.99 |
      | jan         | mobile web  | mobile web  | January  | 0.27 | 0.07 | 94.9      | 2.03 |
      | jan         | android     | android     | January  | 0.26 | 0.06 | 94.95     | 1.92 |
      | jan         | iOS         | iOS         | January  | 0.26 | 0.06 | 94.89     | 1.94 |
      | january     | desktop web | desktop web | January  | 0.26 | 0.06 | 95.05     | 1.99 |
      | january     | mobile web  | mobile web  | January  | 0.27 | 0.07 | 94.9      | 2.03 |
      | january     | android     | android     | January  | 0.26 | 0.06 | 94.95     | 1.92 |
      | january     | iOS         | iOS         | January  | 0.26 | 0.06 | 94.89     | 1.94 |
      | 2           | desktop web | desktop web | February | 0.26 | 0.06 | 95.05     | 1.99 |
      | 2           | mobile web  | mobile web  | February | 0.27 | 0.07 | 94.9      | 2.03 |
      | 2           | android     | android     | February | 0.26 | 0.06 | 94.95     | 1.92 |
      | 2           | iOS         | iOS         | February | 0.26 | 0.06 | 94.89     | 1.94 |
      | feb         | desktop web | desktop web | February | 0.26 | 0.06 | 95.05     | 1.99 |
      | feb         | mobile web  | mobile web  | February | 0.27 | 0.07 | 94.9      | 2.03 |
      | feb         | android     | android     | February | 0.26 | 0.06 | 94.95     | 1.92 |
      | feb         | iOS         | iOS         | February | 0.26 | 0.06 | 94.89     | 1.94 |
      | february    | desktop web | desktop web | February | 0.26 | 0.06 | 95.05     | 1.99 |
      | february    | mobile web  | mobile web  | February | 0.27 | 0.07 | 94.9      | 2.03 |
      | february    | android     | android     | February | 0.26 | 0.06 | 94.95     | 1.92 |
      | february    | iOS         | iOS         | February | 0.26 | 0.06 | 94.89     | 1.94 |
      | 3           | desktop web | desktop web | March    | 0.26 | 0.06 | 95.05     | 1.99 |
      | 3           | mobile web  | mobile web  | March    | 0.27 | 0.07 | 94.9      | 2.03 |
      | 3           | android     | android     | March    | 0.26 | 0.06 | 94.95     | 1.92 |
      | 3           | iOS         | iOS         | March    | 0.26 | 0.06 | 94.89     | 1.94 |
      | mar         | desktop web | desktop web | March    | 0.26 | 0.06 | 95.05     | 1.99 |
      | mar         | mobile web  | mobile web  | March    | 0.27 | 0.07 | 94.9      | 2.03 |
      | mar         | android     | android     | March    | 0.26 | 0.06 | 94.95     | 1.92 |
      | mar         | iOS         | iOS         | March    | 0.26 | 0.06 | 94.89     | 1.94 |
      | march       | desktop web | desktop web | March    | 0.26 | 0.06 | 95.05     | 1.99 |
      | march       | mobile web  | mobile web  | March    | 0.27 | 0.07 | 94.9      | 2.03 |
      | march       | android     | android     | March    | 0.26 | 0.06 | 94.95     | 1.92 |
      | march       | iOS         | iOS         | March    | 0.26 | 0.06 | 94.89     | 1.94 |
      | 4           | desktop web | desktop web | April    | 0.26 | 0.06 | 95.05     | 1.99 |
      | 4           | mobile web  | mobile web  | April    | 0.27 | 0.07 | 94.9      | 2.03 |
      | 4           | android     | android     | April    | 0.26 | 0.06 | 94.95     | 1.92 |
      | 4           | iOS         | iOS         | April    | 0.26 | 0.06 | 94.89     | 1.94 |
      | apr         | desktop web | desktop web | April    | 0.26 | 0.06 | 95.05     | 1.99 |
      | apr         | mobile web  | mobile web  | April    | 0.27 | 0.07 | 94.9      | 2.03 |
      | apr         | android     | android     | April    | 0.26 | 0.06 | 94.95     | 1.92 |
      | apr         | iOS         | iOS         | April    | 0.26 | 0.06 | 94.89     | 1.94 |
      | april       | desktop web | desktop web | April    | 0.26 | 0.06 | 95.05     | 1.99 |
      | april       | mobile web  | mobile web  | April    | 0.27 | 0.07 | 94.9      | 2.03 |
      | april       | android     | android     | April    | 0.26 | 0.06 | 94.95     | 1.92 |
      | april       | iOS         | iOS         | April    | 0.26 | 0.06 | 94.89     | 1.94 |
