package com.epam.hrsystem.controller;

import com.epam.hrsystem.controller.attribute.CommandName;
import com.epam.hrsystem.controller.attribute.JspAttribute;
import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.controller.attribute.SessionAttribute;
import com.epam.hrsystem.controller.attribute.ServletAttribute;
import com.epam.hrsystem.exception.ServiceException;
import com.epam.hrsystem.model.entity.User;
import com.epam.hrsystem.model.service.UserService;
import com.epam.hrsystem.model.service.impl.UserServiceImpl;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.util.UUID;

@WebServlet(urlPatterns = ServletAttribute.UPLOAD_SERVLET_URL_PATTERN, name = ServletAttribute.FILE_UPLOADING_SERVLET_NAME)
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class FileUploadingServlet extends HttpServlet {
    public static final String UPLOAD_AVATAR_PATH = "C:" + File.separator + "Users" + File.separator + "Peachex" + File.separator +
            "IdeaProjects" + File.separator + "hrsystem" + File.separator + "src" + File.separator + "main" + File.separator +
            "webapp" + File.separator + "img" + File.separator + "avatar" + File.separator;
    private static final Logger logger = LogManager.getLogger();
    private static final String DOT_SYMBOL = ".";

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        try {
            if (ServletFileUpload.isMultipartContent(request)) {
                HttpSession session = request.getSession();
                User user = (User) session.getAttribute(SessionAttribute.USER);
                if (user != null) {
                    Part part = request.getPart(RequestParameter.FILE_UPLOADING);
                    String path = part.getSubmittedFileName();
                    if (path != null && !path.isEmpty()) {
                        String randomFilename = UUID.randomUUID() + path.substring(path.lastIndexOf(DOT_SYMBOL));
                        try (InputStream inputStream = part.getInputStream()) {
                            System.out.println(inputStream.available());
                            boolean isSuccess = uploadFile(inputStream, UPLOAD_AVATAR_PATH + randomFilename);
                            if (isSuccess) {
                                UserService service = UserServiceImpl.INSTANCE;
                                try {
                                    service.changePhoto(user.getId(), randomFilename);
                                    user.setPhotoName(randomFilename);
                                    session.setAttribute(SessionAttribute.USER, user);
                                } catch (ServiceException e) {
                                    logger.log(Level.ERROR, "Couldn't upload file: " + e);
                                    throw new ServletException(e);
                                }
                            }
                        }
                    }
                }
            }
        } catch (ServletException e) {
            logger.log(Level.ERROR, "Couldn't upload file: " + e);
            request.setAttribute(JspAttribute.ERROR_MESSAGE_INFO, e.getMessage());
            throw new ServletException(e);
        }
        response.sendRedirect(request.getContextPath() + CommandName.TO_USER_PROFILE);
    }

    private boolean uploadFile(InputStream inputStream, String path) throws ServletException {
        try {
            byte[] bytes = new byte[inputStream.available()];
            int result = inputStream.read(bytes);
            if (result != -1) {
                try (FileOutputStream fops = new FileOutputStream(path)) {
                    fops.write(bytes);
                }
            }
        } catch (IOException e) {
            throw new ServletException(e);
        }
        return true;
    }
}
