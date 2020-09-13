package com.ei41.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ei41.base.Service.BtUnitsService;
import com.ei41.base.condition.BtUnitsCondition;
import com.ei41.base.model.BtUnits;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class btUnitTest {

	@Autowired
	BtUnitsService service;

	@Test
	@Rollback(false)
	public void test() {
		BtUnitsCondition condition = new BtUnitsCondition();
		
		condition.setBtUnitNameC("微波");
		
		
		List<BtUnits> btUnitsList = service.getBtUnitsList(condition);

		for (BtUnits BtUnits : btUnitsList) {

			System.out.println(BtUnits.toString());
		}

	//	System.out.println("2");
	
	}

}
