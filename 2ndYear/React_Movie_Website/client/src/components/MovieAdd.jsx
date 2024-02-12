import React, { useState } from "react";
import { Toaster, toast } from "sonner";

function MovieAdd({ onMovieAdd }) {
  const [title, setTitle] = useState("");
  const [year, setYear] = useState("");
  const [actors, setActors] = useState("");
  const [genres, setGenres] = useState("");
  const [revenue, setRevenue] = useState("");
  const [runtime, setRuntime] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  function handleTitleChange(event) {
    setTitle(event.target.value);
  }

  function handleYearChange(event) {
    setYear(event.target.value);
  }

  function handleActorsChange(event) {
    setActors(event.target.value)
  }

  function handleGenresChange(event) {
    setGenres(event.target.value);
  }

  function handleRuntimeChange(event) {
    setRuntime(event.target.value)
  }

  function handleRevenueChange(event) {
    setRevenue(event.target.value)
  }

  async function handleSubmit() {
    setErrorMessage("");
    if (!title || !year || !actors || !genres || !runtime || !revenue) {
      let error = "Please fill out ";
      switch (true) {
        case !title:
          error += "- title ";
        case !year || year < 0:
          error += "- year ";
        case !actors:
          error += "- actor ";
        case !genres:
          error += "- genre ";
        case !runtime || runtime < 0:
          error += "- runtime ";
        case !revenue || revenue < 0:
          error += "- revenue ";
        default:
          error += "- before submitting"
      }
      setErrorMessage(error)
      return;
    }

    const movieData = {
      Title: title,
      Year: +year,
      Actors: actors.split(",").map((actor) => actor.trim()),
      Genre: genres.split(",").map((genre) => genre.trim()),
      Runtime: +runtime,
      Revenue: +revenue,
    };

    const response = await fetch("/movies", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(movieData),
    });
    if (response.ok) {
      toast.success("Successfully added movie 😝");
    } else {
      toast.error("Could not add the movie 😭");
    }
  }

  return (
    <div>
      <Toaster position="top-right" richColors />
      <h2 className=" movieHeaders">Add a New Movie</h2>
      <div className="p-4 font-bold text-center text-red-600">{errorMessage}</div>
      <div className="flex justify-center text-3xl">
        <form>
          <label className="text-2xl">Title:</label>
          <br />
          <input type="text" value={title} onChange={handleTitleChange} className="inputDisplay" required />
          <br />
          <label className="text-2xl">Year:</label>
          <br />
          <input type="number" value={year} onChange={handleYearChange} className="inputDisplay" required />
          <br />
          <label className="text-2xl">Actors (Seperate by commas):</label>
          <br />
          <input type="text" value={actors} onChange={handleActorsChange} className="inputDisplay" required />
          <br />
          <label className="text-2xl">Genres (Seperate by commas):</label>
          <br />
          <input type="text" value={genres} onChange={handleGenresChange} className="inputDisplay" required />
          <br />
          <label className="text-2xl">Runtime:</label>
          <br />
          <input type="number" value={runtime} onChange={handleRuntimeChange} className="inputDisplay" required />
          <br />
          <label className="text-2xl">Revenue:</label>
          <br />
          <input type="number" value={revenue} onChange={handleRevenueChange} className="inputDisplay" required />
          <br />
          <button type="button" onClick={handleSubmit} className="w-full mx-0 bg-slate-500 optionButtons">Add Movie</button>
        </form>
      </div>
    </div>
  );
}

export default MovieAdd;