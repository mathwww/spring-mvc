package com.training;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.training.dao.EmpDao;
import com.training.model.Employee;

@Controller
public class HomeController {

	@Autowired
	EmpDao dao;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {

		String outString = "Demo Spring";

		model.addAttribute("msg", outString);

		return "index";
	}

	@RequestMapping("/empform")
	public String showform(Model m) {

		m.addAttribute("command", new Employee());

		return "empform";

	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("emp") Employee emp) {
		Random random = new Random();
		emp.setId(random.nextInt());
		dao.save(emp);

		return "redirect:/viewemp";// will redirect to viewemp request mapping

	}

	@RequestMapping("/viewemp")
	public String viewemp(Model m) {

		List<Employee> list = dao.getEmployees();

		m.addAttribute("list", list);

		return "viewemp";

	}

	@RequestMapping(value = "/editemp/{id}")
	public String edit(@PathVariable int id, Model m) {
		System.out.println("Inside Controller");
		Employee emp = dao.getEmpById(id);
		
		m.addAttribute("command", emp);

		return "empeditform";

	}

	@RequestMapping(value = "/editsave", method = RequestMethod.POST)
	public String editsave(@ModelAttribute("emp") Employee emp) {
		System.out.println("Inside editsave");
		dao.update(emp);

		return "redirect:/viewemp";

	}

	@RequestMapping(value = "/deleteemp/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable int id) {

		dao.delete(id);

		return "redirect:/viewemp";

	}
}
