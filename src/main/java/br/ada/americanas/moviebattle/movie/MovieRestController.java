package br.ada.americanas.moviebattle.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movies")
public class MovieRestController {

    private MovieService service;

    @Autowired
    public MovieRestController(MovieService service) {
        this.service = service;
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public Movie create(@RequestBody Movie movie) {
        return this.service.add(movie);
    }

    @PutMapping(
            value = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public Movie update(
            @PathVariable("id") Long id,
            @RequestBody Movie movie
    ) {
        movie.setId(id);
        return this.service.update(movie);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public Iterable<Movie> list() {
        return service.list();
    }

    @GetMapping(
            value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public Movie get(@PathVariable("id") Long id) {
        return this.service.findById(id).get();
    }

    @DeleteMapping(
            value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public Movie delete(@PathVariable("id") Long id) {
        return service.delete(id).get();
    }

}
