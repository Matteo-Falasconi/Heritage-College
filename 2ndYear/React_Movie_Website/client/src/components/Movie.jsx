import React, { useState, useEffect } from 'react';
import Actor from './Actor';
import Genre from './Genre';

function MovieComponent({ movieId }) {
  const [movie, setMovie] = useState(null);

  useEffect(() => {
    const fetchMovies = async () => {
      setMovie(
        await fetch(`/movies/${movieId}`)
          .then((res) => res.json())
          .then((data) => data)
      );
    };
    fetchMovies();
  }, [movieId]);

  return (
    <div className='flex justify-center'>
      <div className="inline-block p-10 min-w-[450px] rounded-lg bg-slate-700">
        <h2 className="text-3xl">{`${movie?.Title}`}</h2>
        <p>{`Year: ${movie?.Year}`}</p>
        <p>{`Runtime: ${movie?.Runtime} minutes`}</p>
        <p>{`Revenue: $${movie?.Revenue}M`}</p>
        {movie?.Actors && <Actor actors={movie?.Actors} />}
        {movie?.Genre && <Genre genres={movie?.Genre} />}
      </div>
    </div>
  );
};

export default MovieComponent;