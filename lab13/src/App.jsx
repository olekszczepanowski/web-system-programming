import { useState } from "react";

const saveToFile = (data) => {
  const blob = new Blob([JSON.stringify(data, null, 2)], {
    type: "application/json",
  });
  const link = document.createElement("a");
  link.href = URL.createObjectURL(blob);
  link.download = "plan_dnia.json";
  link.click();
};

const App = () => {
  const [subjects, setSubjects] = useState([
    { name: "Systemy operacyjne", duration: "2h" },
    { name: "Programowanie w JavaScript", duration: "1.5h" },
    { name: "Programowanie w Pythonie", duration: "3h" },
    { name: "Sztuczna inteligencja", duration: "2h" },
    { name: "Algebra i geometria analityczna", duration: "1h" },
  ]);
  const [newSubject, setNewSubject] = useState({ name: "", duration: "" });

  const addSubject = () => {
    if (newSubject.name && newSubject.duration) {
      setSubjects([...subjects, newSubject]);
      setNewSubject({ name: "", duration: "" });
    }
  };

  const removeSubject = (index) => {
    setSubjects(subjects.filter((_, i) => i !== index));
  };

  return (
    <div className="p-6 border-2 bg-slate-100 w-1/2 mx-auto mt-2">
      <h2 className="text-xl font-semibold mb-4">Plan dnia</h2>
      <ul className="list-none p-0">
        {subjects.map((subject, index) => (
          <li
            key={index}
            className="mb-4 p-4 border border-gray-300 rounded-lg flex justify-between items-center"
          >
            <div>
              <strong>{subject.name}</strong> ({subject.duration})
            </div>
            <button
              onClick={() => removeSubject(index)}
              className="bg-red-500 text-white px-2 py-1 rounded"
            >
              Usuń
            </button>
          </li>
        ))}
      </ul>

      <div className="mt-4 flex items-center justify-center">
        <input
          type="text"
          placeholder="Nazwa przedmiotu"
          value={newSubject.name}
          onChange={(e) =>
            setNewSubject({ ...newSubject, name: e.target.value })
          }
          className="border-2 border-gray-300 rounded px-4 py-2 mr-2 w-60"
        />
        <input
          type="text"
          placeholder="Czas trwania"
          value={newSubject.duration}
          onChange={(e) =>
            setNewSubject({ ...newSubject, duration: e.target.value })
          }
          className="border-2 border-gray-300 rounded px-4 py-2 mr-2 w-32"
        />
        <button
          onClick={addSubject}
          className="bg-green-500 text-white px-4 py-2 rounded"
        >
          Dodaj przedmiot
        </button>
      </div>

      <div className="flex justify-center">
        <button
          onClick={() => saveToFile(subjects)}
          className="mt-6 px-4 py-2 bg-blue-500 text-white rounded "
        >
          Zapisz plan dnia
        </button>
      </div>
    </div>
  );
};

export default App;
