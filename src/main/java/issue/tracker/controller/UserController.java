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

import issue.tracker.domain.User;
import issue.tracker.service.UserService;

@Controller
public class UserController {
	private Log log = LogFactory.getLog(IssueController.class);

	@Autowired
	UserService userService;

	@RequestMapping(value = "/addUser", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, method = RequestMethod.POST)
	public String addUser(@ModelAttribute User user) {
		log.info("addIssue");
		user.setDateRegistration(new Date());
		userService.saveUser(user);
		return "redirect:/users";
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.GET)
	public String getAddUserView(Model model) {
		log.info("addUser");
		model.addAttribute("user", new User());
		return "addUserView";
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String getUsers(Model model) {
		log.info("getUsers");
		model.addAttribute("users", userService.findAll());
		model.addAttribute("text", "");
		return "usersView";
	}

	@RequestMapping(value = "/users/filterJpa", method = RequestMethod.GET)
	public String getUsersByUsernameOrName(@RequestParam("text") String text, Model model) {
		log.info("getUsersByUsernameOrName");
		model.addAttribute("users", userService.findByUserNameOrName(text));
		return "filterUsersViewJpa";
	}

	@RequestMapping(value = "/users/filterJ8", method = RequestMethod.GET)
	public String getUsersByUsernameOrNameJ8(@RequestParam("text") String text, Model model) {
		log.info("getUsersByUsernameOrNameJ8");
		model.addAttribute("users", userService.findByUserNameOrNameJ8(text));
		return "filterUsersViewJ8";
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public String getUserById(@PathVariable("id") Long id, Model model) {
		log.info("getUserById");
		model.addAttribute("users", userService.findById(id));
		return "UserView";
	}

	@RequestMapping(value = "/users/{id}/edit", method = RequestMethod.GET)
	public String getEditUserById(@PathVariable("id") Long id, Model model) {
		log.info("getEditUserById");
		model.addAttribute("users", userService.findById(id));
		return "editUserView";
	}

	@RequestMapping(value = "/users/{id}/edit", method = RequestMethod.PUT)
	public String editUserById(@PathVariable("id") Long id, @ModelAttribute User user) {
		log.info("editUserById");
		userService.editUser(id, user);

		return "redirect:/users";
	}

	@RequestMapping(value = "/user/{id}/delete", method = RequestMethod.POST)
	public String deleteUserById(@PathVariable("id") Long id) {
		log.info("deleteUserById");
		userService.deleteUser(id);

		return "redirect:/users";
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public String deleteUser(@PathVariable("id") Long id, Model model) {
		log.info("deleteUser");
		model.addAttribute("users", userService.findById(id));
		return "usersView";
	}

}
