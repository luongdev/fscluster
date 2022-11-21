package luongld.freeswitch.domain.exceptions;

import luongld.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class DomainNotFoundException extends BusinessException {

    public DomainNotFoundException(String domainName) {
        super(HttpStatus.BAD_REQUEST, String.format("Domain [%s] not found", domainName));
    }
}
