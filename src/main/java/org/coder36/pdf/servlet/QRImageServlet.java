package org.coder36.pdf.servlet;

import org.coder36.pdf.domain.Student;
import org.coder36.pdf.service.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static net.glxn.qrgen.QRCode.from;
import static net.glxn.qrgen.image.ImageType.PNG;

@Service
public class QRImageServlet extends HttpServlet {

    @Autowired
    StudentRepository studentRepository;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Student student = (Student) request.getSession().getAttribute("student");
        String text = "http://localhost:8080/form/" + student.getId();

        try( ByteArrayOutputStream out = from(text).to(PNG).stream() ) {
            response.setContentType("image/png");
            response.setContentLength(out.size());
            OutputStream outStream = response.getOutputStream();
            outStream.write(out.toByteArray());
        }
    }

}
