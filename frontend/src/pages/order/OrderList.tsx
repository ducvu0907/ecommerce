import NotFound from "@/pages/main/NotFound";
import { getMyOrdersQuery } from "@/services/order";
import { Loader2 } from "lucide-react";
import Order from "@/components/order/Order";

const OrderList = () => {
  const { data: orders, isLoading, isError, error } = getMyOrdersQuery();
  console.log("Fetching user orders: ", orders);

  if (isLoading) {
    return (
      <div className="flex justify-center items-center min-h-[400px]">
        <Loader2 className="h-8 w-8 animate-spin" />
      </div>
    );
  }

  if (isError) {
    return (
      <div className="text-center py-8">
        <p className="text-red-500">Error loading orders: {error?.message || 'Something went wrong'}</p>
      </div>
    );
  }

  if (!orders?.result) {
    return <NotFound />;
  }

  if (orders.result.length === 0) {
    return (
      <div className="text-center py-8">
        <p className="text-black text-xl font-medium">You have no orders</p>
      </div>
    );
  }

  return (
    <div className="space-y-4 p-4">
      <h1 className="text-2xl font-bold mb-6">My Orders</h1>
      {orders.result.map((order, idx) => (
        <Order order={order} key={idx}/>
      ))}
    </div>
  );
};

export default OrderList;