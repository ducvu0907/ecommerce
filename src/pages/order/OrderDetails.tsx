import { useContext } from "react";
import { useParams } from "react-router-dom";
import { Card, CardHeader, CardTitle, CardContent, CardFooter, } from "@/components/ui/card";
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow, } from "@/components/ui/table";
import { Alert, AlertDescription, AlertTitle } from "@/components/ui/alert";
import { Skeleton } from "@/components/ui/skeleton";
import { Badge } from "@/components/ui/badge";
import { Separator } from "@/components/ui/separator";
import { AuthContext } from "@/contexts/AuthContext";
import { getOrder } from "@/services/order";
import NotFound from "../main/NotFound";
import { formatDate, getStatusColor } from "@/helpers";
import OrderItem from "@/components/order/OrderItem";

const OrderDetails = () => {
  const { token } = useContext(AuthContext);
  const { orderId } = useParams<{ orderId: string }>();

  if (!orderId || !token) {
    return <NotFound />;
  }

  const { data: order, isLoading, isError, error } = getOrder(orderId, token);

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
              <p className="text-sm text-gray-500">Description</p>
              <p>{orderData.description}</p>
            </div>
          </div>
        </div>

        <Separator />

        <div>
          <h3 className="text-lg font-semibold mb-4">Items</h3>
          <Table>
            <TableHeader>
              <TableRow>
                <TableHead className="text-left">Product</TableHead>
                <TableHead className="text-left">Title</TableHead>
                <TableHead className="text-center">Quantity</TableHead>
                <TableHead className="text-right">Total</TableHead>
              </TableRow>
            </TableHeader>
            <TableBody>
              {orderData.items.map((item) => (
                <OrderItem item={item} />
              ))}
            </TableBody>
          </Table>
        </div>
      </CardContent>

      <CardFooter className="flex justify-end">
        <div className="text-lg font-semibold">
          Total Amount: ${orderData.totalAmount.toFixed(2)}
        </div>
      </CardFooter>

    </Card>
  );
};

export default OrderDetails;