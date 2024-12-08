import CartItemList from "@/components/cart/CartItemList";
import { Card, CardContent, CardTitle, CardHeader } from "@/components/ui/card";
import { Button } from "@/components/ui/button";

const Cart = () => {
  return (
    <div className="flex justify-between max-w-7xl mx-auto py-8">
      <div className="w-3/5 space-y-4">
        <Card>
          <CardHeader>
            <CardTitle>Cart</CardTitle>
          </CardHeader>
          <CardContent>
            <CartItemList />
          </CardContent>
        </Card>
      </div>
      <div className="w-2/5 space-y-4">
        <Card>
          <CardHeader>
            <CardTitle>Promocode</CardTitle>
          </CardHeader>
          <CardContent>
            <div className="flex items-center justify-between">
              <input type="text" placeholder="Enter promocode" className="w-full border border-gray-300 rounded-md px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500 mr-2" />
              <Button variant="default">Apply</Button>
            </div>
          </CardContent>
        </Card>
        <Card>
          <CardHeader>
            <CardTitle>Summary</CardTitle>
          </CardHeader>
          <CardContent>
            <div className="space-y-2">
              <div className="flex justify-between">
                <span>Subtotal</span>
                <span>$99</span>
              </div>
              <div className="flex justify-between">
                <span>Discount</span>
                <span>-15%</span>
              </div>
              <div className="flex justify-between">
                <span>Delivery</span>
                <span>$9.99</span>
              </div>
              <div className="flex justify-between">
                <span>Tax</span>
                <span>$199</span>
              </div>
              <div className="flex justify-between font-medium">
                <span>Total</span>
                <span>$199</span>
              </div>
            </div>
            <div className="mt-4 flex justify-center">
              <Button variant="default">Proceed</Button>
            </div>
          </CardContent>
        </Card>
      </div>
    </div>
  );
};

export default Cart;