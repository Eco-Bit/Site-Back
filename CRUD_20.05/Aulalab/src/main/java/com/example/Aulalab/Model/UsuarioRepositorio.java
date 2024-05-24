package com.example.Aulalab.Model;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import com.example.Aulalab.Model.Usuario;
import com.example.Aulalab.Model.UsuarioRepositorio;
@EnableJpaRepositories(basePackages = "com.example.Aulalab")


@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    Usuario findByemail(String email);

}
