package net.technokolik.blogwebsite.services.businessRules;

import lombok.AllArgsConstructor;
import net.technokolik.blogwebsite.core.exception.BusinessException;
import net.technokolik.blogwebsite.core.messages.Messages;
import net.technokolik.blogwebsite.entities.concretes.User;
import net.technokolik.blogwebsite.repositories.UserRepository;
import net.technokolik.blogwebsite.services.dtos.user.requests.UpdateUserRequest;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class UserBusinessRules {
    private final UserRepository userRepository;

    public void ifUserNotFoundShouldThrowException (Long id){
        if (!userRepository.existsById(id)){
            throw new BusinessException(Messages.getMessageForLocale("technokolik.constraint.user.not_found", LocaleContextHolder.getLocale()));
        }
    }

    public void ifEmailExistsShouldThrowException(UpdateUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())){
            User user = userRepository.findByEmail(request.getEmail());
            if(!Objects.equals(user.getId(), request.getId())){
                throw  new BusinessException(Messages.getMessageForLocale("technokolik.constraint.user.email.in-use",LocaleContextHolder.getLocale()));
            }
        }
    }
}
