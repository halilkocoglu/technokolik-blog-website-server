package net.technokolik.blogwebsite.core.exception;

import net.technokolik.blogwebsite.core.messages.Messages;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Collections;
import java.util.Map;

public class NotUniqueEmailException  extends RuntimeException{
    public NotUniqueEmailException() {
        super(Messages.getMessageForLocale("technokolik.constraint.email.not_unique", LocaleContextHolder.getLocale()));

    }

    public Map<String,String> getValidationErros(){
        return Collections.singletonMap("email", Messages.getMessageForLocale("technokolik.constraint.email.not_unique", LocaleContextHolder.getLocale()));
    }
}
