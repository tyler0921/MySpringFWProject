package mylab.user.di.xml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // @Component의 스테레오타입
public class UserService {
    
    // @Autowired를 사용하여 UserRepository와 SecurityService를 자동 주입
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private SecurityService securityService;
    
    public UserRepository getUserRepository() { return userRepository; }
    public SecurityService getSecurityService() { return securityService; }
    
    public boolean registerUser(String userId, String name, String password) {
        if (securityService.authenticate(userId, password)) {
            return userRepository.saveUser(userId, name);
        }
        return false;
    }
}