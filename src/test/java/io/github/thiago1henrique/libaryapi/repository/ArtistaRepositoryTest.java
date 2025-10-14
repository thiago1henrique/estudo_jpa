package io.github.thiago1henrique.libaryapi.repository;

import io.github.thiago1henrique.libaryapi.model.Artista;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
public class ArtistaRepositoryTest {

    @Autowired
    ArtistaRepository repository;

    Artista artista = new Artista();

    @Test
    void salvarTest() {
        artista.setNome("TE AMO MEU AMOR");
        artista.setDataNascimento(LocalDate.of(2000, 1, 1));
        artista.setGenero("ROCK");
        artista.setVivo(true);

        repository.save(artista);
    }

    @Test
    void deletarTest() {
        var id = UUID.fromString("2fb26999-24b0-449c-b02c-bf46fdefb5fc");
        repository.deleteById(id);
    }

    @Test
    void atualizarTest() {
        var id = UUID.fromString("50d1f9f3-394d-4b83-9cc9-69269724d6fc");
        var artistaEncontrado = repository.findById(id);
        if (artistaEncontrado.isPresent()) {
            Artista artistaAtualizado = artistaEncontrado.get();
            artistaAtualizado.setNome("Hiper mercado de semem");
            artistaAtualizado.setVivo(false);
            repository.save(artistaAtualizado);
        }
    }

    @Test
    void listarTest() {
        repository.findAll().forEach(System.out::println);
    }
}
