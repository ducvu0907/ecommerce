import { getMyCartQuery } from '@/services/cart';
import { CartData } from '@/types/models';
import React, { createContext, useState, ReactNode, useEffect, useContext } from 'react';
import { AuthContext } from './AuthContext';

interface CartContextType {
  cart: CartData | null,
  isLoading: boolean;
};

export const CartContext = createContext<CartContextType>({
  cart: null,
  isLoading: false,
});

interface CartProviderProps {
  children: ReactNode;
};

export const CartProvider: React.FC<CartProviderProps> = ({ children }) => {
  const [cart, setCart] = useState<CartData | null>(null);
  const { token } = useContext(AuthContext);
  const { data, isLoading } = getMyCartQuery();

  useEffect(() => {
    if (data?.result) {
      console.log("Fetching user cart: ", data);
      setCart(data.result);
    }
  }, [data, token]);

  useEffect(() => {
    if (!token) {
      setCart(null);
    }
  }, [token]);

  return (
    <CartContext.Provider value={{cart, isLoading}}>
      {children}
    </CartContext.Provider>
  );
};