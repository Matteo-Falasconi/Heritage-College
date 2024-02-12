import React, { useState } from "react";

function YearSelect({ onYearSelect }) {
  const [year, setYear] = useState("");

  function handleYearInput(event) {
    setYear(event.target.value);
  };

  function handleYearSearch() {
    onYearSelect(year);
  };

  function handleClear() {
    setYear("")
    onYearSelect(null);
  }

  return (
    <div >
      <h2 className="text-2xl">Year Select</h2>
      <input
        type="text"
        value={year}
        onChange={handleYearInput}
        className=" inputDisplay" />
      <button onClick={handleYearSearch} className="optionButtons">Search</button>
      <button onClick={handleClear} className="m-0 optionButtons">Clear</button>
    </div>
  );
};

export default YearSelect;