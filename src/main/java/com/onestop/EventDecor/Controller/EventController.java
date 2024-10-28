package com.onestop.EventDecor.Controller;

import com.onestop.EventDecor.model.ItemList;
import com.onestop.EventDecor.repository.SearchRepository;
import com.onestop.EventDecor.repository.postRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class EventController {

    @Autowired
    private postRepository repo;

    @Autowired
    private SearchRepository srepo;

    @ApiIgnore
    @RequestMapping(value = "/")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }

    @GetMapping("/ItemList")
    @CrossOrigin
    public List<ItemList> getAllPosts() {
        return repo.findAll();
    }

    @GetMapping("/ItemList/{text}")
    public Optional<ItemList> search(@PathVariable String text) {
        return srepo.findById(text);
    }

    @PostMapping("/ItemList")
    @CrossOrigin
    public ItemList addList(@RequestBody ItemList list) {
        // Save the ItemList and return the saved entity
        return repo.save(list);
    }
}
