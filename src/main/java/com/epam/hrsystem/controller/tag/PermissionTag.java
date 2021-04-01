package com.epam.hrsystem.controller.tag;

import com.epam.hrsystem.controller.attribute.SessionAttribute;
import com.epam.hrsystem.model.entity.UserRole;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.TagSupport;

public class PermissionTag extends TagSupport {
    @Override
    public int doStartTag() {
        HttpSession session = pageContext.getSession();
        UserRole currentRole = (UserRole) session.getAttribute(SessionAttribute.CURRENT_ROLE);
        if (currentRole != null && currentRole != UserRole.GUEST) {
            return EVAL_BODY_INCLUDE;
        }
        return SKIP_BODY;
    }
}
