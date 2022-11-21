package luongld.freeswitch.xml.sections.directory;


import javax.xml.bind.annotation.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@XmlRootElement(name = "groups")
@XmlAccessorType(XmlAccessType.NONE)
public class DirectoryGroup {

    @XmlElementRef(name = "group")
    private final Group group;

    private DirectoryGroup() {
        this.group = new Group();
    }

    public DirectoryGroup(Group group) {
        assert group != null;

        this.group = group;
    }

    public Group getGroup() {
        return group;
    }

    @XmlRootElement(name = "group")
    @XmlAccessorType(XmlAccessType.NONE)
    public static class Group {

        @XmlAttribute
        private final String name = "default";

        @XmlElementRef(name = "users")
        private final Directory.DirectoryList users;

        private Group() {
            this.users = new Directory.DirectoryList();
        }

        public Group(Directory.DirectoryList users) {
            assert users != null;

            this.users = users;
        }

        public Group(Collection<Directory> users) {
            this();
            assert users != null;

            for (var user : users) this.users.add(user);
        }

        public Group(Directory directory) {
            this(new HashSet<>(List.of(directory)));
        }

        public Directory.DirectoryList getUsers() {
            return users;
        }
    }
}
