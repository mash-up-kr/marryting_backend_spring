package mashup.spring.jsmr.domain.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface FileUploader {

    String upload(InputStream inputStream, String originFileName, String fileSize) throws IOException;
    List<String> multiUpload(MultipartFile[] multipartFiles, String fileSize) throws IOException;
}
