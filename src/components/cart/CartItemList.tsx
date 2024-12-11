import { ShoppingCart } from 'lucide-react';
import CartItem from './CartItem';
import { useContext } from 'react';
import { CartContext } from '@/contexts/CartContext';

const CartItemList = () => {
  const { cart } = useContext(CartContext);

  return (
    <div className="space-y-4 h-full">
      {cart?.items.length === 0 ? (
        <div className="flex flex-col items-center justify-center p-8 text-gray-500">
          <ShoppingCart className="h-12 w-12 mb-4 text-gray-400" />
          <p className="text-lg font-semibold">Your cart is empty</p>
          <p className="text-sm text-gray-600 mt-2">Explore our products and add some items!</p>
        </div>
      ) : (
        cart?.items.map((item) => (
          <CartItem key={item.id} item={item} />
        ))
      )}
    </div>
  );
};

export default CartItemList;
