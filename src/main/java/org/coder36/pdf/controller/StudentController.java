package org.coder36.pdf.controller;

import org.coder36.pdf.domain.Student;
import org.coder36.pdf.service.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/")
@SessionAttributes( "student" )
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @RequestMapping( value="/", method = RequestMethod.GET)
    public ModelAndView home() {
        return new ModelAndView( "list", "students", studentRepository.findAll() );
    }

    @RequestMapping( value="/new", method = RequestMethod.GET)
    public ModelAndView create() {
        return new ModelAndView( "new", "student", new Student() );
    }

    @RequestMapping( value="/save", method = RequestMethod.POST )
    public ModelAndView save( @Valid Student student, BindingResult result, RedirectAttributes redirect, HttpServletRequest request ) {
        if (result.hasErrors()) {
            return new ModelAndView("new", "formErrors", result.getAllErrors());
        }
        studentRepository.save( student );
        return new ModelAndView("redirect:/form/" + student.getId());
    }

    @RequestMapping( value="/form/{id}", method = RequestMethod.GET )
    public ModelAndView form(@PathVariable Long id ) {
        Student student = studentRepository.findOne( id );
        return new ModelAndView("form", "student", student );
    }
}
