package in.ashokit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import in.ashokit.binding.Dashboard;
import in.ashokit.entity.Counsellor;
import in.ashokit.service.CounsellorService;
import in.ashokit.service.EnquiryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CounsellorController {
	@Autowired
	private CounsellorService counsellorService;
	@Autowired
	private EnquiryService enquiryService;

	@GetMapping("/index")
	public String login(Model model) {
	    model.addAttribute("counsellor", new Counsellor());
	    return "index";
	}


	@PostMapping("/index")
	public String handleLogin(Counsellor counsellor, HttpServletRequest req, Model model) {
		Counsellor c = counsellorService.getCounsellor(counsellor.getEmail(), counsellor.getPassword());
		if (c == null) {
			model.addAttribute("emsg", "Invalid Credentials");
			return "index";
		} else {

			HttpSession session = req.getSession(true);
			session.setAttribute("cid", c.getCid());

			Dashboard dashboardInfo = enquiryService.getDashboardInfo(c.getCid());
			model.addAttribute("dashboard", dashboardInfo);
			return "dashboard";
		}
	}
    @GetMapping("/dashboard")
	public String buildDashboard(HttpServletRequest req, Model model) {
   	 HttpSession session = req.getSession(false);
	        Integer cid = (Integer) session.getAttribute("cid");
	        Dashboard dashboardInfo = enquiryService.getDashboardInfo(cid);
			model.addAttribute("dashboard", dashboardInfo);
			return "dashboard";
   }


	@GetMapping("/registerView")
	public String register(Model model) {
		model.addAttribute("counsellor", new Counsellor());
		return "registerView";
	}
   @PostMapping("/registerView")
	public String handleRegister(Counsellor c, Model model) {
		boolean saveCounsellor = counsellorService.saveCounsellor(c);
		model.addAttribute("saveCounsellor", saveCounsellor);
		 if (saveCounsellor) {
		        model.addAttribute("smsg", "Registration successful!");
		    } else {
		        model.addAttribute("emsg", "Registration failed. Email already exists!");
		    }
		return "registerView";
	}
   @GetMapping("/logout")
   public String logOut(HttpServletRequest req, Model model) {
	   HttpSession session = req.getSession(false);
	   session.invalidate();
	   return "index";
   }

}
