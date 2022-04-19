package com.konak.goodgames.config;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

@Configuration
public class GoogleDriveConfiguration {

  @Value("${google.drive.credentials.location}")
  private String CREDENTIALS_FILE_PATH;

  private static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE);
  private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
  private static final String APPLICATION_NAME = "konak-goodgames-be";

  private Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
    try (InputStream in = new FileInputStream(CREDENTIALS_FILE_PATH)) {
      return GoogleCredential.fromStream(in, HTTP_TRANSPORT, JSON_FACTORY).createScoped(SCOPES);
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException("File not found: " + CREDENTIALS_FILE_PATH);
    }
  }

  @Bean
  public Drive getGoogleDrive() throws IOException, GeneralSecurityException {
    final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
    return new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
        .setApplicationName(APPLICATION_NAME)
        .build();
  }
}
