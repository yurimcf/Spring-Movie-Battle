package br.ada.americanas.moviebattle.moviesort;

import br.ada.americanas.moviebattle.movie.Movie;
import org.springframework.data.util.Pair;

public interface MoviePairSort {

    Pair<Movie, Movie> sort(Iterable<Movie> movies);

}
