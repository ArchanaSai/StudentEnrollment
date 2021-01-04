package com.springboot.demo.controller;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.demo.model.Student;
import com.springboot.demo.service.StudentService;

@Controller
public class StudentEnrollmentController {
	@Autowired
	StudentService studentService;
	
	@RequestMapping(path = "/" ,method = RequestMethod.GET)
	public ModelAndView loadInitialPage() {
		List<Student> studentList = new ArrayList<Student>();
		studentList = (ArrayList) studentService.getAllStudents();
		return new ModelAndView("StudentSummary","studentList", studentList);
	}
	
	@ModelAttribute("genderList")
	public List<String> initializeGenderList(){
		List<String> genderList = new ArrayList<String>();
		genderList.add("Male");
		genderList.add("Female");
		genderList.add("TransGender");
		return genderList;
	}
	
	@ModelAttribute("sectionList")
	public List<String> initializeSectionList(){
		List<String> sectionList = new ArrayList<String>();
		sectionList.add("Graduate");
		sectionList.add("Post Graduate");
		sectionList.add("Research");
		return sectionList;
	}
	
	@ModelAttribute("countryList")
	public List<String> initializeCountryList(){
		List<String> countryList = new ArrayList<String>();
		countryList.add("India");
		countryList.add("USA");
		countryList.add("Canada");
		countryList.add("France");
		countryList.add("Germany");
		countryList.add("Italy");
		countryList.add("Other");
		return countryList;
	}
	
	@ModelAttribute("subjectList")
	public List<String> initializeSubjectList(){
		List<String> subjectList = new ArrayList<String>();
		subjectList.add("Physics");
		subjectList.add("Chemistry");
		subjectList.add("Life Science");
		subjectList.add("Political Science");
		subjectList.add("Computer Science");
		subjectList.add("Mathematics");
		return subjectList;
	}
	
	@ModelAttribute("pageCount")
	public List<String> initializePageCount() {
	    int total=2;  
		List<String> pageCount = new ArrayList<String>();
		int count=studentService.getCount();
		int result=((count/total)+ (count%total));
		for(int k=0;k<result;k++) {
			pageCount.add(new Integer(k).toString());
		}
		
		return pageCount;
	}
	
	@RequestMapping(path = "/studentSummary",method = RequestMethod.GET)
	public ModelAndView loadStudentSummary() {
		List<Student> studentList = new ArrayList<Student>();
		studentList = (ArrayList) studentService.getAllStudents();
		return new ModelAndView("StudentSummary","studentList", studentList);
	}
	
	@RequestMapping(value = "/viewStudent/{id}",method = RequestMethod.GET)  
    public String loadViewStudent(ModelMap modelMap,@PathVariable int id){
		Student student = studentService.getStudentById(id);
		modelMap.addAttribute("student",student);
		modelMap.addAttribute("pageType","view");
		return "ManageStudent";
    }
	
	@RequestMapping(value="/viewstudents/{pageid}")  
    public ModelAndView edit(@PathVariable int pageid){  
        int total=2;  
        if(pageid==1){}  
        else{  
            pageid=(pageid-1)*total+1;  
        }  
        List<Student> list=studentService.getStudentsByPage(pageid,total);  
        return new ModelAndView("StudentSummary","studentList",list);  
    }
	
	@RequestMapping(path = "/enroll",method = RequestMethod.GET)
	public String loadEnrollPage(ModelMap modelMap) {
		Student student =new Student();
		modelMap.addAttribute("student",student);
		modelMap.addAttribute("pageType","add");
		return "ManageStudent";
	}
	
	@RequestMapping(path = "/saveStudent",method = RequestMethod.POST)
	public String saveStudent(@Valid Student student, BindingResult bindingResult, ModelMap modelMap, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()) {
			modelMap.addAttribute("pageType","add");
			return "ManageStudent";
		}
		else {
			studentService.saveStudent(student);
			return "redirect:/studentSummary";
		}
	}
	
	@RequestMapping(value = "/editStudent/{id}",method = RequestMethod.GET)
	public String loadEditStudent(ModelMap modelMap,@PathVariable int id) {
		Student student = studentService.getStudentById(id);
		modelMap.addAttribute("student",student);
		modelMap.addAttribute("pageType","edit");
		return "ManageStudent";
	}
	
	@RequestMapping(path = "/editStudent/updateStudentInfo",method = RequestMethod.POST)
	public String updateStudentInfo(@Valid Student student, BindingResult bindingResult, ModelMap modelMap, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()) {
			modelMap.addAttribute("pageType","edit");
			return "ManageStudent";
		}
		else {
			studentService.editStudent(student);
			return "redirect:/studentSummary";
		}
	}
	
	@RequestMapping(path = "/deleteStudent/{id}",method = RequestMethod.GET)
	public String deleteStudent(@PathVariable int id) {
		studentService.deleteStudent(id);
		return "redirect:/studentSummary";
	}
	
	@RequestMapping(path = "/deleteAll",method = RequestMethod.GET)
	public String deleteAllStudent() {
		studentService.deleteAllStudents();
		return "redirect:/studentSummary";
	}
}
