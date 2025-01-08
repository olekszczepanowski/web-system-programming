import { useEffect, useState } from "react";

const BASE_URL = "https://jsonplaceholder.typicode.com";

export default function FetchData() {
  const [posts, setPosts] = useState([]);
  useEffect(() => {
    const fetchPosts = async () => {
      const response = await fetch(`${BASE_URL}/posts`);
      const posts = await response.json();
      setPosts(posts);
    };
    fetchPosts();
  }, []);

  return (
    <div className="flex justify-center flex-col items-center p-2">
      <h1 className="mb-4 text-xl">Pobieranie postów z zewnętrznego API</h1>
      <div className="border-2 rounded-md border-black bg-slate-300">
        {posts.map((post) => {
          return (
            <div key={post.id} className="p-2 flex justify-center">
              <p>{post.title}</p>
            </div>
          );
        })}
      </div>
    </div>
  );
}
