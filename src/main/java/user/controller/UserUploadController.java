package user.controller;

import org.springframework.ui.Model;
import user.bean.UserImageDTO;
import user.service.ObjectStorageService;
import user.service.UserUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "user")
public class UserUploadController {
    @Autowired
    private UserUploadService userUploadService;
    @Autowired
    private ObjectStorageService objectStorageService;

    private String bucketName = "bitcamp-6th-bucket-94";

    @GetMapping(value = "/uploadForm")
    public String uploadForm(){
        return "/user/uploadForm";
    }

    // 한번에 1개 이상 선택할 때
    @PostMapping(value = "/upload")
    public String upload(@ModelAttribute UserImageDTO userImageDTO,
                         @RequestParam("img[]") List<MultipartFile> list,
                         HttpSession session){
        // 실제 폴더 D:\study\SpringMVC\Chapter06_Web\src\main\webapp\WEB-INF\storage
    /*    String filePath = session.getServletContext().getRealPath("WEB-INF/storage");
        System.out.println("실제폴더 : " + filePath);*/

        String originalFileName = "";
        String result = "";

        List<UserImageDTO> userImageList = new ArrayList<>();

        for(MultipartFile img : list){
            originalFileName = img.getOriginalFilename();

            String imageFileName = objectStorageService.uploadFile(bucketName, "storage/", img);    // 네이버 클라우드 object storage

            //fileName = 네이버클라우드 Object Storage;

            UserImageDTO dto = new UserImageDTO();
            dto.setImageName(userImageDTO.getImageName());
            dto.setImageContent(userImageDTO.getImageContent());
            dto.setImageFileName(imageFileName);
            dto.setImageOriginalName(originalFileName);
            System.out.print("imageFileName(UUID) : " + imageFileName);

            userImageList.add(dto);

        }// for

        // DB
        userUploadService.upload(userImageList);
        return "user/uploadList";
    }

    @GetMapping(value = "uploadList")
    public String uploadList(){
        return "user/uploadList";
    }

    @PostMapping(value = "getUploadList")
    @ResponseBody
    public List<UserImageDTO> getUserList(){
        return userUploadService.getUploadList();
    }

    @GetMapping(value = "uploadUpdateForm")
    public String uploadUpdateForm(@RequestParam int seq, Model model){
        model.addAttribute("seq", seq);
        return "user/uploadUpdateForm";
    }

    @PostMapping(value = "getUpload")
    @ResponseBody
    public UserImageDTO getUpload(@RequestParam String seq){
        UserImageDTO upload = userUploadService.getUpload(Integer.parseInt(seq));
        System.out.println(upload.toString());
        return upload;
    }

    @PostMapping(value = "uploadUpdate")
    public String uploadUpdate(@ModelAttribute UserImageDTO userImageDTO,
                             @RequestParam("img[]") MultipartFile img){

        System.out.println("controller 들어온값 : " + userImageDTO.toString());

        // 초기값
        String originalFileName = userImageDTO.getImageOriginalName();
        String imageFileName = userImageDTO.getImageFileName();
        UserImageDTO dto = new UserImageDTO();

        // 네이버 클라우드 object storage
        //fileName = 네이버클라우드 Object Storage;
        if(!img.isEmpty()){
            // storage에서 파일 삭제
            objectStorageService.deleteFile(bucketName, userImageDTO.getImageFileName());
            System.out.println("imageFileName: " + userImageDTO.getImageFileName());
            originalFileName = img.getOriginalFilename();
            // storage에 새로운 파일 올리기
            imageFileName = objectStorageService.uploadFile(bucketName, "storage/", img);
        }

        dto.setSeq(userImageDTO.getSeq());
        dto.setImageName(userImageDTO.getImageName());
        dto.setImageContent(userImageDTO.getImageContent());
        dto.setImageFileName(imageFileName);
        dto.setImageOriginalName(originalFileName);
        System.out.println("seq : " + userImageDTO.getSeq());
        System.out.println("imageFileName(UUID) : " + imageFileName);
        System.out.println("ImageName : " + userImageDTO.getImageName());
        System.out.println("ImageContent : " + userImageDTO.getImageContent());

        // DB에서 내용 update
        userUploadService.uploadUpdate(dto);
        return "user/uploadList";
    }

    @PostMapping(value = "/uploadDelete")
    @ResponseBody
    public void uploadDelete(@RequestParam String seq,
                             @RequestParam String imageFileName){
        System.out.println("upload delete controller");
        System.out.println("seq : " + seq);
        System.out.println("imageFileName : " + imageFileName);
        userUploadService.uploadDelete(Integer.parseInt(seq));
        objectStorageService.deleteFile(bucketName, imageFileName);
    }

    @PostMapping(value = "/uploadDeleteList")
    @ResponseBody
    public void uploadDeleteList(@RequestParam String[] check){
        for(String s : check){
            System.out.println(s);
        }
        userUploadService.uploadDeleteList(check);
    }

}

/*   @PostMapping(value = "/upload")
    @ResponseBody
    public String upload(@ModelAttribute UserImageDTO userImageDTO,
                         @RequestParam MultipartFile img,
                         HttpSession session){
        // 가상 폴더
        String filePath_lier = "D:\\study\\SpringMVC\\Chapter06_Web\\src\\main\\webapp\\WEB-INF\\storage";
        // 실제 폴더 D:\study\SpringMVC\Chapter06_Web\target\Spring-1.0-SNAPSHOT\WEB-INF\storage
        String filePath = session.getServletContext().getRealPath("WEB-INF/storage");
        //이미지 이름
        String fileName = img.getOriginalFilename();
        // 파일 복사
        File file = new File(filePath_lier, fileName);
        try {
            img.transferTo(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        userImageDTO.setImage1(fileName);
        return "<img src='/Chapter06_Web/storage/'" + fileName + "/>'";
    }*/

// name="img" 2개 이상인 경우 -> 여러개의 파일 업로드 -> 배열로 받는다.
/*
    @PostMapping(value = "/upload")
    @ResponseBody
    public String upload(@ModelAttribute UserImageDTO userImageDTO,
                         @RequestParam MultipartFile[] img,
                         HttpSession session){
        // 실제 폴더 D:\study\SpringMVC\Chapter06_Web\target\Spring-1.0-SNAPSHOT\WEB-INF\storage
        String filePath = session.getServletContext().getRealPath("WEB-INF/storage");
        System.out.println("실제폴더 : " + filePath);

        //이미지 이름
        String fileName;
        File file;
        String result = "";

        if(img[0] != null){
            for(MultipartFile multipartFile : img){
                fileName = multipartFile.getOriginalFilename();
                file = new File(filePath, fileName);

                try {
                    multipartFile.transferTo(file);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                result += "<img src='/Chapter06_Web/storage/" + fileName + "'/>";
            }
        }
        return result;
    }
*/
