package com.example.repoapp.controller;
import com.example.repoapp.model.RepositoryInfo;
import com.example.repoapp.service.GithubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class GitHubController {

    private final GithubService githubService;

    @Autowired
    public GitHubController(GithubService githubService) {
        this.githubService = githubService;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/repositories";
    }

    @GetMapping("/repositories")
    public String listRepositories(@AuthenticationPrincipal OAuth2User principal, Model model) {
        String username = principal.getAttribute("login");

        List<RepositoryInfo> repositories = githubService.getUserRepositories(username);

        model.addAttribute("repositories", repositories);

        // Nazwa widoku Thymeleaf
        return "repositories";
    }
}
