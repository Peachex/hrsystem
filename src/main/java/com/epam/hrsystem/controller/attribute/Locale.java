package com.epam.hrsystem.controller.attribute;

public enum Locale {
    RU("ru_RU"),
    EN("en_EN");

    private final String locale;

    Locale(String locale) {
        this.locale = locale;
    }

    public static Locale defineLocale(String locale) {
        Locale result = EN;
        if (locale != null && !locale.isEmpty()) {
            if (locale.equalsIgnoreCase(RU.locale)) {
                result = RU;
            }
        }
        return result;
    }

    public String getLocale() {
        return locale;
    }
}
