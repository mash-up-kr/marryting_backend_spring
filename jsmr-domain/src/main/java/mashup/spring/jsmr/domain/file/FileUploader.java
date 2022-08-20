package mashup.spring.jsmr.domain.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUploader {

    String upload(MultipartFile multipartFile) throws IOException;
}
