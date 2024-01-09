package com.example.repoapp.service;

import com.example.repoapp.model.RepositoryInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GithubService {

    private final RestTemplate restTemplate;

    @Autowired
    public GithubService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<RepositoryInfo> getUserRepositories(String username) {
        String url = "https://api.github.com/users/" + username + "/repos";

        // Wykonywanie zapytania i odbiór odpowiedzi
        ResponseEntity<List<Map<String, Object>>> response = restTemplate.exchange(
                url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Map<String, Object>>>() {});

        List<Map<String, Object>> repositories = response.getBody();

        // Konwersja odpowiedzi na listę obiektów RepositoryInfo
        List<RepositoryInfo> repoInfos = new ArrayList<>();
        if (repositories != null) {
            for (Map<String, Object> repo : repositories) {
                String repoName = (String) repo.get("name");
                int stars = (Integer) repo.get("stargazers_count");
                repoInfos.add(new RepositoryInfo(repoName, stars));
            }
        }

        return repoInfos;
    }
}
