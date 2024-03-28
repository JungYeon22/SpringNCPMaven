package user.dao;

import org.apache.ibatis.annotations.Mapper;
import user.bean.UserImageDTO;

import java.util.List;

@Mapper
public interface UserUploadDAO {

    List<UserImageDTO> getUploadList();

    void upload(UserImageDTO userImageDTO);

    UserImageDTO getUpload(int seq);

    void uploadUpdate(UserImageDTO userImageDTO);

    void uploadDelete(int seq);

    /*void upload(List<UserImageDTO> userImageList);*/
}
