package com.nelioalves.workshopmongo.resource;

import com.nelioalves.workshopmongo.domain.Post;
import com.nelioalves.workshopmongo.resource.util.URL;
import com.nelioalves.workshopmongo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostResource {

    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<List<Post>> findAll() {
        List<Post> list = postService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id) {
        Post obj = postService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value="/titlesearch", method=RequestMethod.GET)
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text", defaultValue="") String text){
        text = URL.decodeParam(text);
        List<Post> list = postService.findByTitle(text);
        return ResponseEntity.ok().body(list);
    }

}
