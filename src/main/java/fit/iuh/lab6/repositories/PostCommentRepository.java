package fit.iuh.lab6.repositories;

import fit.iuh.lab6.models.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCommentRepository extends JpaRepository<PostComment, Long> {
}