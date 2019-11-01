package br.com.codebit.codemarket.controllers;

import br.com.codebit.codemarket.dtos.ProductDTO;
import br.com.codebit.codemarket.dtos.UserDTO;
import br.com.codebit.codemarket.dtos.UserSaveDTO;
import br.com.codebit.codemarket.entitys.Product;
import br.com.codebit.codemarket.entitys.User;
import br.com.codebit.codemarket.services.ProductService;
import br.com.codebit.codemarket.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> find(@PathVariable String id){
        User obj = service.find(Integer.valueOf(id));
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping()
    public ResponseEntity<List<User>> findAll(){
        List<User> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody UserSaveDTO dto) {
        User obj = service.save(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody UserDTO dto, @PathVariable String id) {
        dto.setId(Integer.valueOf(id));
        service.update(dto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/my-user/{id}")
    public ResponseEntity<Void> updateMyUser(@Valid @RequestBody UserDTO dto, @PathVariable String id) {
        dto.setId(Integer.valueOf(id));
        service.updateMyUser(dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(Integer.valueOf(id));
        return ResponseEntity.noContent().build();
    }
}