package com.epam.hrsystem.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

@WebServlet(urlPatterns = UrlPattern.UPLOAD_SERVLET, name = "FileUploadingServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class FileUploadingServlet extends HttpServlet {
    private static final String UPLOAD_FILE_PATH = "C:" + File.separator + "Users" + File.separator + "Peachex" + File.separator +
            "IdeaProjects" + File.separator + "hrsystem" + File.separator + "uploads" + File.separator;

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        File fileSaveDir = new File(UPLOAD_FILE_PATH);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        request.getParts().stream().forEach(part -> {
            try {
                part.write(UPLOAD_FILE_PATH + part.getSubmittedFileName());//.substring(2)
                String path = part.getSubmittedFileName();
                String randFilename = UUID.randomUUID() + path.substring(path.lastIndexOf("."));//
                part.write(UPLOAD_FILE_PATH + randFilename);
                request.setAttribute("upload_result", " upload successfully ");
            } catch (IOException e) {
                request.setAttribute("upload_result", " upload failed ");
            }
        }); //fixme
        request.getRequestDispatcher("!!!!!!!!!!!!").forward(request, response);
    }
}
