package luongld.freeswitch.xml.sections;

import luongld.freeswitch.xml.Section;
import luongld.freeswitch.xml.sections.configuration.accesscontrol.AccessControlConfiguration;
import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.annotation.*;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement(name = "section")
@XmlAccessorType(XmlAccessType.NONE)
public final class ConfigurationSection extends Section {

    @XmlElementRef(name = "configuration")
    private Set<? extends Configuration> configurations;

    private ConfigurationSection() {
        super("configuration");
        this.configurations = new HashSet<>();
    }

    public ConfigurationSection(Collection<? extends Configuration> configurations) {
        this();

        if (configurations == null || configurations.isEmpty()) return;

        this.configurations = new HashSet<>(configurations);
    }

    public ConfigurationSection(Configuration configuration) {
        this(Collections.singleton(configuration));
    }

    public Set<? extends Configuration> getConfigurations() {
        return configurations;
    }

    public void setConfigurations(Set<? extends Configuration> configurations) {
        this.configurations = configurations;
    }

    @XmlRootElement(name = "configuration")
    @XmlAccessorType(XmlAccessType.NONE)
    @XmlSeeAlso({
            AccessControlConfiguration.class
    })
    public static abstract class Configuration {

        @XmlAttribute(name = "name")
        private String name;

        @XmlAttribute(name = "description")
        private String description;

        private Configuration() {}

        protected Configuration(String name, String description) {
            this();

            assert StringUtils.isNotEmpty(name);
            this.name = name;
            this.description = description;
        }

        protected Configuration(String name) {
            this(name, null);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
