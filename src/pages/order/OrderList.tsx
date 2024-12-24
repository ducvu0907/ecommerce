import { AuthContext } from "@/contexts/AuthContext";
import NotFound from "@/pages/main/NotFound";
import { getMyOrders } from "@/services/order";
import { useContext } from "react";
import { Loader2 } from "lucide-react";
import { OrderData } from "@/types/models";
import Order from "@/components/order/Order";

const OrderList = () => {
  const { token } = useContext(AuthContext);

  if (!token) {
    return <NotFound />;
  }

  const { data: orders, isLoading, isError, error } = getMyOrders(token);

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
        <p className="text-black text-xl font-medium">No orders found</p>
      </div>
    );
  }

  return (
    <div className="space-y-4 p-4">
      <h1 className="text-2xl font-bold mb-6">My Orders</h1>
      {orders.result.map((order: OrderData) => (
        <Order order={order} />
      ))}
    </div>
  );
};

export default OrderList;