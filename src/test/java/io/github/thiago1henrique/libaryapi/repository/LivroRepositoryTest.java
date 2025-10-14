package io.github.thiago1henrique.libaryapi.repository;

import io.github.thiago1henrique.libaryapi.model.Autor;
import io.github.thiago1henrique.libaryapi.model.GeneroLivro;
import io.github.thiago1henrique.libaryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository repository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarTest() {
        Livro livro = new Livro();

        livro.setIsdn("1234567890");
        livro.setPreco(BigDecimal.valueOf(100.00));
        livro.setGenero(GeneroLivro.BIOGRAFIA);
        livro.setTitulo("Thiago Henrique");
        livro.setDataPublicacao(LocalDate.of(2000, 1, 13));

        Autor autor = autorRepository.findById(UUID
                        .fromString("45578974-d96f-4d4b-be92-1dceec31da4b"))
                .orElse(null);

        livro.setAutor(autor);

        repository.save(livro);
    }

    @Test
    void salvarAutorELivroTest() {
        Livro livro = new Livro();

        livro.setIsdn("1234567890");
        livro.setPreco(BigDecimal.valueOf(100.00));
        livro.setGenero(GeneroLivro.BIOGRAFIA);
        livro.setTitulo("Thiago Henrique");
        livro.setDataPublicacao(LocalDate.of(2000, 1, 13));


        repository.save(livro);

        Autor autor = new Autor();
        autor.setNome("Thiago Henrique");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(2001, 2, 13));

        livro.setAutor(autor);

        repository.save(livro);
    }

    @Test
    void salvarCascadeTest() {
        Livro livro = new Livro();

        livro.setIsdn("1234567890");
        livro.setPreco(BigDecimal.valueOf(100.00));
        livro.setGenero(GeneroLivro.BIOGRAFIA);
        livro.setTitulo("Thiago Henrique");
        livro.setDataPublicacao(LocalDate.of(2000, 1, 13));

        Autor autor = new Autor();
        autor.setNome("Thiago Henrique");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(2001, 2, 13));

        livro.setAutor(autor);

        repository.save(livro);
    }

    @Test
    void atualizarAutorDoLivroTest() {
        UUID id = UUID.fromString("c0b30d24-944a-4688-85a6-c18aa78e4481");
        var livroParaAtualizar = repository.findById(id).orElse(null);

        UUID idAutor = UUID.fromString("f4dfc777-33ce-4118-8e3c-cd7606603e8c");
        Autor autor = autorRepository.findById(idAutor).orElse(null);

        livroParaAtualizar.setAutor(autor);

        repository.save(livroParaAtualizar);
    }

    @Test
    void deletarTest() {
        UUID id = UUID.fromString("c0b30d24-944a-4688-85a6-c18aa78e4481");
        repository.deleteById(id);
    }

    @Test
    @Transactional
    void buscarLivroTest() {
        UUID id = UUID.fromString("c0b30d24-944a-4688-85a6-c18aa78e4481");
        Livro livro = repository.findById(id).orElse(null);
        System.out.println("Livro: ");
        System.out.println(livro.getTitulo());
        System.out.println("Autor: ");
        System.out.println(livro.getAutor().getNome());
    }
}