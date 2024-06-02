package com.example.Aulalab.Model;



import org.springframework.stereotype.Repository;
import com.example.Aulalab.Model.Usuario;
import com.example.Aulalab.Model.UsuarioRepositorio;
import org.springframework.data.mongodb.repository.MongoRepository;




@Repository
public interface UsuarioRepositorio extends MongoRepository<Usuario, String> {
    Usuario findByemail(String email);

}
