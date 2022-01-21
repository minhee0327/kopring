package com.official.kopring.controller

import com.official.kopring.entity.Article
import com.official.kopring.entity.User
import com.official.kopring.format
import com.official.kopring.repository.ArticleRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HtmlController(private val repository: ArticleRepository){

    @GetMapping("/")
    fun blog(model: Model):String{
        model["title"] = "Blog"
        model["articles"] = repository.findAllByOrderByAddedAtDesc().map{ it.render()}

        return "blog"
    }
}

fun Article.render() = RenderedArticle(
    slug,
    title,
    headline,
    content,
    author,
    addedAt.format()
)

data class RenderedArticle(
    val slug: String,
    val title: String,
    val headline: String,
    val content : String,
    val author: User,
    val addedAt: String
)