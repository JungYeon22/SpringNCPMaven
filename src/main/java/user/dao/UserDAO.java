package user.dao;

import user.bean.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDAO {
    UserDTO isExistId(String id);
    void write(UserDTO userDTO);
    List<UserDTO> getUserList(int pg);
    int getTotalA();
    UserDTO getUser(String id);
    void update(UserDTO userDTO);
    void delete(String id);
}
