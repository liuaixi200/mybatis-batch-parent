    
/*
 * Copyright (C) 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.liuax.mybatis.batch;

import org.apache.ibatis.executor.BatchExecutor;

/**
 * 功能描述
 *
 * @author liuax01
 * @date 2019/5/2 20:27
 */
public class MybatisBatchPo {

    private boolean needBatch;

    private BatchExecutor batchExecutor;

    private int batchCommit;

    private int modBatchCommit;

    public int getModBatchCommit() {
        return modBatchCommit;
    }

    public void setModBatchCommit(int modBatchCommit) {
        this.modBatchCommit = modBatchCommit;
    }

    public Boolean getNeedBatch() {
        return needBatch;
    }

    public void setNeedBatch(Boolean needBatch) {
        this.needBatch = needBatch;
    }

    public BatchExecutor getBatchExecutor() {
        return batchExecutor;
    }

    public void setBatchExecutor(BatchExecutor batchExecutor) {
        this.batchExecutor = batchExecutor;
    }

    public int getBatchCommit() {
        return batchCommit;
    }

    public void setBatchCommit(int batchCommit) {
        this.batchCommit = batchCommit;
    }
}
