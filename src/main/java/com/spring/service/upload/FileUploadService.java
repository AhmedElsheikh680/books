package com.spring.service.upload;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import com.spring.entity.Author;
import com.spring.exception.FileStorageException;
import com.spring.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileUploadService {

    private final AuthorService authorService;

    private Path fileStorageLocation;

    @Value("${file.upload.base-path}")
    private String basePath;
    //    private final String basePath = "D:\\Global\\book\\";
//	@Value("${google.storage.bucket-name}")
    private String googleBucketName = "";

    //	@Value("${google.storage.project-id}")
    private String projectId = "";

    //	@Value("${google.storage.credentials.path}")
    private String credentialPath = "";

//	endpointUrl: https://s3.us-east-2.amazonaws.com

    //	@Value("${aws.s3.bucket}")
    private String awsBucketName;

    private final AmazonS3 amazonS3;

    Logger log = LoggerFactory.getLogger(FileUploadService.class);


    public String storeFile(File file, Long id, String pathType) {

        // create uploaded path
        this.fileStorageLocation = Paths.get(basePath + pathType).toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.",
                    ex);
        }

        String fileName = StringUtils.cleanPath(id + "-" + file.getName());

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            InputStream targetStream = new FileInputStream(file);
            Files.copy(targetStream, targetLocation, StandardCopyOption.REPLACE_EXISTING);

            updateImagePath(id, pathType, pathType + "/" + fileName);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }


    public void updateImagePath(Long id, String pathType, String imagePath) {
        if (pathType.contains("authors")) {
            Author author = authorService.findById(id);
            author.setImagePath(imagePath);
            authorService.update(author);
        }
    }

    public File convertMultiPartFileToFile(final MultipartFile multipartFile) {
        final File file = new File(multipartFile.getOriginalFilename());
        try (final FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(multipartFile.getBytes());
        } catch (final IOException ex) {
            log.error("Error converting the multi-part file to file= ", ex.getMessage());
        }
        return file;
    }


    /* Start AWS */
    public String cloudUploadFile(MultipartFile file, Long id, String pathType) {

        String fileName = null;

        if (file.getContentType().contains("image")) {
            fileName = id + "_" + UUID.randomUUID() + ".jpg";
        } else {
            fileName = id + file.getOriginalFilename();
        }
        String uniqueFileName = pathType + fileName;
        try {

            awsUploadObject(uniqueFileName, file);

            updateImagePath(id, pathType, pathType + "/" + fileName);
        } catch (Exception e) {
            e.printStackTrace();
            throw new FileStorageException("Error converting the multi-part file to file= ", e);
        }

        return fileName;
    }


    public void awsUploadObject(final String uniqueFileName, final MultipartFile multipartFile) {

        log.info("Uploading file with name= " + uniqueFileName);

        try {

            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentLength(IOUtils.toByteArray(multipartFile.getInputStream()).length);

            final PutObjectRequest putObjectRequest = new PutObjectRequest(awsBucketName, uniqueFileName,
                    multipartFile.getInputStream(), meta).withCannedAcl(CannedAccessControlList.PublicRead);

            PutObjectResult result = amazonS3.putObject(putObjectRequest);
            log.info("File uploaded successfully result" + result.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * @param fileUrl
     */
    public void awsDeleteObject(String fileUrl) {
        final DeleteObjectRequest req = new DeleteObjectRequest(awsBucketName, fileUrl);
        amazonS3.deleteObject(req);
        log.info("File deleted from bucket " + awsBucketName + " as " + fileUrl);
    }

    /* End AWS*/

}
