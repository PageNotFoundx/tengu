package org.raniaia.approve.components.jdbc.datasource.distribute;

/*
 * Copyright (C) 2020 tiansheng All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * Creates on 2020/3/25.
 */

import org.raniaia.approve.components.jdbc.datasource.pooled.PooledDataSource;
import org.raniaia.approve.components.jdbc.datasource.unpooled.IDataSource;
import org.raniaia.available.list.Lists;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 支持分布式的数据源
 * Distributed datasource.
 *
 * @author tiansheng
 */
public class DistributeDatasource {

    List<PooledDataSource> dataSources = Lists.newArrayList();

    public DistributeDatasource(PooledDataSource[] pooledDataSources){
        dataSources.addAll(Arrays.asList(pooledDataSources));
    }

    public DistributeDatasource(Collection<PooledDataSource> pooledDataSources){
        dataSources.addAll(pooledDataSources);
    }

    public DistributeDatasource(IDataSource... iDataSources){
        for (IDataSource iDataSource : iDataSources) {
            dataSources.add(new PooledDataSource(iDataSource));
        }
    }

}
