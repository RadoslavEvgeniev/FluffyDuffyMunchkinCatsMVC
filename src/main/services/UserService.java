package services;

import entities.User;
import models.bindingModels.UserLoginBindingModel;
import models.bindingModels.UserRegisterBindingModel;
import repositories.UserRepository;

public class UserService {

    private UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    public void saveUser(UserRegisterBindingModel userRegisterBindingModel) {
        User user = new User();
        user.setUsername(userRegisterBindingModel.getUsername());
        user.setPassword(userRegisterBindingModel.getPassword());

        this.userRepository.saveUser(user);
    }

    public boolean userIsExistent(UserLoginBindingModel userLoginBindingModel) {
        User userFromDb = this.userRepository.findByUsername(userLoginBindingModel.getUsername());

        return userFromDb != null;
    }

    public boolean userPasswordMatches(UserLoginBindingModel userLoginBindingModel) {
        User userFromDb = this.userRepository.findByUsername(userLoginBindingModel.getUsername());

        return userFromDb.getPassword().equals(userLoginBindingModel.getPassword());
    }

    public String getUserId(UserLoginBindingModel userLoginBindingModel) {
        User userFromDb = this.userRepository.findByUsername(userLoginBindingModel.getUsername());

        return userFromDb.getId();
    }
}
