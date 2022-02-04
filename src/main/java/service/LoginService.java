package service;

import dao.Login;
import dao.Programmer;
import dto.LoginDTO;
import manager.HibernateController;
import mapper.LoginMapper;
import repository.LoginRepository;
import repository.ProgrammerRepository;

import java.util.List;

/**
 * Servicio para la entidad login. Implementa un CRUD y sus metodos retornan listas
 * con los resultados de las operaciones.
 */
public class LoginService extends BaseService<Login, String, LoginRepository>{
    private LoginMapper mapper = new LoginMapper();

    public LoginService(LoginRepository repository) {
        super(repository);
    }

    /**
     * Obtener todas las entidades login
     * @return Lista con todos los logins
     */
    public List<LoginDTO> getAllLogins() throws Exception {
        return mapper.toDTO(this.findAll());
    }
    
    /**
     * Obtener entidad login segun la id
     * @param id String id del login
     * @return Lista con todos los logins
     */
    public LoginDTO getLoginById(String id) throws Exception {
        return mapper.toDTO(this.getById(id));
    }

    /**
     * inserta un login en la base de datos
     * @param loginDTO login a insertar
     * @return Lista de login correcta si la operación se ha realizado, null en caso de no completarse
     */
    public LoginDTO insertLogin(LoginDTO loginDTO) throws Exception {
        Login login = this.save(mapper.fromDTO(loginDTO));
        return mapper.toDTO(login);
    }

    /**
     * actualiza un login en la base de datos
     * @param loginDTO a actualizar
     * @return Lista de login si la operacion se realiza, null en caso de no realizarse
     */
    public LoginDTO updateLogin(LoginDTO loginDTO) throws Exception {
        Login login = this.update(mapper.fromDTO(loginDTO));
        return mapper.toDTO(login);
    }

    /**
     * elimina un login de la base de datos. Se asegura de quitar sus relaciones en otras
     * entidades con las que pueda estar relacionada.
     * @param loginDTO a eliminar
     * @return Lista de login si la operacion se realiza, null en caso de no realizars
     */
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