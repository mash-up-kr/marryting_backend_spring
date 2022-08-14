package mashup.spring.jsmr.adapter.infrastructure.file;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.domain.file.FileUploader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class S3Upload implements FileUploader {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.s3.dir}")
    private String dir;

    private final AmazonS3Client s3Client;

    @Override
    public String upload(InputStream inputStream, String originFileName, String fileSize) {
        String s3FileName = UUID.randomUUID() + "-" + originFileName;

        ObjectMetadata objMeta = new ObjectMetadata();
        objMeta.setContentLength(Long.parseLong(fileSize));

        s3Client.putObject(bucket, s3FileName, inputStream, objMeta);

        return s3Client.getUrl(bucket, dir + s3FileName).toString();
    }

    @Override
    public List<String> multiUpload(MultipartFile[] multipartFiles, String fileSize) throws IOException {
        List<String> imageList = new ArrayList<>();
        for (MultipartFile multiPart : multipartFiles) {
            imageList.add(upload(multiPart.getInputStream(), multiPart.getOriginalFilename(), fileSize));
        }
        return imageList;
    }
}
