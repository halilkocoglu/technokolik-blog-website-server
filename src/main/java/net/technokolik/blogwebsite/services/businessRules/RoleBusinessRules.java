package net.technokolik.blogwebsite.services.businessRules;

import net.technokolik.blogwebsite.core.exception.BusinessException;
import net.technokolik.blogwebsite.core.messages.Messages;
import net.technokolik.blogwebsite.repositories.RoleRepository;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
public class RoleBusinessRules {
    private final RoleRepository roleRepository;

    public RoleBusinessRules(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void ifRoleExistsShouldThrowException(String name){
        if (roleRepository.existsByName(name)){
            throw new BusinessException(Messages.getMessageForLocale("technokolik.constraint.role.name.exists", LocaleContextHolder.getLocale()));
        }
    }
    public void ifRoleNotExistShouldThrowException(String name){
        if (!roleRepository.existsByName(name)){
            throw new BusinessException(Messages.getMessageForLocale("technokolik.constraint.role.name.not_found",LocaleContextHolder.getLocale()));
        }
    }
}
