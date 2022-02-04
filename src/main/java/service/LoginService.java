package service;

import dao.Login;
import dao.Programmer;
import dto.LoginDTO;
import manager.HibernateController;
import mapper.LoginMapper;
import repository.LoginRepository;
import repository.ProgrammerRepository;

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
        ProgrammerRepository programmerRepository = new ProgrammerRepository(HibernateController.getInstance());
        try{
            Programmer programmer = programmerRepository.getById(loginDTO.getProgrammer().getId());
            programmer.getLogins().stream().filter(l -> l.getId().equals(loginDTO.getId())).forEach(programmer.getLogins()::remove);
            programmerRepository.update(programmer);
        }catch (Exception ex) {
            System.out.println("Login's programmer couldn't be retreived, attempting to proceed");
        }
        loginDTO.setProgrammer(null);
        updateLogin(loginDTO);

        Login login = this.delete(mapper.fromDTO(loginDTO));
        return mapper.toDTO(login);
    }
}