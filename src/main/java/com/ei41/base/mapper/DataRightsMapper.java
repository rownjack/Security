package com.ei41.base.mapper;

import com.ei41.base.model.DataRights;

public interface DataRightsMapper {

	Long createDataRights(DataRights dataRights);

	DataRights findDataRightsById(Long id);

	void updateDataRights(DataRights dataRights);

	void delteDataRights(Long id);
	
	
}
