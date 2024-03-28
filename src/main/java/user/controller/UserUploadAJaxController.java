package user.controller;

import user.service.UserUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "user")
public class UserUploadAJaxController {
    @Autowired
    private UserUploadService userUploadService;

    @GetMapping(value = "uploadFormAJax")
    public String uploadFormAJax(){
        return "user/uploadFormAJax";
    }
}
