package net.technokolik.blogwebsite.services.abstracts;

import net.technokolik.blogwebsite.entities.concretes.User;
import net.technokolik.blogwebsite.services.dtos.user.requests.CreateUserRequest;
import net.technokolik.blogwebsite.services.dtos.user.requests.UpdateUserRequest;
import net.technokolik.blogwebsite.services.dtos.user.responses.GetAllUsers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    void add(CreateUserRequest request);
    void update(UpdateUserRequest request);
    void  delete (Long id);
    User getOriginalById(Long id);
    GetAllUsers getById(Long id);
    GetAllUsers getByEmail(String email);
    List<GetAllUsers> getAll();
    Page<GetAllUsers> getAllViaPage(Pageable pageable);
}
