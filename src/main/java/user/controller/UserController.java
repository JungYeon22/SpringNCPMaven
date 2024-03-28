package user.controller;

import user.bean.UserDTO;
import user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping(value = "user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/writeForm")
    public String writeForm(){
        return "user/writeForm";
    }

    @PostMapping(value = "/isExistId")
    @ResponseBody
    public String isExistId(@RequestParam String id){
        String result = userService.isExistId(id);
        return result;
    }

    @PostMapping(value = "/write")
    @ResponseBody   // 기본이 jsp로 가는데, 가지말라는 어노테이션
    public void write(@ModelAttribute UserDTO userDTO){
        userService.write(userDTO);
    }

    @GetMapping(value = "list")
    public String list(@RequestParam(required = false, defaultValue = "1") String pg, Model model){
        model.addAttribute("pg", pg);
        return "user/list";
    }

    @PostMapping(value = "/getUserList")
    @ResponseBody
    public Map<String, Object> getUserList(@RequestParam String pg, Model model){
        return userService.getUserList(pg);
    }

    @GetMapping(value = "updateForm")
    public String updateForm(@RequestParam String id,
                             @RequestParam String pg,
                             Model model){
        model.addAttribute("id", id);
        model.addAttribute("pg", pg);
        return "user/updateForm";
    }

    @PostMapping(value = "getUser")//핸들러 맵핑이 연결해줌
    @ResponseBody   // 스프링 부트에서는 기본으로 @ResponseBody로 설정
    public UserDTO getUser(@RequestParam String id){
        return userService.getUser(id);
    }

    @PostMapping(value = "update")
    @ResponseBody
    public void update(@ModelAttribute UserDTO userDTO){
        userService.update(userDTO);
    }

    @PostMapping(value = "delete")
    @ResponseBody
    public void delete(@RequestParam String id){
        userService.delete(id);
    }
}
