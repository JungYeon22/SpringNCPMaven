package user.service;

import user.bean.UserDTO;
import user.bean.UserPaging;
import user.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserPaging userPaging;

    @Override
    public String isExistId(String id) {
        System.out.println("test");
        UserDTO userDTO = userDAO.isExistId(id);
        if(userDTO == null) return "non_exist";
        else                return "exist";
    }

    @Override
    public void write(UserDTO userDTO) {
        userDAO.write(userDTO);
    }

    @Override
    public Map<String, Object> getUserList(String pg) {
        int startNum = (Integer.parseInt(pg) * 3) - 3;
        int endNum = 3;

        List<UserDTO> list = userDAO.getUserList(startNum);
        // list -> JSON 변환시켜서 보내야 한다. -> pom.xml에서 해당 기능 받음

        //페이징 처리
        int total = userDAO.getTotalA();    // 전체 리스트 개수

        userPaging.setCurrentPage(Integer.parseInt(pg));
        userPaging.setPageBlock(3);
        userPaging.setPageSize(3);
        userPaging.setTotalA(total);
        userPaging.makePaingHTML();

        Map<String, Object> map = new HashMap<>();
        map.put("list", list);
        map.put("userPaging", userPaging);
        return map;

    }

    @Override
    public UserDTO getUser(String id) {
        System.out.println("userService start");
        return userDAO.getUser(id);
    }

    @Override
    public void update(UserDTO userDTO) {
        userDAO.update(userDTO);
    }

    @Override
    public void delete(String id) {
        userDAO.delete(id);
    }
}
