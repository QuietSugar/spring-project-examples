package name.xu;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Created by Xu
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

}
