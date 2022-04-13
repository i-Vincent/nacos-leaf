package com.ivincent.leaf.core;


import com.ivincent.leaf.core.common.Result;

public interface IDGen {
    Result get(String key);
    boolean init();
}
