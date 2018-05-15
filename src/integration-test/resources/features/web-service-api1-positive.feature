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
      | digit | month     | site | ctr                | cr                  | fill_rate         | ecpm               |
      | 1     | January   | all  | 0.2627854905502873 | 0.06467069291025097 | 94.96761460984034 | 1.9761860430155274 |
      | 2     | February  | all  | 0.2627854905502873 | 0.06467069291025097 | 94.96761460984034 | 1.9761860430155274 |
      | 3     | March     | all  | 0.2627854905502873 | 0.06467069291025097 | 94.96761460984034 | 1.9761860430155274 |
      | 4     | April     | all  | 0.2627854905502873 | 0.06467069291025097 | 94.96761460984034 | 1.9761860430155274 |
      | 5     | May       | all  | 0.0                | 0.0                 | 0.0               | 0.0                |
      | 6     | June      | all  | 0.0                | 0.0                 | 0.0               | 0.0                |
      | 7     | July      | all  | 0.0                | 0.0                 | 0.0               | 0.0                |
      | 8     | August    | all  | 0.0                | 0.0                 | 0.0               | 0.0                |
      | 9     | September | all  | 0.0                | 0.0                 | 0.0               | 0.0                |
      | 10    | October   | all  | 0.0                | 0.0                 | 0.0               | 0.0                |
      | 11    | November  | all  | 0.0                | 0.0                 | 0.0               | 0.0                |
      | 12    | December  | all  | 0.0                | 0.0                 | 0.0               | 0.0                |


  Scenario Outline: Access endpoints with Month request param as string
    Given Rest endpoint '/reports?month={month}' request value '<month_full>'
    When Call rest endpoint
    Then Should show data '<month>' '<site>' '<ctr>' '<cr>' '<fill_rate>' '<ecpm>'

    Examples:
      | month_full | month     | site | ctr                | cr                  | fill_rate         | ecpm               |
      | january    | January   | all  | 0.2627854905502873 | 0.06467069291025097 | 94.96761460984034 | 1.9761860430155274 |
      | february   | February  | all  | 0.2627854905502873 | 0.06467069291025097 | 94.96761460984034 | 1.9761860430155274 |
      | march      | March     | all  | 0.2627854905502873 | 0.06467069291025097 | 94.96761460984034 | 1.9761860430155274 |
      | april      | April     | all  | 0.2627854905502873 | 0.06467069291025097 | 94.96761460984034 | 1.9761860430155274 |
      | may        | May       | all  | 0.0                | 0.0                 | 0.0               | 0.0                |
      | june       | June      | all  | 0.0                | 0.0                 | 0.0               | 0.0                |
      | july       | July      | all  | 0.0                | 0.0                 | 0.0               | 0.0                |
      | august     | August    | all  | 0.0                | 0.0                 | 0.0               | 0.0                |
      | september  | September | all  | 0.0                | 0.0                 | 0.0               | 0.0                |
      | october    | October   | all  | 0.0                | 0.0                 | 0.0               | 0.0                |
      | november   | November  | all  | 0.0                | 0.0                 | 0.0               | 0.0                |
      | december   | December  | all  | 0.0                | 0.0                 | 0.0               | 0.0                |

  Scenario Outline: Access endpoints with Month request param as string(Shorted)
    Given Rest endpoint '/reports?month={month}' request value '<month_short>'
    When Call rest endpoint
    Then Should show data '<month>' '<site>' '<ctr>' '<cr>' '<fill_rate>' '<ecpm>'

    Examples:
      | month_short | month     | site | ctr                | cr                  | fill_rate         | ecpm               |
      | jan         | January   | all  | 0.2627854905502873 | 0.06467069291025097 | 94.96761460984034 | 1.9761860430155274 |
      | feb         | February  | all  | 0.2627854905502873 | 0.06467069291025097 | 94.96761460984034 | 1.9761860430155274 |
      | mar         | March     | all  | 0.2627854905502873 | 0.06467069291025097 | 94.96761460984034 | 1.9761860430155274 |
      | apr         | April     | all  | 0.2627854905502873 | 0.06467069291025097 | 94.96761460984034 | 1.9761860430155274 |
      | may         | May       | all  | 0.0                | 0.0                 | 0.0               | 0.0                |
      | jun         | June      | all  | 0.0                | 0.0                 | 0.0               | 0.0                |
      | jul         | July      | all  | 0.0                | 0.0                 | 0.0               | 0.0                |
      | aug         | August    | all  | 0.0                | 0.0                 | 0.0               | 0.0                |
      | sep         | September | all  | 0.0                | 0.0                 | 0.0               | 0.0                |
      | oct         | October   | all  | 0.0                | 0.0                 | 0.0               | 0.0                |
      | nov         | November  | all  | 0.0                | 0.0                 | 0.0               | 0.0                |
      | dec         | December  | all  | 0.0                | 0.0                 | 0.0               | 0.0                |


  Scenario Outline: Access endpoints with Site request param
    Given Rest endpoint '/reports?site={site}' request value '<site>'
    When Call rest endpoint
    Then Should show data '<month>' '<site>' '<ctr>' '<cr>' '<fill_rate>' '<ecpm>'
    Examples:
      | site        | month | ctr                 | cr                  | fill_rate         | ecpm               |
      | desktop web | all   | 0.2609522189871582  | 0.06411511325865653 | 95.05263431934651 | 1.9850959329123994 |
      | mobile web  | all   | 0.269020193586893   | 0.06611954937867727 | 94.90417973373961 | 2.026731189248808  |
      | android     | all   | 0.2591640162422907  | 0.06393314407649539 | 94.9451014544327  | 1.9239871249823912 |
      | iOS         | all   | 0.26164043718873115 | 0.06463523041591779 | 94.88535055574835 | 1.9391726277238024 |


  Scenario Outline: Access endpoints with Month and Site in request param
    Given Rest endpoint '/reports?month={month}&site={site}' request value for month '<param-month>' and for site '<param-site>'
    When Call rest endpoint
    Then Should show data '<month>' '<site>' '<ctr>' '<cr>' '<fill_rate>' '<ecpm>'
    Examples:
      | param-month | param-site  | site        | month    | ctr                 | cr                  | fill_rate         | ecpm               |
      | 1           | desktop web | desktop web | January  | 0.2609522189871582  | 0.06411511325865653 | 95.05263431934651 | 1.9850959329123994 |
      | 1           | mobile web  | mobile web  | January  | 0.269020193586893   | 0.06611954937867727 | 94.90417973373961 | 2.026731189248808  |
      | 1           | android     | android     | January  | 0.2591640162422907  | 0.06393314407649539 | 94.9451014544327  | 1.9239871249823912 |
      | 1           | iOS         | iOS         | January  | 0.26164043718873115 | 0.06463523041591779 | 94.88535055574835 | 1.9391726277238024 |
      | jan         | desktop web | desktop web | January  | 0.2609522189871582  | 0.06411511325865653 | 95.05263431934651 | 1.9850959329123994 |
      | jan         | mobile web  | mobile web  | January  | 0.269020193586893   | 0.06611954937867727 | 94.90417973373961 | 2.026731189248808  |
      | jan         | android     | android     | January  | 0.2591640162422907  | 0.06393314407649539 | 94.9451014544327  | 1.9239871249823912 |
      | jan         | iOS         | iOS         | January  | 0.26164043718873115 | 0.06463523041591779 | 94.88535055574835 | 1.9391726277238024 |
      | january     | desktop web | desktop web | January  | 0.2609522189871582  | 0.06411511325865653 | 95.05263431934651 | 1.9850959329123994 |
      | january     | mobile web  | mobile web  | January  | 0.269020193586893   | 0.06611954937867727 | 94.90417973373961 | 2.026731189248808  |
      | january     | android     | android     | January  | 0.2591640162422907  | 0.06393314407649539 | 94.9451014544327  | 1.9239871249823912 |
      | january     | iOS         | iOS         | January  | 0.26164043718873115 | 0.06463523041591779 | 94.88535055574835 | 1.9391726277238024 |
      | 2           | desktop web | desktop web | February | 0.2609522189871582  | 0.06411511325865653 | 95.05263431934651 | 1.9850959329123994 |
      | 2           | mobile web  | mobile web  | February | 0.269020193586893   | 0.06611954937867727 | 94.90417973373961 | 2.026731189248808  |
      | 2           | android     | android     | February | 0.2591640162422907  | 0.06393314407649539 | 94.9451014544327  | 1.9239871249823912 |
      | 2           | iOS         | iOS         | February | 0.26164043718873115 | 0.06463523041591779 | 94.88535055574835 | 1.9391726277238024 |
      | feb         | desktop web | desktop web | February | 0.2609522189871582  | 0.06411511325865653 | 95.05263431934651 | 1.9850959329123994 |
      | feb         | mobile web  | mobile web  | February | 0.269020193586893   | 0.06611954937867727 | 94.90417973373961 | 2.026731189248808  |
      | feb         | android     | android     | February | 0.2591640162422907  | 0.06393314407649539 | 94.9451014544327  | 1.9239871249823912 |
      | feb         | iOS         | iOS         | February | 0.26164043718873115 | 0.06463523041591779 | 94.88535055574835 | 1.9391726277238024 |
      | february    | desktop web | desktop web | February | 0.2609522189871582  | 0.06411511325865653 | 95.05263431934651 | 1.9850959329123994 |
      | february    | mobile web  | mobile web  | February | 0.269020193586893   | 0.06611954937867727 | 94.90417973373961 | 2.026731189248808  |
      | february    | android     | android     | February | 0.2591640162422907  | 0.06393314407649539 | 94.9451014544327  | 1.9239871249823912 |
      | february    | iOS         | iOS         | February | 0.26164043718873115 | 0.06463523041591779 | 94.88535055574835 | 1.9391726277238024 |
      | 3           | desktop web | desktop web | March    | 0.2609522189871582  | 0.06411511325865653 | 95.05263431934651 | 1.9850959329123994 |
      | 3           | mobile web  | mobile web  | March    | 0.269020193586893   | 0.06611954937867727 | 94.90417973373961 | 2.026731189248808  |
      | 3           | android     | android     | March    | 0.2591640162422907  | 0.06393314407649539 | 94.9451014544327  | 1.9239871249823912 |
      | 3           | iOS         | iOS         | March    | 0.26164043718873115 | 0.06463523041591779 | 94.88535055574835 | 1.9391726277238024 |
      | mar         | desktop web | desktop web | March    | 0.2609522189871582  | 0.06411511325865653 | 95.05263431934651 | 1.9850959329123994 |
      | mar         | mobile web  | mobile web  | March    | 0.269020193586893   | 0.06611954937867727 | 94.90417973373961 | 2.026731189248808  |
      | mar         | android     | android     | March    | 0.2591640162422907  | 0.06393314407649539 | 94.9451014544327  | 1.9239871249823912 |
      | mar         | iOS         | iOS         | March    | 0.26164043718873115 | 0.06463523041591779 | 94.88535055574835 | 1.9391726277238024 |
      | march       | desktop web | desktop web | March    | 0.2609522189871582  | 0.06411511325865653 | 95.05263431934651 | 1.9850959329123994 |
      | march       | mobile web  | mobile web  | March    | 0.269020193586893   | 0.06611954937867727 | 94.90417973373961 | 2.026731189248808  |
      | march       | android     | android     | March    | 0.2591640162422907  | 0.06393314407649539 | 94.9451014544327  | 1.9239871249823912 |
      | march       | iOS         | iOS         | March    | 0.26164043718873115 | 0.06463523041591779 | 94.88535055574835 | 1.9391726277238024 |
      | 4           | desktop web | desktop web | April    | 0.2609522189871582  | 0.06411511325865653 | 95.05263431934651 | 1.9850959329123994 |
      | 4           | mobile web  | mobile web  | April    | 0.269020193586893   | 0.06611954937867727 | 94.90417973373961 | 2.026731189248808  |
      | 4           | android     | android     | April    | 0.2591640162422907  | 0.06393314407649539 | 94.9451014544327  | 1.9239871249823912 |
      | 4           | iOS         | iOS         | April    | 0.26164043718873115 | 0.06463523041591779 | 94.88535055574835 | 1.9391726277238024 |
      | apr         | desktop web | desktop web | April    | 0.2609522189871582  | 0.06411511325865653 | 95.05263431934651 | 1.9850959329123994 |
      | apr         | mobile web  | mobile web  | April    | 0.269020193586893   | 0.06611954937867727 | 94.90417973373961 | 2.026731189248808  |
      | apr         | android     | android     | April    | 0.2591640162422907  | 0.06393314407649539 | 94.9451014544327  | 1.9239871249823912 |
      | apr         | iOS         | iOS         | April    | 0.26164043718873115 | 0.06463523041591779 | 94.88535055574835 | 1.9391726277238024 |
      | april       | desktop web | desktop web | April    | 0.2609522189871582  | 0.06411511325865653 | 95.05263431934651 | 1.9850959329123994 |
      | april       | mobile web  | mobile web  | April    | 0.269020193586893   | 0.06611954937867727 | 94.90417973373961 | 2.026731189248808  |
      | april       | android     | android     | April    | 0.2591640162422907  | 0.06393314407649539 | 94.9451014544327  | 1.9239871249823912 |
      | april       | iOS         | iOS         | April    | 0.26164043718873115 | 0.06463523041591779 | 94.88535055574835 | 1.9391726277238024 |
