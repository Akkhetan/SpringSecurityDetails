package com.ank.ss_action17.services;

import com.ank.ss_action17.model.EmployeeRecord;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CommonServiceRecord {



    private final Map<String, EmployeeRecord> records =
            Map.of("mary",
                    new EmployeeRecord("Mary Thompson",
                            List.of("Karamazov Brothers"),
                            List.of("accountant", "reader")),
                    "bill",
                    new EmployeeRecord("Bill Parker",
                            List.of("Beautiful Paris"),
                            List.of("researcher"))
            );

    @PreAuthorize("hasAuthority('write')")
    public String getName() {
        return "Fantastico";
    }

    /*
    Here in PreAuthorize : 'name' is passed as parameter will be compared if logged-in user. It will execute method only when it matches otherwise AccessDeniedException exception is thrown
    In PostAuthorize : method will execute first then as per the given condition it will check if role is reader only then it returns the result
     */
    @PreAuthorize("#name == authentication.principal.username")
    @PostAuthorize("returnObject.roles.contains('reader')")
    public EmployeeRecord getBookDetails(String name) {
        return records.get(name);
    }
}
