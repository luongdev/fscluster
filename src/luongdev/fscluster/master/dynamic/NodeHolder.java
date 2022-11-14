package luongdev.fscluster.master.dynamic;

public class NodeHolder {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static void setNode(String dbType) {
        contextHolder.set(dbType);
    }

    public static String getNode() {
        return contextHolder.get();
    }

    public static void clear() {
        contextHolder.remove();
    }

}
