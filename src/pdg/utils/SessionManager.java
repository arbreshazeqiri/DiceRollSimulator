package pdg.utils;

import pdg.models.LangEnum;
import pdg.models.User;

import java.util.Locale;

public class SessionManager {
    public static User user = null;
    public static Locale getLocale() {
        LangEnum lang = AppConfig.get().getLanguage();
        if(lang == LangEnum.EN)
            return new Locale("en","US");
        else
            return new Locale("al","AL");
    }
}
