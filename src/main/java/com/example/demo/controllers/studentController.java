package com.example.demo.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.models.student;

import com.example.demo.models.studentRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class studentController {  
    @Autowired
    private studentRepository studentRepo;

    @GetMapping("/students/add")
    public String addStudent(@RequestParam Map<String, String> newStudent, HttpServletResponse response, Model model){
        System.out.println("ADD user");
        String newSid = newStudent.get("sid");
        String newName = newStudent.get("name");
        String newHeight = newStudent.get("height");
        String newWeight = newStudent.get("weight");
        String newHC = newStudent.get("hairColor");
        String newGpa = newStudent.get("gpa");
        

        if(!isNum(newSid) || !isNum(newWeight) || !isNum(newHeight) || !isNum(newGpa)){
            model.addAttribute("msg", "Error: Invalid Input");
            return "students/error";
        }

        if(studentRepo.findBySid(newSid).size() != 0){
            model.addAttribute("msg", "Error: Student Exist Already");
            return "students/error";
        }
        if(newSid == ""){
            model.addAttribute("msg", "Error: You forgot an input!");
            return "students/error";
        }

        if(newName == ""){
            model.addAttribute("msg", "Error: You forgot an input!");
            return "students/error";
        }

        if(newHeight == ""){
            model.addAttribute("msg", "Error: You forgot an input!");
            return "students/error";
        }

        if(newWeight == ""){
            model.addAttribute("msg", "Error: You forgot an input!");
            return "students/error";
        }

        if(newHC == ""){
            model.addAttribute("msg", "Error: You forgot an input!");
            return "students/error";
        }

        if(newGpa == ""){
            model.addAttribute("msg", "Error: You forgot an input!");
            return "students/error";
        }



        student s=new student(newSid,newName,newHeight,newWeight,newHC,newGpa);
        studentRepo.save(s);
        //userRepo.save(new User());
        response.setStatus(201);
        //return "users/addedUser";
        return "students/addedStudent";
    }

    private boolean isNum(String str){
        try{
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException e){
            return false;
        }
        return true;
    }

    @GetMapping("/students/del")
    public String delStudent(@RequestParam("deleteSID") String sid,Model model){
        List<student> l=studentRepo.findBySid(sid);
        if(l.size()==0){
            model.addAttribute("msg", "Error: Student does not exist");
            return "students/error";
        }
        studentRepo.delete(l.get(0));
        return "students/addedStudent";
    }

    @GetMapping("/students/mod")
    public String modStudent(@RequestParam("modify") String sid,Model model){
        List<student> l=studentRepo.findBySid(sid);
        if(l.size()==0){
            model.addAttribute("msg", "Error: Student does not exist!");
            return "students/error";
        }
        //student student_to_update=l.get(0);
        model.addAttribute("originSID", sid);
        return "students/modForm";
    }

    @GetMapping("/students/submitMod")
    private String modSubmit(@RequestParam Map<String, String> newStudent, HttpServletResponse response){
        System.out.println("ADD user");
        List<student> l=studentRepo.findBySid(newStudent.get("origSID"));
        String newSid = newStudent.get("sid");
        String newName = newStudent.get("name");
        String newHeight = newStudent.get("height");
        String newWeight = newStudent.get("weight");
        String newHC = newStudent.get("hairColor");
        String newGpa = newStudent.get("gpa");
        l.get(0).setSid(newSid);
        l.get(0).setName(newName);
        l.get(0).setHeight(newHeight);
        l.get(0).setWeight(newWeight);
        l.get(0).setHaircolor(newHC);
        l.get(0).setGpa(newGpa);
        studentRepo.save(l.get(0));
        //userRepo.save(new User());
        //return "users/addedUser";
        return "students/addedStudent";
    }

    @GetMapping("/students/show")
    public String viewAll(Model model){
       System.out.println("Viewing all students!");
        // get all users from database
        List<student> s = studentRepo.findAll();
        // end of database call
        model.addAttribute("stu", s);
        return "/students/viewAll"; 
    }
    
    // @Autowired
    // private UserRepository userRepo;

    // @GetMapping("/users/view")
    // public String getAllUsers(Model model){
    //     System.out.println("Getting all users");
    //     // get all users from database
    //     List<User> users = userRepo.findAll();
    //     // end of database call
    //     model.addAttribute("us", users);
    //     return "users/showAll";
    // }

    // @GetMapping("/")
    // public RedirectView process(){
    //     return new RedirectView("login");
    // }

    // @PostMapping("/users/add")
    // public String addUser(@RequestParam Map<String, String> newuser, HttpServletResponse response){
    //     System.out.println("ADD user");
    //     String newName = newuser.get("name");
    //     String newPwd = newuser.get("password");
    //     int newSize = Integer.parseInt(newuser.get("size"));
    //     userRepo.save(new User(newName,newPwd,newSize));
    //     response.setStatus(201);
    //     return "users/addedUser";
    // }

    // @GetMapping("/login")
    // public String getLogin(Model model, HttpServletRequest request, HttpSession session){
    //     User user = (User) session.getAttribute("session_user");
    //     if (user == null){
    //         return "users/login";
    //     }
    //     else {
    //         model.addAttribute("user",user);
    //         return "users/protected";
    //     }
    // }

    // @PostMapping("/login")
    // public String login(@RequestParam Map<String,String> formData, Model model, HttpServletRequest request, HttpSession session){
    //     // processing login
    //     String name = formData.get("name");
    //     String pwd = formData.get("password");
    //     List<User> userlist = userRepo.findByNameAndPassword(name, pwd);
    //     if (userlist.isEmpty()){
    //         return "users/login";
    //     }
    //     else {
    //         // success
    //         User user = userlist.get(0);
    //         request.getSession().setAttribute("session_user", user);
    //         model.addAttribute("user", user);
    //         return "users/protected";
    //     }
    // }

    // @GetMapping("/logout")
    // public String destroySession(HttpServletRequest request){
    //     request.getSession().invalidate();
    //     return "/users/login";
    // }

}
