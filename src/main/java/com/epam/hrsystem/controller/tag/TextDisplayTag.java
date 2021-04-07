package com.epam.hrsystem.controller.tag;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Custom tag that prevents cross-site scripting.
 *
 * @author Aleksey Klevitov
 */
public class TextDisplayTag extends TagSupport {
    private static final Logger logger = LogManager.getLogger();
    private static final String NEW_LINE_SYMBOL = "\n";
    private static final String NEW_LINE_HTML_SYMBOL = "<br>";
    private static final String SPACE_SYMBOL = "\\s";
    private static final String SPACE_HTML_SYMBOL = "&nbsp";
    private static final String GREATER_THAN_SYMBOL = ">";
    private static final String GREATER_THAN_HTML_SYMBOL = "&gt";
    private static final String LESS_THAN_SYMBOL = "<";
    private static final String LESS_THAN_HTML_SYMBOL = "&lt";
    private static final String QUOTATION_SYMBOL = "\"";
    private static final String QUOTATION_HTML_SYMBOL = "&quot";
    private String text;

    /**
     * Setter method of text.
     *
     * @param text String object
     */
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int doStartTag() throws JspException {
        String result = text
                .replace(GREATER_THAN_SYMBOL, GREATER_THAN_HTML_SYMBOL)
                .replace(SPACE_SYMBOL, SPACE_HTML_SYMBOL)
                .replace(QUOTATION_SYMBOL, QUOTATION_HTML_SYMBOL)
                .replace(LESS_THAN_SYMBOL, LESS_THAN_HTML_SYMBOL)
                .replace(NEW_LINE_SYMBOL, NEW_LINE_HTML_SYMBOL);
        try {
            pageContext.getOut().write(result);
        } catch (IOException e) {
            logger.log(Level.ERROR, "Couldn't display text: " + e);
            throw new JspException(e);
        }
        return SKIP_BODY;
    }
}
