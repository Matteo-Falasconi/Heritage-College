const express = require("express");
const app = express();
const fs = require("fs");
const path = require("path");
const port = 8888;

app.use(express.static(path.join(__dirname, "client/public")));
app.use(express.json())
const moviesJSON = path.join(__dirname, "data/movies.json");

app.get("/movies", (req, res) => {
    fs.readFile(moviesJSON, (err, data) => {
        if (err) throw err;
        else {
            const values = JSON.parse(data);
            const allMovies = values.map(({ Key, Title, Year }) => ({ id: Key, title: Title, year: Year, }))
                .sort((a, b) => a.title.localeCompare(b.title));
            res.json(allMovies);
        }
    });
});

app.get("/movies/:id", (req, res) => {
    const id = parseInt(req.params.id);
    fs.readFile(moviesJSON, (err, data) => {
        if (err) throw err;
        else {
            const values = JSON.parse(data);
            const movieWithId = values.find((movie) => movie.Key === id);
            res.json(movieWithId)
        }
    })
})

app.get("/actors/:name", (req, res) => {
    const actorName = req.params.name.toLowerCase().trim();
    fs.readFile(moviesJSON, (err, data) => {
        if (err) throw err;
        const values = JSON.parse(data);
        const moviesWithActor = values.filter((movie) =>
            movie.Actors.some((actor) => actor.toLowerCase().includes(actorName))
        );
        const actorsMovies = moviesWithActor.map(({ Key, Title, Year }) => ({
            id: Key,
            title: Title,
            year: Year,
        }))
            .sort((a, b) => a.title.localeCompare(b.title));
        res.json(actorsMovies);
    })
});

app.get("/years/:year", (req, res) => {
    const searchYear = parseInt(req.params.year)
    fs.readFile(moviesJSON, (err, data) => {
        if (err) throw err;
        const movies = JSON.parse(data);
        const moviesInYear = movies.filter((movie) => movie.Year === searchYear)
            .map(({ Key, Title }) => ({ id: Key, title: Title, }))
            .sort((a, b) => a.title.localeCompare(b.title));
        res.json(moviesInYear)
    })
})

app.get("/genres/:genre", (req, res) => {
    const searchGenre = req.params.genre.toLowerCase().trim();
    fs.readFile(moviesJSON, (err, data) => {
        if (err) throw err;
        const movies = JSON.parse(data);
        const moviesWithGenre = movies.filter((movie) =>
            movie.Genre.map(g => g.toLowerCase()).includes(searchGenre))
            .map(({ Key, Title, Year }) => ({ id: Key, title: Title, year: Year }))
            .sort((a, b) => a.title.localeCompare(b.title));
        res.json(moviesWithGenre);
    });
});

app.post("/movies", (req, res) => {
    fs.readFile(moviesJSON, (err, data) => {
        if (err) throw err;
        let movies = JSON.parse(data);
        const newKey = Math.max(...movies.map((movie) => movie.Key), 0) + 1;
        const { Title, Genre, Actors, Year, Runtime, Revenue } = req.body;
        const newMovie = {
            Key: newKey,
            Title,
            Genre,
            Actors,
            Year,
            Runtime,
            Revenue,
        }
        movies.push(newMovie);
        fs.writeFile(moviesJSON, JSON.stringify(movies, null, 2), (err) => {
            if (err) {
                res.status(500).send("failure");
            } else {
                res.status(200).send("success");
            }
        });
    })
});

app.listen(port, () => {
    console.log(`Server is running on port ${port}`);
});