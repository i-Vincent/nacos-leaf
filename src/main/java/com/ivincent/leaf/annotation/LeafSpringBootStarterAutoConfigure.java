package com.ivincent.leaf.annotation;

import com.alibaba.druid.pool.DruidDataSource;
import com.ivincent.leaf.core.exception.InitException;
import com.ivincent.leaf.core.service.SegmentService;
import com.ivincent.leaf.core.service.SnowflakeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author zhaodong.xzd (github.com/yaccc)
 * @date 2019/10/09
 * @since support springboot starter with dubbo and etc rpc
 */
@Configuration
@EnableConfigurationProperties(LeafSpringBootProperties.class)
public class LeafSpringBootStarterAutoConfigure {
    private Logger logger = LoggerFactory.getLogger(LeafSpringBootStarterAutoConfigure.class);
    @Autowired
    private LeafSpringBootProperties properties;

    @Bean
    public DataSource druidDataSource() throws SQLException {
        if (properties != null && properties.getSegment() != null && properties.getSegment().isEnable()) {
            DruidDataSource dataSource = new DruidDataSource();
            dataSource.setUrl(properties.getSegment().getUrl());
            dataSource.setUsername(properties.getSegment().getUsername());
            dataSource.setPassword(properties.getSegment().getPassword());
            dataSource.setDriverClassName(properties.getSegment().getDriverClassName());
            dataSource.init();
            return dataSource;
        }
        return null;
    }

    @Bean
    public SegmentService initLeafSegmentStarter(DataSource druidDataSource) throws Exception {
        if (properties != null && properties.getSegment() != null && properties.getSegment().isEnable()) {
            SegmentService segmentService = new SegmentService(druidDataSource);
            return segmentService;
        }
        logger.warn("init leaf segment ignore properties is {}", properties);
        return null;
    }

    @Bean
    public SnowflakeService initLeafSnowflakeStarter() throws InitException {
        if (properties != null && properties.getSnowflake() != null && properties.getSnowflake().isEnable()) {
            SnowflakeService snowflakeService = new SnowflakeService(properties.getSnowflake().getAddress(), properties.getSnowflake().getPort());
            return snowflakeService;
        }
        logger.warn("init leaf snowflake ignore properties is {}", properties);
        return null;
    }
}
