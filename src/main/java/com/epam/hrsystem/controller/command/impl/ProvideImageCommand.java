package com.epam.hrsystem.controller.command.impl;

import com.epam.hrsystem.controller.FileUploadingServlet;
import com.epam.hrsystem.controller.attribute.RequestParameter;
import com.epam.hrsystem.controller.command.ActionCommand;
import com.epam.hrsystem.controller.command.CommandResult;
import com.epam.hrsystem.exception.CommandException;
import com.epam.hrsystem.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ProvideImageCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();
    private static final String DEFAULT_AVATAR_NAME = "default_avatar.png";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String fileName = request.getParameter(RequestParameter.FILE_NAME);
        if (fileName != null && !fileName.isEmpty()) {
            try (ServletOutputStream outputStream = response.getOutputStream()) {
                outputStream.write(readFile(fileName));
            } catch (IOException | ServiceException e) {
                logger.log(Level.ERROR, e);
            }
        }
        return null;
    }

    private byte[] readFile(String fileName) throws ServiceException {
        byte[] result;
        String fileUri = FileUploadingServlet.UPLOAD_AVATAR_PATH + fileName;
        Path filePath = Paths.get(fileUri);
        if (!Files.exists(filePath)) {
            filePath = Paths.get(FileUploadingServlet.UPLOAD_AVATAR_PATH + DEFAULT_AVATAR_NAME);
        }
        try {
            result = Files.readAllBytes(filePath);
        } catch (IOException e) {
            throw new ServiceException(e);
        }
        return result;
    }
}
