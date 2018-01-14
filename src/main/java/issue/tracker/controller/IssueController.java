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
import org.springframework.web.bind.annotation.RequestParam;

import issue.tracker.domain.Issue;
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

	@RequestMapping(value = "/issues/filterJpa", method = RequestMethod.GET)
	public String getIssuesByTitleOrDescription(@RequestParam("text") String text, Model model) {
		log.info("getIssuesByTitleOrDescription");
		model.addAttribute("issues", issueService.findByTitleOrDescription(text));
		return "filterIssuesViewJpa";
	}

	@RequestMapping(value = "/issues/{id}", method = RequestMethod.GET)
	public String getIssueById(@PathVariable("id") Long id, Model model) {
		log.info("getIssueById");
		model.addAttribute("issue", issueService.findById(id));
		return "issueView";
	}

	@RequestMapping(value = "/issues/{id}/edit", method = RequestMethod.GET)
	public String getEditIssueById(@PathVariable("id") Long id, Model model) {
		log.info("getEditIssueById");
		model.addAttribute("issue", issueService.findById(id));
		return "editIssueView";
	}

	@RequestMapping(value = "/issues/{id}/edit", method = RequestMethod.PUT)
	public String editIssueById(@PathVariable("id") Long id, @ModelAttribute Issue issue) {
		log.info("editIssueById");
		issueService.editIssue(id, issue);

		return "redirect:/issues";
	}

	@RequestMapping(value = "/issues/{id}/delete", method = RequestMethod.POST)
	public String deleteIssueById(@PathVariable("id") Long id) {
		log.info("deleteIssueById");
		issueService.deleteIssue(id);

		return "redirect:/issues";
	}

	@RequestMapping(value = "/issues/{id}", method = RequestMethod.DELETE)
	public String deleteIssue(@PathVariable("id") Long id, Model model) {
		log.info("getIssueById");
		model.addAttribute("issue", issueService.findById(id));
		return "issueView";
	}
}
