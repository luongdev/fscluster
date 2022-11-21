package luongld.freeswitch.configurations.directory.commands.handlers;

import luongld.cqrs.RequestHandler;
import luongld.freeswitch.configurations.directory.Extension;
import luongld.freeswitch.configurations.directory.commands.CreateExtensionCommand;
import luongld.freeswitch.configurations.directory.exceptions.ExtensionDuplicateException;
import luongld.freeswitch.configurations.directory.repositories.ExtensionJpaRepository;
import luongld.freeswitch.domain.exceptions.DomainNotFoundException;
import luongld.freeswitch.domain.repositories.DomainJpaRepository;
import org.apache.commons.lang3.StringUtils;
import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.stereotype.Component;

import static org.passay.DigestDictionaryRule.ERROR_CODE;

@Component
public class CreateExtensionHandler implements RequestHandler<Extension, CreateExtensionCommand> {

    private final DomainJpaRepository domainJpaRepository;
    private final ExtensionJpaRepository extensionJpaRepository;

    public CreateExtensionHandler(
            DomainJpaRepository domainJpaRepository,
            ExtensionJpaRepository extensionJpaRepository) {
        this.domainJpaRepository = domainJpaRepository;
        this.extensionJpaRepository = extensionJpaRepository;
    }

    @Override
    public Extension handle(CreateExtensionCommand cmd) {
        var domainOpt = domainJpaRepository.findById(cmd.getDomainName());

        if (domainOpt.isEmpty()) throw new DomainNotFoundException(cmd.getDomainName());

        var extensionIdentifier = String.format("%s@%s", cmd.getExtension(), cmd.getDomainName());
        var extensionOpt = extensionJpaRepository.findById(extensionIdentifier);

        if (extensionOpt.isPresent()) throw new ExtensionDuplicateException(cmd.getExtension());

        var extension = new Extension(cmd.getExtension(), domainOpt.get());
        extension.setCallTimeout(cmd.getCallTimeout());
        extension.setDescription(cmd.getDescription());
        extension.setLimitMax(cmd.getLimitMax());
        extension.setLimitDestination(cmd.getLimitDestination());
        extension.setOutboundCallerNumber(cmd.getOutboundCallerNumber());
        extension.setRecord(cmd.getRecord());

        var password = cmd.getPassword();
        if (StringUtils.isEmpty(password)) {
            password = randomPassword(15);
        }

        extension.setPassword(password);

        return extensionJpaRepository.save(extension);
    }

    private String randomPassword(Integer len) {
        var gen = new PasswordGenerator();
        var lowerCaseRule = new CharacterRule(EnglishCharacterData.LowerCase);
        lowerCaseRule.setNumberOfCharacters(4);

        CharacterRule upperCaseRule = new CharacterRule(EnglishCharacterData.UpperCase);
        upperCaseRule.setNumberOfCharacters(4);

        CharacterRule digitRule = new CharacterRule(EnglishCharacterData.Digit);
        digitRule.setNumberOfCharacters(2);

        var specialChars = new CharacterData() {
            public String getErrorCode() {
                return ERROR_CODE;
            }

            public String getCharacters() {
                return "!@#$%^&*()_+";
            }
        };
        CharacterRule splCharRule = new CharacterRule(specialChars);
        splCharRule.setNumberOfCharacters(2);

        return gen.generatePassword(len == null || len < 10 ? 10 : len, splCharRule, lowerCaseRule, upperCaseRule, digitRule);
    }
}
