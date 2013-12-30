package org.coder36.pdf.servlet;

import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

public class PDFServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter( "id" );
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocument( "http://localhost:8080/form/" + id);
        renderer.layout();
        response.setContentType("application/pdf");
        try( OutputStream os = response.getOutputStream() ) {
            renderer.createPDF(os);
        }
        catch( Exception e ) {
            throw new RuntimeException(e);
        }
    }
}
