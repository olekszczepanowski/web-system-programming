import CountListener from "./CountListener";
import FetchData from "./FetchData";

export default function App() {
  return (
    <>
      <h1 className="text-3xl font-bold underline text-center">Demo Reacta</h1>
      <FetchData />
      <CountListener />
    </>
  );
}
