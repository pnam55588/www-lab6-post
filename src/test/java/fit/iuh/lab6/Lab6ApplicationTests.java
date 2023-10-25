package fit.iuh.lab6;

import fit.iuh.lab6.models.Post;
import fit.iuh.lab6.repositories.PostRepository;
import fit.iuh.lab6.services.PostService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class Lab6ApplicationTests {
    @Autowired
    private PostService postService;

    @Test
    void testFindAllPostService(){
        Page<Post> result = postService.findAll(1,10,"id","desc");
        assertEquals(999,result.getTotalElements());
        for (int i=0;i<2;i++){
            System.out.println(result.getContent().get(i));
        }
    }

}
