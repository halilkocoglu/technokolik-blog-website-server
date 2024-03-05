package net.technokolik.blogwebsite.services.concretes;

import net.technokolik.blogwebsite.core.exception.BusinessException;
import net.technokolik.blogwebsite.core.messages.Messages;
import net.technokolik.blogwebsite.core.utils.mapper.MapperService;
import net.technokolik.blogwebsite.entities.concretes.Role;
import net.technokolik.blogwebsite.repositories.RoleRepository;
import net.technokolik.blogwebsite.services.abstracts.RoleService;
import net.technokolik.blogwebsite.services.businessRules.RoleBusinessRules;
import net.technokolik.blogwebsite.services.dtos.role.requests.CreateRoleRequest;
import net.technokolik.blogwebsite.services.dtos.role.requests.UpdateRoleRequest;
import net.technokolik.blogwebsite.services.dtos.role.responses.GetAllRoles;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleManager implements RoleService {
    private final RoleRepository roleRepository;
    private final MapperService mapperService;
    private final RoleBusinessRules roleBusinessRules;

    public RoleManager(RoleRepository roleRepository, MapperService mapperService, RoleBusinessRules roleBusinessRules) {
        this.roleRepository = roleRepository;
        this.mapperService = mapperService;
        this.roleBusinessRules = roleBusinessRules;
    }

    @Override
    public void add(CreateRoleRequest request) {
        roleBusinessRules.ifRoleExistsShouldThrowException(request.getName());
        Role role = mapperService.forRequest().map(request, Role.class);
        roleRepository.save(role);
    }

    @Override
    public void update(UpdateRoleRequest request) {
        roleBusinessRules.ifRoleExistsShouldThrowException(request.getName());
        Role role = this.getOriginalById(request.getId());
        mapperService.forRequest().map(request, Role.class);
        roleRepository.save(role);
    }

    @Override
    public void delete(Long id) {
        Role role = this.getOriginalById(id);
        roleRepository.delete(role);
    }

    @Override
    public Role getOriginalById(Long id) {
        return roleRepository.findById(id).orElseThrow(() ->
                new BusinessException(Messages.getMessageForLocale("technokolik.constraint.role.invalid.id", LocaleContextHolder.getLocale())));
    }

    @Override
    public GetAllRoles getById(Long id) {
        Role role = this.getOriginalById(id);
        return mapperService.forResponse().map(role,GetAllRoles.class);
    }

    @Override
    public GetAllRoles getByName(String name) {
        roleBusinessRules.ifRoleNotExistShouldThrowException(name);
        Role role = roleRepository.findByName(name);
        return mapperService.forResponse().map(role,GetAllRoles.class);
    }

    @Override
    public List<GetAllRoles> getAll() {
        List<Role> roleList = roleRepository.findAll();
        return roleList.stream().map(role -> mapperService.forResponse().map(role, GetAllRoles.class)).toList();
    }

    @Override
    public Page<GetAllRoles> getAllViaPage(Pageable pageable) {
        Page<Role> roleList = roleRepository.findAll(pageable);
        return roleList.map(role -> mapperService.forResponse().map(role, GetAllRoles.class));
    }
}
