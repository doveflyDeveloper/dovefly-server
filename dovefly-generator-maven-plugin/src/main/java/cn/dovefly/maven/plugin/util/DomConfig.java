package cn.dovefly.maven.plugin.util;

/**
 * Created by fengchunming on 2017/7/26.
 */
public class DomConfig {
    private JdbcConnection jdbcConnection;
    private TableGenerator tableGenerator;

    class JdbcConnection {
        private String driverClass;
        private String connectionURL;
        private String userId;
        private String password;

        public String getDriverClass() {
            return driverClass;
        }

        public void setDriverClass(String driverClass) {
            this.driverClass = driverClass;
        }

        public String getConnectionURL() {
            return connectionURL;
        }

        public void setConnectionURL(String connectionURL) {
            this.connectionURL = connectionURL;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    class TableGenerator {
        private String tableName;
        private String domainObjectName;




    }

}
