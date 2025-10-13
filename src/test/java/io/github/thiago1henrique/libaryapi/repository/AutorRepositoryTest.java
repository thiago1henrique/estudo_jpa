package io.github.thiago1henrique.libaryapi.repository;

import io.github.thiago1henrique.libaryapi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Test
    public void salvarTest() {
        Autor autor = new Autor();
        autor.setNome("Henrique");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(2001, 2, 13));

        var autorSalvo = repository.save(autor);
        System.out.println("Autor salvo com sucesso: " + autorSalvo);
    }

    @Test
    public void atualizarTest() {
        var id = UUID.fromString("10abc6c4-3eb8-4954-91b2-032c83a47e65");
        Optional<Autor> possivelAutor = repository.findById(id);
        if(possivelAutor.isPresent()) {
            Autor autorEncontrado = possivelAutor.get();
            System.out.print("Dados do autor: ");
            System.out.println(autorEncontrado);

            autorEncontrado.setDataNascimento(LocalDate.of(2000, 1, 12));

            repository.save(autorEncontrado);
        }
    }

    @Test
    public void listarTest() {
        List<Autor> lista = repository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    public void countTest() {
        System.out.println("Contagem de autores: " + repository.count());
    }

    @Test
    public void deletarPorIdTest() {
        var id = UUID.fromString("10abc6c4-3eb8-4954-91b2-032c83a47e65");
        repository.deleteById(id);
    }

    @Test
    public void deleteTeste() {
        var id = UUID.fromString("e3e0a2dc-9ca3-4696-a234-a1de2738de0f");
        var henrique = repository.findById(id).get();
        repository.delete(henrique);

    }
}
