package fit.iuh.lab6.services;

import fit.iuh.lab6.models.PostComment;
import fit.iuh.lab6.repositories.PostCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostCommentService {
    @Autowired
    private PostCommentRepository postCommentRepository;

    public PostComment save(PostComment postComment) {
        return postCommentRepository.save(postComment);
    }
}
