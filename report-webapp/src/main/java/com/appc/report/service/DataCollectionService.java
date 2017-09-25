package com.appc.report.service;

import com.appc.report.model.DataCollection;

/**
 *  DataCollectionService
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: 2017-9-25 
 */
public interface DataCollectionService extends CommonService<DataCollection>{

    void save(DataCollection collection);
}
