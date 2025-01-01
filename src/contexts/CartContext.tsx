import { getMyCartQuery } from '@/services/cart';
import { CartData } from '@/types/models';
import React, { createContext, useState, ReactNode, useEffect } from 'react';

interface CartContextType {
  cart: CartData | null,
  isLoading: boolean;
};

const CartContext = createContext<CartContextType>({
  cart: null,
  isLoading: false,
});

interface CartProviderProps {
  children: ReactNode;
};

const CartProvider: React.FC<CartProviderProps> = ({ children }) => {
  const [cart, setCart] = useState<CartData | null>(null);
  const { data, isLoading } = getMyCartQuery();

  useEffect(() => {
    if (data?.result) {
      console.log("Fetching user cart: ", data);
      setCart(data.result);
    }
  }, [data]);

  return (
    <CartContext.Provider value={{cart, isLoading}}>
      {children}
    </CartContext.Provider>
  );
};

export {CartContext, CartProvider};