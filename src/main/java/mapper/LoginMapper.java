package mapper;

import dao.Login;
import dto.LoginDTO;

public class LoginMapper extends BaseMapper<Login, LoginDTO>{
    @Override
    public Login fromDTO(LoginDTO item) {
        return new Login(item.getId(), item.getProgrammer(), item.getTime(), item.getToken(), item.isActive());
    }

    @Override
    public LoginDTO toDTO(Login item) {
        return new LoginDTO(item.getId(), item.getProgrammer(), item.getTime(), item.getToken(), item.isActive());
    }
}
