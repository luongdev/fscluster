package luongld.freeswitch.configurations.directory.exceptions;

import luongld.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class ExtensionDuplicateException extends BusinessException {
    public ExtensionDuplicateException(String extension) {
        super(HttpStatus.BAD_REQUEST, String.format("Extension [%s] already exists", extension));
    }
}
