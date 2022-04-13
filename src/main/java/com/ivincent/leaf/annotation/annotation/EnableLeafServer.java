package com.ivincent.leaf.annotation.annotation;

import com.ivincent.leaf.annotation.LeafSpringBootStarterAutoConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;
/**
 * @author zhaodong.xzd (github.com/yaccc)
 * @date 2019/10/09
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(LeafSpringBootStarterAutoConfigure.class)
@Inherited
public @interface EnableLeafServer {
}
