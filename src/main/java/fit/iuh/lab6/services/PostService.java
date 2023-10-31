package fit.iuh.lab6.services;

import fit.iuh.lab6.models.Post;
import fit.iuh.lab6.models.Users;
import fit.iuh.lab6.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public Optional<Post> findByID(long id){return  postRepository.findById(id);}
    public Page<Post> findAll(int page, int size, String sortBy, String sortDirection){
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection),sortBy);
        Pageable pageable = PageRequest.of(page-1,size,sort);
        return postRepository.findAll(pageable);
    }
    public  Page<Post> findByAuthor(Users user, int page, int size, String sortBy, String sortDirection){
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection),sortBy);
        Pageable pageable = PageRequest.of(page-1,size,sort);
        return postRepository.findByAuthor(user,pageable);
    }

    public Post save(Post post) {
        return postRepository.save(post);
    }
}
