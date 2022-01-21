package com.official.kopring.repository

import com.official.kopring.entity.Article
import com.official.kopring.entity.User
import org.springframework.data.repository.CrudRepository

interface ArticleRepository: CrudRepository<Article, Long>{
    fun findBySlug(slug: String): Article?
    fun findAllByOrderByAddedAtDesc(): Iterable<Article>
}

interface UserRepository: CrudRepository<User, Long>{
    fun findByLogin(login: String): User?
}