package br.com.codebit.codemarket.services;

import br.com.codebit.codemarket.dtos.UserDTO;
import br.com.codebit.codemarket.dtos.UserSaveDTO;
import br.com.codebit.codemarket.entitys.User;
import br.com.codebit.codemarket.entitys.enums.Profile;
import br.com.codebit.codemarket.repositories.UserRepository;
import br.com.codebit.codemarket.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {

    private final UserRepository repo;

    @Autowired
    public UserService(UserRepository repo) {
        this.repo = repo;
    }

//    @Autowired
//    private BCryptPasswordEncoder be;

    public User find(Integer id) {
        return repo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado"));
    }

    public List<User> findAll() {
        return repo.findAll();
    }

    public User save(UserSaveDTO dto) {
        User user = new User(dto.getName(), dto.getEmail(), dto.getPassword());
        return repo.save(user);
    }

    public void update(UserDTO dto) {
        User obj = find(dto.getId());
        repo.save(updateEntity(obj, dto, true));
    }

    public void updateMyUser(UserDTO dto) {
        User obj = find(dto.getId());
        repo.save(updateEntity(obj, dto, false));
    }

    public void delete(Integer id) {
        User obj = find(id);
        obj.setExclude_email(obj.getEmail());
        obj.setEmail(null);
        obj.setExcluded_at(new Date());
        obj.setExcluded(true);
        repo.save(obj);
    }

    private User updateEntity(User user, UserDTO dto, boolean profile) {
        user.setEmail(dto.getEmail());
        user.setEnabled(dto.getEnabled());
        user.setName(dto.getName());

        if(dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            dto.setPassword(dto.getPassword());
        }

        if (profile) {
            user.getProfiles().clear();
            user.addProfile(Profile.CLIENTE);

            if (user.getProfiles() != null && user.getProfiles().contains(Profile.ADMIN)) {
                user.addProfile(Profile.ADMIN);
            }
        }

        return user;
    }
}
