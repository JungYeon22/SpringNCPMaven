package user.service;

import user.bean.UserImageDTO;
import user.dao.UserUploadDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserUploadServiceImpl implements UserUploadService{
    @Autowired
    private UserUploadDAO userUploadDAO;
    @Autowired
    private ObjectStorageService objectStorageService;

    private String bucketName = "bitcamp-6th-bucket-94";
    @Override
    public void upload(List<UserImageDTO> userImageList) {
        for(UserImageDTO userImageDTO : userImageList){
            userUploadDAO.upload(userImageDTO);
        }
    }

    @Override
    public UserImageDTO getUpload(int seq) {
        UserImageDTO upload = userUploadDAO.getUpload(seq);
        System.out.println(upload.toString());
        return upload;
    }

    @Override
    public void uploadUpdate(UserImageDTO userImageDTO) {
        userUploadDAO.uploadUpdate(userImageDTO);
    }

    @Override
    public void uploadDelete(int seq) {
        System.out.println("upload delete service");
        userUploadDAO.uploadDelete(seq);
    }

    @Override
    public void uploadDeleteList(String[] check) {
        for(String item :  check){
            // DB에서 imageFileName 가져오기
            UserImageDTO upload = userUploadDAO.getUpload(Integer.parseInt(item));
            // NCP storage 이미지 삭제
            objectStorageService.deleteFile(bucketName, upload.getImageFileName());
            // DB 내용 삭제
            userUploadDAO.uploadDelete(Integer.parseInt(item));
        }
    }

/*  @Override
    public void upload(List<UserImageDTO> userImageList) {
        userUploadDAO.upload(userImageList);
    }*/

    @Override
    public List<UserImageDTO> getUploadList() {
        return userUploadDAO.getUploadList();
    }




}
