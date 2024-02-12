import React from "react";

function Genre({ genres }) {
  return (
    <div>
      <h3 className='mt-1'>Genres</h3>
      {Array.isArray(genres) && genres.length > 0 ? (
        <ul className="pl-4 list-disc">
          {genres.map((genre, index) => (
            <li key={index}>{genre}</li>
          ))}
        </ul>
      ) : (
        <p>No genres found.</p>
      )}
    </div>
  );
};

export default Genre;