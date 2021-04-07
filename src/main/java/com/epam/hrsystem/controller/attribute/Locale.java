package com.epam.hrsystem.controller.attribute;

/**
 * Enumeration of application's locales.
 *
 * @author Aleksey Klevitov
 */
public enum Locale {
    /**
     * Represents russian locale.
     */
    RU("ru_RU"),
    /**
     * Represents english locale.
     */
    EN("en_EN");

    private final String locale;

    Locale(String locale) {
        this.locale = locale;
    }

    /**
     * Defines locale from String object.
     *
     * @param locale String object.
     * @return Locale object.
     */
    public static Locale defineLocale(String locale) {
        Locale result = EN;
        if (locale != null && !locale.isEmpty()) {
            if (locale.equalsIgnoreCase(RU.locale)) {
                result = RU;
            }
        }
        return result;
    }

    /**
     * Getter method of locale.
     *
     * @return String object of locale.
     */
    public String getLocale() {
        return locale;
    }
}
