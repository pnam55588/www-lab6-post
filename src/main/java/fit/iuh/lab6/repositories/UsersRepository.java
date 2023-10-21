package fit.iuh.lab6.repositories;

import fit.iuh.lab6.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
}