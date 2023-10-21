package fit.iuh.lab6.repositories;

import fit.iuh.lab6.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}