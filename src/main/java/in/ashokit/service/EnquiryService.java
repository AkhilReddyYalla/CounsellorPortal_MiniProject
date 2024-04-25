package in.ashokit.service;

import java.util.List;

import in.ashokit.binding.Dashboard;
import in.ashokit.entity.Enquiry;

public interface EnquiryService {

	public Dashboard getDashboardInfo(Integer counselorId);
	
	public boolean addEnquiry(Enquiry enquiry, Integer cid);
	
	public List<Enquiry> getEnquiries(Enquiry enquiry , Integer cid);
	
	public Enquiry getEnquiry(Integer enId);

	boolean updateEnquiry(Enquiry enquiry, Integer cid);
	
	
	
	
	
}
