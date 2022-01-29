package service;

import dao.Login;
import dto.LoginDTO;
import mapper.LoginMapper;
import repository.LoginRepository;

import java.util.List;

public class LoginService extends BaseService<Login, String, LoginRepository>{
    private LoginMapper mapper = new LoginMapper();

    public LoginService(LoginRepository repository) {
        super(repository);
    }

    public List<LoginDTO> getAllLogins() throws Exception {
        return mapper.toDTO(this.findAll());
    }

    public LoginDTO getLoginById(String id) throws Exception {
        return mapper.toDTO(this.getById(id));
    }

    public LoginDTO insertLogin(LoginDTO loginDTO) throws Exception {
        Login login = this.save(mapper.fromDTO(loginDTO));
        return mapper.toDTO(login);
    }

    public LoginDTO updateLogin(LoginDTO loginDTO) throws Exception {
        Login login = this.update(mapper.fromDTO(loginDTO));
        return mapper.toDTO(login);
    }

    public LoginDTO deleteLogin(LoginDTO loginDTO) throws Exception {
        Login login = this.delete(mapper.fromDTO(loginDTO));
        return mapper.toDTO(login);
    }
}