package com.ei41.base.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ei41.base.condition.BtUnitsCondition;
import com.ei41.base.mapper.BtUnitsMapper;
import com.ei41.base.model.BtUnits;
import com.ei41.mybatis.search.BaseService;
import com.ei41.mybatis.search.PageResult;



@Service
public class BtUnitsService extends BaseService<BtUnitsMapper> {

	public BtUnitsService(BtUnitsMapper baseMapper) {
		super(baseMapper);
	}

	@Transactional
	public Long addBtUnits(BtUnits BtUnits) {
		return (Long) this.insert(BtUnits);
	}

	@Transactional
	public void deleteBtUnits(Long id) {
		this.delete(id, BtUnits.class);
	}

	@Transactional
	public void updateBtUnits(BtUnits BtUnits) {
		this.update(BtUnits);
	}

	@Transactional(readOnly = true)
	public PageResult getBtUnits(BtUnitsCondition condition) {
		return this.selectPage(condition);
	}

	public BtUnits findBtUnitsById(Long id) {
		return (BtUnits) this.find(id, BtUnits.class);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<BtUnits> getBtUnitsList(BtUnitsCondition condition) {
		return (List<BtUnits>) this.selectMany(condition);
	}

}
