/**
 * Copyright (c) 2011-2014, L.cm (596392912@qq.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.baomidou.framework.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.concurrent.atomic.AtomicLong;

/**
 * <p>
 * ������Դ������,��֧�ֶ�ӿ�
 * </p>
 *
 * @author L.cm
 * @Date 2016-04-14
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicDataSource.class);

    private static final AtomicLong dbCount = new AtomicLong(0L);
    private static final ThreadLocal<String> dataSourceHolder = new ThreadLocal<String>();

    /**
     * ѡ��ʹ�����ݿ⣬����ѡ��ŵ���ǰThreadLocal��ջ��
     */
    public static void use(String key, int dbSize) {
        if (dbSize < 1) {
            dataSourceHolder.set(key);
        } else {
            long c = dbCount.incrementAndGet();
            c = c % dbSize;
            dataSourceHolder.set(key + c);
        }
    }

    /**
     * ����
     */
    public static void reset() {
        dataSourceHolder.remove();
    }

    /**
     * �����ѡ��ʹ�����ݿ�
     */
    @Override
    protected Object determineCurrentLookupKey() {
        String key = dataSourceHolder.get();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("currenty datasource :" + key);
        }
        return key;
    }

}