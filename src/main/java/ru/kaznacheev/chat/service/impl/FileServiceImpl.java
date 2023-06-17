package ru.kaznacheev.chat.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.kaznacheev.chat.dto.ResponseFileId;
import ru.kaznacheev.chat.entity.File;
import ru.kaznacheev.chat.exception.MainException;
import ru.kaznacheev.chat.repository.FileRepository;
import ru.kaznacheev.chat.service.FileService;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class FileServiceImpl implements FileService {

    private FileRepository fileRepository;

    @Override
    public ResponseFileId save(MultipartFile file) {
        try {
            File newFile = File.builder()
                    .name(file.getOriginalFilename())
                    .contentType(file.getContentType())
                    .size(file.getSize())
                    .data(file.getBytes())
                    .build();
            newFile = fileRepository.save(newFile);
            return new ResponseFileId(newFile.getId().toString(), newFile.getName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public File getFile(UUID id) {
        Optional<File> file = fileRepository.findById(id);
        if (file.isEmpty()) {
            throw new MainException(HttpStatus.NOT_FOUND, "Нет файла с таким id");
        }
        return file.get();
    }

}
