import React, { useState } from "react";

function ActorSelect({ onActorSelect }) {
  const [name, setName] = useState("");

  function handleActorInput(event) {
    setName(event.target.value);
  };

  function handleActorSearch() {
    onActorSelect(name);
  };

  function handleClear() {
    setName("")
    onActorSelect(null);
  }

  return (
    <div>
      <h2 className="text-2xl">Actor Select</h2>
      <input
        type="text"
        value={name}
        onChange={handleActorInput}
        className="inputDisplay"
      />
      <button onClick={handleActorSearch} className="optionButtons">Search</button>
      <button onClick={handleClear} className="m-0 optionButtons">Clear</button>
    </div>
  );
};

export default ActorSelect;