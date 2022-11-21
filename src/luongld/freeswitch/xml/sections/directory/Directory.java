package luongld.freeswitch.xml.sections.directory;


import luongld.freeswitch.xml.shared.ParamList;
import luongld.freeswitch.xml.shared.VariableList;
import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.annotation.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.NONE)
public class Directory {

    @XmlAttribute
    private String id;

    @XmlAttribute
    private String type;

    @XmlElementRef(name = "params")
    private final ParamList params;

    @XmlElementRef(name = "variables")
    private final VariableList variables;

    private Directory() {
        this.params = new ParamList();
        this.variables = new VariableList();
    }

    public Directory(String id, String type) {
        this();

        assert StringUtils.isNotEmpty(id);

        this.id = id;
        this.type = type;
    }

    public Directory param(String name, Object value) {
        this.params.add(name, value);

        return this;
    }

    public Directory variable(String name, Object value) {
        this.variables.add(name, value);

        return this;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public ParamList getParams() {
        return params;
    }

    public VariableList getVariables() {
        return variables;
    }

    @XmlRootElement(name = "users")
    @XmlAccessorType(XmlAccessType.NONE)
    public static class DirectoryList {

        @XmlElementRef(name = "user")
        private final Set<Directory> directories;

        public DirectoryList() {
            this.directories = new HashSet<>();
        }

        public DirectoryList(Collection<Directory> directories) {
            this();

            if (directories != null && !directories.isEmpty()) this.directories.addAll(directories);
        }

        public DirectoryList add(Directory directory) {
            assert directory != null;

            this.directories.add(directory);

            return this;
        }

        public Set<Directory> getDirectories() {
            return directories;
        }
    }
}
