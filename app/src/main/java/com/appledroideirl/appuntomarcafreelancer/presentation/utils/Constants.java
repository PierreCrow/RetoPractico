package com.appledroideirl.appuntomarcafreelancer.presentation.utils;

public class Constants {

    public class URLS {
        public static final String URL_BASE = "https://developer-dot-app-puntos-285220.uc.r.appspot.com/";
        public static final String GENERATE_TOKEN = "token";
        public static final String LOGIN = "login";
        public static final String REQUEST_LIST_USER = "saleuser/";
        public static final String REQUEST_ACCEPT = "saleaccept/";
        public static final String REQUEST_REFUSE = "salerefuse/";
        public static final String LIST_AVAILABLE_DATE_HOUR = "userdateavailability/";
        public static final String LIST_REPORTS = "dashboardmobile/";
        public static final String LIST_SERVICES = "services";
        public static final String LIST_USER_SERVICES = "services/";
        public static final String ADD_SERVICE = "userserviceadd";
        public static final String ADD_DATE_AVAILABLE = "userdateavailability";
        public static final String ADD_USER = "users";
        public static final String ADD_BANK_ACCOUNT = "userbankaccount";
        public static final String LIST_BANK_ACCOUNT = "userbankaccount/";
        public static final String ADD_SUB_SERVICE = "usersubserviceadd";
        public static final String LIST_LOCALES = "userstore/";
        public static final String ADD_LOCAL = "userstore";
        public static final String RECOVERY_PASSWORD = "recoveruser";
    }

    public static class PDF_URL {
        public static final String Terminons_Partners = "https://storage.googleapis.com/app-puntos-285220.appspot.com/mobile/Appunto%20-%20Te%CC%81rminos%20y%20Condiciones-partners.pdf";
        public static final String PoliticasPrivacidad = "https://storage.googleapis.com/app-puntos-285220.appspot.com/mobile/Appunto%20-%20Poli%CC%81tica%20de%20Privacidad-customers.pdf";
    }

    public static class STORE {
        public static final int CLOUD = 0;
        public static final int DB = 1;
    }


    public static class REQUEST_CODES {
        public static final int REQUEST_CODE_LOCATION = 123;
        public static final int REQUEST_CODE_STORAGE = 124;
        public static final int REQUEST_CODE_CALENDAR = 789;
        public static final int REQUEST_CODE_CAMERA = 966;
    }

    public class PREFERENCES {
        public static final String PREFERENCE_CURRENT_USER = "PREFERENCE_CURRENT_USER";
    }

    public class PREFERENCES_KEYS {
        public static final String CURRENT_USER_ABOUT = "CURRENT_USER_ABOUT";
        public static final String CURRENT_USER_AVG_RATE = "CURRENT_USER_AVG_RATE";
        public static final String CURRENT_USER_PHONE = "CURRENT_USER_PHONE";
        public static final String CURRENT_USER_DOCUMENT_NUMBER = "CURRENT_USER_DOCUMENT_NUMBER";
        public static final String CURRENT_USER_FULL_NAME = "CURRENT_USER_FULL_NAME";
        public static final String CURRENT_USER_ID = "CURRENT_USER_ID";
        public static final String CURRENT_USER_ID_TYPE_DOCUMENT = "CURRENT_USER_ID_TYPE_DOCUMENT";
        public static final String CURRENT_USER_EMAIL = "CURRENT_USER_EMAIL";
        public static final String CURRENT_USER_PASSWORD = "CURRENT_USER_PASSWORD";
        public static final String CURRENT_USER_PHOTO = "CURRENT_USER_PHOTO";
        public static final String CURRENT_USER_SOCIAL_NAME = "CURRENT_USER_SOCIAL_NAME";
        public static final String CURRENT_USER_TYPE_USER = "CURRENT_USER_TYPE_USER";
        public static final String CURRENT_USER_TOKEN = "CURRENT_USER_TOKEN";
        public static final String CURRENT_USER_LOGGED = "CURRENT_USER_LOGGED";
        public static final String CURRENT_USER_FCM = "CURRENT_USER_FCM";
        public static final String CURRENT_USER_HAS_LOCATION = "CURRENT_USER_HAS_LOCATION";
        public static final String CURRENT_USER_LAT = "CURRENT_USER_LAT";
        public static final String CURRENT_USER_LNG = "CURRENT_USER_LNG";
        public static final String CURRENT_USER_USER = "CURRENT_USER_USER";
    }


    public class FRAGMENTS_TABS {
        public static final int HOME = 0;
        public static final int FAVORITES = 1;
        public static final int EVENTS = 2;
        public static final int ACCOUNT = 3;
    }


    public class REGISTER_TYPES {
        public static final String EMAIL = "TIRE0003";
        public static final String FACEBOOK = "TIRE0002";
        public static final String GOOGLE = "TIRE0001";
        public static final String NOT_LOGIN = "TIRE000005";
    }

    public class REGISTER_STATES {
        public static final String EMAIL = "TIRE0003";
        public static final String FACEBOOK = "TIRE0002";
    }

    public class SYSTEM {
        public static final String APP = "TISI0001";
    }

    public class RESPONSE_CODES {
        public static final int SUCCESS = 1;
        public static final int SERVER_CONNECTION_SUCCESS = 200;
        public static final String USER_CODE_NOT_REGISTERED = "ESRE0001";
    }

    public class RESPONSE_MESSAGES {
        public static final String ERROR = "Error al conectarse al servidor";
    }

    public class CARD_TYPES {
        public static final int VISA = 1;
        public static final int MASTERCARD = 2;
        public static final int AMERICAN_EXPRESS = 3;
        public static final int DINNERS_CLUB = 4;
    }


    public class SOLICITUD_TYPES {
        public static final int PENDIENTE = 0;
        public static final int ACEPTADO_POR_USUARIO = 1;
        public static final int RECHAZADO_POR_USUARIO = 2;
        public static final int PAGADO_POR_CLIENTE = 3;
        public static final int CANCELADO = 4;
    }

    public class IMAGE_EXTENSION {
        public static final String DOT_JPG = ".jpg";
        public static final String DASH_MIN = "-min";
    }


    public static class APP_VERSION {
        public static final String MAJOR = "1";
        public static final String EQUAL = "0";
        public static final String MINOR = "-1";
    }


    public static class TYPE_PHOTO_IMPORT {
        public static final int CAMERA = 221;
        public static final int GALLERY = 222;
    }


    public class FIREBASE_EVENT {
        public static final String NEW_USER = "Nuevos_usuarios";
        public static final String DOWNLOAD_ROUTE = "Descargas_rutas";
        public static final String DOWNLOAD_MAP = "Descargas_mapas";
    }

    public class FIREBASE_SCREEN_NAMES {
        public static final String COMPLETE_INFO = "Completar_informacion";
        public static final String DISTRIT_MAP = "Distrito_mapa";
        public static final String EDIT_PROFILE = "Editar_perfil";
        public static final String FORGOT_PASSWORD = "Olvidar_contrasena";
        public static final String LOGIN = "Login";
        public static final String LOGIN_SOCIAL_MEDIA = "Login_red_social";
        public static final String MAIN = "Pantalla_principal";
        public static final String PLACE_DETAIL = "Detalle_lugar";
        public static final String PREFERENCE = "Preferencias ";
        public static final String REGISTER_USER = "Registro_usuario ";
        public static final String ROUTE_LIST = "Ruta_lista ";
        public static final String ROUTE_MAP = "Ruta_mapa ";
        public static final String VALITATION = "Validacion ";
    }

}



