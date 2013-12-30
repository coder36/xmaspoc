package org.coder36.pdf.controller

import org.coder36.pdf.domain.Student
import org.coder36.pdf.service.StudentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.SessionAttributes
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.mvc.support.RedirectAttributes

import javax.servlet.http.HttpServletRequest
import javax.validation.Valid

@Controller
@RequestMapping("/groovy")
@SessionAttributes( "student" )
class GroovyStudentController {

    @Autowired
    StudentRepository studentRepository;

    @RequestMapping( value="/", method = RequestMethod.GET)
    def home() {
        new ModelAndView( "list", "students", studentRepository.findAll() );
    }

    @RequestMapping( value="/new", method = RequestMethod.GET)
    def create() {
        new ModelAndView( "new", "student", new Student());
    }

    @RequestMapping( value="/save", method = RequestMethod.POST )
    def save( @Valid Student student, BindingResult result ) {
        if (result.hasErrors()) return new ModelAndView("new", "formErrors", result.getAllErrors() );
        studentRepository.save( student );
        new ModelAndView("redirect:form/" + student.id);
    }

    @RequestMapping( value="/form/{id}", method = RequestMethod.GET )
    def form(@PathVariable Long id ) {
        def student = studentRepository.findOne( id );
        new ModelAndView("form", "student", student );
    }

}
