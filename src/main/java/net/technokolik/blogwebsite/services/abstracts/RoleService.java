package net.technokolik.blogwebsite.services.abstracts;

import net.technokolik.blogwebsite.entities.concretes.Role;
import net.technokolik.blogwebsite.services.dtos.role.requests.CreateRoleRequest;
import net.technokolik.blogwebsite.services.dtos.role.requests.UpdateRoleRequest;
import net.technokolik.blogwebsite.services.dtos.role.responses.GetAllRoles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoleService {
    void add(CreateRoleRequest request);
    void update(UpdateRoleRequest request);
    void delete(Long id);
    Role getOriginalById(Long id);
    GetAllRoles getById(Long id);
    GetAllRoles getByName(String name);
    List<GetAllRoles> getAll();
    Page<GetAllRoles> getAllViaPage(Pageable pageable);
}
