package com.epam.hrsystem.controller;

import com.epam.hrsystem.controller.attribute.UrlPattern;

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

@WebServlet(urlPatterns = UrlPattern.UPLOAD_SERVLET, name = "FileUploadingServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class FileUploadingServlet extends HttpServlet {
    public static final String UPLOAD_FILE_PATH = "C:" + File.separator + "Users" + File.separator + "Peachex" + File.separator +
            "IdeaProjects" + File.separator + "hrsystem" + File.separator + "src" + File.separator + "main" + File.separator +
            "webapp" + File.separator + "img" + File.separator + "avatar" + File.separator;

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        File fileSaveDir = new File(UPLOAD_FILE_PATH);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        try {
            Part part = request.getPart("file");
            String path = part.getSubmittedFileName();
            String randFilename = UUID.randomUUID() + path.substring(path.lastIndexOf("."));//
            try (InputStream inputStream = part.getInputStream()) {
                boolean isSuccess = uploadFile(inputStream, UPLOAD_FILE_PATH + randFilename);
                if (isSuccess) {
                    request.setAttribute("error", " upload successfully ");
                    HttpSession session = request.getSession();
                    session.setAttribute("avatar", randFilename);
                } else {
                    request.setAttribute("error", " upload failed ");
                }
            }
        } catch (IOException e) {
            request.setAttribute("error", " upload failed ");
        }
        response.sendRedirect(request.getContextPath() + "/test");
        //request.getRequestDispatcher("/test").forward(request, response);
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
