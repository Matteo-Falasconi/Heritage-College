import React, { useState, useEffect } from "react";
import List from "./List";
import Movie from "./Movie";
import ActorSelect from "./ActorSelect";
import YearSelect from "./YearSelect";
import MovieAdd from "./MovieAdd"
import GenreSelect from "./GenreSelect"

function App() {
  const [movies, setMovies] = useState([]);
  const [selectedMovieId, setSelectedMovieId] = useState(null);
  const [selectedActor, setSelectedActor] = useState(null);
  const [selectedYear, setSelectedYear] = useState(null);
  const [selectedGenre, setSelectedGenre] = useState(null);
  const [isAddingMovie, setIsAddingMovie] = useState(false);

  useEffect(() => {
    loadMovies();
  }, [selectedActor, selectedYear, selectedGenre])

  function loadMovies() {
    if (!selectedActor && !selectedYear && !selectedGenre) {
      fetch("/movies")
        .then((res) => res.json())
        .then((data) => {
          setMovies(data)
        });
    } else if (selectedActor && !selectedYear && !selectedGenre) {
      fetch(`/actors/${selectedActor}`)
        .then((res) => res.json())
        .then((data) => {
          setMovies(data)
        });
    } else if (selectedYear && !selectedActor && !selectedGenre) {
      fetch(`/years/${selectedYear}`)
        .then((res) => res.json())
        .then((data) => {
          setMovies(data)
        });
    } else if (selectedGenre && !selectedActor && !selectedYear) {
      fetch(`/genres/${selectedGenre}`)
        .then((res) => res.json())
        .then((data) => {
          setMovies(data)
        });
    }
  }

  function handleMovieClick(movie) {
    setSelectedMovieId(movie);
  };

  function handleActorSelect(actor) {
    setSelectedYear(null);
    setSelectedGenre(null);
    setSelectedActor(actor);
  };

  function handleYearSelect(year) {
    setSelectedActor(null);
    setSelectedGenre(null);
    setSelectedYear(year);
  }

  function handleGenreSelect(genre) {
    setSelectedActor(null);
    setSelectedYear(null);
    setSelectedGenre(genre);
  }

  function handleToggleAddMovie() {
    setIsAddingMovie(true);
  };

  function handleToggleMovieList() {
    setIsAddingMovie(false);
  }

  return (
    <div className="font-mono text-yellow-50">
      <h1 className="p-5 text-5xl font-bold text-center underline">Awesome Movie List</h1>
      <div className="flex justify-center">
        <button onClick={handleToggleMovieList} className="text-2xl optionButtons">Movie List</button>
        <button onClick={handleToggleAddMovie} className="text-2xl optionButtons">Add Movies</button>
      </div>
      {isAddingMovie && (
        <MovieAdd onMovieAdd={() => setIsAddingMovie(false)} />
      )}
      {!isAddingMovie && (
        <div className="m-4">
          <div className="grid grid-cols-1 gap-4 md:grid-cols-2 lg:grid-cols-3">
            <div >
              <ActorSelect onActorSelect={handleActorSelect} />
              <YearSelect onYearSelect={handleYearSelect} />
              <GenreSelect onGenreSelect={handleGenreSelect} />
            </div>
            <div>
              {selectedMovieId && <Movie movieId={selectedMovieId} />}
            </div>
          </div>
          {selectedActor || selectedYear || selectedGenre ?
            <h2 className="movieHeaders">Movie List for "{selectedActor || selectedYear || selectedGenre}"</h2> :
            <h2 className="movieHeaders">Movie List</h2>}
          <List movies={movies} onMovieClick={handleMovieClick} />
        </div>
      )}
    </div>
  );
};

export default App;