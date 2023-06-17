package ru.kaznacheev.chat.service;

import org.springframework.web.multipart.MultipartFile;
import ru.kaznacheev.chat.dto.ResponseFileId;
import ru.kaznacheev.chat.entity.File;

import java.util.UUID;

public interface FileService {

    ResponseFileId save(MultipartFile file);
    File getFile(UUID id);

}
