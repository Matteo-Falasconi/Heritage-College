import React from "react";
import '../styles/index.css';

function List({ movies, onMovieClick }) {
  const scrollToTop = () => {
    window.scrollTo({
      top: 0,
      behavior: "smooth",
    });
  };

  return (
    <div className="grid grid-cols-1 gap-4 text-2xl md:grid-cols-2 lg:grid-cols-3">
      {movies.length > 0 ? (
        movies.map((movie) => (
          <div key={movie.id} className="p-4 border rounded-md bg-slate-600 hover:scale-100 active:scale-95">
            <button onClick={() => { onMovieClick(movie.id); scrollToTop(); }} className="w-full h-full text-center">
              <p className="font-bold">{movie.title}</p>
              {movie.year && <p>Year: {movie.year}</p>}
            </button>
          </div>
        ))
      ) : (
        <div className="col-span-3 mt-10 text-center">No movies found... 😔</div>
      )}
    </div>
  );
};

export default List;