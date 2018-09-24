package web.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/Servlet/UploadServlet")
public class UploadServlet extends HttpServlet {
	
    private static final long serialVersionUID = 1L;
     
    // the directory name
    private static final String UPLOAD_DIRECTORY = "upload";
 
    // the Configuration fo up load file
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
 
    /**
     * Upload file and save file
     */
    protected void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
        // Detect whether it is a multimedia upload
        if (!ServletFileUpload.isMultipartContent(request)) {
            // Otherwise stop here
            PrintWriter writer = response.getWriter();
            writer.println("Error: it is need: enctype=multipart/form-data");
            writer.flush();
            return;
        }
 
        // Configure upload parameters
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // Set the memory threshold - will generate a temporary file and store it in a temporary directory
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // Set temporary storage directory
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
 
        ServletFileUpload upload = new ServletFileUpload(factory);
        // Set the maximum file upload value
        upload.setFileSizeMax(MAX_FILE_SIZE);
         
        // Set the maximum request value (including file and form data)
        upload.setSizeMax(MAX_REQUEST_SIZE);

        // Chinese processing
        upload.setHeaderEncoding("UTF-8"); 

        //Construct a temporary path to store uploaded files
        //This path is relative to the currently applied directory
        String uploadPath = request.getServletContext().getRealPath("./") + File.separator + UPLOAD_DIRECTORY;
       
         
        // Create if directory does not exist
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
 
        try {
            // Parse the requested content to extract file data
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);
 
            if (formItems != null && formItems.size() > 0) {
                // Iterative form data
                for (FileItem item : formItems) {
                    // Handle fields that are not in the form
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);
                        // Save file to hard disk
                        item.write(storeFile);
                        //D:\Tomcat\webapps\TomcatTest\\upload\try.txt
                        request.setAttribute("message",  "Upload successfully! The file has been save!");
                    }
                }
            }
        } catch (Exception ex) {
            request.setAttribute("message", "Error Message:" + ex.getMessage());
        }
        request.getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);
    }
}
	
