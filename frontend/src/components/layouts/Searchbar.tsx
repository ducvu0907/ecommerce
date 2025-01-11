import { useEffect, useState } from "react";
import { Input } from "../ui/input";
import { Button } from "../ui/button";
import { Search } from "lucide-react";
import { useLocation, useNavigate } from "react-router-dom";

const Searchbar = () => {
  const location = useLocation();
  const [query, setQuery] = useState<string>("");
  const navigate = useNavigate();

  useEffect(() => {
    // if back to home then remove query
    if (location.pathname === "/") {
      setQuery("");
    }
  }, [location]);

  const handleSearch = () => {
    if (!query.trim()) {
      return;
    }
    navigate("/products/search", { state: query });
  };

  return (
    <div className="w-full flex items-center bg-gradient-to-r from-[#FF6B6B] to-[#FCA311] rounded-md p-2 shadow-lg">
      <Input
        type="text"
        value={query}
        onChange={(e) => setQuery(e.target.value)}
        placeholder="Discover..."
        className="w-full bg-white/80 border-0 rounded-md py-4 text-lg placeholder-orange-800 focus:outline-none focus:ring-2 focus:ring-orange-500"
      />
      <Button
        onClick={handleSearch}
        className="ml-2 w-1/12 bg-[#4ECDC4] text-white rounded-md p-3 hover:bg-[#45B7AA] transition-colors duration-300 ease-in-out"
      >
        <Search className="w-6 h-6" />
      </Button>
    </div>
  );
};

export default Searchbar;