package io.github.thiago1henrique.libaryapi.repository;

import io.github.thiago1henrique.libaryapi.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AutorRepository extends JpaRepository<Autor, UUID> {

}
