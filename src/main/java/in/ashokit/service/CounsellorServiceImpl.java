package in.ashokit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.entity.Counsellor;
import in.ashokit.repo.CounsellorRepository;
import in.ashokit.repo.EnquiryRepository;

@Service
public class CounsellorServiceImpl implements CounsellorService {
	@Autowired
	private CounsellorRepository counsellorRepo;
	@Autowired
	private EnquiryRepository enquiryRepo;

	@Override
	public boolean saveCounsellor(Counsellor counsellor) {
		Counsellor findByEmail = counsellorRepo.findByEmail(counsellor.getEmail());
		if (findByEmail != null) {
			return false;
		} else {
			Counsellor saveCounsellor = counsellorRepo.save(counsellor);
			return saveCounsellor.getCid() != null;
		}
	}

	@Override
	public Counsellor getCounsellor(String email, String password) {

		Counsellor obj = counsellorRepo.findByEmailAndPassword(email, password);
		System.out.println(obj);
		return obj;

	}

}
