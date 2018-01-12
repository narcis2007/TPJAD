package issue.tracker.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import issue.tracker.domain.Issue;

public interface IssueRepository extends PagingAndSortingRepository<Issue, Long> {

}
