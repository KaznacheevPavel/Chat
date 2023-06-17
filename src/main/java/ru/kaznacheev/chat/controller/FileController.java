package ru.kaznacheev.chat.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.kaznacheev.chat.dto.ResponseFileId;
import ru.kaznacheev.chat.entity.File;
import ru.kaznacheev.chat.service.FileService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/files")
@AllArgsConstructor
public class FileController {

    private FileService fileService;

    @PostMapping
    public ResponseFileId uploadFile(@RequestParam("file") MultipartFile file) {
        return fileService.save(file);
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable UUID id) {
        File file = fileService.getFile(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .contentType(MediaType.valueOf(file.getContentType()))
                .body(file.getData());
    }

}
