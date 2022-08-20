package mashup.spring.jsmr.domain.file;

import java.io.IOException;
import java.io.InputStream;

public interface FileUploader {

    String upload(InputStream inputStream, String fileSize) throws IOException;
}
