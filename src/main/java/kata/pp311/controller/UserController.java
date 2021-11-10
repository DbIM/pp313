package kata.pp311.controller;

import kata.pp311.model.Role;
import kata.pp311.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import kata.pp311.model.User;
import kata.pp311.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;


@Controller
@RequestMapping("")
public class UserController {
	private final UserService userService;
	private final RoleService roleService;

	@Autowired
	public UserController(UserService userService, RoleService roleService) {
		this.userService = userService;
		this.roleService = roleService;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		return "login";
	}

/*	@RequestMapping(value = "/admin/logout", method = RequestMethod.GET)
	public String logoutPage() {
		return "admin/logout";
	}*/

	@GetMapping(value = "/")
	public String indexPage() {
		return "login";
	}

	@GetMapping(value = "/admin/adminusers")
	public String getAdminUserPage(ModelMap model, Principal principal) {
		String name = principal.getName();
		User user = userService.getUserByName(name);
		model.addAttribute("userAuth", user);
		model.addAttribute("allRoles", roleService.getAllRoles());
		model.addAttribute("adminusers", userService.getAllUsers());
		return "admin/adminusers";
	}

	@GetMapping(value = "/users")
	public String getUserPage(ModelMap model) {
		model.addAttribute("users", userService.getAllUsers());
		return "users";
	}

	@GetMapping("/{id}")
	public String showUser (@PathVariable("id") Long id, ModelMap model){
		model.addAttribute("user", userService.getUserById(id));
		return "/userpage";
	}

	@GetMapping("/admin/addUser")
	public String createUser(ModelMap model) {
		User user = new User();
		model.addAttribute("users", user);
		return "admin/adduser";
	}

	@PostMapping("/admin/saveUser")
	public String saveUser(@ModelAttribute("user") User user) {
		userService.saveUser(user);
		return "redirect:/admin/adminusers";
	}

	@GetMapping("/admin/{id}/updateUser")
	public String updateUser(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("user", userService.getUserById(id));
		model.addAttribute("allRoles", roleService.getAllRoles());
		return "admin/updateuser";
	}

	@PatchMapping("/admin/updateUser")
	public String updateUser(@ModelAttribute("user") User user,
							 @RequestParam("rolesSelected") Long[] rolesId) {
		Set<Role> roleSet = new HashSet<>();
		for(Long roleId : rolesId) {
			roleSet.add(roleService.getRoleById(roleId));
		}
		user.setRoles(roleSet);
		userService.updateUser(user);
		return "redirect:/admin/adminusers";
	}

	@GetMapping("/admin/deleteUser/{id}")
	public String deleteUser(@PathVariable("id") Long id){
		userService.removeUser(id);
		return "redirect:/admin/adminusers";
	}
}