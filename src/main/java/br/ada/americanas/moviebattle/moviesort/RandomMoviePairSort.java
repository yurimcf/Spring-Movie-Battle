package br.ada.americanas.moviebattle.moviesort;

import br.ada.americanas.moviebattle.movie.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class RandomMoviePairSort implements MoviePairSort {

    private Random random;

    @Autowired
    public RandomMoviePairSort() {
        this.random = new Random();
    }

    @Override
    public Pair<Movie, Movie> sort(Iterable<Movie> movies) {
        List<Movie> list = Streamable.of(movies).toList();
        Movie left = list.get(random(list.size()));
        Movie right = list.get(random(list.size()));
        return Pair.of(left, right);
    }

    private Integer random(int bound) {
        return random.nextInt(0, bound);
    }
}
