package io.github.thiago1henrique.libaryapi.repository;

import io.github.thiago1henrique.libaryapi.model.Autor;
import io.github.thiago1henrique.libaryapi.model.GeneroLivro;
import io.github.thiago1henrique.libaryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

        livro.setIsbn("1234567890");
        livro.setPreco(BigDecimal.valueOf(100.00));
        livro.setGenero(GeneroLivro.BIOGRAFIA);
        livro.setTitulo("Thiago Henrique");
        livro.setDataPublicacao(LocalDate.of(2000, 1, 13));

        Autor autor = autorRepository.findById(UUID
                        .fromString("f4dfc777-33ce-4118-8e3c-cd7606603e8c"))
                        .orElse(null);

        livro.setAutor(autor);

        repository.save(livro);

    }

}