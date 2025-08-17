package mylab.user.di.xml;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository // @Component의 스테레오타입
public class UserRepository {
    
    // @Value 어노테이션을 사용하여 XML 설정에서 값을 주입
    @Value("MySQL")
    private String dbType;
    
    public UserRepository() {}
    
    public String getDbType() { return dbType; }
    public void setDbType(String dbType) { this.dbType = dbType; }
    
    public boolean saveUser(String userId, String name) {
        System.out.println("사용자 저장: " + userId + ", " + name + " (DB: " + dbType + ")");
        return true;
    }
    
    @Override
    public String toString() {
        return "UserRepository [dbType=" + dbType + "]";
    }
}