package SpringBootApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/product/api")
public class productController {

    Logger logger = Logger.getLogger("productController.class");

    @Autowired
    productService service;

    ArrayList<Product> productList = new ArrayList<>();

    {
        productList.add(new SpringBootApi.Product(101, "Nike", "Shoe", 15, 10000));
        productList.add(new SpringBootApi.Product(102, "Marshall", "Speaker", 2, 400000));
        productList.add(new SpringBootApi.Product(103, "Rolex", "Watch", 20, 50000));
        productList.add(new SpringBootApi.Product(104, "Adidas", "Shoe", 13, 3440));
    }


    @GetMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SpringBootApi.Product> getById(@PathVariable int id) {

        Optional<SpringBootApi.Product> optional = productList.stream().filter(product -> product.getId() == id).findFirst();
        return optional.map(product -> ResponseEntity.status(HttpStatus.OK).body(product)).orElse(null);

    }


    @GetMapping(value = "/brand/{brand}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SpringBootApi.Product> getByBrand(@PathVariable String brand) {

        Optional<SpringBootApi.Product> optional = productList.stream().filter(product -> product.getBrand().equals(brand)).findAny();
        return optional.map(product -> ResponseEntity.status(HttpStatus.OK).body(product)).orElse(null);

    }


    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SpringBootApi.Product> addProduct(@RequestBody SpringBootApi.Product product) {

        logger.info("Entered Post Method " + product + "Successfully\n");
        Product newProduct = service.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);

    }


    @GetMapping(value = "/info", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<SpringBootApi.Product>> productInfo() {

        return ResponseEntity.status(HttpStatus.OK).body(productList);

    }


    @PutMapping(value = "/modify", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SpringBootApi.Product> updateProduct(@RequestBody SpringBootApi.Product product) {

        Optional<SpringBootApi.Product> optional = productList.stream().filter(p -> p.getId() == product.getId()).findFirst();
        if(optional.isPresent()){
            SpringBootApi.Product temp = optional.get();

            temp.setBrand(product.getBrand());
            temp.setDesc(product.getDesc());
            temp.setQty(product.getQty());
            temp.setPrice(product.getPrice());
        }

//        int index = productList.indexOf(temp);
//        productList.remove(index);
//        productList.add(temp);

        return null;
    }
}