import { useState, useEffect, useRef } from "react";
import { FaUserCircle } from "react-icons/fa";
import { Button } from "../ui/button";
import useAuth, { isLoggedIn } from "@/hooks/useAuth";
import { Link } from "react-router-dom";

const UserMenu = () => {
  const loggedIn = isLoggedIn();
  const [showDropdown, setShowDropdown] = useState<boolean>(false);
  const dropdownRef = useRef<HTMLDivElement>(null);
  const { logout } = useAuth();

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
    <div className="flex items-center space-x-6">
      {!loggedIn ? (
        <div>
          <Button variant="link" type="button" className="text-white font-normal">
            <Link to={"/login"}>Log in</Link>
          </Button>
          <span className="border border-white"></span>
          <Button variant="link" type="button" className="text-white font-normal">
            <Link to={"/signup"}>Sign up</Link>
          </Button>
        </div>
      ) : (
          <div className="relative">
            <FaUserCircle
              onClick={() => setShowDropdown((prev) => !prev)}
              size={28}
              className="text-white cursor-pointer hover:text-gray-400"
            />
            {showDropdown && (
              <div
                ref={dropdownRef}
                className="absolute mt-2 w-32 bg-white text-gray-800 rounded-md shadow-lg z-50"
              >
                <ul className="space-y-2 p-3">
                  <li className="hover:bg-gray-100 cursor-pointer">Profile</li>
                  <li className="hover:bg-gray-100 cursor-pointer">Settings</li>
                  <li onClick={logout} className="hover:bg-gray-100 cursor-pointer">Log out</li>
                </ul>
              </div>
            )}
          </div>
      )}
    </div>
  );
};

export default UserMenu;
