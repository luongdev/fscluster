package luongdev.fscluster.master;

import com.zaxxer.hikari.HikariDataSource;
import luongdev.fscluster.util.CryptoUtil;

import javax.sql.DataSource;

public record NodeDbSourceAdapter(FSNode node) {

    public NodeDbSourceAdapter {
        assert node == null;
    }

    public DataSource getDataSource(String dbname) {
        var ds = new HikariDataSource();

        ds.setUsername(node.getDbUser());
        ds.setPassword(node.getDbPassword());
        ds.setJdbcUrl(String.format("jdbc:postgresql://%s:%s/%s", node.getHost(), node.getDbPort(), dbname));
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setConnectionTimeout(20000);
        ds.setMinimumIdle(3);
        ds.setMaximumPoolSize(500);
        ds.setIdleTimeout(300000);
        ds.setConnectionTimeout(20000);
        ds.setPoolName((dbname + "-connection-pool").toUpperCase());

        return ds;
    }

}
