package com.epam.hrsystem.controller.attribute;

/**
 * Class represents constant names of servlet attributes.
 *
 * @author Aleksey Klevitov
 */
public class ServletAttribute {
    /**
     * Represents a name of the main controller.
     */
    public static final String CONTROLLER_SERVLET_NAME = "Controller";
    /**
     * Represents a name of the file uploading servlet.
     */
    public static final String FILE_UPLOADING_SERVLET_NAME = "FileUploadingServlet";
    /**
     * Represents url pattern of the main controller.
     */
    public static final String CONTROLLER_URL_PATTERN = "*.do";
    /**
     * Represents url pattern of the file uploading servlet.
     */
    public static final String UPLOAD_SERVLET_URL_PATTERN = "*.upload";
    /**
     * Represents url pattern of the home page.
     */
    public static final String HOME_URL_PATTERN = "/home";
    /**
     * Represents url pattern of the login page.
     */
    public static final String LOGIN_URL_PATTERN = "/login";

    private ServletAttribute() {
    }
}
