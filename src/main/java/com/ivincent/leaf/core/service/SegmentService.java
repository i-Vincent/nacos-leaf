package com.ivincent.leaf.core.service;

import com.ivincent.leaf.core.IDGen;
import com.ivincent.leaf.core.common.Result;
import com.ivincent.leaf.core.exception.InitException;
import com.ivincent.leaf.core.segment.SegmentIDGenImpl;
import com.ivincent.leaf.core.segment.dao.IDAllocDao;
import com.ivincent.leaf.core.segment.dao.impl.IDAllocDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;

public class SegmentService {
    private Logger logger = LoggerFactory.getLogger(SegmentService.class);

    private IDGen idGen;
//    private DruidDataSource dataSource;

//    public SegmentService(String url, String username, String pwd, String driverClassName) throws SQLException, InitException {
//        Preconditions.checkNotNull(url,"database url can not be null");
//        Preconditions.checkNotNull(username,"username can not be null");
//        Preconditions.checkNotNull(pwd,"password can not be null");
//        // Config dataSource
//        dataSource = new DruidDataSource();
//        dataSource.setUrl(url);
//        dataSource.setUsername(username);
//        dataSource.setPassword(pwd);
//        dataSource.setDriverClassName(driverClassName);
//        dataSource.init();
//        // Config Dao
//        IDAllocDao dao = new IDAllocDaoImpl(dataSource);
//        // Config ID Gen
//        idGen = new SegmentIDGenImpl();
//        ((SegmentIDGenImpl) idGen).setDao(dao);
//        if (idGen.init()) {
//            logger.info("Segment Service Init Successfully");
//        } else {
//            throw new InitException("Segment Service Init Fail");
//        }
//    }

    public SegmentService(DataSource druidDataSource) throws InitException {
        // Config Dao
        IDAllocDao dao = new IDAllocDaoImpl(druidDataSource);
        // Config ID Gen
        idGen = new SegmentIDGenImpl();
        ((SegmentIDGenImpl) idGen).setDao(dao);
        if (idGen.init()) {
            logger.info("Segment Service Init Successfully");
        } else {
            throw new InitException("Segment Service Init Fail");
        }
    }

    public Result getId(String key) {
        return idGen.get(key);
    }

    public SegmentIDGenImpl getIdGen() {
        if (idGen instanceof SegmentIDGenImpl) {
            return (SegmentIDGenImpl) idGen;
        }
        return null;
    }
}
