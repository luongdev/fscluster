package luongld.freeswitch.configurations.directory.commands;

import luongld.cqrs.Request;
import luongld.freeswitch.configurations.directory.Extension;
import org.apache.commons.lang3.StringUtils;

public class CreateExtensionCommand implements Request<Extension> {

    private String extension;
    private String domainName;
    private String password;
    private String outboundCallerNumber;
    private Integer limitMax;
    private String limitDestination;
    private int callTimeout = 30;
    private String record;
    private String description;

    private CreateExtensionCommand() {
    }

    public CreateExtensionCommand(
            String extension,
            String domainName,
            String password,
            String outboundCallerNumber,
            Integer limitMax,
            String limitDestination,
            Integer callTimeout,
            String record,
            String description) {
        this();
        assert StringUtils.isNotEmpty(extension);
        assert StringUtils.isNotEmpty(domainName);

        this.extension = extension;
        this.domainName = domainName;
        this.outboundCallerNumber = outboundCallerNumber;
        this.limitMax = limitMax;
        this.limitDestination = limitDestination;
        this.record = record;
        this.description = description;

        if (callTimeout != null) this.callTimeout = callTimeout;
        if (StringUtils.isNotEmpty(password)) this.password = password;
    }

    public CreateExtensionCommand(String extension, String domainName, String password) {
        this(extension, domainName, password, null, null, null, null, null, null);
    }

    public CreateExtensionCommand(String extension, String domainName) {
        this(extension, domainName, null);
    }

    public String getExtension() {
        return extension;
    }

    public String getDomainName() {
        return domainName;
    }

    public String getPassword() {
        return password;
    }

    public String getOutboundCallerNumber() {
        return outboundCallerNumber;
    }

    public Integer getLimitMax() {
        return limitMax;
    }

    public String getLimitDestination() {
        return limitDestination;
    }

    public int getCallTimeout() {
        return callTimeout;
    }

    public String getRecord() {
        return record;
    }

    public String getDescription() {
        return description;
    }
}
