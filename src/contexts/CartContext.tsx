import { getMyCart } from '@/services/cart';
import { ApiResponse, CartData } from '@/types/models';
import React, { createContext, useState, ReactNode, useEffect, useContext } from 'react';
import { AuthContext } from './AuthContext';

interface CartContextType {
  cart: CartData | null,
  setCart: (cart: CartData | null) => void;
  isLoading: boolean;
};

const CartContext = createContext<CartContextType>({
  cart: null,
  setCart: () => {},
  isLoading: false,
});

interface CartProviderProps {
  children: ReactNode;
};

const CartProvider: React.FC<CartProviderProps> = ({ children }) => {
  const {token} = useContext(AuthContext);
  const [cart, setCart] = useState<CartData | null>(null);
  const {mutate: fetchCart, isPending: isLoading} = getMyCart();

  useEffect(() => {
    if (token) {
      fetchCart(token, {
        onSuccess: (data: ApiResponse<CartData>) => {
          if (data.result) {
            console.log("Fetch user cart: ", data);
            setCart(data.result);
          } else {
            throw new Error(data.message);
          }
        },
      });
    }
  }, [token]);

  return (
    <CartContext.Provider value={{cart, setCart, isLoading}}>
    {children}
    </CartContext.Provider>
  );
};

export {CartContext, CartProvider};