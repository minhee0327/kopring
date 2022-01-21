package com.official.kopring

import com.official.kopring.entity.Article
import com.official.kopring.entity.User
import com.official.kopring.repository.ArticleRepository
import com.official.kopring.repository.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest
class RepositoriesTest @Autowired constructor(
    val entityManager: TestEntityManager,
    val userRepository: UserRepository,
    val articleRepository: ArticleRepository
){
    @Test
    fun `When findByIdOrNull then return Article`(){
        val mini = User("minimi", "mini", "kang")
        entityManager.persist(mini)
        val article = Article("Spring Framework 5.0", "Dear Spring community", "Lorem ipsum", mini)
        entityManager.persist(article)
        entityManager.flush()
        val found = articleRepository.findByIdOrNull(article.id!!)
        assertThat(found).isEqualTo(article)
    }

    @Test
    fun `When findByLogin then return User`(){
        val mini = User("minimi", "mini", "kang")
        entityManager.persist(mini)
        entityManager.flush()
        val user = userRepository.findByLogin(mini.login)
        assertThat(user).isEqualTo(mini)
    }
}