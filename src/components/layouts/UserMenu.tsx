import { useState, useEffect, useRef } from "react";
import { FaUserCircle } from "react-icons/fa";
import useAuth, { isLoggedIn } from "@/hooks/useAuth";
import { Link, useNavigate } from "react-router-dom";

const UserMenu = () => {
  const loggedIn = isLoggedIn();
  const [showDropdown, setShowDropdown] = useState<boolean>(false);
  const dropdownRef = useRef<HTMLDivElement>(null);
  const { logout } = useAuth();
  const navigate = useNavigate();

  const handleClickOutside = (event: MouseEvent) => {
    if (dropdownRef.current && !dropdownRef.current.contains(event.target as Node)) {
      setShowDropdown(false);
    }
  };

  useEffect(() => {
    document.addEventListener("mousedown", handleClickOutside);
    return () => {
      document.removeEventListener("mousedown", handleClickOutside);
    };
  }, []);

  return (
    <div className="flex items-center space-x-4">
      {!loggedIn ? (
        <div className="flex items-center space-x-2">
          <Link to="/login" className="text-white text-md hover:underline">Log in</Link>
          <span className="border-r border-white h-4"></span>
          <Link to="/signup" className="text-white text-md hover:underline">Sign up</Link>
        </div>
      ) : (
        <div className="relative">
          <FaUserCircle
            onClick={() => setShowDropdown(prev => !prev)}
            className="text-white cursor-pointer hover:text-gray-400"
            size={28}
          />
          {showDropdown && (
            <div 
              ref={dropdownRef} 
              className="absolute mt-2 w-32 bg-white rounded-md shadow-lg z-50"
            >
              <ul className="py-2">
                <li onClick={() => navigate("/profile")} className="px-4 py-2 hover:bg-gray-100 cursor-pointer">Profile</li>
                <li onClick={() => navigate("/settings")} className="px-4 py-2 hover:bg-gray-100 cursor-pointer">Settings</li>
                <li onClick={logout} className="px-4 py-2 hover:bg-gray-100 cursor-pointer">
                  Log out
                </li>
              </ul>
            </div>
          )}
        </div>
      )}
    </div>
  );
};

export default UserMenu;
