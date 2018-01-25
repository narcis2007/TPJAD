package issue.tracker.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import issue.tracker.domain.User;
import issue.tracker.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;

	public User saveUser(User user) {
		return userRepository.save(user);

	}

	public Iterable<User> findAll() {
		return userRepository.findAll();
	}

	public User findById(Long id) {
		return userRepository.findOne(id);
	}

	public User editUser(Long id, User user) {
		User storedUser = userRepository.findOne(id);
		storedUser.setUsername(user.getUsername());
		storedUser.setName(user.getName());
		return userRepository.save(storedUser);

	}

	public void deleteUser(Long id) {
		this.userRepository.delete(userRepository.findOne(id));

	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;

	}

	public List<User> findByUserNameOrName(String text) {
		return userRepository.findByUsernameOrName(text, text);
	}

	public Iterable<User> findByUserNameOrNameJ8(String text) {
		Iterable<User> users = userRepository.findAll();
		List<User> userList = StreamSupport.stream(users.spliterator(), false).collect(Collectors.toList());
		return userList.stream()
				.filter(user -> user.getName().contains(text) || user.getUsername().contains(text))
				.collect(Collectors.toList());
	}
}
