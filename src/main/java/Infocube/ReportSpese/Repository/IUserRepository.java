package Infocube.ReportSpese.Repository;

import Infocube.ReportSpese.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IUserRepository extends CrudRepository<User, Integer>, JpaRepository<User, Integer>
{
//    List<User> findByEmailAndPassword(String mail, String pass);
    User findByEmailAndPassword(String mail, String pass);
    User findByEmail(String mail);

    @Query( value = "INSERT INTO user (name, surname, email, password, sex, region, birth_date) values (?1.name, ?1.surname, ?1.email, ?1.password, ?1.sex, ?1.region, ?1.birth_date) ", nativeQuery = true)
    User createUser(User u);

    @Query( value = "UPDATE user u SET u.name=?1.name, u.surname=?1.surname, u.email=?1.email, u.password=?1.password, u.sex=?1.sex, u.region=?1.region, u.bith_date=?1.birth_date WHERE id= ?2", nativeQuery = true)
    User updateUser(User u, Integer id);

    @Query( value = "DELETE FROM user u WHERE id= ?1", nativeQuery = true)
    void deleteUser(Integer id);

}
