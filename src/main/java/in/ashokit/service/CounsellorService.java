package in.ashokit.service;

import in.ashokit.entity.Counsellor;

public interface CounsellorService {
	
	public boolean saveCounsellor(Counsellor counselor);

	public Counsellor getCounsellor(String email, String password);

}
