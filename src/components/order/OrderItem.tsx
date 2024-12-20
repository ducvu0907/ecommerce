import { getProduct } from "@/services/product";
import { OrderItemData } from "@/types/models";
import { TableCell, TableRow } from "../ui/table";
import { Skeleton } from "@/components/ui/skeleton";
import { AlertCircle } from "lucide-react";
import { Alert, AlertDescription } from "@/components/ui/alert";
import { useNavigate } from "react-router-dom";

interface OrderItemProps {
  item: OrderItemData;
}

const OrderItem: React.FC<OrderItemProps> = ({item}) => {
  const { data: product, isLoading, isError } = getProduct(item.productId);
  const navigate = useNavigate();

  if (isLoading) {
    return (
      <TableRow>
        <TableCell className="w-24">
          <Skeleton className="h-24 w-24" />
        </TableCell>
        <TableCell>
          <Skeleton className="h-4 w-48" />
        </TableCell>
        <TableCell>
          <Skeleton className="h-4 w-12" />
        </TableCell>
        <TableCell className="text-right">
          <Skeleton className="h-4 w-16 ml-auto" />
        </TableCell>
      </TableRow>
    );
  }

  if (isError) {
    return (
      <TableRow>
        <TableCell colSpan={4}>
          <Alert variant="destructive">
            <AlertCircle className="h-4 w-4" />
            <AlertDescription>
              Error loading product details
            </AlertDescription>
          </Alert>
        </TableCell>
      </TableRow>
    );
  }

  return (
    <TableRow key={item.id} >
      <TableCell>
        <div className="w-24">
          <img
            src={product?.result?.imageUrl}
            alt={product?.result?.title}
            className="rounded-md object-cover"
          />
        </div>
      </TableCell>
      <TableCell onClick={() => navigate(`/products/${product?.result?.id}`)} >
        <div className="flex flex-col">
          <span className="font-medium hover:underline cursor-pointer">{product?.result?.title}</span>
          <span className="text-sm text-gray-500">{product?.result?.sku}</span>
        </div>
      </TableCell>
      <TableCell className="text-center">{item.quantity}</TableCell>
      <TableCell className="text-right">
        ${item.price.toFixed(2)}
      </TableCell>
    </TableRow>
  );
};

export default OrderItem;