package luongld.freeswitch.xml.shared;

import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

@XmlRootElement(name = "params")
@XmlAccessorType(XmlAccessType.NONE)
public class ParamList {

    @XmlElementRef(name = "param")
    private final Set<Param> params;

    public ParamList() {
        this.params = new HashSet<>();
    }

    public ParamList(Collection<Param> params) {
        this();

        if (params != null && !params.isEmpty()) this.params.addAll(params);
    }

    public ParamList add(String name, Object value) {
        assert StringUtils.isNotEmpty(name);
        assert value != null;

        params.add(new Param(name, String.valueOf(value)));

        return this;
    }

    public Set<Param> getParams() {
        return params;
    }
}
