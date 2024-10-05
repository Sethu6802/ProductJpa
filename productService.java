package SpringBootApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class productService {

    @Autowired
    private productRepo repo;

    public Product addProduct(Product newProduct){
        return repo.save(newProduct);
    };

}
