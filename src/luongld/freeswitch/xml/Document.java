package luongld.freeswitch.xml;

import javax.xml.bind.annotation.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement(name = "document")
@XmlAccessorType(XmlAccessType.NONE)
public class Document {

    @XmlAttribute(name = "type")
    private String type;

    @XmlElementRef(name = "section")
    private Set<? extends Section> sections;

    public Document() {
        this.type = "freeswitch/xml";
        this.sections = new HashSet<>();
    }

    public Document(Collection<? extends Section> sections) {
        this();
        if (sections == null || sections.isEmpty()) return;

        this.sections = new HashSet<>(sections);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<? extends Section> getSections() {
        return sections;
    }

    public void setSections(Set<? extends Section> sections) {
        this.sections = sections;
    }
}
