package br.com.codebit.codemarket.controllers;

import br.com.codebit.codemarket.dtos.ProductDTO;
import br.com.codebit.codemarket.entitys.Product;
import br.com.codebit.codemarket.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> find(@PathVariable String id){
        ProductDTO dto = service.find(Integer.valueOf(id));
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<Page<Product>> search(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy
    ) {
        Page<Product> products = service.search(name, page, linesPerPage, direction, orderBy);
        return ResponseEntity.ok().body(products);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody ProductDTO dto) {
        Product obj = service.save(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody ProductDTO dto, @PathVariable String id) {
        dto.setId(Integer.valueOf(id));
        service.update(dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id)")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(Integer.valueOf(id));
        return ResponseEntity.noContent().build();
    }
}