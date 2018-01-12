package issue.tracker.controller;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import issue.tracker.domain.Issue;
import issue.tracker.repository.IssueRepository;
import issue.tracker.service.IssueService;

@Controller
public class IssueController {

	private Log log = LogFactory.getLog(IssueController.class);

	@Autowired
	IssueService issueService;

	@RequestMapping(value = "/addIssue", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, method = RequestMethod.POST)
	public String addIssue(@ModelAttribute Issue issue) {
		log.info("addIssue");
		issue.setDateAdded(new Date());
		issueService.saveIssue(issue);
		return "redirect:/issues";
	}

	@RequestMapping(value = "/addIssue", method = RequestMethod.GET)
	public String getAddIssueView(Model model) {
		log.info("addIssueView");
		model.addAttribute("issue", new Issue());
		return "addIssueView";
	}

	@RequestMapping(value = "/issues", method = RequestMethod.GET)
	public String getIssues(Model model) {
		log.info("getIssues");
		model.addAttribute("issues", issueService.findAll());
		return "issuesView";
	}

	@RequestMapping(value = "/issues/{id}", method = RequestMethod.GET)
	public String getIssueById(@PathVariable("id") Long id, Model model) {
		log.info("getIssueById");
		model.addAttribute("issue", issueService.findById(id));
		return "issueView";
	}
}
