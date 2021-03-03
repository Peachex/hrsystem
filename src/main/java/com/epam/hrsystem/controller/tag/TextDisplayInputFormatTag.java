package com.epam.hrsystem.controller.tag;

import com.epam.hrsystem.controller.attribute.Constant;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class TextDisplayInputFormatTag extends TagSupport {
    private String text;

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int doStartTag() throws JspException {
        String result = text.replaceAll(Constant.NEW_LINE_HTML_TAG, Constant.EMPTY_SYMBOL);
        try {
            pageContext.getOut().write(result);
        } catch (IOException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
    }
}
