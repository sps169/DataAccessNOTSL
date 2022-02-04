package mapper;

import dao.Login;
import dto.LoginDTO;
/**
 * Clase de mapeo de datos de Login y LoginDTO. Implementa interfaz BaseMapper
 */
public class LoginMapper extends BaseMapper<Login, LoginDTO>{
    /**
     * metodo para mapear desde un LoginDto a Login
     * @param item LoginDTO
     * @return Login
     */
    @Override
    public Login fromDTO(LoginDTO item) {
        return new Login(item.getId(), item.getProgrammer(), item.getTime(), item.getToken(), item.isActive());
    }
    /**
     * metodo para mapear desde un Login a LoginDto
     * @param item Login
     * @return LoginDTO
     */
    @Override
    public LoginDTO toDTO(Login item) {
        return new LoginDTO(item.getId(), item.getProgrammer(), item.getTime(), item.getToken(), item.isActive());
    }
}
