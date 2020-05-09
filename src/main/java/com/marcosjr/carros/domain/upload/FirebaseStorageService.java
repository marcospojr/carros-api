package com.marcosjr.carros.domain.upload;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import com.marcosjr.carros.api.upload.UploadInput;
import com.marcosjr.carros.api.upload.UploadOutput;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

//https://firebase.google.com/docs/storage/admin/start
@Service
public class FirebaseStorageService {

    @PostConstruct
    private void init() throws IOException {
        if (FirebaseApp.getApps().isEmpty()) {
            InputStream in = FirebaseStorageService.class.getResourceAsStream("path/to/serviceAccountKey.json");

            System.out.println(in);

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(in))
                    .setStorageBucket("gs://carros-f6f39.appspot.com/")
                    .setDatabaseUrl("https://carros-f6f39.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);

        }
    }

    public String upload (UploadInput uploadInput) {
        Bucket bucket = StorageClient.getInstance().bucket();
        System.out.println(bucket);

        byte[] bytes = Base64.getDecoder().decode(uploadInput.getBase64());

        String fileName = uploadInput.getFileName();
        Blob blob = bucket.create(fileName, bytes, uploadInput.getMimeType());

//      Assina uma URL por x dias
//      URL signedUrl = blob.signUrl(30, TimeUnit.DAYS);

//      Deixa public para todos os usuarios
        blob.createAcl(Acl.of(Acl.User.ofAllUsers(),Acl.Role.READER));

        return String.format("https://storage.googleapis.com/%s/%s", bucket.getName(), fileName);
    }
}
