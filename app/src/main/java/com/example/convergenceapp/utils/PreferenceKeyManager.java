package com.example.convergenceapp.utils;

public class PreferenceKeyManager {
    private static final String PrefLoginSessionKey="PREF_LOGIN_SESSION_KEY";
    private static final String PrefSelectedMemberCode="PrefSelectedMemberCode";
    private static final String PrefSelectedBlockCode="PrefSelectedBlockCode";
    private static final String PrefSelectedGpCode="PrefSelectedGpCode";
    private static final String PrefSelectedVillageCode="PrefSelectedVillageCode";
    private static final String PrefSelectedShgCode="PrefSelectedShgCode";
    private static final String PrefSelectedClfCode="PrefSelectedClfCode";
    private static final String PrefSelectedVoCode="PrefSelectedVoCode";
    private static final String PrefLoginId="PrefLoginId";
    private static final String PrefStateShortName="PrefStateShortName";
    private static final String PrefImeiNo="PrefImeiNo";
   // private static final String PrefLoginId="LoginId";
    private static final String PrefDeviceinfo="PrefDeviceInfo";
    private static final String PrefLogoutTime="PrefLogoutTime";
    private static final String MST_FLAG="mstflag";

    private static final String ShgAlotted="shgAlotted";
    private static final String memberAlotted="memberAlotted";

    public static String getShgAlotted() {
        return ShgAlotted;
    }

    public static String getMemberAlotted() {
        return memberAlotted;
    }

    public static String getShgMemberSurveyCompleted() {
        return shgMemberSurveyCompleted;
    }

    public static String getShgMemberSurveyPending() {
        return shgMemberSurveyPending;
    }

    public static String getShgWhoseAllMemberSurveyCompleted() {
        return shgWhoseAllMemberSurveyCompleted;
    }

    public static String getShgAtleastOneMemberSurveyCompleted() {
        return shgAtleastOneMemberSurveyCompleted;
    }

    public static String getDate() {
        return date;
    }

    private static final String shgMemberSurveyCompleted="shgMemberSurveyCompleted";
    private static final String shgMemberSurveyPending="shgMemberSurveyPending";
    private static final String shgWhoseAllMemberSurveyCompleted="shgWhoseAllMemberSurveyCompleted";
    private static final String shgAtleastOneMemberSurveyCompleted="shgAtleastOneMemberSurveyCompleted";
    private static final String date="date";

    public static String getMstFlag() {
        return MST_FLAG;
    }

    private static final String ForgotMobileNumber="forgotmobile";
    private static final String RandomOtp="genratedOtp";
    private static final String PREF_KEY_MPIN= "prfMPIN";
    private static final String PREF_KEY_LOGIN_DONE = "Longin";
    private static final String PREF_KEY_Time_For_InActive = "InActive";
    private static final String PREF_LANGUAGE_CODE="langCode";
    private static final String PREF_LANGUAGE_ID="langId";
    private static final String PREF_FRGT_PASS="frgtpassword";
    private  static final String PREF_KEY_COUNTDOWN_TIME = "countDownTime";
    private static final String PREF_KEY_MPIN_COUNTER = "mpinCounter";
    private static final String PREF_KEY_MST_DATA = "mstdata";
    private static final String PREF_KEY_IMEI_FLAG = "imeiflag";
    private static final String PREF_KEY_SECCNO = "seccno";
    private static final String PREF_KEY_LOGINID = "loginid";
    private static final String PREF_KEY_Demo = "Demo";
    private static final String PREF_KEY_month = "month";
    private static final String PREF_KEY_NRLMMEMALOT = "nrlmmemberalot";
    private static final String PREF_KEY_PMAYGBENALOT = "pmaygmemberalot";
    private static final String PREF_KEY_NRLMGPALOT= "nrlmgpalot";
    private static final String PREF_KEY_PMAYGGPALOT= "pmayggpalot";
    private static final String PREF_KEY_NRLMVILLAGEALOT= "nrlmvillagealot";

    public static String getPrefKeyNrlmmemalot() {
        return PREF_KEY_NRLMMEMALOT;
    }

    public static String getPrefKeyPmaygbenalot() {
        return PREF_KEY_PMAYGBENALOT;
    }

    public static String getPrefKeyNrlmgpalot() {
        return PREF_KEY_NRLMGPALOT;
    }

    public static String getPrefKeyPmayggpalot() {
        return PREF_KEY_PMAYGGPALOT;
    }

    public static String getPrefKeyNrlmvillagealot() {
        return PREF_KEY_NRLMVILLAGEALOT;
    }

    public static String getPrefKeyPmaygvillagealot() {
        return PREF_KEY_PMAYGVILLAGEALOT;
    }

    public static String getPrefKeyNrlmsurveycom() {
        return PREF_KEY_NRLMSURVEYCOM;
    }

    public static String getPrefKeyPmaygsurveycom() {
        return PREF_KEY_PMAYGSURVEYCOM;
    }

    public static String getPrefKeyNrlmsurveypen() {
        return PREF_KEY_NRLMSURVEYPEN;
    }

    public static String getPrefKeyPmaygsurveypen() {
        return PREF_KEY_PMAYGSURVEYPEN;
    }

    private static final String PREF_KEY_PMAYGVILLAGEALOT= "pmaygvillagealot";
    private static final String PREF_KEY_NRLMSURVEYCOM= "nrlmcomplete";
    private static final String PREF_KEY_PMAYGSURVEYCOM= "pmaygcomplete";
    private static final String PREF_KEY_NRLMSURVEYPEN= "nrlmcomplete";
    private static final String PREF_KEY_PMAYGSURVEYPEN= "pmaygcomplete";

    public static String getPrefKeyLanguageId() {
        return PREF_KEY_LANGUAGE_ID;
    }

    private static final String PREF_KEY_LANGUAGE_ID = "language";

    public static String getPREF_KEY_month() {
        return PREF_KEY_month;
    }

    public static String getPREF_KEY_Demo() {
        return PREF_KEY_Demo;
    }

    public static String getPREF_KEY_Time_For_InActive() {
        return PREF_KEY_Time_For_InActive;
    }

    public static String getPrefKeyLoginid() {
        return PREF_KEY_LOGINID;
    }

    public static String getPrefKeyStateShortName() {
        return PREF_KEY_STATE_SHORT_NAME;
    }

    public static String getPrefKeyLogoutdays() {
        return PREF_KEY_LOGOUTDAYS;
    }

    public static String getPrefKeyServerdate() {
        return PREF_KEY_SERVERDATE;
    }

    private static final String PREF_KEY_STATE_SHORT_NAME = "stateshortname";
    private static final String PREF_KEY_LOGOUTDAYS = "logoutdays";
    private static final String PREF_KEY_SERVERDATE = "serverdate";
    private static final String PREF_KEY_distictcode = "distict";

    public static String getPREF_KEY_distictcode() {
        return PREF_KEY_distictcode;
    }

    public static String getPrefKeySeccno() {
        return PREF_KEY_SECCNO;
    }

    public static String getPrefKeyImeiFlag() {
        return PREF_KEY_IMEI_FLAG;
    }

    public static String getPrefKeyMpinCounter() {
        return PREF_KEY_MPIN_COUNTER;
    }


    public static String getPrefKeyMstData() {
        return PREF_KEY_MST_DATA;
    }

    public static String getPrefKeyCountdownTime() {
        return PREF_KEY_COUNTDOWN_TIME;
    }



    public static String getPrefFrgtPass() {
        return PREF_FRGT_PASS;
    }



    public static String getPrefLanguageCode() {
        return PREF_LANGUAGE_CODE;
    }

    public static String getPrefLanguageId() {
        return PREF_LANGUAGE_ID;
    }



    public static String getPrefImeiNo() {
        return PrefImeiNo;
    }
    public static String getPrefDeviceinfo() {
        return PrefDeviceinfo;
    }

    public static String getPrefStateShortName() {
        return PrefStateShortName;
    }

    public static String getPrefLoginId() {
        return PrefLoginId;
    }

    public static String getPrefSelectedClfCode() {
        return PrefSelectedClfCode;
    }

    public static String getPrefSelectedVoCode() {
        return PrefSelectedVoCode;
    }

    public static String getPrefSelectedBlockCode() {
        return PrefSelectedBlockCode;
    }

    public static String getPrefSelectedGpCode() {
        return PrefSelectedGpCode;
    }

    public static String getPrefSelectedVillageCode() {
        return PrefSelectedVillageCode;
    }

    public static String getPrefSelectedShgCode() {
        return PrefSelectedShgCode;
    }


    public static String getPrefKeyLoginDone() {
        return PREF_KEY_LOGIN_DONE;
    }

    public static String getTimeForInActive() {
        return PREF_KEY_Time_For_InActive;
    }



    public static String getPrefKeyMpin() {
        return PREF_KEY_MPIN;
    }


    public static String getRandomOtp() {
        return RandomOtp;
    }



    public static String getForgotMobileNumber() {
        return ForgotMobileNumber;
    }



    public static String getPrefSelectedMemberCode() {
        return PrefSelectedMemberCode;
    }

    public static String getPrefLoginSessionKey() {
        return PrefLoginSessionKey;
    }


    public static String getPrefLogoutTime() {
        return PrefLogoutTime;
    }
}
