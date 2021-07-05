package com.jmpaniego.movies.bootstrap;

import com.jmpaniego.movies.models.Gender;
import com.jmpaniego.movies.models.Movie;
import com.jmpaniego.movies.repositories.GenderRepository;
import com.jmpaniego.movies.repositories.MovieRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Component
public class Loader implements CommandLineRunner {
    private final GenderRepository genderRepository;
    private final MovieRepository movieRepository;

    public Loader(GenderRepository genderRepository, MovieRepository movieRepository) {
        this.genderRepository = genderRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData() throws IOException, ParseException {

            /* PARSEO JSON */
            FileReader reader = new FileReader(
                    new ClassPathResource("movies_db.json").getFile()
            );
            JSONParser jsonParser = new JSONParser();
            Object obj = jsonParser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
        if(genderRepository.count() == 0) {
            /* LEER GENEROS Y GUARDARLOS */
            JSONArray generes = (JSONArray) jsonObject.get("genres");
            generes.forEach(g -> {
                Gender gender = new Gender(g.toString());
                genderRepository.save(gender);
            });
        }

        if(movieRepository.count() == 0){

            /* LEER PELICULAS */
            JSONArray movies = (JSONArray) jsonObject.get("movies");
            Random rand = new Random();
            int upperBound = 5;
            movies.forEach( m -> {
                JSONObject movie = (JSONObject) m;
                Movie movie_db = new Movie();
                movie_db.setActors(movie.get("actors").toString());
                movie_db.setDirector(movie.get("director").toString());
                movie_db.setPlot(movie.get("plot").toString());
                movie_db.setPosterUrl(movie.get("posterUrl").toString());
                movie_db.setRuntime(
                        Integer.parseInt(movie.get("runtime").toString())
                );
                movie_db.setYear(
                        Integer.parseInt(movie.get("year").toString())
                );
                movie_db.setTitle(movie.get("title").toString());
                movie_db.setRate(
                        rand.nextDouble()*upperBound
                );

                Set<Gender> genres = new HashSet<>();
                ((JSONArray) movie.get("genres"))
                        .stream()
                        .map(g -> genderRepository.findByName(g.toString()).orElseThrow(() -> new IllegalArgumentException("Problem at init")))
                        .forEach(gM -> genres.add((Gender) gM));
                movie_db.setGenders(genres);

                movieRepository.save(movie_db);
            });
        }
    }
}
