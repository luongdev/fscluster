package luongld.freeswitch.xml.shared;

import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "variable")
@XmlAccessorType(XmlAccessType.NONE)
public class Variable {

    @XmlAttribute
    private String name;

    @XmlAttribute
    private String value;

    private Variable() {
    }

    public Variable(String name, String value) {
        this();
        assert StringUtils.isNotEmpty(name);
        assert value != null;

        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
