/**
 * Project: dubbo.registry.server-1.1.0-SNAPSHOT
 * <p>
 * File Created at 2010-6-30
 * $Id: ConfigService.java 181723 2012-06-26 01:56:06Z tony.chenl $
 * <p>
 * Copyright 2008 Alibaba.com Croporation Limited.
 * All rights reserved.
 * <p>
 * This software is the confidential and proprietary information of
 * Alibaba Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Alibaba.com.
 */
package com.alibaba.dubbo.governance.service;

import com.alibaba.dubbo.registry.common.domain.Config;

import java.util.List;
import java.util.Map;

/**
 * TODO Comment of ConfigDAO
 *
 * @author rain.chenjr
 */
public interface ConfigService {

    void update(List<Config> configs);

    Map<String, String> findAllConfigsMap();

}
