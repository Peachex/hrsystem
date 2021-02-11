package com.epam.hrsystem.controller.attribute;

public enum Locale {
    RU("ru_RU"),
    EN("en_EN");

    private final String locale;

    Locale(String locale) {
        this.locale = locale;
    }

    public String getLocale() {
        return locale;
    }
}
