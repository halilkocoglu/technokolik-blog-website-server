package net.technokolik.blogwebsite.controllers;

import jakarta.validation.Valid;
import net.technokolik.blogwebsite.services.abstracts.RoleService;
import net.technokolik.blogwebsite.services.dtos.role.requests.CreateRoleRequest;
import net.technokolik.blogwebsite.services.dtos.role.requests.UpdateRoleRequest;
import net.technokolik.blogwebsite.services.dtos.role.responses.GetAllRoles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("roles")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public void add(@RequestBody @Valid CreateRoleRequest request){
        roleService.add(request);
    }
    @PutMapping("{id}")
    public void update(@RequestBody @Valid UpdateRoleRequest request){
        roleService.update(request);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id){
        roleService.delete(id);
    }
    @GetMapping("{id}")
    public GetAllRoles getById(@PathVariable("id") Long id){
        return roleService.getById(id);
    }
    @GetMapping
    public GetAllRoles getByName(@RequestParam(required = false) String name){
        return roleService.getByName(name);
    }
    @GetMapping("getAll")
    public List<GetAllRoles> getAll(){
        return roleService.getAll();
    }
    @GetMapping("getAllViaPage")
    public Page<GetAllRoles> getAllViaPage(Pageable pageable){
        return roleService.getAllViaPage(pageable);
    }
}
