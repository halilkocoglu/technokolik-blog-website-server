package net.technokolik.blogwebsite.services.concretes;

import lombok.AllArgsConstructor;
import net.technokolik.blogwebsite.core.exception.BusinessException;
import net.technokolik.blogwebsite.core.messages.Messages;
import net.technokolik.blogwebsite.core.utils.mapper.MapperService;
import net.technokolik.blogwebsite.entities.concretes.User;
import net.technokolik.blogwebsite.repositories.UserRepository;
import net.technokolik.blogwebsite.services.abstracts.UserService;
import net.technokolik.blogwebsite.services.businessRules.UserBusinessRules;
import net.technokolik.blogwebsite.services.dtos.user.requests.CreateUserRequest;
import net.technokolik.blogwebsite.services.dtos.user.requests.UpdateUserRequest;
import net.technokolik.blogwebsite.services.dtos.user.responses.GetAllUsers;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class UserManager implements UserService {
    private final UserRepository userRepository;
    private final MapperService mapperService;
    private final UserBusinessRules userBusinessRules;
    @Override
    public void add(CreateUserRequest request) {

        User user = mapperService.forRequest().map(request, User.class);
        userRepository.save(user);
    }

    @Override
    public void update(UpdateUserRequest request) {
        userBusinessRules.ifEmailExistsShouldThrowException(request);
        //TODO: Eposta - kullanıcı konrolü sağla
        User user = this.getOriginalById(request.getId());
        mapperService.forRequest().map(request, User.class);
        userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        User user = this.getOriginalById(id);
        userRepository.delete(user);
    }

    @Override
    public User getOriginalById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new BusinessException(Messages.getMessageForLocale("technokolik.constraint.user.invalid.id", LocaleContextHolder.getLocale())));
    }

    @Override
    public GetAllUsers getById(Long id) {
        User user = this.getOriginalById(id);
        return mapperService.forResponse().map(user, GetAllUsers.class);
    }

    @Override
    public GetAllUsers getByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return mapperService.forResponse().map(user, GetAllUsers.class);
    }

    @Override
    public List<GetAllUsers> getAll() {
        List<User> userList = userRepository.findAll();
        List<GetAllUsers> responseList = userList
                .stream()
                .map(user -> mapperService.forResponse().map(user, GetAllUsers.class))
                .toList();
        return responseList;
    }

    @Override
    public Page<GetAllUsers> getAllViaPage(Pageable pageable) {
        Page<User> userList = userRepository.findAll(pageable);
        return userList.map(user -> mapperService.forResponse().map(user, GetAllUsers.class));
    }
}
