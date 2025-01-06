import { useContext, useState } from "react";
import { useForm } from "react-hook-form";
import { CartContext } from "@/contexts/CartContext";
import { createOrderMutation } from "@/services/order";
import { DiscountData } from "@/types/models";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Textarea } from "@/components/ui/textarea";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import DiscountList from "@/components/discount/DiscountList";
import { calculateCartTotal, calculateDiscountAmount, calculateFinalTotal } from "@/helpers";
import { Form, FormField, FormItem, FormLabel, FormControl, FormMessage } from "@/components/ui/form";
import { getMyProfileQuery } from "@/services/user";
import { Loader2 } from "lucide-react";
import CreateOrderItems from "@/components/order/CreateOrderItems";

interface OrderFormValues {
  address: string;
  instruction: string | undefined;
  discountId: string | undefined;
}

const CreateOrder = () => {
  const { cart } = useContext(CartContext);
  const [showDiscounts, setShowDiscounts] = useState<boolean>(false);
  const { data, isLoading } = getMyProfileQuery();
  const { mutate: createOrder, isPending } = createOrderMutation();
  const [discount, setDiscount] = useState<DiscountData | null>(null);
  const user = data?.result;

  const form = useForm<OrderFormValues>({
    defaultValues: {
      address: "",
      instruction: "",
    },
  });

  const onSubmit = (data: OrderFormValues) => {
    createOrder({ 
      request: {
        address: data.address, 
        instruction: data.instruction || null, 
        discountId: discount?.id || null
      } 
    });
  };

  const shippingFee: number = 10.00;
  const cartTotal: number = calculateCartTotal(cart);
  const discountAmount: number = calculateDiscountAmount(discount, cartTotal);
  const finalTotal: number = calculateFinalTotal(cartTotal, discountAmount, shippingFee);

  if (isLoading) {
    return (
      <div className="flex items-center justify-center h-screen">
        <Loader2 className="h-8 w-8 animate-spin text-blue-500" />
      </div>
    );
  }

  return (
    <div className="container max-w-7xl mx-auto py-8 px-4">
      <Form {...form}>
        <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-6">
          <div className="grid grid-cols-1 lg:grid-cols-3 gap-6">

            {/* Left Column - Customer Info, Cart Items, and Delivery */}
            <div className="lg:col-span-2 space-y-6">
              <Card className="shadow-sm">
                <CardHeader className="pb-4">
                  <CardTitle className="text-lg font-medium">Customer Information</CardTitle>
                </CardHeader>
                <CardContent className="space-y-3 text-sm">
                  <div className="grid grid-cols-2 gap-2">
                    <div className="text-muted-foreground font-semibold">Full Name</div>
                    <div>{user?.fullName}</div>
                    <div className="text-muted-foreground font-semibold">Phone</div>
                    <div>{user?.phone}</div>
                  </div>
                </CardContent>
              </Card>

              <CreateOrderItems />

              <Card className="shadow-sm">
                <CardHeader className="pb-4">
                  <CardTitle className="text-lg font-medium">Delivery Details</CardTitle>
                </CardHeader>
                <CardContent className="space-y-4">
                  <FormField
                    control={form.control}
                    name="address"
                    rules={{ required: "Address is required" }}
                    render={({ field }) => (
                      <FormItem>
                        <FormLabel>Delivery Address</FormLabel>
                        <FormControl>
                          <Input {...field} placeholder="Enter delivery address" />
                        </FormControl>
                        <FormMessage />
                      </FormItem>
                    )}
                  />

                  <FormField
                    control={form.control}
                    name="instruction"
                    render={({ field }) => (
                      <FormItem>
                        <FormLabel>Additional Instructions (Optional)</FormLabel>
                        <FormControl>
                          <Textarea {...field} placeholder="Add any special instructions" className="h-24" />
                        </FormControl>
                        <FormMessage />
                      </FormItem>
                    )}
                  />
                </CardContent>
              </Card>
            </div>

            {/* Right Column - Discounts and Order Summary */}
            <div className="space-y-6">
              <Card className="shadow-sm">
                <CardHeader className="pb-4">
                  <CardTitle className="text-lg font-medium">Discounts</CardTitle>
                </CardHeader>
                <CardContent>
                  <Button
                    variant="outline"
                    onClick={() => setShowDiscounts(true)}
                    className="w-full"
                  >
                    {discount ? 'Change Discount' : 'Choose Discount'}
                  </Button>
                  {discount && (
                    <div className="mt-4 text-sm text-green-600">
                      Applied: {discount.description}
                    </div>
                  )}
                </CardContent>
              </Card>

              <Card className="shadow-sm">
                <CardHeader className="pb-4">
                  <CardTitle className="text-lg font-medium">Order Summary</CardTitle>
                </CardHeader>
                <CardContent>
                  <div className="space-y-3">
                    <div className="flex justify-between text-sm">
                      <span className="text-muted-foreground">Subtotal</span>
                      <span>${cartTotal.toFixed(2)}</span>
                    </div>

                    {discount && (
                      <div className="flex justify-between text-sm text-green-600">
                        <span>Discount</span>
                        <span>-${discountAmount.toFixed(2)}</span>
                      </div>
                    )}

                    <div className="flex justify-between text-sm">
                      <span className="text-muted-foreground">Shipping Fee</span>
                      <span>${shippingFee.toFixed(2)}</span>
                    </div>

                    <div className="pt-3 border-t">
                      <div className="flex justify-between font-medium">
                        <span>Total</span>
                        <span>${finalTotal.toFixed(2)}</span>
                      </div>
                    </div>

                    <Button
                      type="submit"
                      disabled={isPending || !cart?.items.length}
                      className="w-full mt-4"
                    >
                      {isPending ? (
                        <Loader2 className="h-4 w-4 animate-spin mr-2" />
                      ) : "Place Order"}
                    </Button>
                  </div>
                </CardContent>
              </Card>
            </div>
          </div>
        </form>
      </Form>

      <DiscountList
        isOpen={showDiscounts}
        onClose={() => setShowDiscounts(false)}
        selectedDiscount={discount}
        setSelectedDiscount={setDiscount}
      />
    </div>
  );
};

export default CreateOrder;