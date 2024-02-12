import React from 'react';

function Actor({ actors }) {
  return (
    <div>
      <h3 className='mt-1'>Actors</h3>
      {Array.isArray(actors) && actors.length > 0 ? (
        <ul className="pl-4 list-disc">
          {actors.map((actor, index) => (
            <li key={index}>{actor}</li>
          ))}
        </ul>
      ) : (
        <p>No actors found.</p>
      )}
    </div>
  );
};

export default Actor;