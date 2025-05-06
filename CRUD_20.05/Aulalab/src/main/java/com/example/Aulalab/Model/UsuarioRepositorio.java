package com.example.Aulalab.Model;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.Aulalab.Model.Usuario;
import com.example.Aulalab.Model.UsuarioRepositorio;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


@Repository

public interface UsuarioRepositorio extends MongoRepository<Usuario, String> {

    Optional<Usuario> findByemail(String email);

}
