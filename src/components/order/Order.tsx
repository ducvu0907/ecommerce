import { useNavigate } from "react-router-dom";
import { Card, CardHeader, CardTitle, CardContent } from "@/components/ui/card";
import { OrderData, OrderStatus } from "@/types/models";

interface OrderProps {
  order: OrderData;
};

const Order: React.FC<OrderProps> = ({ order }) => {
  const navigate = useNavigate();

  return (
    <Card
      key={order.id}
      className="hover:bg-gray-50 cursor-pointer transition-colors"
      onClick={() => navigate(`/orders/${order.id}`)}
    >
      <CardHeader className="pb-2">
        <CardTitle className="text-lg flex justify-between items-center">
          <span>Order #{order.id}</span>
          <span className={`text-sm px-3 py-1 rounded-full 
          ${order.status === OrderStatus.COMPLETED ? 'bg-green-100 text-green-800' :
              order.status === OrderStatus.DELIVERING ? 'bg-blue-100 text-blue-800' :
                order.status === OrderStatus.CANCELLED ? 'bg-red-100 text-red-800' :
                  'bg-yellow-100 text-yellow-800'
            }`}>
            {order.status}
          </span>
        </CardTitle>
      </CardHeader>
      <CardContent>
        <div className="flex justify-between items-center">
          <div>
            <p className="text-gray-600">{order.items.length} items</p>
            <p className="text-sm text-gray-500">
              {new Date(order.createdAt).toLocaleDateString()}
            </p>
          </div>
          <p className="font-semibold">
            ${order.totalAmount.toFixed(2)}
          </p>
        </div>
      </CardContent>
    </Card>
  );
};

export default Order;