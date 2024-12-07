import { useContext } from 'react';
import { CartContext } from "@/contexts/CartContext";
import { Card, CardContent } from '../ui/card';
import { ShoppingCart } from 'lucide-react';
import CartItem from './CartItem';

const CartItemList = () => {
  const { cart } = useContext(CartContext);

  return (
    <div className="h-screen space-y-2">
      {cart?.items.length === 0 ? (
        <Card>
          <CardContent className="flex items-center justify-center p-6">
            <ShoppingCart className="mr-2 h-6 w-6" />
            <span>Your cart is empty</span>
          </CardContent>
        </Card>
      ) : (
        cart?.items.map((item) => (
          <CartItem key={item.id} item={item} />
        ))
      )}
    </div>
  );
};

export default CartItemList;
