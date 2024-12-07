import { getProduct } from "@/services/product";
import { Button } from "@/components/ui/button";
import { HoverCard, HoverCardContent, HoverCardTrigger } from "@/components/ui/hover-card";
import { Tooltip, TooltipContent, TooltipProvider, TooltipTrigger } from "@/components/ui/tooltip";
import { Card, CardContent } from "@/components/ui/card";
import { CartItemData } from "@/types/models";
import { Loader, AlertTriangle, Minus, Plus, Trash } from "lucide-react";
import { deleteItem, updateItem } from "@/services/cart";
import { useContext, useState } from "react";
import { AuthContext } from "@/contexts/AuthContext";

interface CartItemProps {
  item: CartItemData;
}

const CartItem: React.FC<CartItemProps> = ({ item }) => {
  const { data: product, isLoading, isError } = getProduct(item.productId);
  const { token } = useContext(AuthContext);
  const { mutate, isPending } = deleteItem();
  const [quantity, setQuantity] = useState<number>(item.quantity);
  const { mutate: update, isPending: isUpdateLoading } = updateItem();

  const handleDeleteItem = async () => {
    if (!token) {
      return;
    }
    mutate({itemId: item.id, token});
  };

  const handleUpdateItem = async () => {

  };
  
  if (isLoading) {
    return (
      <Card>
        <CardContent className="flex items-center justify-center p-6">
          <Loader className="mr-2 h-6 w-6 animate-spin" />
        </CardContent>
      </Card>
    );
  }

  if (isError || !product?.result) {
    return (
      <Card>
        <CardContent className="flex items-center justify-center p-6 text-destructive">
          <AlertTriangle className="mr-2 h-6 w-6" />
          <span>Error loading product</span>
        </CardContent>
      </Card>
    );
  }

  return (
    <Card className="mb-2">
      <CardContent className="p-4">
        <div className="flex items-center justify-between">
          <div className="flex items-center space-x-4">
            <HoverCard>
              <HoverCardTrigger>
                <img 
                  src={product.result.imageUrl} 
                  alt={product.result.title} 
                  className="w-16 h-16 rounded-md object-cover" 
                />
              </HoverCardTrigger>
              <HoverCardContent>
                <img 
                  src={product.result.imageUrl} 
                  alt={product.result.title} 
                  className="w-full h-auto rounded-md" 
                />
              </HoverCardContent>
            </HoverCard>
            
            <div>
              <h3 className="font-semibold">{product.result.title}</h3>
            </div>
          </div>

          <div className="flex items-center space-x-4">
            <div className="flex items-center space-x-2">
              <TooltipProvider>
                <Tooltip>
                  <TooltipTrigger asChild>
                    <Button 
                      variant="outline" 
                      size="icon" 
                      className="h-8 w-8"
                      onClick={() => setQuantity(quantity - 1)}
                      disabled={quantity === 1}
                    >
                      <Minus className="h-4 w-4" />
                    </Button>
                  </TooltipTrigger>
                  <TooltipContent>Decrease Quantity</TooltipContent>
                </Tooltip>
              </TooltipProvider>

              <span className="px-2">{quantity}</span>

              <TooltipProvider>
                <Tooltip>
                  <TooltipTrigger asChild>
                    <Button 
                      variant="outline" 
                      size="icon" 
                      className="h-8 w-8"
                      onClick={() => setQuantity(quantity + 1)}
                    >
                      <Plus className="h-4 w-4" />
                    </Button>
                  </TooltipTrigger>
                  <TooltipContent>Increase Quantity</TooltipContent>
                </Tooltip>
              </TooltipProvider>
            </div>

            <p className="font-medium text-lg">${item.price.toFixed(2)}</p>

            <div className="flex space-x-2">
              <Button onClick={handleDeleteItem} variant="destructive" size="sm" disabled={isPending}>
                <Trash className="mr-2 h-4 w-4" /> Delete
              </Button>
            </div>
          </div>
        </div>
      </CardContent>
    </Card>
  );
};

export default CartItem;