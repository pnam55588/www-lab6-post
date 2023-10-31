package fit.iuh.lab6.services;

import fit.iuh.lab6.models.Users;
import fit.iuh.lab6.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private UsersRepository usersRepository;

    public Users update(Users user){
        return usersRepository.save(user);
    };
    public Users getUserLogin(String email, String password){
        return usersRepository.findByEmailAndPasswordHash(email,password);
    }
}
