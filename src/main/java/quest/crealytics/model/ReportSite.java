package quest.crealytics.model;

import quest.crealytics.exception.SiteNotFoundException;

/**
 * Created by Pranav S Kurup on 5/15/2018.
 */
public enum ReportSite {

    DESKTOP_WEB("desktop_web"), MOBILE_WEB("mobile_web"), ANDROID("android"), IOS("iOS");

    private String siteType;

    ReportSite(String siteType) {
        this.siteType = siteType;
    }

    public static ReportSite ofSite(String site) {
        ReportSite currentSite = null;
        ReportSite[] values = ReportSite.values();
        for (int i = 0; i < values.length; i++) {
            ReportSite siteEnum = values[i];
            if (siteEnum.getSiteType().equalsIgnoreCase(site.replace(' ', '_'))) {
                currentSite = siteEnum;
            }
        }
        if (currentSite == null) {
            throw new SiteNotFoundException("Invalid value for Site: " + site);
        }
        return currentSite;
    }

    public String getSiteType() {
        return siteType;
    }

    public String getSiteTypeViewName() {
        return siteType.replace('_', ' ');
    }

}
