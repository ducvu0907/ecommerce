import { ShoppingCart, Truck, Package, Home } from "lucide-react";
import Searchbar from "./Searchbar";
import UserMenu from "./UserMenu";
import { useContext } from "react";
import { CartContext } from "@/contexts/CartContext";
import { useNavigate } from "react-router-dom";

import { Button } from "@/components/ui/button";
import { Tooltip, TooltipContent, TooltipProvider, TooltipTrigger } from "@/components/ui/tooltip";
import { Badge } from "@/components/ui/badge";

const Topbar = () => {
  const { cart } = useContext(CartContext);
  const navigate = useNavigate();

  return (
    <div className="w-full h-[80px] flex items-center justify-center px-12 bg-gray-500 relative">
      <div className="max-w-6xl w-full flex items-center justify-between">
        <div className="flex items-center space-x-2">
          <TooltipProvider>
            <Tooltip>
              <TooltipTrigger asChild>
                <div className="relative">
                  <Button 
                    variant="ghost" 
                    size="icon" 
                    onClick={() => navigate("/")}
                    className="text-white hover:text-[#4ECDC4] transition-colors duration-300"
                  >
                    <Home size={24} />
                  </Button>
                </div>
              </TooltipTrigger>
              <TooltipContent>Home</TooltipContent>
            </Tooltip>
          </TooltipProvider>

          <TooltipProvider>
            <Tooltip>
              <TooltipTrigger asChild>
                <div className="relative">
                  <Badge 
                    variant="destructive" 
                    className="absolute -top-2 -right-2 z-10 w-2 flex items-center justify-center"
                  >
                    {(cart && cart.items.length) || 0}
                  </Badge>
                  <Button 
                    variant="ghost" 
                    size="icon" 
                    onClick={() => navigate("/cart")}
                    className="text-white hover:text-[#4ECDC4] transition-colors duration-300"
                  >
                    <ShoppingCart size={24} />
                  </Button>
                </div>
              </TooltipTrigger>
              <TooltipContent>Cart</TooltipContent>
            </Tooltip>
          </TooltipProvider>

          <TooltipProvider>
            <Tooltip>
              <TooltipTrigger asChild>
                <Button 
                  variant="ghost" 
                  size="icon" 
                  onClick={() => navigate("/shipping")}
                  className="text-white hover:text-[#4ECDC4] transition-colors duration-300"
                >
                  <Truck size={24} />
                </Button>
              </TooltipTrigger>
              <TooltipContent>Shipping</TooltipContent>
            </Tooltip>
          </TooltipProvider>

          <TooltipProvider>
            <Tooltip>
              <TooltipTrigger asChild>
                <Button 
                  variant="ghost" 
                  size="icon" 
                  onClick={() => navigate("/orders")}
                  className="text-white hover:text-[#4ECDC4] transition-colors duration-300"
                >
                  <Package size={24} />
                </Button>
              </TooltipTrigger>
              <TooltipContent>Orders</TooltipContent>
            </Tooltip>
          </TooltipProvider>
        </div>

        <div className="w-2/3 px-4">
          <Searchbar />
        </div>

        <div>
          <UserMenu />
        </div>
      </div>
    </div>
  );
};

export default Topbar;