package in.ashokit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.ashokit.binding.Dashboard;
import in.ashokit.entity.Counsellor;
import in.ashokit.entity.Enquiry;
import in.ashokit.repo.CounsellorRepository;
import in.ashokit.repo.EnquiryRepository;

@Service
public class EnquiryServiceImpl implements EnquiryService {
	@Autowired
	private EnquiryRepository enquiryRepo;
	@Autowired
	private CounsellorRepository counsellorRepo;

	@Override
	public Dashboard getDashboardInfo(Integer cId) {
		Dashboard d = new Dashboard();
//		Long totalEnquries = enquiryRepo.getEnquries(cId);
//		Long openEnquries = enquiryRepo.getEnquries(cId, "Open");
//		Long enrolledEnquries = enquiryRepo.getEnquries(cId, "Enrolled");
//		Long lostEnquries = enquiryRepo.getEnquries(cId, "Lost");

		List<Enquiry> list = enquiryRepo.findByCid(cId);
		Integer totalEnquiries = list.size();
		long openEnquiries = list.stream().filter(e -> e.getStatus().equalsIgnoreCase("open")).count();
		long enrolledEnquiries = list.stream().filter(e -> e.getStatus().equalsIgnoreCase("enrolled")).count();
		long lostEnquiries = totalEnquiries - openEnquiries - enrolledEnquiries;
		d.setTotalEnq(totalEnquiries);
		d.setOpenEnq(openEnquiries);
		d.setEnrolledEnq(enrolledEnquiries);
		d.setLostEnq(lostEnquiries);

		return d;
	}

	@Override
	public boolean addEnquiry(Enquiry enquiry, Integer cid) {

		Counsellor counsellor = counsellorRepo.findById(cid).orElseThrow();
		enquiry.setCounsellor(counsellor); // association for fk.
		Enquiry saveEnq = enquiryRepo.save(enquiry);
		return saveEnq.getEid() != null;
	}

	@Override
	public List<Enquiry> getEnquiries(Enquiry enquiry, Integer cid) {
		Counsellor counsellor = counsellorRepo.findById(cid).orElseThrow();
		enquiry.setCounsellor(counsellor);
		
		//adding filter values to entity
		Enquiry searchCriteria = new Enquiry();
		searchCriteria.setCounsellor(counsellor);
		
		if(null!=enquiry.getCourse()&& !"".equals(enquiry.getCourse())) {
			searchCriteria.setCourse(enquiry.getCourse());
		}
		
		if(null!=enquiry.getClassmode() && !"".equals(enquiry.getClassmode())) {
			searchCriteria.setClassmode(enquiry.getClassmode());
		}
		
		if(null!=enquiry.getStatus() && !"".equals(enquiry.getStatus())) {
			searchCriteria.setStatus(enquiry.getStatus());
		}
		
		
		Example<Enquiry> of = Example.of(searchCriteria);
		return enquiryRepo.findAll(of);
	}

	@Override
	public Enquiry getEnquiry(Integer enqId) {
		return enquiryRepo.findById(enqId).orElseThrow();
	}
	 @Override
	    public boolean updateEnquiry(Enquiry enquiry, Integer cid) {
	        // Check if the enquiry ID is not null
	        if (enquiry.getEid() != null) {
	            // Retrieve the existing enquiry from the database
	            Optional<Enquiry> existingEnquiryOptional = enquiryRepo.findById(enquiry.getEid());
	            if (existingEnquiryOptional.isPresent()) {
	                Enquiry existingEnquiry = existingEnquiryOptional.get();
	                // Update the existing enquiry fields with the new values
	                existingEnquiry.setName(enquiry.getName());
	                existingEnquiry.setPhno(enquiry.getPhno());
	                existingEnquiry.setClassmode(enquiry.getClassmode());
	                existingEnquiry.setCourse(enquiry.getCourse());
	                existingEnquiry.setStatus(enquiry.getStatus());
	                // Save the updated enquiry
	                enquiryRepo.save(existingEnquiry);
	                return true; // Return true indicating successful update
	            }
	        }
	        return false; // Return false if the enquiry ID is null or the record doesn't exist
	    }

	
}
