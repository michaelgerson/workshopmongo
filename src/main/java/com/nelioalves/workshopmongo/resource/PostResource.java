package com.nelioalves.workshopmongo.resource;

import com.nelioalves.workshopmongo.domain.Post;
import com.nelioalves.workshopmongo.resource.util.URL;
import com.nelioalves.workshopmongo.services.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/posts")
@Slf4j
public class PostResource {

    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<List<Post>> findAll() {
        log.info("Listando todos os posts");
        List<Post> list = postService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id) {
        log.info("Buscando post pelo id: {}", id);
        Post obj = postService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value="/titlesearch", method=RequestMethod.GET)
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text", defaultValue="") String text){
        text = URL.decodeParam(text);
        List<Post> list = postService.findByTitle(text);
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(value="/fullsearch", method=RequestMethod.GET)
    public ResponseEntity<List<Post>> fullSearch(
            @RequestParam(value="text", defaultValue="") String text,
            @RequestParam(value="minDate", defaultValue="") String minDate,
            @RequestParam(value="maxDate", defaultValue="") String maxDate) {
        text = URL.decodeParam(text);
        Date min = URL.convertDate(minDate, new Date(0L));
        Date max = URL.convertDate(maxDate, new Date());
        List<Post> list = postService.fullSearch(text, min, max);
        return ResponseEntity.ok().body(list);
    }

}
