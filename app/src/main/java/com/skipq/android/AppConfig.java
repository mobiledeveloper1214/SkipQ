package com.skipq.android;

public interface AppConfig {
    String TAG = "skipq_app";
    String APP_NAME = "SkipQ";

//    String PATH_BASE = "http://skipq.net/skipq";
    String PATH_BASE = "http://172.16.1.210/skipq";

    String PATH_USER_AVATAR   = PATH_BASE + "/assets/media/users/";
    String PATH_BUSINESS_LOGO = PATH_BASE + "/assets/media/business_logo/";

//    Navigation Control
    String INDEX = "index";












    String FROM    = "from";
    String SIGN_IN = "sign_in";
    String SIGN_UP = "sign_up";
    String SETTING = "setting";

//    SharedPreferences Key
    String AUTHORIZATION  = "authorization";
    String PREFS_ENABLED  = "pref_enabled";
    String RANGE_ENABLED  = "range_enabled";
    String PROMO_ENABLED  = "promo_enabled";
    String COMP_ENABLED   = "comp_enabled";
    String RANGE_DISTANCE = "range_distance";

//    Response Key
    String STATUS  = "status";
    String RESULT  = "result";
    String RESULTS = "results";

    String ACCESS_TOKEN      = "access_token";
    String TOKEN_TYPE        = "token_type";
    String MESSAGE           = "message";
    String ERROR_DESCRIPTION = "error_description";

    String USER        = "user";
    String USER_ID     = "user_id";
    String FIRST_NAME  = "first_name";
    String LAST_NAME   = "last_name";
    String EMAIL       = "email";
    String PICTURE     = "picture";
    String FACEBOOK_ID = "facebook_id";
    String FEED        = "feed";
    String DATES       = "dates";
    String KEY_URL     = "url";
    String RANGE       = "range";

    String ID            = "id";
    String BUSINESSES    = "businesses";
    String BUSINESS      = "business";
    String ADDRESS       = "address";
    String CLAIM         = "claim";
    String CLAIM_WALK_IN = "claim_walk_in";
    String CLAIM_CALL_UP = "claim_call_up";
    String CLAIM_WEBSITE = "claim_website";
    String CLAIM_SPECIAL = "claim_special";
    String CALL_IN       = "call_in";
    String CALL_UP       = "call_up";
    String WEBSITE       = "website";
    String SPECIAL       = "special";
    String ENABLED       = "enabled";
    String LOGO          = "logo";
    String FEATUREIMAGE  = "featureimage";
    String NAME          = "name";
    String TITLE         = "title";
    String DESCRIPTION   = "description";
    String TIME          = "time";
    String DAYS          = "days";
    String HOURS         = "hours";
    String MINUTES       = "minutes";
    String FORMATTED     = "formatted";
    String COUNTRY       = "country";
    String COUNTY        = "county";
    String TELEPHONE     = "telephone";
    String FOLLOWING     = "following";
    String TYPE          = "type";
    String START         = "start";
    String END           = "end";
    String HUB           = "hub";
    String HUBS          = "hubs";
    String CATEGORIES    = "categories";

    String WALLET = "wallet";

    String OFFER    = "offer";
    String OFFER_ID = "offer_id";
    String OFFERS   = "offers";
    String COMP     = "comp";
    String PROMO    = "promo";
    String EVENT    = "event";
    String EVENTS   = "events";
    String META     = "meta";
    String PAGINATION = "pagination";
    String TOTAL_PAGES  = "total_pages";
    String CURRENT_PAGE = "current_page";

    String LINKS  = "links";
    String NEXT   = "next";

//    Image
    long MAX_FILE_SIZE = 3000000; // byte

//    Duration
    long FADE_DURATION = 500;
    long ALERT_TIME = 2000;

//    Permission Request Code
    int PERMISSIONS_REQUEST_CALL_PHONE             = 0;
    int PERMISSIONS_REQUEST_LOCATION               = 1;
}
