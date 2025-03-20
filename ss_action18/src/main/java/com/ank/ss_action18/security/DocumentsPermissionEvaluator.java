package com.ank.ss_action18.security;

import com.ank.ss_action18.model.Document;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;

//For implementing permission logic, need to implement PermissionEvaluator contract.
@Component
public class DocumentsPermissionEvaluator implements PermissionEvaluator {

    @Override
    public boolean hasPermission(Authentication authentication,
                                 Object target,
                                 Object permission) {
        Document document = (Document) target;  //Casts the target object to Document
        String p = (String) permission; //The permission object in our case is the role name, so we cast it to a String.



        boolean admin =
           authentication.getAuthorities()
           .stream()
           .anyMatch(a -> a.getAuthority().equals(p));

        return admin || document.owner().equals(authentication.getName());
    }

    @Override
    public boolean hasPermission(Authentication authentication,
                                 Serializable targetId,
                                 String targetType,
                                 Object permission) {
        return false;
    }

    /*

    This commented code to be used when authorising using  PreAuthorize instead of PostAuthorize. In that case above code should be commented

    @Override
    public boolean hasPermission(Authentication authentication,
                                 Object target,
                                 Object permission) {
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication,
                                 Serializable targetId,
                                 String targetType,
                                 Object permission) {
        String code = targetId.toString();
        Document document = documentRepository.findDocument(code);

        String p = (String) permission;


        boolean admin =
                authentication.getAuthorities()
                        .stream()
                        .anyMatch(a -> a.getAuthority().equals(p));

        return admin || document.getOwner().equals(authentication.getName());
    }

     */
}
