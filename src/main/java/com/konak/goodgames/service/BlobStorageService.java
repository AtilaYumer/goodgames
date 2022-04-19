package com.konak.goodgames.service;

import java.io.File;
import java.io.IOException;

public interface BlobStorageService {
    String upload(File file, String mimeType) throws IOException;
}
