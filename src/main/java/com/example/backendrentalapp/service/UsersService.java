package com.example.backendrentalapp.service;


import com.example.backendrentalapp.model.UsersModel;
import com.example.backendrentalapp.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public UsersModel registerUsers(String login, String password, String email) {
        if(login !=null && password !=null){
            UsersModel usersModel = new UsersModel();
            usersModel.setLogin(login);
            usersModel.setPassword(password);
            usersModel.setEmail(email);
            return usersRepository.save(usersModel);
        } else {
            return null;
        }
    }

    public UsersModel authentication(String login, String password) {
        return usersRepository.findByLoginAndPassword(login, password).orElse(null);
    }
}
