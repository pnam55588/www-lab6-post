package fit.iuh.lab6.repositories;

import fit.iuh.lab6.models.Post;
import fit.iuh.lab6.models.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByAuthor(Users user, Pageable pageable);
}