import { useState } from "react";

export default function CountListener() {
  const [clicks, setClicks] = useState(0);
  const handleClick = () => {
    setClicks(clicks + 1);
  };

  return (
    <div className="flex justify-center flex-col items-center p-2">
      <h1 className="mb-4 text-xl">Zliczanie kliknięć w przycisk</h1>
      <button
        onClick={handleClick}
        className="bg-blue-500 hover:bg-blue-700 rounded py-2 px-4 text-white font-bold"
      >
        Zostałem kliknęty {clicks} razy.
      </button>
    </div>
  );
}
