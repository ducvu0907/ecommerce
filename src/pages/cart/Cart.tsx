import CartItemList from "@/components/cart/CartItemList";
import { Card, CardContent, CardTitle, CardHeader, CardFooter } from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import { useContext, useEffect, useState } from "react";
import { CartContext } from "@/contexts/CartContext";
import { computeSubtotal } from "@/helpers";
import { ArrowRight, ShoppingCart } from "lucide-react";

const Cart = () => {
  const { cart } = useContext(CartContext);
  const [subtotal, setSubtotal] = useState<number>(0);

  useEffect(() => {
    const calculatedSubtotal = cart ? computeSubtotal(cart.items) : 0;
    setSubtotal(calculatedSubtotal);
  }, [cart]);

  return (
    <div className="container mx-auto max-w-4xl px-4 py-8 space-y-6">
      <Card className="shadow-lg">
        <CardHeader>
          <CardTitle className="flex items-center">
            <ShoppingCart className="mr-3 h-6 w-6" />
            Your Cart
          </CardTitle>
        </CardHeader>
        <CardContent>
          <CartItemList />
        </CardContent>
        <CardFooter className="border-t pt-4 sticky bottom-2">
          <div className="w-full space-y-4">
            <div className="space-y-2 text-sm">
              <div className="flex justify-between">
                <span className="text-gray-600">Subtotal</span>
                <span className="font-semibold">${subtotal.toFixed(2)}</span>
              </div>
            </div>
            <Button 
              className="w-full mt-4" 
              disabled={cart?.items.length === 0}
            >
              Proceed to Checkout
              <ArrowRight className="ml-2 h-4 w-4" />
            </Button>
          </div>
        </CardFooter>
      </Card>
    </div>
  );
};

export default Cart;