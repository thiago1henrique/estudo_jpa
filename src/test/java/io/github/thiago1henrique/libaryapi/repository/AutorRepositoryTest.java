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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Autowired
    LivroRepository livroRepository;

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
    @Transactional
    public void atualizarTest() {
        var id = UUID.fromString("1d536e7d-499a-42a4-a5c9-8e484020f137");
        Optional<Autor> possivelAutor = repository.findById(id);
        if(possivelAutor.isPresent()) {
            Autor autorEncontrado = possivelAutor.get();
            System.out.print("Dados do autor: ");
            System.out.println(autorEncontrado);

            autorEncontrado.setNome("Thiago Henrique");
            autorEncontrado.setDataNascimento(LocalDate.of(2000, 1, 12));

            repository.save(autorEncontrado);
        }
    }

    @Test
    @Transactional
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
        var id = UUID.fromString("1d536e7d-499a-42a4-a5c9-8e484020f137");
        repository.deleteById(id);
    }

    @Test
    public void deleteTeste() {
        var id = UUID.fromString("e3e0a2dc-9ca3-4696-a234-a1de2738de0f");
        var henrique = repository.findById(id).get();
        repository.delete(henrique);

    }

    @Test
    void salvarAutorComLivroTest() {
        Autor autor = new Autor();
        autor.setNome("Paulo");
        autor.setNacionalidade("Japones");
        autor.setDataNascimento(LocalDate.of(1980, 5, 1));


        Livro livro = new Livro();
        livro.setIsdn("0987654321");
        livro.setPreco(BigDecimal.valueOf(150.00));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("PRA");
        livro.setDataPublicacao(LocalDate.of(2010, 9, 13));
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsdn("123678");
        livro2.setPreco(BigDecimal.valueOf(150.00));
        livro2.setGenero(GeneroLivro.FANTASIA);
        livro2.setTitulo("PRE");
        livro2.setDataPublicacao(LocalDate.of(2012, 9, 13));
        livro2.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);

        repository.save(autor);

        livroRepository.saveAll(autor.getLivros());

    }
}
