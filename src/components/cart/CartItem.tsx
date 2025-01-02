import { getProductQuery } from "@/services/product";
import { Button } from "@/components/ui/button";
import { HoverCard, HoverCardContent, HoverCardTrigger } from "@/components/ui/hover-card";
import { Tooltip, TooltipContent, TooltipProvider, TooltipTrigger } from "@/components/ui/tooltip";
import { Card, CardContent } from "@/components/ui/card";
import { Dialog, DialogContent, DialogDescription, DialogFooter, DialogHeader, DialogTitle } from "@/components/ui/dialog";
import { CartItemData } from "@/types/models";
import { Loader, AlertTriangle, Minus, Plus, Trash } from "lucide-react";
import { deleteItemMutation, updateItemMutation } from "@/services/cart";
import { ChangeEvent, useContext, useEffect, useState } from "react";
import { AuthContext } from "@/contexts/AuthContext";
import { Input } from "../ui/input";

interface CartItemProps {
  item: CartItemData;
}

const CartItem: React.FC<CartItemProps> = ({ item }) => {
  const { data: product, isLoading, isError } = getProductQuery(item.productId);
  const { token } = useContext(AuthContext);
  const { mutate: deleteItemMutate, isPending: isDeletePending } = deleteItemMutation();
  const [quantity, setQuantity] = useState<number>(item.quantity);
  const [isQuantityChanged, setIsQuantityChanged] = useState<boolean>(false);
  const { mutate: updateItemMutate, isPending: isUpdateLoading } = updateItemMutation();
  const [isDeleteModalOpen, setIsDeleteModalOpen] = useState<boolean>(false);

  useEffect(() => {
    if (quantity !== item.quantity) {
      setIsQuantityChanged(true);
    } else {
      setIsQuantityChanged(false);
    }
  }, [quantity]);

  const handleDeleteItem = async () => {
    if (!token) {
      return;
    }
    deleteItemMutate({ itemId: item.id });
  };

  const handleUpdateItem = async () => {
    if (!token) {
      return;
    }
    updateItemMutate({ itemId: item.id, request: {quantity: quantity}});
    setIsQuantityChanged(false);
  };
  
  const handleQuantityChange = (newQuantity: number) => {
    setQuantity(newQuantity);
  };

  const handleQuantityInput = (e: ChangeEvent<HTMLInputElement>) => {
    const newQuantity = parseInt(e.target.value);
    if (!newQuantity) {
      return;
    }
    if (newQuantity && product?.result && newQuantity >= 0) {
      if (newQuantity <= product.result.quantity) {
        setQuantity(newQuantity);
      } else {
        setQuantity(product.result.quantity);
      }
    } else {
      setQuantity(item.quantity);
    }
  };

  const handleCancelUpdate = () => {
    setQuantity(item.quantity);
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
    <>
      <Card className="mb-2">
        <CardContent className="p-4">
          <div className="flex items-center justify-between">
            <div className="flex items-center space-x-4">
              <HoverCard>
                <HoverCardTrigger>
                  <img 
                    src={"https://placehold.co/600x400"}  // FIXME: placeholder image
                    alt={product.result.title} 
                    className="w-16 h-16 rounded-md object-cover" 
                  />
                </HoverCardTrigger>
                <HoverCardContent>
                  <img 
                    src={"https://placehold.co/600x400"} // FIXME: placeholder image
                    alt={product.result.title} 
                    className="w-full h-auto rounded-md" 
                  />
                </HoverCardContent>
              </HoverCard>
              
              <div>
                <h3 className="font-semibold">{product.result.title}</h3>
              </div>
            </div>

            <div className="flex flex-col items-end space-y-2">
              <div className="flex items-center space-x-2">
                <p className="text-sm text-muted-foreground text-gray-500">
                  Available: {product.result.quantity} in stock
                </p>

                <TooltipProvider>
                  <Tooltip>
                    <TooltipTrigger asChild>
                      <Button 
                        variant="outline" 
                        size="icon" 
                        className="h-8 w-8"
                        onClick={() => handleQuantityChange(quantity - 1)}
                        disabled={quantity === 1}
                      >
                        <Minus className="h-4 w-4" />
                      </Button>
                    </TooltipTrigger>
                    <TooltipContent>Decrease Quantity</TooltipContent>
                  </Tooltip>
                </TooltipProvider>

                <Input
                  type="text"
                  value={quantity}
                  onChange={handleQuantityInput}
                  className="w-16 text-center border border-gray-300 rounded-md"
  />

                <TooltipProvider>
                  <Tooltip>
                    <TooltipTrigger asChild>
                      <Button
                        variant="outline"
                        size="icon"
                        className="h-8 w-8"
                        onClick={() => handleQuantityChange(quantity + 1)}
                        disabled={quantity >= product.result.quantity}
                      >
                        <Plus className="h-4 w-4" />
                      </Button>
                    </TooltipTrigger>
                    <TooltipContent>Increase Quantity</TooltipContent>
                  </Tooltip>
                </TooltipProvider>
              </div>

              {isQuantityChanged && (
                <div className="flex items-center">
                <Button 
                  variant="default" 
                  size="sm"
                  onClick={handleUpdateItem}
                  disabled={isUpdateLoading}
                  className="w-full"
                >
                  Confirm
                </Button>
                <Button 
                  variant="outline" 
                  size="sm"
                  onClick={handleCancelUpdate}
                  disabled={isUpdateLoading}
                  className="w-full"
                >
                  Cancel
                </Button>
                </div>
              )}

              <div className="flex items-center space-x-4">
                <p className="font-medium text-lg">${item.subtotal.toFixed(2)}</p>
                <Button 
                  onClick={() => setIsDeleteModalOpen(true)} 
                  variant="destructive" 
                  size="sm"
                >
                  <Trash className="h-4 w-4" />
                </Button>
              </div>
            </div>
          </div>
        </CardContent>
      </Card>

      <Dialog open={isDeleteModalOpen} onOpenChange={setIsDeleteModalOpen}>
        <DialogContent>
          <DialogHeader>
            <DialogTitle>Delete Item</DialogTitle>
            <DialogDescription>
              Are you sure you want to remove this item from your cart?
            </DialogDescription>
          </DialogHeader>
          <DialogFooter>
            <Button 
              variant="outline" 
              onClick={() => setIsDeleteModalOpen(false)}
            >
              Cancel
            </Button>
            <Button 
              variant="destructive" 
              onClick={handleDeleteItem} 
              disabled={isDeletePending}
            >
              {isDeletePending ? (
                <Loader className="mr-2 h-4 w-4 animate-spin" />
              ) : (
                  <span>Delete</span>
              )}
            </Button>
          </DialogFooter>
        </DialogContent>
      </Dialog>
    </>
  );
};

export default CartItem;