package in.ashokit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.ashokit.entity.Enquiry;
import in.ashokit.service.CounsellorService;
import in.ashokit.service.EnquiryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EnquiryController {
 
	@Autowired
	private CounsellorService counsellorService;
	@Autowired
	private EnquiryService enquiryService;

	
	    @GetMapping("/addEnq")
	    public String addEnquiry(Model model) {
	        model.addAttribute("enquiryObj", new Enquiry());
	        model.addAttribute("classmode", ""); // Add empty value for selection
	        model.addAttribute("course", ""); // Add empty value for selection
	        model.addAttribute("status", ""); // Add empty value for selection
	        return "addEnq";
	    }

	    @PostMapping("/addEnq")
	    public String saveEnquiry(@ModelAttribute("enquiryObj")  Enquiry enquiry, HttpServletRequest req, Model model) {
	        HttpSession session = req.getSession(false);
	        Integer cid = (Integer) session.getAttribute("cid");
	        boolean status = enquiryService.addEnquiry(enquiry, cid);
	        if (status) {
	            model.addAttribute("smsg", "Enquiry Saved");
	        } else {
	            model.addAttribute("emsg", "Enquiry not saved");
	        }
	        return "addEnq";
	    }
	    
	    

	@GetMapping("/viewEnquiries")
	public String getEnquiries(Model model, HttpServletRequest req) {

		HttpSession session = req.getSession(false);
		Integer cid = (Integer) session.getAttribute("cid");

		List<Enquiry> list = enquiryService.getEnquiries(new Enquiry(), cid);
		model.addAttribute("enqs", list);
		model.addAttribute("enquiryObj", new Enquiry());

		return "viewEnquiries";

	}

	@PostMapping("/filter-enqs")
	public String filterEnquiries( @ModelAttribute("enquiryObj") Enquiry enq, HttpServletRequest req, Model model) {

		HttpSession session = req.getSession(false);
		Integer cid = (Integer) session.getAttribute("cid");

		List<Enquiry> list = enquiryService.getEnquiries(enq, cid);
		model.addAttribute("enqs", list);
		return "viewEnquiries";

	}

//	@GetMapping("/edit")
//	public String editEnquiry(@RequestParam("eid") Integer enqId, Model model) {
//	    Enquiry enquiry = enquiryService.getEnquiry(enqId);
//	    model.addAttribute("enquiryObj", enquiry); 
//	
//	    model.addAttribute("classmode", ""); 
//	    model.addAttribute("course", ""); 
//	    model.addAttribute("status", ""); 
//	    return "addEnq";
//	}
	@GetMapping("/edit")
	public String editEnquiry(@RequestParam("eid") Integer enqId, Model model) {
	    Enquiry enquiry = enquiryService.getEnquiry(enqId);
	    model.addAttribute("enquiryObj", enquiry); 
	    return "editEnq"; // Redirect to the edit view
	}
	@PostMapping("/saveEnquiry")
	public String saveEnquiry1(@ModelAttribute("enquiryObj") Enquiry enquiry, HttpServletRequest req, Model model) {
	    HttpSession session = req.getSession(false);
	    Integer cid = (Integer) session.getAttribute("cid");
	    
	    if (enquiry.getEid() != null) {
	        // It's an edit operation
	        boolean status = enquiryService.updateEnquiry(enquiry, cid);
	        if (status) {
	            model.addAttribute("smsg", "Enquiry Updated");
	        } else {
	            model.addAttribute("emsg", "Enquiry update failed");
	        }
	    } else {
	        // It's an add operation
	        boolean status = enquiryService.addEnquiry(enquiry, cid);
	        if (status) {
	            model.addAttribute("smsg", "Enquiry Saved");
	        } else {
	            model.addAttribute("emsg", "Enquiry not saved");
	        }
	    }
	    return "redirect:/viewEnquiries";
	}

	

}
