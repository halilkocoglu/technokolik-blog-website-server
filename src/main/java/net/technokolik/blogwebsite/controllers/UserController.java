package net.technokolik.blogwebsite.controllers;

import jakarta.validation.Valid;
import net.technokolik.blogwebsite.services.abstracts.UserService;
import net.technokolik.blogwebsite.services.dtos.user.requests.CreateUserRequest;
import net.technokolik.blogwebsite.services.dtos.user.requests.UpdateUserRequest;
import net.technokolik.blogwebsite.services.dtos.user.responses.GetAllUsers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public  void  add(@RequestBody @Valid CreateUserRequest request){
        userService.add(request);
    }
    @PutMapping("{id}")
    public void update(@RequestBody @Valid UpdateUserRequest request){
        userService.update(request);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id){
        userService.delete(id);
    }
    @GetMapping("{id}")
    public GetAllUsers getById(@PathVariable("id") Long id){
        return userService.getById(id);
    }
    @GetMapping("getByEmail")
    public GetAllUsers getByEmail(@PathVariable("email") String email){
        return userService.getByEmail(email);
    }
    @GetMapping("getAll")
    public List<GetAllUsers> getAll() {
        return userService.getAll();
    }
    @GetMapping("getAllViaPage")
    public Page<GetAllUsers> getAllViaPage(Pageable pageable){
        return userService.getAllViaPage(pageable);
    }
}
