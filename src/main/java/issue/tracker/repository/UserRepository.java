package issue.tracker.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import issue.tracker.domain.User;


public interface UserRepository extends PagingAndSortingRepository<User, Long>  {
	List<User> findByUsernameOrName(String username,String name );
}
