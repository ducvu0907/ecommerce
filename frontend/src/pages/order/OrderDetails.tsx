import { useParams } from "react-router-dom";
import { Card, CardHeader, CardTitle, CardContent, CardFooter } from "@/components/ui/card";
import { Table, TableBody, TableHead, TableHeader, TableRow } from "@/components/ui/table";
import { Alert, AlertDescription, AlertTitle } from "@/components/ui/alert";
import { Skeleton } from "@/components/ui/skeleton";
import { Badge } from "@/components/ui/badge";
import { Separator } from "@/components/ui/separator";
import { Button } from "@/components/ui/button";
import { Dialog, DialogTrigger, DialogContent, DialogHeader, DialogTitle, DialogDescription, DialogFooter } from "@/components/ui/dialog";
import { getOrderQuery, cancelOrderMutation } from "@/services/order";
import NotFound from "../main/NotFound";
import { formatDate, getStatusColor } from "@/helpers";
import OrderItem from "@/components/order/OrderItem";
import PaymentDetail from "@/components/payment/PaymentDetail";
import { Loader2 } from "lucide-react";
import { OrderStatus } from "@/types/models";
import { useState } from "react";

const OrderDetails = () => {
  const { orderId } = useParams<{ orderId: string }>();
  const [isDialogOpen, setIsDialogOpen] = useState(false);

  if (!orderId) {
    return <NotFound />;
  }

  const { data: order, isLoading, isError, error } = getOrderQuery(orderId);
  const { mutate: cancelOrder, isPending } = cancelOrderMutation();

  const handleCancelOrder = () => {
    cancelOrder({ orderId: orderId });
    setIsDialogOpen(false);
  };

  if (isLoading) {
    return (
      <Card className="w-full max-w-4xl mx-auto">
        <CardHeader>
          <Skeleton className="h-8 w-48" />
        </CardHeader>
        <CardContent>
          <div className="space-y-4">
            <Skeleton className="h-4 w-full" />
            <Skeleton className="h-4 w-full" />
            <Skeleton className="h-4 w-3/4" />
          </div>
        </CardContent>
      </Card>
    );
  }

  if (isError) {
    return (
      <Alert variant="destructive" className="max-w-4xl mx-auto">
        <AlertTitle>Error</AlertTitle>
        <AlertDescription>
          {error?.message || "Failed to load order details"}
        </AlertDescription>
      </Alert>
    );
  }

  if (!order?.result) {
    return <NotFound />;
  }

  const { result: orderData } = order;

  return (
    <Card className="w-full max-w-4xl mx-auto">
      <CardHeader>
        <div className="flex justify-between items-center">
          <CardTitle>Order #{orderData.id}</CardTitle>
          <Badge className={`${getStatusColor(orderData.status)}`}>
            {orderData.status}
          </Badge>
        </div>
      </CardHeader>
      <CardContent className="space-y-6">
        <div>
          <h3 className="text-lg font-semibold mb-2">Order Information</h3>
          <div className="grid grid-cols-2 gap-4">
            <div>
              <p className="text-sm text-gray-500">Timestamp</p>
              <p>{formatDate(orderData.createdAt)}</p>
            </div>
            <div>
              <p className="text-sm text-gray-500">Instruction</p>
              <p>{orderData.instruction || "None"}</p>
            </div>
          </div>
        </div>

        <Separator />

        <div className="overflow-y-auto">
          <h3 className="text-lg font-semibold mb-4">Items</h3>
          <Table>
            <TableHeader>
              <TableRow>
                <TableHead className="text-left">Product</TableHead>
                <TableHead className="text-left">Title</TableHead>
                <TableHead className="text-center">Quantity</TableHead>
                <TableHead className="text-right">Subtotal</TableHead>
              </TableRow>
            </TableHeader>
            <TableBody>
              {orderData.items.map((item, idx) => (
                <OrderItem item={item} key={idx} />
              ))}
            </TableBody>
          </Table>
        </div>

        <Separator />

        {orderData.status !== OrderStatus.CANCELLED && <PaymentDetail />}
        
      </CardContent>

      <CardFooter className="flex justify-between items-center">
        <div className="text-lg font-semibold">
          Total: ${orderData.totalPrice.toFixed(2)}
        </div>
        {orderData.status === OrderStatus.PENDING && (
          <Dialog open={isDialogOpen} onOpenChange={setIsDialogOpen}>
            <DialogTrigger asChild>
              <Button variant="destructive" disabled={isPending}>
                Cancel Order
              </Button>
            </DialogTrigger>
            <DialogContent>
              <DialogHeader>
                <DialogTitle>Are you sure?</DialogTitle>
                <DialogDescription>
                  This action cannot be undone. This will permanently cancel the order.
                </DialogDescription>
              </DialogHeader>
              <DialogFooter>
                <Button
                  variant="outline"
                  onClick={() => setIsDialogOpen(false)}
                >
                  Cancel
                </Button>
                <Button
                  variant="destructive"
                  onClick={handleCancelOrder}
                  disabled={isPending}
                >
                  {isPending ? <Loader2 className="animate-spin" /> : "Confirm"}
                </Button>
              </DialogFooter>
            </DialogContent>
          </Dialog>
        )}
      </CardFooter>
    </Card>
  );
};

export default OrderDetails;