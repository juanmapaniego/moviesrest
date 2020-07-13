package com.jmpaniego.movies;

import com.jmpaniego.movies.models.Gender;
import com.jmpaniego.movies.models.Movie;
import com.jmpaniego.movies.repositories.GenderRepository;
import com.jmpaniego.movies.repositories.MovieRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import java.io.FileReader;
import java.util.*;

@SpringBootApplication
public class MoviesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoviesApplication.class, args);
	}

	@Bean
	public CommandLineRunner init(GenderRepository genderRepository,
																MovieRepository movieRepository){
		return args -> {
			/* PARSEO JSON */
			FileReader reader = new FileReader(
					new ClassPathResource("movies_db.json").getFile()
			);
			JSONParser jsonParser = new JSONParser();
			Object obj = jsonParser.parse(reader);
			JSONObject jsonObject = (JSONObject) obj;

			/* LEER GENEROS Y GUARDARLOS */
			JSONArray generes = (JSONArray) jsonObject.get("genres");
			generes.forEach(g -> {
				Gender gender = new Gender(g.toString());
				genderRepository.save(gender);
			});

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
		};
	}
}
