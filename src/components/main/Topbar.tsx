import { FaShoppingCart } from "react-icons/fa";
import Searchbar from "./Searchbar";
import UserMenu from "./UserMenu";

const Topbar = () => {
  return (
    <div className="w-full h-[100px] flex items-center justify-around bg-gray-700 px-8 py-4">
      <UserMenu />
      <div className="w-3/4 flex items-center space-x-6">
        <Searchbar />
        <FaShoppingCart color={"white"} size={"28px"} className="cursor-pointer hover:text-gray-400" />
      </div>
    </div>
  );
};

export default Topbar;
