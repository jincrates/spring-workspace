package me.jincrates.blog.web;

import lombok.RequiredArgsConstructor;
import me.jincrates.blog.config.auth.LoginUser;
import me.jincrates.blog.config.auth.dto.SessionUser;
import me.jincrates.blog.service.posts.PostsService;
import me.jincrates.blog.web.dto.posts.PostsResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(Model model, @LoginUser SessionUser user) {
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "posts/posts-edit";
    }

    @GetMapping("/posts/view/{id}")
    public String postsView(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts/posts-view";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts/posts-edit";
    }

    @GetMapping("/about")
    public String about(@LoginUser SessionUser user) {
        return "about";
    }

    @GetMapping("/contact")
    public String contact(@LoginUser SessionUser user) {
        return "contact";
    }

    @GetMapping("/auth/signup")
    public String signup() {
        return "users/signup";
    }

    @GetMapping("/auth/signin")
    public String signin() {
        return "users/signin";
    }
}
