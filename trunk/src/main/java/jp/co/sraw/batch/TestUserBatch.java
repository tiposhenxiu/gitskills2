package jp.co.sraw.batch;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.sraw.entity.UsUserTbl;
import jp.co.sraw.service.UserServiceImpl;

@Component
public class TestUserBatch implements BatchRunner {

	@Autowired
	private UserServiceImpl userServiceImpl;

	@Override
	public boolean run(Map<String, String> parameters) throws Exception {

		String userKey = parameters.get("user");
		UsUserTbl u = userServiceImpl.findOne(userKey);

		System.out.println("UserKey="+ u.getUserKey());

		return true;

	}

}
