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
}
