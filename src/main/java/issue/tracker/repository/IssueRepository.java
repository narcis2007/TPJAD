package issue.tracker.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import issue.tracker.domain.Issue;

public interface IssueRepository extends PagingAndSortingRepository<Issue, Long> {

	List<Issue> findByTitleContainingOrDescriptionContaining(String title,String description);

}
