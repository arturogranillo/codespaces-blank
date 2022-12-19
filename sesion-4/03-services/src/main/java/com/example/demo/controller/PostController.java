package com.example.demo.controller;

import java.util.List;

import com.example.demo.model.Post;
import com.example.demo.service.IPostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
public class PostController {

    private IPostService postService;

    public PostController(IPostService postService){
        this.postService = postService;
    }

    @GetMapping("")
    public List<Post> getAll(){
        return this.postService.getAll();
    }
}