package com.ank.ss_action18.services;


import com.ank.ss_action18.model.Document;
import com.ank.ss_action18.repositories.DocumentRepository;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    //We use a hasPermission() SpEL expression which allows us to refer to an external authorization
    @PostAuthorize("hasPermission(returnObject, 'ROLE_admin')")
    public Document getDocument(String code) {
        return documentRepository.findDocument(code);
    }

    /*
    Here we are implementing similar logic by using PreAuthorize instead of PostAuthorize

    @PreAuthorize("hasPermission(#code, 'document', 'ROLE_admin')")
    public Document getDocument(String code) {
        return documentRepository.findDocument(code);
    }

     */
}
