package spe.booker.booker;

import org.springframework.stereotype.Component;


import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
@Component
public interface UserRepository extends CrudRepository<User, Integer> {

}