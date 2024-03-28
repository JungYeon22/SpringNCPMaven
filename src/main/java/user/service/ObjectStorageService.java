package user.service;

import org.springframework.web.multipart.MultipartFile;

public interface ObjectStorageService {
    String uploadFile(String bucketName, String s, MultipartFile img);

    void deleteFile(String bucketName, String imageFileName);
}
