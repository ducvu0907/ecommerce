import { useState } from "react";
import { Input } from "../ui/input";
import { Button } from "../ui/button";
import { FaSearch } from 'react-icons/fa';

const Searchbar = () => {
  const [query, setQuery] = useState<string>("");

  // FIXME: placeholder
  const handleSearch = () => {
    console.log("you pressed search");
  };

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setQuery(e.target.value);
  };

  return (
    <div className="w-full flex items-center space-x-4 bg-white rounded-md shadow-lg px-4 py-2">
      <Input
        type="text"
        value={query}
        onChange={handleChange}
        placeholder="Search..."
        className="w-full p-2 rounded-md border-none border-gray-300 focus:ring-0 focus:ring-blue-500"
      />
      <Button
        onClick={handleSearch}
        className="flex items-center justify-center bg-blue-500 text-white hover:bg-blue-700 py-5 px-7 rounded-sm focus:outline-none focus:ring-2"
      >
        <FaSearch size={26} />
      </Button>
    </div>
  );
};

export default Searchbar;
