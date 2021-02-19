package com.epam.hrsystem.controller.command;

import com.epam.hrsystem.controller.attribute.UrlPattern;

public class CommandResult {
    public static final String DEFAULT_PATH = UrlPattern.HOME;

    private String path;
    private Type type;

    public enum Type {
        FORWARD,
        REDIRECT,
        RETURN,
    }

    public CommandResult(String path, Type type) {
        this.path = path;
        this.type = type;
    }

    public CommandResult(String path) {
        this.path = path;
        this.type = Type.FORWARD;
    }

    public CommandResult(Type type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String providePath() {
        String result;
        if (path == null || path.isEmpty()) {
            result = DEFAULT_PATH;
        } else {
            result = path;
        }
        return result;
    }
}
