package com.konak.goodgames.service.impl;

import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.konak.goodgames.service.BlobStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;

@Service
@Slf4j
@RequiredArgsConstructor
public class BlobStorageServiceImpl implements BlobStorageService {

  public static final String GOOGLE_DRIVE_IMAGE_URL = "https://drive.google.com/uc?id=";

  private final Drive driveService;

  @Override
  public String upload(java.io.File file, String mimeType) throws IOException {
    try {
      File fileMetadata = new File();
      fileMetadata.setName(file.getName());
      fileMetadata.setParents(Collections.singletonList("1gEHQ1suDMp_2s3NSRMyZ7m5P4uR8rh8U"));

      FileContent mediaContent = new FileContent(mimeType, file);

      File uploadedFile = driveService.files().create(fileMetadata, mediaContent).setFields("id").execute();
      file.delete();
      return String.format("%s%s", GOOGLE_DRIVE_IMAGE_URL, uploadedFile.getId());
    } catch (IOException e) {
      log.error("Cannot upload file to blob: {}", e.getMessage());
      throw new IOException(e.getMessage());
    }
  }

  @Override
  public void delete(String url) throws IOException {
    try {
      String fileId = extractFileIdFromUrl(url);
      driveService.files().delete(fileId).execute();
    } catch (IOException e) {
      log.error("Cannot delete file to blob: {}", e.getMessage());
    }
  }

  private String extractFileIdFromUrl(String url) {
    return url.substring(GOOGLE_DRIVE_IMAGE_URL.length());
  }
}
