package luongld.freeswitch.xml.sections.directory;

import luongld.freeswitch.xml.shared.ParamList;
import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "domain")
@XmlAccessorType(XmlAccessType.NONE)
public class DirectoryDomain {

    @XmlAttribute
    private String name;

    @XmlAttribute
    private boolean alias;

    @XmlElementRef(name = "params")
    private final ParamList params;

    private DirectoryDomain() {
        this.alias = true;
        this.params = new ParamList();
    }

    public DirectoryDomain(String name) {
        this();
        assert StringUtils.isNotEmpty(name);

        this.name = name;
    }

    public DirectoryDomain param(String name, Object value) {
        this.params.add(name, value);

        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAlias() {
        return alias;
    }

    public void setAlias(boolean alias) {
        this.alias = alias;
    }

    public ParamList getParams() {
        return params;
    }
}
