package consultation.by.video.call.service.impl;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.Identity;
import com.google.cloud.Policy;
import com.google.cloud.storage.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import consultation.by.video.call.service.FirebaseService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;


@Service
public class FirebaseServiceImpl implements FirebaseService {

//    === Initialize FIREBASE ===
    @Data
    @Configuration
    @ConfigurationProperties(prefix = "firebase")
    public class Properties {
        private String bucketName;
        private String imageUrl;
    }
    @Autowired
    Properties properties;
    @EventListener
    public void init(ApplicationReadyEvent event) {
        try {
            FileInputStream serviceAccount =
                    new FileInputStream("./serviceAccountKey.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setStorageBucket(properties.getBucketName())
                    .build();

            FirebaseApp.initializeApp(options);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String subirImagen(MultipartFile[] files) {
        for (MultipartFile file : files) {
            try {
                String fileName = this.save(file);
                String imageUrl = this.getImageUrl(fileName);
                return imageUrl ;
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return "Ha ocurrido un error.";
    }

    @Override
    public String getImageUrl(String name) {
        return String.format(properties.imageUrl, name);
    }

//    === Upload Images ===
    @Override
    public String save(MultipartFile file) throws IOException {
        Bucket bucket = StorageClient.getInstance().bucket();
        String name = generateFileName(file.getOriginalFilename());
        bucket.create(name, file.getBytes(), file.getContentType());
        return name;
    }

    @Override
    public String save(BufferedImage bufferedImage, String originalFileName) throws IOException {
        byte[] bytes = getByteArrays(bufferedImage, getExtension(originalFileName));
        Bucket bucket = StorageClient.getInstance().bucket();
        String name = generateFileName(originalFileName);
        bucket.create(name, bytes);
        return name;
    }

    @Override
    public void delete(String name) throws IOException {
        Bucket bucket = StorageClient.getInstance().bucket();
        if (StringUtils.isEmpty(name)) {
            throw new IOException("invalid file name");
        }
        Blob blob = bucket.get(name);
        if (blob == null) {
            throw new IOException("file not found");
        }
        blob.delete();
    }

}
