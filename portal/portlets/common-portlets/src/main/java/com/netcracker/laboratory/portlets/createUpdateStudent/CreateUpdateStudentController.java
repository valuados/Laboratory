package com.netcracker.laboratory.portlets.createUpdateStudent;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.netcracker.entities.Student;
import com.netcracker.laboratory.portlets.utils.PortletParentController;
import com.netcracker.laboratory.services.RestServiceWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import java.io.IOException;

/**
 * Created by ilkh0715 on 07.11.2016.
 */
@Controller
public class CreateUpdateStudentController extends PortletParentController {

    @Autowired
    private RestServiceWrapper service;

    @RenderMapping
    @RequestMapping("VIEW")
    private String render()
            throws SystemException, PortalException {
        return "createUpdateStudents/view";
    }


    @ResourceMapping("show.popup.student")
    public String showPopup(Model model, @RequestParam(required = false) Integer studentId) {

        Student student = new Student();
        if (studentId != null) {
            student = service.getStudent(studentId);
        }

        model.addAttribute("student", student);
        return "createUpdateStudents/popup";
    }

    @ResourceMapping("save.student")
    public void saveStudent(Model model, @RequestParam String student) throws IOException {

        if (student != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            Student preparedStudent = objectMapper.readValue(student, Student.class);
            if (preparedStudent.getId() != null) {
                service.updateStudent(preparedStudent.getId(), preparedStudent);
            } else {
                service.addStudent(preparedStudent);
            }
        }
    }


}
