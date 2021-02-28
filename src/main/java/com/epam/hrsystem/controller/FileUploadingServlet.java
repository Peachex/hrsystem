package com.epam.hrsystem.controller;

import com.epam.hrsystem.controller.attribute.CommandName;
import com.epam.hrsystem.controller.attribute.Constant;
import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.controller.attribute.SessionAttribute;
import com.epam.hrsystem.controller.attribute.UrlPattern;
import com.epam.hrsystem.controller.command.CommandResult;
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
import java.io.*;
import java.util.UUID;

@WebServlet(urlPatterns = UrlPattern.UPLOAD_SERVLET, name = Constant.FILE_UPLOADING_SERVLET_NAME)
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class FileUploadingServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        File fileSaveDir = new File(Constant.UPLOAD_AVATAR_PATH);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        try {
            if (ServletFileUpload.isMultipartContent(request)) {
                HttpSession session = request.getSession();
                User user = (User) session.getAttribute(SessionAttribute.USER);
                if (user != null) {
                    Part part = request.getPart(RequestParameter.FILE_UPLOADING);
                    String path = part.getSubmittedFileName();
                    String randomFilename = UUID.randomUUID() + path.substring(path.lastIndexOf(Constant.DOT_SYMBOL));
                    try (InputStream inputStream = part.getInputStream()) {
                        boolean isSuccess = uploadFile(inputStream, Constant.UPLOAD_AVATAR_PATH + randomFilename);
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
        } catch (ServletException e) {
            logger.log(Level.ERROR, "Couldn't upload file: " + e);
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
