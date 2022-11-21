package luongld.freeswitch.configurations.directory.exceptions;

import luongld.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class ExtensionNotFoundException extends BusinessException {
    public ExtensionNotFoundException(String extension) {
        super(HttpStatus.BAD_REQUEST, String.format("Extension [%s] not found", extension));
    }
}
