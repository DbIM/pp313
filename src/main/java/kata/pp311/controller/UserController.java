package kata.pp311.controller;

import kata.pp311.model.Role;
import kata.pp311.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import kata.pp311.model.User;
import kata.pp311.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
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

	@GetMapping(value = "/login")
	public String loginPage() {
		return "login";
	}

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

	@GetMapping(value = "/admin/adminuser")
	public String printAdmin(Model model, Principal principal) {
		String name = principal.getName();
		User user = userService.getUserByName(name);
		model.addAttribute("username", user);
		return "admin/adminuser";
	}

	@GetMapping(value = "/user")
	public String printWelcome(Model model, Principal principal ) {
		String name = principal.getName();
		User user = userService.getUserByName(name);
		model.addAttribute("username", user);
		return "userpage";
	}

	@PostMapping("/admin/saveUser")
	public String saveUser(@ModelAttribute("user") User user,
						   @RequestParam("rolesSelected") Long[] rolesId) {
		userService.saveUser(user);
		Set<Role> roleSet = new HashSet<>();
		for(Long roleId : rolesId) {
			roleSet.add(roleService.getRoleById(roleId));
		}
		user.setRoles(roleSet);
		userService.updateUser(user);
		return "redirect:/admin/adminusers";
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


	//Rest block
	@GetMapping("/users")
	public List<User> list() {
		return userService.getAllUsers();
	}
}