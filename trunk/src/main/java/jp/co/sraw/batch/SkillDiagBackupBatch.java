package jp.co.sraw.batch;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.sraw.service.SkillDiagServiceImpl;

@Component
public class SkillDiagBackupBatch implements BatchRunner {

	@Autowired
	SkillDiagServiceImpl service;
	
	@Override
	public boolean run(Map<String, String> parameters) throws Exception {
		service.backup();
		return true;
	}

}
