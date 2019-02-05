package template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import template.service.contract.PhotoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ImageController {
    @Autowired
    private PhotoService photoService;

    @RequestMapping("/image/{id}")
    public void getImage(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int id) {
        try {
            byte[] content = photoService.findPhoto(id).getBody();
            response.setContentType("image/png");
            response.getOutputStream().write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
