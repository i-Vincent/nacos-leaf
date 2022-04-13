package com.ivincent.leaf.annotation;

import com.ivincent.leaf.core.common.PropertyFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @author zhaodong.xzd (github.com/yaccc)
 * @date 2019/10/09
 */
@Component
@ConfigurationProperties(prefix = "leaf",ignoreUnknownFields = true)
@Primary
//@PropertySource("classpath:leaf.properties")
public class LeafSpringBootProperties {
    private String name;
    private Segment segment;
    private Snowflake snowflake;
    public static class Segment{
        private boolean enable=false;
        private String url;
        private String username;
        private String password;
        private String driverClassName;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public boolean isEnable() {
            return enable;
        }
        public void setEnable(boolean enable) {
            this.enable = enable;
        }

        public String getDriverClassName() {
            return driverClassName;
        }

        public void setDriverClassName(String driverClassName) {
            this.driverClassName = driverClassName;
        }

        @Override
        public String toString() {
            return "Segment{" +
                    "enable=" + enable +
                    ", url='" + url + '\'' +
                    ", username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    ", driverClassName='" + driverClassName + '\'' +
                    '}';
        }
    }
    public static class Snowflake{
        private boolean enable =false;
        private String address;
        private int port;
        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public boolean isEnable() {
            return enable;
        }
        public void setEnable(boolean enable) {
            this.enable = enable;
        }

        @Override
        public String toString() {
            return "Snowflake{" +
                    "enable=" + enable +
                    '}';
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        PropertyFactory.setLeafName(name);
        this.name = name;
    }

    public Segment getSegment() {
        return segment;
    }

    public void setSegment(Segment segment) {
        this.segment = segment;
    }

    public Snowflake getSnowflake() {
        return snowflake;
    }

    public void setSnowflake(Snowflake snowflake) {
        this.snowflake = snowflake;
    }

    @Override
    public String toString() {
        return "LeafSpringBootProperties{" +
                "name='" + name + '\'' +
                ", segment=" + segment +
                ", snowflake=" + snowflake +
                '}';
    }
}
