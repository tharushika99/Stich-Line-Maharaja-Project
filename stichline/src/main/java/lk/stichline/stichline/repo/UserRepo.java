package lk.stichline.stichline.repo;

import lk.stichline.stichline.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends MongoRepository<User,String> {
    User findByUsername(String username);
}
