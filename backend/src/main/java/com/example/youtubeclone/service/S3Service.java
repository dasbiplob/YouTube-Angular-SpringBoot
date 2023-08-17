        package com.example.youtubeclone.service;

        import com.amazonaws.services.s3.AmazonS3;
        import com.amazonaws.services.s3.AmazonS3Client;
        import com.amazonaws.services.s3.AmazonS3ClientBuilder;
        import com.amazonaws.services.s3.model.CannedAccessControlList;
        import com.amazonaws.services.s3.model.ObjectMetadata;
        import lombok.RequiredArgsConstructor;
        import org.springframework.http.HttpStatus;
        import org.springframework.stereotype.Service;
        import org.springframework.util.StringUtils;
        import org.springframework.web.multipart.MultipartFile;
        import org.springframework.web.server.ResponseStatusException;

        import java.io.File;
        import java.io.IOException;
        import java.util.UUID;

        @Service
        @RequiredArgsConstructor
        public class S3Service implements FileService {


            // Constructor Injection
            public static final String BUCKET_NAME = "youtubeclones3bucket";
            private final AmazonS3Client awsS3Client;

            /**
             * Upload File to AWS S3 and Prepare a key
             * 1st will ge the file name extension from the OriginalFile Name
             * 2nd will generate the random UUID along with the filenameExtension
             * 3rd will create the ObjectMetadata() from service.s3.modelObjectMetadata and set the ContentLength()
             * and ContentType() to save the data into mongodb.
             * From AmazonS3Client object we are providing the details for storing the video files
             * To access the amazonS3Client publically from Angular
             */
            @Override
            public String  uploadFile(MultipartFile file){

                var filenameExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());

                var key = UUID.randomUUID().toString() + "." + filenameExtension;

                var  metaData = new ObjectMetadata();
                metaData.setContentLength(file.getSize());
                metaData.setContentType(file.getContentType());

                try {
                    System.out.println(BUCKET_NAME);
                    System.out.println(key);
                    System.out.println(file.getInputStream());
                    System.out.println(metaData);
                    awsS3Client.putObject(BUCKET_NAME,key,file.getInputStream(),metaData);
                } catch (IOException e) {
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                            "An Exception Occurred While Uploading The File");
                }

                awsS3Client.setObjectAcl(BUCKET_NAME, key, CannedAccessControlList.PublicRead);
               return awsS3Client.getResourceUrl(BUCKET_NAME, key);
            }
        }
