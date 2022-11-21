package luongld.freeswitch.domain.exceptions;

import luongld.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class DomainDuplicateException extends BusinessException {
    public DomainDuplicateException(String domainName) {
        super(HttpStatus.BAD_REQUEST, String.format("Domain [%s] already exists", domainName));
    }
}
