package luongld.freeswitch.xml.shared;

import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement(name = "variables")
@XmlAccessorType(XmlAccessType.NONE)
public class VariableList {

    @XmlElementRef(name = "variable")
    private final Set<Variable> variables;

    public VariableList() {
        this.variables = new HashSet<>();
    }

    public VariableList(Collection<Variable> variables) {
        this();

        if (variables != null && !variables.isEmpty()) this.variables.addAll(variables);
    }

    public VariableList add(String name, Object value) {
        assert StringUtils.isNotEmpty(name);
        assert value != null;

        variables.add(new Variable(name, String.valueOf(value)));

        return this;
    }

    public Set<Variable> getVariables() {
        return variables;
    }
}
