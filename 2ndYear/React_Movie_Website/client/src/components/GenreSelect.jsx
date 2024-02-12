import React, { useState } from "react";

function GenreSelect({ onGenreSelect }) {
  const [genre, setGenre] = useState("");

  function handleGenreInput(event) {
    setGenre(event.target.value);
  };

  function handleGenreSearch() {
    onGenreSelect(genre);
  };

  function handleClear() {
    setGenre("")
    onGenreSelect(null);
  }

  return (
    <div>
      <h2 className="text-2xl">Genre Select</h2>
      <input
        type="text"
        value={genre}
        onChange={handleGenreInput}
        className="inputDisplay"
      />
      <button onClick={handleGenreSearch} className="optionButtons">Search</button>
      <button onClick={handleClear} className="m-0 optionButtons">Clear</button>
    </div>
  );
};

export default GenreSelect;