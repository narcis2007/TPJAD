package issue.tracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import issue.tracker.domain.Issue;
import issue.tracker.repository.IssueRepository;

@Service
public class IssueService {

	@Autowired
	IssueRepository issueRepository;

	public Issue saveIssue(Issue issue) {
		return issueRepository.save(issue);

	}

	public Iterable<Issue> findAll() {
		return issueRepository.findAll();
	}

	public Issue findById(Long id) {
		return issueRepository.findOne(id);
	}

	public Issue editIssue(Long id, Issue issue) {
		Issue storedIssue = issueRepository.findOne(id);
		storedIssue.setDescription(issue.getDescription());
		storedIssue.setTitle(issue.getTitle());
		return issueRepository.save(storedIssue);

	}

	public void deleteIssue(Long id) {
		this.issueRepository.delete(issueRepository.findOne(id));

	}

	public void setIssueRepository(IssueRepository issueRepository) {
		this.issueRepository = issueRepository;

	}

	public Iterable<Issue> findByTitleOrDescription(String text) {
		return issueRepository.findByTitleContainingOrDescriptionContaining(text, text);
	}
}
